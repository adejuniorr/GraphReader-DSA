package grafos;

import java.util.Arrays;
import java.util.Scanner;

public class GraphReader {

    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);

        System.out.print("Insira o número de vértices do grafo: ");
        int numVertices = ler.nextInt();

        int[][] inputAdjencyMatrix = new int[numVertices][numVertices];

        System.out.print("Insira os elementos da matriz de adjacência (linha a linha):\n");
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                inputAdjencyMatrix[i][j] = ler.nextInt();
            }
        }

        printAdjencyMatrix(inputAdjencyMatrix);

        checkGraphType(inputAdjencyMatrix);

        ler.close();
    }

    public static void printAdjencyMatrix(int[][] adjencyMatrix) {
        System.out.println("Matriz de adjacência inserida:");
        for (int i = 0; i < adjencyMatrix.length; i++) {
            for (int j = 0; j < adjencyMatrix.length; j++) {
                System.out.print(adjencyMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void checkGraphType(int[][] adjencyMatrix) {
        // Arrays to store degrees of each vertex:
        int[] degrees = new int[adjencyMatrix.length];

        // Boolean flags to check the type of graph:
        boolean isSimple = false;
        boolean isComplete = true;
        boolean isDirected = false;
        boolean isPseudoGraph = false;
        boolean hasLoop = false;
        boolean isConnected = true;
        boolean isMultiGraph = false;
        boolean isDirectedMultiGraph = false;

        // Double loop to iterate each element of the adjency matrix
        for (int i = 0; i < adjencyMatrix.length; i++) { // Loop for lines

            for (int j = 0; j < adjencyMatrix.length; j++) { // Loop for columns

                if (i == j && adjencyMatrix[i][j] == 1) { // If the element is in the diagonal and is equal to 1
                    hasLoop = true; // The graph has a loop
                } // end if hasLoop

                if (i != j) { // If the element is not in the diagonal and is equal to 1 (calculate degrees of a simples graph)            
                    degrees[i] += adjencyMatrix[i][j]; // Summing the values of each line
                } // end if degrees

                if (i != j && adjencyMatrix[i][j] == 0) { // If there is a vertex that is not connected to another vertex
                    isComplete = false; // The graph is not complete
                } // end if isComplete

                if (hasLoop || adjencyMatrix[i][j] > 1) { // If the graph has a loop or the sum of the line is greater than 1 (parallel edges)
                    isPseudoGraph = true; // The graph is a pseudo graph
                } // end if isPseudoGraph

                if (adjencyMatrix[i][j] != adjencyMatrix[j][i]) { // If the a[i][j] element is not equal to a[j][i] element (the matrix is not symmetric)
                    isDirected = true; // The graph is directed (one of the elements is sending at least one extra edge to another element)
                } // end if isDirected

                if (!hasLoop && adjencyMatrix[i][j] < 2) { // If the graph has no loop and each element is equal to 0 or 1 (the graph has no parallel edges)
                    isSimple = true; // The graph is complete
                } // end if isSimple

                if (!hasLoop || adjencyMatrix[i][j] > 1) { // If the graph hasn't loop and has at least one parallel edge
                    isMultiGraph = true; // The graph is a multi graph
                }

                if (adjencyMatrix[i][j] != adjencyMatrix[j][i] && (adjencyMatrix[i][j] > 1 || hasLoop)) { // If the a[i][j] element is not equal to a[j][i] element (the matrix is not symmetric) and has at least one parallel edge
                    isDirectedMultiGraph = true; // The graph is a directed multi graph
                } // end if isDirectedMultiGraph

            } // end loop for columns
        } // end loop for lines

        for (int i = 1 ; i < adjencyMatrix.length ; i++) {
            if (!checkGraphConnectivity(adjencyMatrix, 0, i, new boolean[adjencyMatrix.length])) {
                isConnected = false;
                break;
            }
        }

        System.out.println("\nNº de vértices: " + adjencyMatrix.length + 
                           (isDirected ? "\nGrafo direcionado:" + checkDirectedDegrees(adjencyMatrix) : "\nGrafo não direcionado:\n\tGrau de cada vértice: " + Arrays.toString(degrees)) +
                           "\n\nPropriedades do Grafo:" + 
                           "\nSimples: " + isSimple + 
                           "\nCompleto: " + isComplete + 
                           "\nGrafo com loop/laço: " + hasLoop + 
                           "\nPseudografo: " + isPseudoGraph +  
                           "\nConexo: " + isConnected +
                           "\nMultigrafo: " + isMultiGraph + 
                           "\nMultigrafo direcionado: " + isDirectedMultiGraph);
    }

    public static boolean checkGraphConnectivity(int[][] adjencyMatrix, int startV, int targetV, boolean[] visited) {
        if (startV == targetV) {
            return true;
        }
        visited[startV] = true;

        for (int i = 0 ; i < adjencyMatrix.length ; i++) {
            if (adjencyMatrix[startV][i] >= 1 && !visited[i]) {
                if (checkGraphConnectivity(adjencyMatrix, i, targetV, visited)) {
                    return true;
                }
            }
        }
        visited[startV] = false;

        return false;
    }

    public static String checkDirectedDegrees(int[][] adjencyMatrix) {
        int[] degreesIn = new int[adjencyMatrix.length];
        int[] degreesOut = new int[adjencyMatrix.length];

        for (int i = 0 ; i < adjencyMatrix.length ; i++) {
            for (int j = 0 ; j < adjencyMatrix.length ; j++) {
                if (adjencyMatrix[i][j] == 1) {
                    degreesIn[j]++;
                    degreesOut[i]++;
                }
            }
        }

        return "\n\tGrau de entrada de cada vértice: " + Arrays.toString(degreesIn) +
               "\n\tGray de saída de cada vértice: " + Arrays.toString(degreesOut);
    }
}