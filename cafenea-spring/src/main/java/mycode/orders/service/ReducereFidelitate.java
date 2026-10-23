package mycode.orders.service;

import mycode.orders.model.Comanda;

public class ReducereFidelitate implements Reducere {

    @Override
    public String nume(){
        return "Fidelitate";
    }
    @Override
    public double aplica(Comanda comanda,double suma){
        if (suma > 30){
            return suma *0.95;
        }
        return suma;
    }
}
