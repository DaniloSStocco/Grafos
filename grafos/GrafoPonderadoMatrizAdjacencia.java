package grafos;

import java.util.ArrayList;

public class GrafoPonderadoMatrizAdjacencia extends Grafo {
    
    // Lista tradutora: O índice do elemento aqui dita a linha/coluna na matriz
    private ArrayList<String> listaVertices;
    
    // Matriz dinâmica: Guarda o PESO da aresta. O valor 0 significa "Sem Aresta"
    private ArrayList<ArrayList<Integer>> matriz;

    // Construtor
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

        // Adiciona nova linha com 0s
        ArrayList<Integer> novaLinha = new ArrayList<>();
        for (int i = 0; i <= novoIndice; i++) {
            novaLinha.add(0);
        }
        matriz.add(novaLinha);

        // Adiciona nova coluna (um 0 no final de cada linha existente)
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

    // ---------------------------------------------------------
    // NOVO MÉTODO (SOBRECARGA) - Exclusivo para adicionar com peso
    // ---------------------------------------------------------
    public void adicionarAresta(String origem, String destino, int peso) {
        int idxOrigem = listaVertices.indexOf(origem);
        int idxDestino = listaVertices.indexOf(destino);

        if (idxOrigem != -1 && idxDestino != -1) {
            matriz.get(idxOrigem).set(idxDestino, peso); // Ida
            matriz.get(idxDestino).set(idxOrigem, peso); // Volta (Não direcionado)
        }
    }

    // ---------------------------------------------------------
    // MÉTODO DA CLASSE MÃE (SOBRESCRITA) - Mantém a compatibilidade
    // ---------------------------------------------------------
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
            matriz.get(idxOrigem).set(idxDestino, 0); // 0 indica corte da conexão
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
            // Retorna true apenas se o valor for diferente de 0 (peso vazio)
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
        // O grau é o número de conexões incidentes, independente do peso delas.
        // Logo, contamos quantas células na linha são diferentes de zero.
        for (Integer pesoAresta : matriz.get(indice)) {
            if (pesoAresta != 0) {
                contagemGrau++;
            }
        }
        return contagemGrau;
    }

    @Override   
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Ponderado - Matriz de Adjacencia\n");
        sb.append("graph {\n");

        for (int i = 0; i < matriz.size(); i++) {
            // Inicia o 'j' igual a 'i' pelo mesmo motivo (evitar duplicatas)
            for (int j = i; j < matriz.get(i).size(); j++) {
                
                int peso = matriz.get(i).get(j);
                
                if (peso != 0) { // Diferente de 0 significa que a aresta existe
                    sb.append("    \"")
                      .append(listaVertices.get(i))
                      .append("\" -- \"")
                      .append(listaVertices.get(j))
                      .append("\" [label=\"")
                      .append(peso)
                      .append("\"];\n");
                }
            }
        }
        
        sb.append("}\n");
        return sb.toString();
    }
}