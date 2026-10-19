package mycode.orders.service;

import mycode.orders.model.Comanda;
import mycode.products.model.Produs;

public class CalculatorPret {

    private final double tva;

    public CalculatorPret(double tva) {
        this.tva = tva;
    }

    public double subtotal(Comanda comanda) {
        return comanda.getProduse().stream().mapToDouble(Produs::getPret).sum();
    }

    public double tva(Comanda comanda) {
        return subtotal(comanda) * tva;
    }

    public double total(Comanda comanda) {
        return subtotal(comanda) + tva(comanda);
    }
}

