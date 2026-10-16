package app.view;

import app.comenzi.model.Comanda;
import app.comenzi.service.ComandaService;
import app.comenzi.service.ComandaServiceSingleton;
import app.produse.service.ProdusService;
import app.produse.service.ProdusServiceSingleton;

import java.util.Scanner;

public class ViewCafenea {

    private final ProdusService produsService;
    private final ComandaService comandaService;
    private final Scanner scanner;

    public ViewCafenea() {
        produsService = ProdusServiceSingleton.getINSTANCE();
        comandaService = ComandaServiceSingleton.getINSTANCE();
        scanner = new Scanner(System.in);
    }

    public void start() {
        Comanda comanda = comandaService.comandaNoua();
        boolean running = true;
        while (running) {
            meniu();
            int optiune = Integer.parseInt(scanner.nextLine().trim());
            switch (optiune) {
                case 1 -> afiseazaProduse();
                case 2 -> adaugaProdus(comanda);
                case 3 -> afiseazaBon(comanda);
                case 4 -> comanda = finalizeaza(comanda);
                case 5 -> afiseazaComenzi();
                case 0 -> running = false;
                default -> System.out.println("Optiune invalida");
            }
        }
        System.out.println("La revedere!");
    }

    private void meniu() {
        System.out.println();
        System.out.println("=== Cafenea ===");
        System.out.println("1. Lista produse");
        System.out.println("2. Adauga produs in comanda");
        System.out.println("3. Afiseaza bonul");
        System.out.println("4. Finalizeaza comanda");
        System.out.println("5. Comenzi finalizate");
        System.out.println("0. Iesire");
        System.out.print("Optiune: ");
    }

    private void afiseazaProduse() {
        produsService.listProduse().forEach(System.out::println);
    }

    private void adaugaProdus(Comanda comanda) {
        System.out.print("Id produs: ");
        int produsId = Integer.parseInt(scanner.nextLine().trim());
        if (comandaService.adaugaProdus(comanda, produsId)) {
            System.out.println("Adaugat");
        } else {
            System.out.println("Produs inexistent");
        }
    }

    private void afiseazaBon(Comanda comanda) {
        System.out.println("Bon comanda #" + comanda.getId());
        comanda.getProduse().forEach(produs ->
                System.out.printf("  %-12s %6.2f%n", produs.getNume(), produs.getPret()));
        System.out.printf("  Subtotal     %6.2f%n", comandaService.subtotal(comanda));
        System.out.printf("  TVA          %6.2f%n", comandaService.tva(comanda));
        System.out.printf("  TOTAL        %6.2f lei%n", comandaService.total(comanda));
    }

    private Comanda finalizeaza(Comanda comanda) {
        if (comanda.getProduse().isEmpty()) {
            System.out.println("Comanda e goala");
            return comanda;
        }
        comandaService.finalizeaza(comanda);
        System.out.println("Comanda #" + comanda.getId() + " finalizata");
        return comandaService.comandaNoua();
    }

    private void afiseazaComenzi() {
        comandaService.listComenzi().forEach(comanda ->
                System.out.printf("#%d  %d produse  %6.2f lei%n",
                        comanda.getId(), comanda.getProduse().size(), comandaService.total(comanda)));
    }
}
