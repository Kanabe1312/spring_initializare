package mycode.products.view;

import mycode.products.services.ProdusService;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ViewCafenea {

    private final ProdusService produsService;
    private final Scanner scanner;

    public ViewCafenea(ProdusService produsService, Scanner scanner) {
        this.produsService = produsService;
        this.scanner = scanner;
    }

    public void play() {
        System.out.println("========== CAFENEA ==========");
        System.out.println("1. Afiseaza produse");
        System.out.println("0. Iesire");
        System.out.print("Alege optiunea: ");

        int alegere = scanner.nextInt();

        switch (alegere) {
            case 1:
                produsService.listProduse().forEach(System.out::println);
                break;
            case 0:
                System.out.println("La revedere!");
                break;
            default:
                System.out.println("Optiune invalida!");
        }
    }
}