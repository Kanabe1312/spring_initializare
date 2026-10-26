package mycode.raport;

import mycode.orders.repository.ComandaRepositoryImpl;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class RaportVanzari {
    public RaportVanzari(){
        System.out.println("[Raport] creat acum");
    }
    public void afiseaza(){
        System.out.println("Raport vanzari");
        System.out.println("Instante ComandaRepository: " + ComandaRepositoryImpl.getInstance()
        );
    }

}
