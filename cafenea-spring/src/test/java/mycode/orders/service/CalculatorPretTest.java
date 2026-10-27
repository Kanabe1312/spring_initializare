package mycode.orders.service;

import mycode.orders.model.Comanda;
import mycode.products.model.Produs;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CalculatorPretTest {


    @Test
    void subtotalCalculSumaPreturiProduse(){

        //Arrange
        Comanda comanda = new Comanda(1);
        Produs produs1 = new Produs(1,"Espresso",8.00);
        Produs produs2 = new Produs(2,"Cappuccino",12);
        comanda.adaugaProdus(produs1);
        comanda.adaugaProdus(produs2);
        CalculatorPret calculatorPret = new CalculatorPret(0.21, List.of());
        //act
        double rezultat = calculatorPret.subtotal(comanda);


        assertEquals(rezultat,20,0.001);



    }


}
