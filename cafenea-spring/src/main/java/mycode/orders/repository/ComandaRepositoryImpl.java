package mycode.orders.repository;

import mycode.orders.model.Comanda;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Scope("singleton")
public class ComandaRepositoryImpl implements ComandaRepository {

    private final List<Comanda> comenzi = new ArrayList<>();
    private static int instanteCreate = 0;
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


    public ComandaRepositoryImpl(){
        instanteCreate++;
    }
    public static int getInstance(){
        return instanteCreate;
    }
}