package mycode.config;
import mycode.orders.service.CalculatorPret;
import mycode.products.view.ViewCafenea;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
public class AppConfig {

    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }

    @Bean
    public CalculatorPret calculatorPret() {
        return new CalculatorPret(0.21);
    }


    @Bean
    public CommandLineRunner pornesteMeniul(ViewCafenea view) {
        return args -> view.play();
    }

}
