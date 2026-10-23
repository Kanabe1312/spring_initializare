package mycode.orders.service;

import mycode.orders.model.Comanda;

public interface Reducere {

    String nume();

    double aplica(Comanda comanda,double suma);

}
