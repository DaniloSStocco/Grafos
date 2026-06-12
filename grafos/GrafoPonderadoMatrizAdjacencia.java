package grafos;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class GrafoPonderadoMatrizAdjacencia extends Grafo {
    
    private ArrayList<String> listaVertices;
    
    private ArrayList<ArrayList<Integer>> matriz;

    public GrafoPonderadoMatrizAdjacencia() {
        this.listaVertices = new ArrayList<>();
        this.matriz = new ArrayList<>();
    }

    @Override   
    public void adicionarVertice(String vertice) {
        if (listaVertices.contains(vertice)) {
            return;
        }

        int novoIndice = listaVertices.size();
        listaVertices.add(vertice);

        ArrayList<Integer> novaLinha = new ArrayList<>();
        for (int i = 0; i <= novoIndice; i++) {
            novaLinha.add(0);
        }
        matriz.add(novaLinha);

        for (int i = 0; i < novoIndice; i++) {
            matriz.get(i).add(0);
        }
    }

    @Override   
    public void removerVertice(String vertice) {
        int indice = listaVertices.indexOf(vertice);
        
        if (indice == -1) {
            return;
        }

        listaVertices.remove(indice); // Remove o nome
        matriz.remove(indice);        // Remove a linha
        
        // Remove a coluna de todas as linhas restantes
        for (ArrayList<Integer> linha : matriz) {
            linha.remove(indice);
        }
    }


    public void adicionarAresta(String origem, String destino, int peso) {
        // SOLUÇÃO: Garante que os vértices existem na matriz antes de ligá-los
        adicionarVertice(origem);
        adicionarVertice(destino);

        int idxOrigem = listaVertices.indexOf(origem);
        int idxDestino = listaVertices.indexOf(destino);

        if (idxOrigem != -1 && idxDestino != -1) {
            matriz.get(idxOrigem).set(idxDestino, peso); 
            matriz.get(idxDestino).set(idxOrigem, peso); 
        }
    }

    @Override   
    public void adicionarAresta(String origem, String destino) {
        // Se a classe mãe pedir para adicionar, usamos um peso padrão (ex: 1)
        adicionarAresta(origem, destino, 1);
    }

    @Override   
    public void removerAresta(String origem, String destino) {
        int idxOrigem = listaVertices.indexOf(origem);
        int idxDestino = listaVertices.indexOf(destino);

        if (idxOrigem != -1 && idxDestino != -1) {
            matriz.get(idxOrigem).set(idxDestino, 0);
            matriz.get(idxDestino).set(idxOrigem, 0); 
        }
    }

    @Override   
    public boolean existeVertice(String vertice) {
        return listaVertices.contains(vertice);
    }

    @Override   
    public boolean existeAresta(String origem, String destino) {
        int idxOrigem = listaVertices.indexOf(origem);
        int idxDestino = listaVertices.indexOf(destino);

        if (idxOrigem != -1 && idxDestino != -1) {
            return matriz.get(idxOrigem).get(idxDestino) != 0;
        }
        return false;
    }

    @Override   
    public int grau(String vertice) {
        int indice = listaVertices.indexOf(vertice);
        if (indice == -1) {
            return 0; 
        }

        int contagemGrau = 0;
        for (Integer pesoAresta : matriz.get(indice)) {
            if (pesoAresta != 0) {
                contagemGrau++;
            }
        }
        return contagemGrau;
    }

    @Override   
    public String toString() {
        List<String> isolados = new ArrayList<>();
        List<String> arestas = new ArrayList<>();
        boolean[] temAresta = new boolean[listaVertices.size()];

        for (int i = 0; i < matriz.size(); i++) {
            for (int j = i; j < matriz.get(i).size(); j++) {
                int peso = matriz.get(i).get(j);
                if (peso != 0) { 
                    temAresta[i] = true;
                    temAresta[j] = true;
                    
                    String v1 = listaVertices.get(i);
                    String v2 = listaVertices.get(j);
                    
                    if (v1.compareTo(v2) > 0) {
                        String temp = v1; v1 = v2; v2 = temp;
                    }
                    arestas.add("    \"" + v1 + "\" -- \"" + v2 + "\" [label=\"" + peso + "\"];");
                }
            }
        }
        
        for (int i = 0; i < listaVertices.size(); i++) {
            if (!temAresta[i]) {
                isolados.add("    \"" + listaVertices.get(i) + "\";");
            }
        }

        Collections.sort(isolados);
        Collections.sort(arestas);

        StringBuilder sb = new StringBuilder();
        sb.append("Ponderado - Matriz de Adjacencia\n");
        sb.append("graph {\n");
        for (String iso : isolados) sb.append(iso).append("\n");
        for (String aresta : arestas) sb.append(aresta).append("\n");
        sb.append("}\n");
        
        return sb.toString();
    }
}