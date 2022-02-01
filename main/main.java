package main;

import java.io.File;

import model.*;

public class main {
  public static void main(String[] args) {
    ReadGrafo r = new ReadGrafo();
    // File saida = new File("/home/lucas/saida");
    Grafo g = r.readFile("/home/lucas/entrada.txt");
    // GeradorDeImagemDeGrafo gr = new GeradorDeImagemDeGrafo(g);
    // gr.gerarImagem(saida, g.getArestas());
    System.out.println("teste");

  }
}
