package htb.cloudhosting.scheduled;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.WebContentGenerator;

@Component
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/classes/htb/cloudhosting/scheduled/FakeUser.class */
public class FakeUser {
    @Scheduled(timeUnit = TimeUnit.MINUTES, fixedDelay = 5)
    public void login() throws IOException {
        System.out.println("Logging in user ...");
        Runtime.getRuntime().exec(new String[]{"curl", "localhost:8080/login", "--request", WebContentGenerator.METHOD_POST, "--header", "Content-Type: application/x-www-form-urlencoded", "--data-raw", "username=kanderson&password=MRdEQuv6~6P9", "-v"});
    }
}