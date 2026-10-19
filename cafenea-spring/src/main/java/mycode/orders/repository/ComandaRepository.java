package mycode.orders.repository;

import mycode.orders.model.Comanda;

import java.util.List;

public interface ComandaRepository {

    void addComanda(Comanda comanda);

    List<Comanda> listComenzi();

    int nextId();
}
