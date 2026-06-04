package grafos;
import java.util.Scanner;
/**
 *
 * @author Danilo Salmen Stocco - 16861501
 * @author Diogo Salmen Stocco - 
 */


public class Main {

    public static void main(String[] args) {
        GrafoListaAdjacencia GLista = new GrafoListaAdjacencia();
        GrafoMatrizAdjacencia GMatriz = new GrafoMatrizAdjacencia();
        GrafoPonderadoMatrizAdjacencia GPMatriz = new GrafoPonderadoMatrizAdjacencia();
        
        Scanner sc = new Scanner(System.in);
        String comando;
        String[] partes;
        
        do{
            comando = sc.nextLine();
            partes = comando.split(" ");
            if(partes[0].equals("i")){
                GLista.adicionarAresta(partes[1], partes[2]);
                GMatriz.adicionarAresta(partes[1], partes[2]);
                GPMatriz.adicionarAresta(partes[1], partes[2]);
            }
            if(partes[0].equals("r")){
                if(partes.length == 2){
                    GLista.removerVertice(partes[1]);
                    GMatriz.removerVertice(partes[1]);
                    GPMatriz.removerVertice(partes[1]);
                }
                else{
                    GLista.removerAresta(partes[1], partes[2]);
                    GMatriz.removerAresta(partes[1], partes[2]);
                    GPMatriz.removerAresta(partes[1], partes[2]);
                }
            }
            if(partes[0].equals("p")){
                System.out.print(GLista.toString());
                System.out.print(GMatriz.toString());
                System.out.print(GPMatriz.toString());
            }

        }while(!comando.contentEquals(""));
    }

    
}