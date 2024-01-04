package jakarta.xml.bind;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.ServiceLoader;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jakarta.xml.bind-api-4.0.0.jar:jakarta/xml/bind/ServiceLoaderUtil.class */
class ServiceLoaderUtil {
    private static final String OSGI_SERVICE_LOADER_CLASS_NAME = "org.glassfish.hk2.osgiresourcelocator.ServiceLoader";
    private static final String OSGI_SERVICE_LOADER_METHOD_NAME = "lookupProviderClasses";

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jakarta.xml.bind-api-4.0.0.jar:jakarta/xml/bind/ServiceLoaderUtil$ExceptionHandler.class */
    public static abstract class ExceptionHandler<T extends Exception> {
        public abstract T createException(Throwable th, String str);
    }

    ServiceLoaderUtil() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <P, T extends Exception> P firstByServiceLoader(Class<P> spiClass, Logger logger, ExceptionHandler<T> handler) throws Exception {
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

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> T lookupUsingOSGiServiceLoader(String factoryId, Logger logger) {
        try {
            Class<?> cls = Class.forName(factoryId);
            Class<?> target = Class.forName(OSGI_SERVICE_LOADER_CLASS_NAME);
            Method m = target.getMethod(OSGI_SERVICE_LOADER_METHOD_NAME, Class.class);
            Iterator<? extends T> iter = ((Iterable) m.invoke(null, cls)).iterator();
            if (iter.hasNext()) {
                T next = iter.next();
                logger.log(Level.FINE, "Found implementation using OSGi facility; returning object [{0}].", next.getClass().getName());
                return next;
            }
            return null;
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException ex) {
            logger.log(Level.FINE, "Unable to find from OSGi: [" + factoryId + "]", (Throwable) ex);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> Iterable<T> lookupsUsingOSGiServiceLoader(String factoryId, Logger logger) {
        try {
            return (Iterable) Class.forName(OSGI_SERVICE_LOADER_CLASS_NAME).getMethod(OSGI_SERVICE_LOADER_METHOD_NAME, Class.class).invoke(null, Class.forName(factoryId));
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException ex) {
            logger.log(Level.FINE, ex, () -> {
                return "Unable to find from OSGi: [" + factoryId + "]";
            });
            return null;
        }
    }

    static void checkPackageAccess(String className) {
        int i;
        SecurityManager s = System.getSecurityManager();
        if (s != null && (i = className.lastIndexOf(46)) != -1) {
            s.checkPackageAccess(className.substring(0, i));
        }
    }

    static Class<?> nullSafeLoadClass(String className, ClassLoader classLoader) throws ClassNotFoundException {
        if (classLoader == null) {
            return Class.forName(className);
        }
        return classLoader.loadClass(className);
    }

    static <T extends Exception> Object newInstance(String className, String defaultImplClassName, ExceptionHandler<T> handler) throws Exception {
        try {
            return safeLoadClass(className, defaultImplClassName, contextClassLoader(handler)).getConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (ClassNotFoundException x) {
            throw handler.createException(x, "Provider " + className + " not found");
        } catch (Exception x2) {
            throw handler.createException(x2, "Provider " + className + " could not be instantiated: " + x2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Class<?> safeLoadClass(String className, String defaultImplClassName, ClassLoader classLoader) throws ClassNotFoundException {
        try {
            checkPackageAccess(className);
            return nullSafeLoadClass(className, classLoader);
        } catch (SecurityException se) {
            if (defaultImplClassName != null && defaultImplClassName.equals(className)) {
                return Class.forName(className);
            }
            throw se;
        }
    }

    static <T extends Exception> ClassLoader contextClassLoader(ExceptionHandler<T> exceptionHandler) throws Exception {
        try {
            return Thread.currentThread().getContextClassLoader();
        } catch (Exception x) {
            throw exceptionHandler.createException(x, x.toString());
        }
    }
}