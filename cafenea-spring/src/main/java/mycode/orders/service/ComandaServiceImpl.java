package mycode.orders.service;

import mycode.orders.model.Comanda;
import mycode.orders.repository.ComandaRepository;
import mycode.products.model.Produs;
import mycode.products.services.ProdusService;

import java.util.List;
import java.util.Optional;

public class ComandaServiceImpl implements ComandaService {

    private final ComandaRepository comandaRepository;
    private final ProdusService produsService;
    private final CalculatorPret calculatorPret;

    public ComandaServiceImpl(
            ComandaRepository comandaRepository,
            ProdusService produsService,
            CalculatorPret calculatorPret) {

        this.comandaRepository = comandaRepository;
        this.produsService = produsService;
        this.calculatorPret = calculatorPret;
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