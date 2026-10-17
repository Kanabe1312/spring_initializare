package mycode.products.model;

public class Produs {
    private final int id;
    private final String nume;
    private final double pret;

    public Produs(int id, String nume, double pret) {
        this.id = id;
        this.nume = nume;
        this.pret = pret;
    }

    public int getId() {
        return id;
    }

    public String getNume() {
        return nume;
    }

    public double getPret() {
        return pret;
    }

    @Override
    public String toString() {
        return String.format("%d. %-12s %6.2f lei", id, nume, pret);
    }
}
