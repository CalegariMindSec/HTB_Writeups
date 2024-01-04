package jakarta.activation;

import java.util.Iterator;
import java.util.ServiceLoader;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jakarta.activation-api-2.1.1.jar:jakarta/activation/ServiceLoaderUtil.class */
class ServiceLoaderUtil {

    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jakarta.activation-api-2.1.1.jar:jakarta/activation/ServiceLoaderUtil$ExceptionHandler.class */
    static abstract class ExceptionHandler<T extends Exception> {
        public abstract T createException(Throwable th, String str);
    }

    ServiceLoaderUtil() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <P, T extends Exception> P firstByServiceLoader(Class<P> spiClass, Logger logger, ExceptionHandler<T> handler) throws Exception {
        logger.log(Level.FINE, "Using java.util.ServiceLoader to find {0}", spiClass.getName());
        try {
            ServiceLoader<P> serviceLoader = ServiceLoader.load(spiClass);
            Iterator<P> it = serviceLoader.iterator();
            if (it.hasNext()) {
                P impl = it.next();
                logger.log(Level.FINE, "ServiceProvider loading Facility used; returning object [{0}]", impl.getClass().getName());
                return impl;
            }
            return null;
        } catch (Throwable t) {
            throw handler.createException(t, "Error while searching for service [" + spiClass.getName() + "]");
        }
    }

    static void checkPackageAccess(String className) {
        int i;
        SecurityManager s = System.getSecurityManager();
        if (s != null && (i = className.lastIndexOf(46)) != -1) {
            s.checkPackageAccess(className.substring(0, i));
        }
    }

    static <P> Class<P> nullSafeLoadClass(String className, ClassLoader classLoader) throws ClassNotFoundException {
        if (classLoader == null) {
            return (Class<P>) Class.forName(className);
        }
        return (Class<P>) classLoader.loadClass(className);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <P, T extends Exception> P newInstance(String className, String defaultImplClassName, ClassLoader classLoader, ExceptionHandler<T> handler) throws Exception {
        try {
            Class<P> cls = safeLoadClass(className, defaultImplClassName, classLoader);
            return cls.getConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (ClassNotFoundException x) {
            throw handler.createException(x, "Provider " + className + " not found");
        } catch (Exception x2) {
            throw handler.createException(x2, "Provider " + className + " could not be instantiated: " + x2);
        }
    }

    static <P> Class<P> safeLoadClass(String className, String defaultImplClassName, ClassLoader classLoader) throws ClassNotFoundException {
        try {
            checkPackageAccess(className);
            return nullSafeLoadClass(className, classLoader);
        } catch (SecurityException se) {
            if (defaultImplClassName != null && defaultImplClassName.equals(className)) {
                return (Class<P>) Class.forName(className);
            }
            throw se;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T extends Exception> ClassLoader contextClassLoader(ExceptionHandler<T> exceptionHandler) throws Exception {
        try {
            return Thread.currentThread().getContextClassLoader();
        } catch (Exception x) {
            throw exceptionHandler.createException(x, x.toString());
        }
    }
}