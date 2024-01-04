package com.zaxxer.hikari.util;

import com.zaxxer.hikari.pool.ProxyCallableStatement;
import com.zaxxer.hikari.pool.ProxyConnection;
import com.zaxxer.hikari.pool.ProxyDatabaseMetaData;
import com.zaxxer.hikari.pool.ProxyPreparedStatement;
import com.zaxxer.hikari.pool.ProxyResultSet;
import com.zaxxer.hikari.pool.ProxyStatement;
import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import javassist.CannotCompileException;
import javassist.ClassMap;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.LoaderClassPath;
import javassist.Modifier;
import javassist.NotFoundException;
import net.bytebuddy.implementation.MethodDelegation;
import org.postgresql.jdbc.EscapedFunctions;
import org.springframework.util.ClassUtils;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/HikariCP-5.0.1.jar:com/zaxxer/hikari/util/JavassistProxyFactory.class */
public final class JavassistProxyFactory {
    private static ClassPool classPool;
    private static String genDirectory = "";

    public static void main(String... args) throws Exception {
        classPool = new ClassPool();
        classPool.importPackage("java.sql");
        classPool.appendClassPath(new LoaderClassPath(JavassistProxyFactory.class.getClassLoader()));
        if (args.length > 0) {
            genDirectory = args[0];
        }
        generateProxyClass(Connection.class, ProxyConnection.class.getName(), "{ try { return delegate.method($$); } catch (SQLException e) { throw checkException(e); } }");
        generateProxyClass(Statement.class, ProxyStatement.class.getName(), "{ try { return delegate.method($$); } catch (SQLException e) { throw checkException(e); } }");
        generateProxyClass(ResultSet.class, ProxyResultSet.class.getName(), "{ try { return delegate.method($$); } catch (SQLException e) { throw checkException(e); } }");
        generateProxyClass(DatabaseMetaData.class, ProxyDatabaseMetaData.class.getName(), "{ try { return delegate.method($$); } catch (SQLException e) { throw checkException(e); } }");
        generateProxyClass(PreparedStatement.class, ProxyPreparedStatement.class.getName(), "{ try { return ((cast) delegate).method($$); } catch (SQLException e) { throw checkException(e); } }");
        generateProxyClass(CallableStatement.class, ProxyCallableStatement.class.getName(), "{ try { return ((cast) delegate).method($$); } catch (SQLException e) { throw checkException(e); } }");
        modifyProxyFactory();
    }

    private static void modifyProxyFactory() throws NotFoundException, CannotCompileException, IOException {
        CtMethod[] methods;
        System.out.println("Generating method bodies for com.zaxxer.hikari.proxy.ProxyFactory");
        String packageName = ProxyConnection.class.getPackage().getName();
        CtClass proxyCt = classPool.getCtClass("com.zaxxer.hikari.pool.ProxyFactory");
        for (CtMethod method : proxyCt.getMethods()) {
            String name = method.getName();
            boolean z = true;
            switch (name.hashCode()) {
                case -1995233385:
                    if (name.equals("getProxyStatement")) {
                        z = true;
                        break;
                    }
                    break;
                case -1729648339:
                    if (name.equals("getProxyResultSet")) {
                        z = true;
                        break;
                    }
                    break;
                case -443793985:
                    if (name.equals("getProxyCallableStatement")) {
                        z = true;
                        break;
                    }
                    break;
                case 1457258178:
                    if (name.equals("getProxyDatabaseMetaData")) {
                        z = true;
                        break;
                    }
                    break;
                case 2011710902:
                    if (name.equals("getProxyConnection")) {
                        z = false;
                        break;
                    }
                    break;
                case 2145615834:
                    if (name.equals("getProxyPreparedStatement")) {
                        z = true;
                        break;
                    }
                    break;
            }
            switch (z) {
                case false:
                    method.setBody("{return new " + packageName + ".HikariProxyConnection($$);}");
                    break;
                case true:
                    method.setBody("{return new " + packageName + ".HikariProxyStatement($$);}");
                    break;
                case true:
                    method.setBody("{return new " + packageName + ".HikariProxyPreparedStatement($$);}");
                    break;
                case true:
                    method.setBody("{return new " + packageName + ".HikariProxyCallableStatement($$);}");
                    break;
                case true:
                    method.setBody("{return new " + packageName + ".HikariProxyResultSet($$);}");
                    break;
                case true:
                    method.setBody("{return new " + packageName + ".HikariProxyDatabaseMetaData($$);}");
                    break;
            }
        }
        proxyCt.writeFile(genDirectory + "target/classes");
    }

