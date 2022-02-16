package model;

import static guru.nidi.graphviz.model.Factory.to;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;

import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.attribute.Label;
import guru.nidi.graphviz.attribute.Style;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Factory;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.Link;
import guru.nidi.graphviz.model.Node;

public class GeradorDeImagemDeGrafo {
  Grafo grafo;

  public GeradorDeImagemDeGrafo(Grafo g) {
    this.grafo = g;
  }

  public BufferedImage gerarImagem(Vertice atual, List<Aresta> ad) {
    Graph g = Factory.graph();
    if (this.grafo.isOrientado())
      g = g.directed();

    HashMap<Vertice, Node> nodes = new HashMap<Vertice, Node>();
    for (Vertice v : this.grafo.getVertices()) {
      Node node = Factory.node(v.getNome());
      if (v == atual)
        node = Factory.node(Label.htmlLines("<font color=\"white\">" + v.getNome() + "</font>"))
            .with(Style.FILLED, Color.BLUE);
      nodes.put(v, node);
      g = g.with(node);
    }

    for (Aresta a : grafo.getArestas()) {
      Node n1 = nodes.get(a.getV1());
      Node n2 = nodes.get(a.getV2());
      Link aresta = to(n2).with(Label.htmlLines(a.getNome(), "<font color=\"blue\">" + a.getPeso() + "</font>"));
      if ((ad != null) && ad.contains(a))
        aresta = aresta.with(Color.RED);

      g = g.with(n1.link(aresta));
    }

    try {
      return Graphviz.fromGraph(g).height(600).render(Format.PNG).toImage();
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

}
