package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dijkstra extends Algoritmo {
  private static Integer INFINITE = Integer.MAX_VALUE;

  private List<Vertice> shortestWay, VisitedNeighbors, notVisited;
  private Vertice vertexPath, currentVertex, neighbors;

  public Dijkstra() {
    this.shortestWay = new ArrayList<Vertice>();
    this.VisitedNeighbors = new ArrayList<Vertice>();
    this.notVisited = new ArrayList<Vertice>();
    this.vertexPath = new Vertice();
    this.currentVertex = new Vertice();
    this.neighbors = new Vertice();
  }

  public List<Vertice> getVisitedNeighbors() {
    return this.VisitedNeighbors;
  }

  public List<Vertice> getShortestWay() {
    return this.shortestWay;
  }

  @Override
  public void executar(Grafo grafo, Vertice origin, Vertice destiny) {
    resultado = new ResultadoAlgoritmo(origin, destiny);
    shortestWay.add(origin);

    // Colocando a distancias iniciais
    for (int i = 0; i < grafo.getVertices().size(); i++) {
      if (grafo.getVertices().get(i).getNome()
          .equals(origin.getNome())) {
        grafo.getVertices().get(i).setDistancia(0);
      } else {
        grafo.getVertices().get(i).setDistancia(INFINITE);
      }
      this.notVisited.add(grafo.getVertices().get(i));
    }
    Collections.sort(notVisited);

    // resultado.atualizar(this.notVisited.get(0), null);
    // O algoritmo continua ate que todos os vertices sejam visitados
    while (!this.notVisited.isEmpty()) {
      currentVertex = this.notVisited.get(0);
      super.aguardar();
      for (int i = 0; i < currentVertex.getArestas().size(); i++) {
        if (neighbors != null && neighbors == destiny)
          break;
        resultado.atualizar(currentVertex, currentVertex.getArestas().get(i));
        super.aguardar();
        neighbors = currentVertex.getArestas().get(i).getV2();
        if (!VisitedNeighbors.contains(neighbors)) {
          if (neighbors.getDistancia() > (currentVertex.getDistancia() + currentVertex.getArestas().get(i).getPeso())) {
            resultado.atualizar(neighbors, currentVertex.getArestas().get(i));
            super.aguardar();
            neighbors.setDistancia(currentVertex.getDistancia() + currentVertex.getArestas().get(i).getPeso());
            neighbors.setPredecessor(currentVertex);
            if (neighbors == destiny) {
              shortestWay.clear();
              vertexPath = neighbors;
              shortestWay.add(neighbors);
              while (vertexPath.getPredecessor() != null) {
                shortestWay.add(vertexPath.getPredecessor());
                vertexPath = vertexPath.getPredecessor();
              }
              // Ordena a lista do menor caminho, para que ele
              // seja exibido da origem ao destino.
              Collections.sort(shortestWay);
              break;
            }
          }
        }
      }
      VisitedNeighbors.add(currentVertex);
      this.notVisited.remove(currentVertex);
      Collections.sort(notVisited);
    }
    System.out.println(this.getShortestWay());
    resultado.finalizar();
    super.aguardar();
  }

  @Override
  public String toString() {
    // TODO Auto-generated method stub
    return "Djikstra";
  }
}