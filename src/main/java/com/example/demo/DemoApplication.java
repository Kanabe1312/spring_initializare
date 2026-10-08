package com.example.demo;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(DemoApplication.class)
                .bannerMode(Banner.Mode.OFF)
                .logStartupInfo(false)
                .properties("logging.level.root=OFF", "logging.pattern.console=%msg%n")
                .run(args);
    }
}