    private static <T> void generateProxyClass(Class<T> primaryInterface, String superClassName, String methodBody) throws Exception {
        CtMethod[] methods;
        CtMethod[] declaredMethods;
        String modifiedBody;
        String newClassName = superClassName.replaceAll("(.+)\\.(\\w+)", "$1.Hikari$2");
        CtClass superCt = classPool.getCtClass(superClassName);
        CtClass targetCt = classPool.makeClass(newClassName, superCt);
        targetCt.setModifiers(Modifier.setPublic(16));
        System.out.println("Generating " + newClassName);
        HashSet<String> superSigs = new HashSet<>();
        for (CtMethod method : superCt.getMethods()) {
            if ((method.getModifiers() & 16) == 16) {
                superSigs.add(method.getName() + method.getSignature());
            }
        }
        HashSet<String> methods2 = new HashSet<>();
        for (Class<?> intf : getAllInterfaces(primaryInterface)) {
            CtClass intfCt = classPool.getCtClass(intf.getName());
            targetCt.addInterface(intfCt);
            for (CtMethod intfMethod : intfCt.getDeclaredMethods()) {
                String signature = intfMethod.getName() + intfMethod.getSignature();
                if (!superSigs.contains(signature) && !methods2.contains(signature)) {
                    methods2.add(signature);
                    CtMethod method2 = CtNewMethod.copy(intfMethod, targetCt, (ClassMap) null);
                    String modifiedBody2 = methodBody;
                    CtMethod superMethod = superCt.getMethod(intfMethod.getName(), intfMethod.getSignature());
                    if ((superMethod.getModifiers() & 1024) != 1024 && !isDefaultMethod(intf, intfMethod)) {
                        modifiedBody2 = modifiedBody2.replace("((cast) ", "").replace(MethodDelegation.ImplementationDelegate.FIELD_NAME_PREFIX, "super").replace("super)", "super");
                    }
                    String modifiedBody3 = modifiedBody2.replace("cast", primaryInterface.getName());
                    if (isThrowsSqlException(intfMethod)) {
                        modifiedBody = modifiedBody3.replace("method", method2.getName());
                    } else {
                        modifiedBody = "{ return ((cast) delegate).method($$); }".replace("method", method2.getName()).replace("cast", primaryInterface.getName());
                    }
                    if (method2.getReturnType() == CtClass.voidType) {
                        modifiedBody = modifiedBody.replace("return", "");
                    }
                    method2.setBody(modifiedBody);
                    targetCt.addMethod(method2);
                }
            }
        }
        targetCt.getClassFile().setMajorVersion(52);
        targetCt.writeFile(genDirectory + "target/classes");
    }

    private static boolean isThrowsSqlException(CtMethod method) {
        CtClass[] exceptionTypes;
        try {
            for (CtClass clazz : method.getExceptionTypes()) {
                if (clazz.getSimpleName().equals("SQLException")) {
                    return true;
                }
            }
            return false;
        } catch (NotFoundException e) {
            return false;
        }
    }

    private static boolean isDefaultMethod(Class<?> intf, CtMethod intfMethod) throws Exception {
        CtClass[] parameterTypes;
        ArrayList<Class<?>> paramTypes = new ArrayList<>();
        for (CtClass pt : intfMethod.getParameterTypes()) {
            paramTypes.add(toJavaClass(pt));
        }
        return intf.getDeclaredMethod(intfMethod.getName(), (Class[]) paramTypes.toArray(new Class[0])).toString().contains("default ");
    }

    private static Set<Class<?>> getAllInterfaces(Class<?> clazz) {
        Class<?>[] interfaces;
        LinkedHashSet<Class<?>> interfaces2 = new LinkedHashSet<>();
        for (Class<?> intf : clazz.getInterfaces()) {
            if (intf.getInterfaces().length > 0) {
                interfaces2.addAll(getAllInterfaces(intf));
            }
            interfaces2.add(intf);
        }
        if (clazz.getSuperclass() != null) {
            interfaces2.addAll(getAllInterfaces(clazz.getSuperclass()));
        }
        if (clazz.isInterface()) {
            interfaces2.add(clazz);
        }
        return interfaces2;
    }

    private static Class<?> toJavaClass(CtClass cls) throws Exception {
        if (cls.getName().endsWith(ClassUtils.ARRAY_SUFFIX)) {
            return Array.newInstance(toJavaClass(cls.getName().replace(ClassUtils.ARRAY_SUFFIX, "")), 0).getClass();
        }
        return toJavaClass(cls.getName());
    }

    private static Class<?> toJavaClass(String cn) throws Exception {
        boolean z = true;
        switch (cn.hashCode()) {
            case -1325958191:
                if (cn.equals("double")) {
                    z = true;
                    break;
                }
                break;
            case 104431:
                if (cn.equals("int")) {
                    z = false;
                    break;
                }
                break;
            case 3039496:
                if (cn.equals("byte")) {
                    z = true;
                    break;
                }
                break;
            case 3052374:
                if (cn.equals(EscapedFunctions.CHAR)) {
                    z = true;
                    break;
                }
                break;
            case 3327612:
                if (cn.equals("long")) {
                    z = true;
                    break;
                }
                break;
            case 3625364:
                if (cn.equals("void")) {
                    z = true;
                    break;
                }
                break;
            case 64711720:
                if (cn.equals("boolean")) {
                    z = true;
                    break;
                }
                break;
            case 97526364:
                if (cn.equals("float")) {
                    z = true;
                    break;
                }
                break;
            case 109413500:
                if (cn.equals("short")) {
                    z = true;
                    break;
                }
                break;
        }
        switch (z) {
            case false:
                return Integer.TYPE;
            case true:
                return Long.TYPE;
            case true:
                return Short.TYPE;
            case true:
                return Byte.TYPE;
            case true:
                return Float.TYPE;
            case true:
                return Double.TYPE;
            case true:
                return Boolean.TYPE;
            case true:
                return Character.TYPE;
            case true:
                return Void.TYPE;
            default:
                return Class.forName(cn);
        }
    }
}