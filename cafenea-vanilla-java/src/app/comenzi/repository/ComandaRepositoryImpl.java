package app.comenzi.repository;

import app.comenzi.model.Comanda;

import java.util.ArrayList;
import java.util.List;

public class ComandaRepositoryImpl implements ComandaRepository {

    private final List<Comanda> comenzi = new ArrayList<>();

    @Override
    public void addComanda(Comanda comanda) {
        comenzi.add(comanda);
    }

    @Override
    public List<Comanda> listComenzi() {
        return comenzi;
    }

    @Override
    public int nextId() {
        return comenzi.size() + 1;
    }
}
