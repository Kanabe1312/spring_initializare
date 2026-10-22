package mycode.products.services;

import mycode.products.model.Produs;
import mycode.products.repository.ProdusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ProdusServiceImpl implements ProdusService {

    @Autowired
    private  ProdusRepository produsRepository;



    @Override
    public List<Produs> listProduse() {
        return produsRepository.listProduse();
    }

    @Override
    public Optional<Produs> findById(int id) {
        return produsRepository.findById(id);
    }
}

