package mycode.orders.service;

import mycode.orders.model.Comanda;
import mycode.products.model.Produs;

public class ReducereMeniu implements Reducere{

    @Override
    public String nume(){
        return  "Meniu cafea + croissant";
    }

    @Override
    public double aplica(Comanda comanda,double suma){
        boolean espresso = false;
        boolean cappuccino = false;
        boolean latte = false;

        for (Produs produs : comanda.getProduse()){
            switch (produs.getNume()){
                case "Espresso" -> espresso = true;
                case "Cappuccino" -> cappuccino = true;
                case "Latte"->latte = true;

            }
        }
        if (espresso && cappuccino && latte){
            return Math.max(0,suma - 2.0);
        }
        return suma;
    }


}
