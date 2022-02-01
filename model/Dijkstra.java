package model;

import java.util.ArrayList;

public class Dijkstra {
  private static final int INFINITO = 10000000;

  private Grafo grafo;

  public Dijkstra(Grafo grafo) {
    this.grafo = grafo;
  }

  public void run(String Origem, String Destino) {
    ArrayList<Vertice> dist = new ArrayList<Vertice>();
    ArrayList<Vertice> visitados = new ArrayList<Vertice>();
    ArrayList<Aresta> prioridade = new ArrayList<Aresta>();

  }

}
