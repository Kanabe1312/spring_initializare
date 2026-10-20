package mycode.products.services;

import mycode.products.model.Produs;
import mycode.products.repository.ProdusRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ProdusServiceImpl implements ProdusService {

    private final ProdusRepository produsRepository;

    public ProdusServiceImpl(ProdusRepository produsRepository) {
        this.produsRepository = produsRepository;
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

