package mycode.products.repository;

import mycode.products.model.Produs;

import java.util.List;
import java.util.Optional;

public interface ProdusRepository {

    List<Produs> listProduse();

    Optional<Produs> findById(int id);
}