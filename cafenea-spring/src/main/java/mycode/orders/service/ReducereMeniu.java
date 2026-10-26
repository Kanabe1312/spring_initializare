package mycode.orders.service;

import mycode.orders.model.Comanda;
import mycode.products.model.Produs;
import org.springframework.stereotype.Component;

@Component
public class ReducereMeniu implements Reducere{

    @Override
    public String nume(){
        return  "Meniu cafea + croissant";
    }

    @Override
    public double aplica(Comanda comanda, double suma) {
        boolean cafea = false;
        boolean croissant = false;

        for (Produs produs : comanda.getProduse()) {
            switch (produs.getNume()) {
                case "Espresso", "Cappuccino", "Latte" -> cafea = true;
                case "Croissant" -> croissant = true;
            }
        }

        if (cafea && croissant) {
            return Math.max(0, suma - 2.0);
        }

        return suma;

    }
}
