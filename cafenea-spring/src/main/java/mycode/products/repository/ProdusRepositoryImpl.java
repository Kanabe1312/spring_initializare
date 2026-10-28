package mycode.products.repository;

import jakarta.annotation.PostConstruct;
import mycode.products.model.Produs;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.imageio.IIOException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Repository
@DependsOn("jurnalCafenea")
public class ProdusRepositoryImpl implements ProdusRepository {

    private final List<Produs> produse = new ArrayList<>();

    @Override
    public List<Produs> listProduse() {
        return produse;
    }

    @Override
    public Optional<Produs> findById(int id) {
        return produse.stream().filter(produs -> produs.getId() == id).findAny();
    }


    @PostConstruct
    public void incarcareProduse(){
        try {
            for(String linie : Files.readAllLines(Path.of("src/main/resources/produse.txt"))){
                String[] date = linie.split(";");
                int id = Integer.parseInt(date[0]);
                String nume = date[1];
                double pret = Double.parseDouble(date[2]);

                produse.add(new Produs(id,nume,pret));
            }
            System.out.println("[Produse] "+ produse.size() + "produse incarcate din produse.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}