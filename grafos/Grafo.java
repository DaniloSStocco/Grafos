package grafos;

public abstract class Grafo {
    int nVertices = 0;
    int nArestas = 0;

    /**
     * Adiciona um vértice avulso, pelo nome, no grafo.
     * @param vertice O nome do vértice via String
     */
    public abstract void adicionarVertice(String vertice);

    public abstract void removerVertice(String vertice);

    public abstract void adicionarAresta(String origem, String destino);

    public abstract void removerAresta(String origem, String destino);

    public abstract boolean existeVertice(String vertice);

    public abstract boolean existeAresta(String origem, String destino);

    public abstract int grau(String vertice);

    public int ordem(){
        return this.nVertices;
    }

    public int tamanho(){
        return this.nArestas;
    }

    public abstract String toString();

}
