package mycode.orders.model;

import mycode.products.model.Produs;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
@Scope("prototype")
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
