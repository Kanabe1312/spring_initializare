package mycode.orders.service;

import mycode.orders.model.Comanda;
import mycode.orders.repository.ComandaRepository;
import mycode.products.model.Produs;
import mycode.products.services.ProdusService;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class ComandaServiceImpl implements ComandaService {

    private final ComandaRepository comandaRepository;
    private final ProdusService produsService;
    private final CalculatorPret calculatorPret;
    private final ObjectProvider<Comanda> comandaProvider;
    public ComandaServiceImpl(
            ComandaRepository comandaRepository,
            ProdusService produsService,
            CalculatorPret calculatorPret,
            ObjectProvider<Comanda> comandaProvider) {

        this.comandaRepository = comandaRepository;
        this.produsService = produsService;
        this.calculatorPret = calculatorPret;
        this.comandaProvider = comandaProvider;
    }

    @Override
    public Comanda comandaNoua() {
        int id = comandaRepository.nextId();
        return comandaProvider.getObject(id);
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

    @Override
    public double subtotalDupaReduceri(Comanda comanda) {
        return calculatorPret.subtotalDupaReduceri(comanda);
    }
}