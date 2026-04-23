package com.scheduleappdevelopment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.security.autoconfigure.SecurityAutoConfiguration;
import org.springframework.boot.security.autoconfigure.actuate.web.servlet.ManagementWebSecurityAutoConfiguration;

@SpringBootApplication(exclude = {
        SecurityAutoConfiguration.class,
        ManagementWebSecurityAutoConfiguration.class // 이것까지 추가!
})
public class ScheduleAppDevelopmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScheduleAppDevelopmentApplication.class, args);
    }

}
