package app.produse.service;

import app.produse.model.Produs;
import app.produse.repository.ProdusRepository;
import app.produse.repository.ProdusRepositorySingleton;

import java.util.List;
import java.util.Optional;

public class ProdusServiceImpl implements ProdusService {

    private final ProdusRepository produsRepository;

    public ProdusServiceImpl() {
        produsRepository = ProdusRepositorySingleton.getINSTANCE();
    }

    @Override
    public List<Produs> listProduse() {
        return produsRepository.listProduse();
    }

    @Override
    public Optional<Produs> findById(int id) {
        return produsRepository.findById(id);
    }
}
