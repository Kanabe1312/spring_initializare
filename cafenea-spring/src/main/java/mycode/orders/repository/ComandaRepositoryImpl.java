package mycode.orders.repository;

import jakarta.annotation.PreDestroy;
import mycode.orders.model.Comanda;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.IOError;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Repository
@Scope("singleton")
public class ComandaRepositoryImpl implements ComandaRepository {

    private final List<Comanda> comenzi = new ArrayList<>();
    private static int instanteCreate = 0;
    @Override
    public void addComanda(Comanda comanda) {
        comenzi.add(comanda);
    }

    @Override
    public List<Comanda> listComenzi() {
        return comenzi;
    }

    @Override
    public int nextId() {
        return comenzi.size() + 1;
    }


    public ComandaRepositoryImpl(){
        instanteCreate++;
    }
    public static int getInstance(){
        return instanteCreate;
    }

    @PreDestroy
    public void salveazaComenzi(){
        try (PrintWriter writer = new PrintWriter("comenzi.txt")){
            for (Comanda comanda : comenzi){
                writer.print(comanda.getId() + ";");

               for(int i = 0;i<comanda.getProduse().size();i++){
                   writer.print(comanda.getProduse().get(i).getNume());

                   if(i < comanda.getProduse().size()-1){
                       writer.print(",");
                   }
               }
                writer.println();
            }
            System.out.println("[Comenzi] "+ comenzi.size() + " comenzi salvate in comenzi.txt");
        }catch (IOException e){
            throw  new RuntimeException(e);
        }
    }


}