package mycode.products.view;

import mycode.products.repository.ProdusRepository;
import mycode.products.services.ProdusService;
import org.springframework.stereotype.Component;

@Component
public class ViewCafenea {

    ProdusService produsService;

    public ViewCafenea( ProdusService produsService){

        this.produsService=produsService;
        System.out.println("===================Alex=========================");

        produsService.listProduse().forEach(System.out::println);
    }


}
