package ch.qos.logback.core.testUtil;

import java.util.Random;

/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/lib/logback-core-1.4.5.jar:ch/qos/logback/core/testUtil/RandomUtil.class */
public class RandomUtil {
    private static Random random = new Random();

    public static int getRandomServerPort() {
        int r = random.nextInt(20000);
        return r + 1024;
    }

    public static int getPositiveInt() {
        int r = random.nextInt();
        if (r < 0) {
            r = -r;
        }
        return r;
    }
}