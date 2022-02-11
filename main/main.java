package main;

import java.io.File;
import java.util.List;

import model.*;

public class main {
  public static void main(String[] args) {
    ReadGrafo r = new ReadGrafo();
    Dijkstra dj = new Dijkstra();
    File saida = new File("/home/lucas/saida");
    Grafo g = r.readFile("/home/lucas/entrada1.txt");
    System.out.println(g.getVertices().get(9) + "" + g.getVertices().get(0));
    // for (Vertice v : g.getVertices()) {
    // System.out.println(v.getNome());
    // }
    List<Vertice> v = dj.execute(g, g.getVertices().get(0), g.getVertices().get(9));
    GeradorDeImagemDeGrafo gr = new GeradorDeImagemDeGrafo(g);
    gr.gerarImagem(saida, g.getArestas());
    // for (Vertice va : v) {
    System.out.println(v);
    // }

  }
}
