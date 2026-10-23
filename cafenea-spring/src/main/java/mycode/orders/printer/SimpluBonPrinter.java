package mycode.orders.printer;

import mycode.orders.model.Comanda;
import mycode.products.model.Produs;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class SimpluBonPrinter implements  BonPrinter{

    @Override
    public void printeaza(Comanda comanda){
        System.out.println("Bon comanda #"+comanda.getId());
       for (Produs produs : comanda.getProduse()){
           System.out.printf("%-15s %6.2f %n",produs.getNume(),produs.getPret());
       }
    }


}

