package grafos;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GrafoListaAdjacencia extends Grafo{
    List<VerticeLista> vertices = new ArrayList<>();

    public GrafoListaAdjacencia(){

    }

    public void adicionarVertice(String vertice){
        if(!existeVertice(vertice)){
            vertices.add(new VerticeLista(vertice));
            nVertices++;
        }
    }

    public void removerVertice(String vertice){
        for (VerticeLista v : vertices) {
            v.ligacoes.remove(vertice); 
        }

        vertices.removeIf(v -> v.getNome().equals(vertice));
    }

    public void adicionarAresta(String origem, String destino){
        adicionarVertice(origem);
        adicionarVertice(destino);
        
        for (VerticeLista v : vertices) {
            if(v.getNome().equals(origem)){
                v.ligacoes.add(destino);
            }
            if(v.getNome().equals(destino)){
                v.ligacoes.add(origem);
                nArestas++;
            }
        }
        
    }

    public void removerAresta(String origem, String destino){
        
        for (VerticeLista v : vertices) {
            if(v.getNome().equals(origem)){
                v.ligacoes.remove(destino);
            }
            if(v.getNome().equals(destino)){
                v.ligacoes.remove(destino);
                nArestas--;
            }
        }
    }

    public boolean existeVertice(String vertice){
        for (VerticeLista v : vertices) {
            if(v.getNome().equals(vertice)){
                return true;
            }
        }
        return false;
    }

    public boolean existeAresta(String origem, String destino){
        for (VerticeLista v : vertices) {
            if(v.getNome().equals(origem) && v.ligacoes.contains(destino)){
                return true;
            }
        }
        return false;
    }

    public int grau(String vertice){
        for (VerticeLista v : vertices) {
            if(v.getNome().equals(vertice)){
                return v.ligacoes.size();
            }
        }
        return -1; //não encontrou
    }

    public String toString(){
        List<String> verticesJaLidos = new ArrayList<>();

        String saida = "Lista de Adjacencia\n"+ 
                       "graph {";
        this.vertices.sort(Comparator.comparing(VerticeLista::getNome));
        for (VerticeLista v : vertices) {
            v.ligacoes.sort(null);
            for (String ligado : v.ligacoes) {
                if(!verticesJaLidos.contains(ligado))
                    saida += ("\n\t\""+v.getNome()+"\" -- \""+ligado+"\";");
            }
            verticesJaLidos.add(v.getNome());
        }
        saida += "\n}\n";

        return saida;
    }
}
