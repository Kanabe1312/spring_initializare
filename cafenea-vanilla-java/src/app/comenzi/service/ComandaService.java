package app.comenzi.service;

import app.comenzi.model.Comanda;

import java.util.List;

public interface ComandaService {

    Comanda comandaNoua();

    boolean adaugaProdus(Comanda comanda, int produsId);

    void finalizeaza(Comanda comanda);

    List<Comanda> listComenzi();

    double subtotal(Comanda comanda);

    double tva(Comanda comanda);

    double total(Comanda comanda);
}
