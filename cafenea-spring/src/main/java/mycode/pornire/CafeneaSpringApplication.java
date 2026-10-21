package mycode.pornire;

import mycode.config.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@ComponentScan("mycode")
@Import(AppConfig.class)
public class CafeneaSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(CafeneaSpringApplication.class, args);
    }
}
