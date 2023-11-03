# GraphReader

GraphReader is a Java program for reading and analyzing graphs represented as adjacency matrices. You can use this program to input a graph's adjacency matrix and determine various properties of the graph.

## Features

- Input the number of vertices and the adjacency matrix of a graph.
- Determine the type of graph based on properties like simplicity, completeness, directedness, and more.
- Check for loops, connectivity, and multigraph characteristics.
- Handle both simple and directed graphs.

## Usage

1. Compile the program using Java:
2. Run the program:
3. Follow the prompts to input the number of vertices and the adjacency matrix.
4. The program will display various properties of the graph.

## Graph Properties

- **Simples**: Indicates if the graph is simple (no parallel edges or loops).
- **Completo**: Indicates if the graph is complete (all vertices are connected to all other vertices).
- **Grafo com loop/laço**: Indicates if the graph contains loops.
- **Pseudografo**: Indicates if the graph is a pseudograph.
- **Conexo**: Indicates if the graph is connected.
- **Multigrafo**: Indicates if the graph is a multigraph.
- **Multigrafo direcionado**: Indicates if the graph is a directed multigraph.

## Example

Here's an example of how to use the program:

Insira o número de vértices do grafo: 4
Insira os elementos da matriz de adjacência (linha a linha):
0 1 0 1
1 0 1 0
0 1 0 0
0 0 0 0

Matriz de adjacência inserida:
0 1 0 1
1 0 1 0
0 1 0 0
0 0 0 0

Nº de vértices: 4
Grafo não direcionado:
Grau de cada vértice: [2, 2, 1, 0]

Propriedades do Grafo:
Simples: true
Completo: false
Grafo com loop/laço: false
Pseudografo: false
Conexo: true
Multigrafo: false
Multigrafo direcionado: false


## License

This project is open-source and available under the [MIT License](LICENSE).

Feel free to contribute or report issues on GitHub.

---

**Note**: This README provides a basic overview of the GraphReader program and its usage. You can expand it further with additional details, examples, and instructions as needed for your project.
