package org.glassfish.jaxb.runtime.v2.runtime.reflect.opt;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.security.ProtectionDomain;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.glassfish.jaxb.runtime.v2.runtime.reflect.Accessor;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/jaxb-runtime-4.0.1.jar:org/glassfish/jaxb/runtime/v2/runtime/reflect/opt/Injector.class */
final class Injector {
    private static final ReentrantReadWriteLock irwl;
    private static final Lock ir;
    private static final Lock iw;
    private static final Map<ClassLoader, WeakReference<Injector>> injectors;
    private static final Logger logger;
    private final Map<String, Class> classes = new HashMap();
    private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    private final Lock r = this.rwl.readLock();
    private final Lock w = this.rwl.writeLock();
    private final ClassLoader parent;
    private final boolean loadable;
    private static Method defineClass;
    private static Method resolveClass;
    private static Method findLoadedClass;
    private static Object U;
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !Injector.class.desiredAssertionStatus();
        irwl = new ReentrantReadWriteLock();
        ir = irwl.readLock();
        iw = irwl.writeLock();
        injectors = new WeakHashMap();
        logger = Logger.getLogger(Injector.class.getName());
        try {
            Method[] m = (Method[]) AccessController.doPrivileged(new PrivilegedAction<Method[]>() { // from class: org.glassfish.jaxb.runtime.v2.runtime.reflect.opt.Injector.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // java.security.PrivilegedAction
                public Method[] run() {
                    return new Method[]{Injector.getMethod(ClassLoader.class, "defineClass", String.class, byte[].class, Integer.TYPE, Integer.TYPE), Injector.getMethod(ClassLoader.class, "resolveClass", Class.class), Injector.getMethod(ClassLoader.class, "findLoadedClass", String.class)};
                }
            });
            defineClass = m[0];
            resolveClass = m[1];
            findLoadedClass = m[2];
        } catch (Throwable th) {
            try {
                U = AccessController.doPrivileged(new PrivilegedExceptionAction() { // from class: org.glassfish.jaxb.runtime.v2.runtime.reflect.opt.Injector.2
                    @Override // java.security.PrivilegedExceptionAction
                    public Object run() throws Exception {
                        Class u = Injector.classForNames("sun.misc.Unsafe", "jdk.internal.misc.Unsafe");
                        Field theUnsafe = u.getDeclaredField("theUnsafe");
                        theUnsafe.setAccessible(true);
                        return theUnsafe.get(null);
                    }
                });
                defineClass = (Method) AccessController.doPrivileged(new PrivilegedExceptionAction<Method>() { // from class: org.glassfish.jaxb.runtime.v2.runtime.reflect.opt.Injector.3
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // java.security.PrivilegedExceptionAction
                    public Method run() throws Exception {
                        try {
                            return Injector.U.getClass().getMethod("defineClass", String.class, byte[].class, Integer.TYPE, Integer.TYPE, ClassLoader.class, ProtectionDomain.class);
                        } catch (NoSuchMethodException | SecurityException ex) {
                            throw ex;
                        }
                    }
                });
            } catch (SecurityException | PrivilegedActionException ex) {
                Logger.getLogger(Injector.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex);
            }
        }
    }

    static Class inject(ClassLoader cl, String className, byte[] image) {
        Injector injector = get(cl);
        if (injector != null) {
            return injector.inject(className, image);
        }
        return null;
    }

    static Class find(ClassLoader cl, String className) {
        Injector injector = get(cl);
        if (injector != null) {
            return injector.find(className);
        }
        return null;
    }

    private static Injector get(ClassLoader cl) {
        Injector injector = null;
        ir.lock();
        try {
            WeakReference<Injector> wr = injectors.get(cl);
            ir.unlock();
            if (wr != null) {
                injector = wr.get();
            }
            if (injector == null) {
                try {
                    Injector injector2 = new Injector(cl);
                    injector = injector2;
                    WeakReference<Injector> wr2 = new WeakReference<>(injector2);
                    iw.lock();
                    if (!injectors.containsKey(cl)) {
                        injectors.put(cl, wr2);
                    }
                    iw.unlock();
                } catch (SecurityException e) {
                    logger.log(Level.FINE, "Unable to set up a back-door for the injector", (Throwable) e);
                    return null;
                }
            }
            return injector;
        } catch (Throwable th) {
            ir.unlock();
            throw th;
        }
    }

    private static Class classForNames(String... names) throws ClassNotFoundException {
        for (String name : names) {
            try {
                return Class.forName(name);
            } catch (ClassNotFoundException e) {
            }
        }
        throw new ClassNotFoundException(String.format("No class found for supplied FQDNs %s", Arrays.toString(names)));
    }

    private static Method getMethod(Class<?> c, String methodname, Class<?>... params) {
        try {
            Method m = c.getDeclaredMethod(methodname, params);
            m.setAccessible(true);
            return m;
        } catch (NoSuchMethodException e) {
            throw new NoSuchMethodError(e.getMessage());
        }
    }

    private Injector(ClassLoader parent) {
        this.parent = parent;
        if (!$assertionsDisabled && parent == null) {
            throw new AssertionError();
        }
        boolean loadableCheck = false;
        try {
            loadableCheck = parent.loadClass(Accessor.class.getName()) == Accessor.class;
        } catch (ClassNotFoundException e) {
        }
        this.loadable = loadableCheck;
    }

    private Class inject(String className, byte[] image) {
        if (this.loadable) {
            boolean wlocked = false;
            boolean rlocked = false;
            try {
                this.r.lock();
                Class c = this.classes.get(className);
                this.r.unlock();
                rlocked = false;
                if (c == null && findLoadedClass != null) {
                    try {
                        c = (Class) findLoadedClass.invoke(this.parent, className.replace('/', '.'));
                    } catch (IllegalAccessException | IllegalArgumentException e) {
                        logger.log(Level.FINE, "Unable to find " + className, (Throwable) e);
                    } catch (InvocationTargetException e2) {
                        logger.log(Level.FINE, "Unable to find " + className, e2.getTargetException());
                    }
                    if (c != null) {
                        this.w.lock();
                        this.classes.put(className, c);
                        this.w.unlock();
                        Class cls = c;
                        if (0 != 0) {
                            this.r.unlock();
                        }
                        if (0 != 0) {
                            this.w.unlock();
                        }
                        return cls;
                    }
                }
                if (c == null) {
                    this.r.lock();
                    c = this.classes.get(className);
                    this.r.unlock();
                    rlocked = false;
                    if (c == null) {
                        try {
                            if (resolveClass != null) {
                                c = (Class) defineClass.invoke(this.parent, className.replace('/', '.'), image, 0, Integer.valueOf(image.length));
                                resolveClass.invoke(this.parent, c);
                            } else {
                                c = (Class) defineClass.invoke(U, className.replace('/', '.'), image, 0, Integer.valueOf(image.length), this.parent, Injector.class.getProtectionDomain());
                            }
                            this.w.lock();
                            if (!this.classes.containsKey(className)) {
                                this.classes.put(className, c);
                            }
                            this.w.unlock();
                            wlocked = false;
                        } catch (IllegalAccessException | LinkageError | SecurityException e3) {
                            logger.log(Level.FINE, "Unable to inject " + className, e3);
                            if (0 != 0) {
                                this.r.unlock();
                            }
                            if (0 != 0) {
                                this.w.unlock();
                            }
                            return null;
                        } catch (InvocationTargetException e4) {
                            Throwable t = e4.getTargetException();
                            if (t instanceof LinkageError) {
                                logger.log(Level.FINE, "duplicate class definition bug occured? Please report this : " + className, t);
                            } else {
                                logger.log(Level.FINE, "Unable to inject " + className, t);
                            }
                            if (0 != 0) {
                                this.r.unlock();
                            }
                            if (0 != 0) {
                                this.w.unlock();
                            }
                            return null;
                        }
                    }
                }
                Class cls2 = c;
                if (rlocked) {
                    this.r.unlock();
                }
                if (wlocked) {
                    this.w.unlock();
                }
                return cls2;
            } catch (Throwable th) {
                if (rlocked) {
                    this.r.unlock();
                }
                if (0 != 0) {
                    this.w.unlock();
                }
                throw th;
            }
        }
        return null;
    }

    private Class find(String className) {
        this.r.lock();
        try {
            return this.classes.get(className);
        } finally {
            this.r.unlock();
        }
    }
}