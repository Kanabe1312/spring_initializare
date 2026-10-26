package mycode.config;
import mycode.orders.service.CalculatorPret;
import mycode.orders.service.Reducere;
import mycode.products.view.ViewCafenea;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;
import java.util.Scanner;

@Configuration
@EnableConfigurationProperties(CafeneaProperties.class)
public class AppConfig {

    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }

    @Bean
    public CalculatorPret calculatorPret(
            @Value("${cafenea.tva}") double tva, List<Reducere> reduceri) {
        return new CalculatorPret(tva, reduceri);
    }

    //@Bean
    // public CafeneaProperties cafeneaProperties(@Value("${cafenea.nume}")String nume,
    //                                         @Value("${cafenea.moneda}")String moneda,
    //                                       @Value("${cafenea.program}")String program){
    //return new CafeneaProperties(nume,moneda,program);
    //}

    @Bean
    public CommandLineRunner pornesteMeniul(ViewCafenea view) {
        return args -> view.play();
    }

}
