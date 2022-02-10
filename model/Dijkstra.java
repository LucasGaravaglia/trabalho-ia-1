package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dijkstra {
  private static Integer INFINITO = Integer.MAX_VALUE;

  List<Vertice> menorCaminho, vizinhosVisitados, naoVisitados;
  Vertice verticeCaminho, atual, vizinho;

  public Dijkstra() {
    this.menorCaminho = new ArrayList<Vertice>();
    this.vizinhosVisitados = new ArrayList<Vertice>();
    this.naoVisitados = new ArrayList<Vertice>();
    this.verticeCaminho = new Vertice();
    this.atual = new Vertice();
    this.vizinho = new Vertice();
  }

  public List<Vertice> execute(Grafo grafo, Vertice v1, Vertice v2) {
    menorCaminho.add(v1);

    // Colocando a distancias iniciais
    for (int i = 0; i < grafo.getVertices().size(); i++) {
      if (grafo.getVertices().get(i).getNome()
          .equals(v1.getNome())) {
        grafo.getVertices().get(i).setDistancia(0);
      } else {
        grafo.getVertices().get(i).setDistancia(INFINITO);
      }
      this.naoVisitados.add(grafo.getVertices().get(i));
    }
    Collections.sort(naoVisitados);

    // O algoritmo continua ate que todos os vertices sejam visitados
    while (!this.naoVisitados.isEmpty()) {
      atual = this.naoVisitados.get(0);
      for (int i = 0; i < atual.getArestas().size(); i++) {
        vizinho = atual.getArestas().get(i).getV2();
        if (!vizinhosVisitados.contains(vizinho)) {
          if (vizinho.getDistancia() > (atual.getDistancia() + atual.getArestas().get(i).getPeso())) {
            vizinho.setDistancia(atual.getDistancia() + atual.getArestas().get(i).getPeso());
            vizinho.setPai(atual);
            if (vizinho == v2) {
              menorCaminho.clear();
              verticeCaminho = vizinho;
              menorCaminho.add(vizinho);
              while (verticeCaminho.getPai() != null) {
                menorCaminho.add(verticeCaminho.getPai());
                verticeCaminho = verticeCaminho.getPai();
              }
              // Ordena a lista do menor caminho, para que ele
              // seja exibido da origem ao destino.
              Collections.sort(menorCaminho);
            }
          }
        }
      }
      vizinhosVisitados.add(atual);
      this.naoVisitados.remove(atual);
      Collections.sort(naoVisitados);
    }
    return menorCaminho;
  }
}