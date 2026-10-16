package app.comenzi.model;

import app.produse.model.Produs;

import java.util.ArrayList;
import java.util.List;

public class Comanda {

    private final int id;
    private final List<Produs> produse = new ArrayList<>();

    public Comanda(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public List<Produs> getProduse() {
        return produse;
    }

    public void adaugaProdus(Produs produs) {
        produse.add(produs);
    }
}
