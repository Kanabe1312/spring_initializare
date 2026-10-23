package mycode.orders.printer;

import mycode.orders.model.Comanda;
import mycode.orders.repository.ComandaRepository;
import mycode.orders.service.ComandaService;
import mycode.products.model.Produs;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("bondetaliat")
public class DetaliatBonPrinter implements BonPrinter {
    private final ComandaService comandaService;
    public DetaliatBonPrinter(ComandaService comandaService){
        this.comandaService= comandaService;
    }


    public void printeaza(Comanda comanda){
        System.out.println("Bon detaliat comada#"+comanda.getId());

        for (Produs produs:comanda.getProduse()){
            System.out.printf("%-15s %6.2f %n",produs.getNume(),produs.getPret());
        }
        System.out.printf("Subtotal      %6.2f %n",comandaService.subtotal(comanda));

        System.out.printf("TVA 21        %6.2f %n",comandaService.tva(comanda));

        System.out.printf("TOTAL          %6.2f %n",comandaService.total(comanda));
    }



}
