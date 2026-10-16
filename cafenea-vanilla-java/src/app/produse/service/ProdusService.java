package app.produse.service;

import app.produse.model.Produs;

import java.util.List;
import java.util.Optional;

public interface ProdusService {

    List<Produs> listProduse();

    Optional<Produs> findById(int id);
}
