package app.produse.repository;

import app.produse.model.Produs;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProdusRepositoryImpl implements ProdusRepository {

    private final List<Produs> produse = new ArrayList<>();

    public ProdusRepositoryImpl() {
        produse.add(new Produs(1, "Espresso", 8.00));
        produse.add(new Produs(2, "Cappuccino", 12.50));
        produse.add(new Produs(3, "Latte", 13.00));
        produse.add(new Produs(4, "Croissant", 9.50));
    }

    @Override
    public List<Produs> listProduse() {
        return produse;
    }

    @Override
    public Optional<Produs> findById(int id) {
        return produse.stream().filter(produs -> produs.getId() == id).findAny();
    }
}
