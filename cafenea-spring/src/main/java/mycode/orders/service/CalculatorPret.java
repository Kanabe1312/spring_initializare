package mycode.orders.service;

import mycode.orders.model.Comanda;
import mycode.products.model.Produs;

import java.util.List;

public class CalculatorPret {

    private final double tva;
    private final List<Reducere> reduceri;

    public CalculatorPret(double tva, List<Reducere> reduceri) {
        this.tva = tva;
        this.reduceri = reduceri;
    }

    public double subtotal(Comanda comanda) {
        return comanda.getProduse().stream().mapToDouble(Produs::getPret).sum();
    }

    public double subtotalDupaReduceri(Comanda comanda) {
        double suma = subtotal(comanda);

        for (Reducere reducere : reduceri) {
            suma = reducere.aplica(comanda, suma);
        }

        return suma;
    }

    public double tva(Comanda comanda) {
        return subtotalDupaReduceri(comanda) * tva;
    }

    public double total(Comanda comanda) {
        double suma = subtotalDupaReduceri(comanda);
        return suma + suma * tva;
    }
}