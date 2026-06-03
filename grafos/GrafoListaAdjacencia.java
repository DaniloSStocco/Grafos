package grafos;

import java.util.ArrayList;
import java.util.List;

public class GrafoListaAdjacencia extends Grafo{
    List<VerticeLista> vertices = new ArrayList<>();

    public void adicionarVertice(String vertice){
        vertices.add(new VerticeLista(vertice));
        nVertices++;
    }

    public void removerVertice(String vertice){
        for (VerticeLista v : vertices) {
            if(v.ligacoes.contains(vertice)){
                v.ligacoes.remove(vertice);
            }
            if(v.getNome().equals(vertice)){
                vertices.remove(v);
            }
        }
    }

    public void adicionarAresta(String origem, String destino){
        if(nVertices>1){
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
        
    }

    public void removerAresta(String origem, String destino){
        if(nVertices>1){
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
        String saida = "Lista de Adjacencia\n"+
                       "graph {\n\t";
        for (VerticeLista v : vertices) {
            for (String ligado : v.ligacoes) {
                saida += ("\""+v.getNome()+"\" -- \""+ligado);
            }
            
        }
        return saida;
    }
}
