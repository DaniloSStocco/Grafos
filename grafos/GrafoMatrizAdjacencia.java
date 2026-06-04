package grafos;

import java.util.ArrayList;

public class GrafoMatrizAdjacencia extends Grafo {

    // Lista que serve como "tradutora": a posição da String aqui é o índice na
    // matriz
    private ArrayList<String> listaVertices;

    // Matriz dinâmica (lista de listas) que guarda apenas 0 (sem aresta) ou 1 (com
    // aresta)
    private ArrayList<ArrayList<Integer>> matriz;

    // Construtor: Inicializa as listas
    public GrafoMatrizAdjacencia() {
        this.listaVertices = new ArrayList<>();
        this.matriz = new ArrayList<>();
    }

    @Override
    public void adicionarVertice(String vertice) {
        // Se já existe, não faz nada para evitar duplicatas
        if (listaVertices.contains(vertice)) {
            return;
        }

        // Adiciona na lista tradutora. O índice será o tamanho atual (antes de
        // adicionar)
        int novoIndice = listaVertices.size();
        listaVertices.add(vertice);

        // Expande a matriz: 1. Cria uma nova linha inteira com 0s
        ArrayList<Integer> novaLinha = new ArrayList<>();
        for (int i = 0; i <= novoIndice; i++) {
            novaLinha.add(0);
        }
        matriz.add(novaLinha);

        // Expande a matriz: 2. Adiciona uma nova coluna (um '0' no final) em cada linha
        // antiga
        for (int i = 0; i < novoIndice; i++) {
            matriz.get(i).add(0);
        }
    }

    @Override
    public void removerVertice(String vertice) {
        int indice = listaVertices.indexOf(vertice);

        // Se não achou o vértice, sai da função
        if (indice == -1) {
            return;
        }

        // 1. Remove da lista de nomes (o Java reorganiza os índices automaticamente)
        listaVertices.remove(indice);

        // 2. Remove a linha correspondente da matriz
        matriz.remove(indice);

        // 3. Entra em cada linha que sobrou e remove a coluna correspondente
        for (ArrayList<Integer> linha : matriz) {
            linha.remove(indice);
        }
    }

    @Override
    public void adicionarAresta(String origem, String destino) {
        int idxOrigem = listaVertices.indexOf(origem);
        int idxDestino = listaVertices.indexOf(destino);

        // Verifica se ambos existem
        if (idxOrigem != -1 && idxDestino != -1) {
            matriz.get(idxOrigem).set(idxDestino, 1); // Marca a ida
            matriz.get(idxDestino).set(idxOrigem, 1); // Marca a volta
        }
    }

    @Override
    public void removerAresta(String origem, String destino) {
        int idxOrigem = listaVertices.indexOf(origem);
        int idxDestino = listaVertices.indexOf(destino);

        if (idxOrigem != -1 && idxDestino != -1) {
            matriz.get(idxOrigem).set(idxDestino, 0); // Remove a ida
            matriz.get(idxDestino).set(idxOrigem, 0); // Remove a volta
        }
    }

    @Override
    public boolean existeVertice(String vertice) {
        // O ArrayList já tem um método pronto para verificar existência!
        return listaVertices.contains(vertice);
    }

    @Override
    public boolean existeAresta(String origem, String destino) {
        int idxOrigem = listaVertices.indexOf(origem);
        int idxDestino = listaVertices.indexOf(destino);

        if (idxOrigem != -1 && idxDestino != -1) {
            // Retorna true se tiver 1, false se tiver 0
            return matriz.get(idxOrigem).get(idxDestino) == 1;
        }
        return false;
    }

    @Override
    public int grau(String vertice) {
        int indice = listaVertices.indexOf(vertice);
        if (indice == -1) {
            return 0; // Se o vértice não existe, grau é 0
        }

        int contagemGrau = 0;

        // Entra na linha do vértice e conta quantos '1's ele tem
        // OBS: Isso conta o grau de SAÍDA em grafos direcionados.
        for (Integer aresta : matriz.get(indice)) {
            if (aresta == 1) {
                contagemGrau++;
            }
        }
        return contagemGrau;
    }

    @Override   
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Matriz de Adjacencia\n");
        sb.append("graph {\n");

        for (int i = 0; i < matriz.size(); i++) {
            // Inicia o 'j' igual a 'i' para não imprimir a ida e a volta da mesma aresta
            for (int j = i; j < matriz.get(i).size(); j++) {
                
                if (matriz.get(i).get(j) == 1) { // 1 significa que a aresta existe
                    sb.append("    \"")
                      .append(listaVertices.get(i))
                      .append("\" -- \"")
                      .append(listaVertices.get(j))
                      .append("\";\n");
                }
            }
        }
        
        sb.append("}\n");
        return sb.toString();
    }
}