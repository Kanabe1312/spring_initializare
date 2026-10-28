package casa;

public class CasaDeMarcat {
    private double totalZi = 0;

    public void deschide(){
        System.out.println("[Casa] deschisa");
    }
    public void inregistreaza(double suma){
        totalZi += suma;
    }

    public void inchide(){
        System.out.printf("[Casa] inchisa.Total zi: %.2f%n",totalZi);
    }


}
