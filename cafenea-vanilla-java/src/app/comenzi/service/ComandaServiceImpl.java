package app.comenzi.service;

import app.comenzi.model.Comanda;
import app.comenzi.repository.ComandaRepository;
import app.comenzi.repository.ComandaRepositorySingleton;
import app.produse.model.Produs;
import app.produse.service.ProdusService;
import app.produse.service.ProdusServiceSingleton;

import java.util.List;
import java.util.Optional;

public class ComandaServiceImpl implements ComandaService {

    private final ComandaRepository comandaRepository;
    private final ProdusService produsService;
    private final CalculatorPret calculatorPret;

    public ComandaServiceImpl() {
        comandaRepository = ComandaRepositorySingleton.getINSTANCE();
        produsService = ProdusServiceSingleton.getINSTANCE();
        calculatorPret = new CalculatorPret(0.21);
    }

    @Override
    public Comanda comandaNoua() {
        return new Comanda(comandaRepository.nextId());
    }

    @Override
    public boolean adaugaProdus(Comanda comanda, int produsId) {
        Optional<Produs> produs = produsService.findById(produsId);
        produs.ifPresent(comanda::adaugaProdus);
        return produs.isPresent();
    }

    @Override
    public void finalizeaza(Comanda comanda) {
        comandaRepository.addComanda(comanda);
    }

    @Override
    public List<Comanda> listComenzi() {
        return comandaRepository.listComenzi();
    }

    @Override
    public double subtotal(Comanda comanda) {
        return calculatorPret.subtotal(comanda);
    }

    @Override
    public double tva(Comanda comanda) {
        return calculatorPret.tva(comanda);
    }

    @Override
    public double total(Comanda comanda) {
        return calculatorPret.total(comanda);
    }
}
