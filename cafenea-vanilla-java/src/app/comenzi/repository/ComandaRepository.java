package app.comenzi.repository;

import app.comenzi.model.Comanda;

import java.util.List;

public interface ComandaRepository {

    void addComanda(Comanda comanda);

    List<Comanda> listComenzi();

    int nextId();
}
