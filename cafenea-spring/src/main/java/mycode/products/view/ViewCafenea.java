package mycode.products.view;

import mycode.config.CafeneaProperties;
import mycode.orders.model.Comanda;
import mycode.orders.printer.BonPrinter;
import mycode.orders.service.ComandaService;
import mycode.products.services.ProdusService;
import mycode.raport.RaportVanzari;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ViewCafenea {

    private final ProdusService produsService;
    private final ComandaService comandaService;
    private final Scanner scanner;
    private Comanda comandaCurenta;
    private  CafeneaProperties cafeneaProperties;
    private final BonPrinter bonPrinter;
    private final BonPrinter bonDetaliatPrinter;
    private final ObjectProvider<RaportVanzari> raportProvider;



    public ViewCafenea(ProdusService produsService, ComandaService comandaService,
                       Scanner scanner, CafeneaProperties cafeneaProperties,
                       BonPrinter bonPrinter, @Qualifier("bondetaliat")BonPrinter bonDetaliatPrinter,
                       ObjectProvider<RaportVanzari> raportProvider) {
        this.produsService = produsService;
        this.comandaService = comandaService;
        this.scanner = scanner;
        this.cafeneaProperties=cafeneaProperties;
        this.bonPrinter = bonPrinter;
        this.bonDetaliatPrinter = bonDetaliatPrinter;
        this.raportProvider = raportProvider;


    }

    public void play() {
        boolean ruleaza = true;
        System.out.println(cafeneaProperties);
        while (ruleaza) {
            System.out.println("\n========== CAFENEA ==========");
            System.out.println("1. Afiseaza produse");
            System.out.println("2. Comanda noua");
            System.out.println("3. Afiseaza bonul");
            System.out.println("4. Finalizeaza comanda");
            System.out.println("5. Afiseaza comenzile");
            System.out.println("6.Bon detaliat");
            System.out.println("0. Iesire");
            System.out.print("Alege optiunea: ");

            int alegere = scanner.nextInt();

            switch (alegere) {
                case 1:
                    produsService.listProduse()
                            .forEach(System.out::println);
                    break;

                case 2:
                    comandaCurenta = comandaService.comandaNoua();
                    System.out.println("Comanda #" + comandaCurenta.getId());
                    produsService.listProduse().forEach(System.out ::println);

                    System.out.println("Introdu Id-ul produsului:");
                    int produsid;

                    do {
                        System.out.println("ID produs: ");
                        produsid = scanner.nextInt();

                        if (produsid != 0){
                            boolean adaugat = comandaService.adaugaProdus(comandaCurenta,produsid);
                            if (adaugat){
                                System.out.println("Produs adaugat cu succes!");
                            }else {
                                System.out.println("Nu exista acest produs");
                            }
                        }
                    }while (produsid != 0);
                    break;

                case 3:
                    if (comandaCurenta == null) {
                        System.out.println("Nu exista o comanda activa.");
                        break;
                    }

                    System.out.println("Bon comanda #" + comandaCurenta.getId());

                    comandaCurenta.getProduse().forEach(produs -> System.out.printf("  %-15s %6.2f%n", produs.getNume(),
                            produs.getPret()));

                    System.out.printf("  Subtotal    %6.2f%n", comandaService.subtotal(comandaCurenta));
                    System.out.printf("  TVA       %6.2f%n", comandaService.tva(comandaCurenta));
                    System.out.printf("  TOTAL       %6.2f lei%n", comandaService.total(comandaCurenta));
                    break;

                case 4:
                    if (comandaCurenta == null) {
                        System.out.println("Nu exista o comanda activa.");
                        break;
                    }

                    comandaService.finalizeaza(comandaCurenta);
                    System.out.println("Comanda finalizata.");
                    comandaCurenta = null;
                    break;

                case 5:
                    comandaService.listComenzi().forEach(comanda -> System.out.printf("#%d   %d produse      %.2f lei%n", comanda.getId(),
                            comanda.getProduse().size(), comandaService.total(comanda)));
                    raportProvider.getObject().afiseaza();
                    break;
                case 6:
                    if (comandaCurenta == null){
                        System.out.println("Nu exista o comanda activa.");
                        break;
                    }
                    bonDetaliatPrinter.printeaza(comandaCurenta);
                    break;

                case 0:
                    ruleaza = false;
                    System.out.println("La revedere!");
                    break;

                default:
                    System.out.println("Optiune invalida!");
            }
        }
    }
}