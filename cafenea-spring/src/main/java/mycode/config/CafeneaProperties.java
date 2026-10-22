package mycode.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix="cafenea")
public record CafeneaProperties(String nume ,String moneda ,String program) {
}
