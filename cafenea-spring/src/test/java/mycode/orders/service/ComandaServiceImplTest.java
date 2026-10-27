package mycode.orders.service;

import mycode.orders.model.Comanda;
import mycode.orders.repository.ComandaRepository;
import mycode.products.model.Produs;
import mycode.products.services.ProdusService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.ObjectProvider;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ComandaServiceImplTest {
    @Mock
    private ComandaRepository comandaRepository;

    @Mock
    private ProdusService produsService;

    @Mock
    private  CalculatorPret calculatorPret;

    @Mock
    private  ObjectProvider<Comanda> comandaProvider;

    private  ComandaServiceImpl service;

    @BeforeEach
    void setUp(){

        service = new ComandaServiceImpl(comandaRepository,produsService,calculatorPret,comandaProvider);

    }


    @Test
    void addPuneInComanda(){
        //Arrange
        Comanda comanda = new Comanda(1);

        Produs produs = new Produs(1,"Espresso",8.00);

        when(produsService.findById(4)).thenReturn(Optional.of(produs));

        //actiunea
        boolean adaugat=service.adaugaProdus(comanda,4);

        //assert
        assertTrue(adaugat);

        assertEquals(1,comanda.getProduse().size());
    }


    @Test
    void addPuneInComandaProdusInexistent(){
        //Arrange
        Comanda comanda = new Comanda(1);

        when(produsService.findById(4)).thenReturn(Optional.empty());

        //Actiunea
        boolean adaugat = service.adaugaProdus(comanda,4);

        //assert
        assertFalse(adaugat);
        assertEquals(0,comanda.getProduse().size());
    }



}
