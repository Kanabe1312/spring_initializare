package mycode.orders.repository;

import mycode.orders.model.Comanda;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
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