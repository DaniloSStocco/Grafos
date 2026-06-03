package grafos;
import java.util.List;
import java.util.ArrayList;

public class VerticeLista {
    String Nome;
    List<String> ligacoes = new ArrayList<>();

    public VerticeLista(String nome){
        this.Nome = nome;
    }

    public String getNome(){
        return this.Nome;
    }
}
