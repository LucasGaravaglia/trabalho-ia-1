package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class ReadGrafo {

  public int searchVertice(List<Vertice> vertices, String name) {
    for (int i = 0; i < vertices.size(); i++) {
      if (vertices.get(i).getNome().compareTo(name) == 0)
        return i;
    }
    return -1;
  }

  public Grafo readFile(String path) {
    Grafo grafo = new Grafo();
    try {
      FileReader arq = new FileReader(path);
      BufferedReader reader = new BufferedReader(arq);
      String line;
      String origem, destino;
      Integer peso, posOrigem, posDestino;
      try {
        line = reader.readLine();
        while (line != null) {
          origem = line.split("\\(")[1].split("\\,")[0];
          destino = line.split("\\(")[1].split("\\,")[1];
          peso = Integer.parseInt(line.split("\\(")[1].split("\\,")[2].split("\\)")[0]);

          posOrigem = this.searchVertice(grafo.getVertices(), origem);
          posDestino = this.searchVertice(grafo.getVertices(), destino);
          if (posOrigem == -1 && posDestino == -1) {
            grafo.addVertice(new Vertice(origem));
            grafo.addVertice(new Vertice(destino));
            grafo.addAresta(grafo.getVertices().get(grafo.getVertices().size() - 2),
                grafo.getVertices().get(grafo.getVertices().size() - 1), peso.intValue(),
                "");

            grafo.getVertices().get(grafo.getVertices().size() - 2)
                .addAresta(grafo.getArestas().get(grafo.getArestas().size() - 1));

          } else if (posOrigem == -1 && posDestino != -1) {
            grafo.addVertice(new Vertice(origem));
            grafo.addAresta(grafo.getVertices().get(grafo.getVertices().size()-1),
                grafo.getVertices().get(posDestino), peso, "");

            grafo.getVertices().get(grafo.getVertices().size()-1)
                .addAresta(grafo.getArestas().get(grafo.getArestas().size()-1));
          } else if (posOrigem != -1 && posDestino == -1) {
            grafo.addVertice(new Vertice(destino));
            grafo.addAresta(grafo.getVertices().get(posOrigem),
                grafo.getVertices().get(grafo.getVertices().size() - 1), peso, "");

            grafo.getVertices().get(posOrigem)
                .addAresta(grafo.getArestas().get(grafo.getArestas().size() - 1));
          } else {
            grafo.addAresta(grafo.getVertices().get(posOrigem),
                grafo.getVertices().get(posDestino), peso, "");

            grafo.getVertices().get(posOrigem)
                .addAresta(grafo.getArestas().get(grafo.getArestas().size() - 1));
          }
          line = reader.readLine();
        }
        reader.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    } catch (

    Exception e) {
      e.printStackTrace();
    }
    return grafo;
  }
}