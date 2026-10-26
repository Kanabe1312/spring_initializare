package mycode.products.repository;

import mycode.products.model.Produs;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Repository
@DependsOn("jurnalCafenea")
public class ProdusRepositoryImpl implements ProdusRepository {

    private final List<Produs> produse = new ArrayList<>();

    public ProdusRepositoryImpl() {
        produse.add(new Produs(1, "Espresso", 8.00));
        produse.add(new Produs(2, "Cappuccino", 12.50));
        produse.add(new Produs(3, "Latte", 13.00));
        produse.add(new Produs(4, "Croissant", 9.50));
        System.out.println("[Produse] repository creat");
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