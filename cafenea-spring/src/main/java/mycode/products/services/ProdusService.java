package mycode.products.services;

import mycode.products.model.Produs;

import java.util.List;
import java.util.Optional;

public interface ProdusService {

    List<Produs> listProduse();

    Optional<Produs> findById(int id);
}
