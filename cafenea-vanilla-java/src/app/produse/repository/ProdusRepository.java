package app.produse.repository;

import app.produse.model.Produs;

import java.util.List;
import java.util.Optional;

public interface ProdusRepository {

    List<Produs> listProduse();

    Optional<Produs> findById(int id);
}
