package model;

import java.util.ArrayList;
import java.util.List;

public class Grafo {

	private boolean orientado;
	private List<Vertice> vertices;
	private List<Aresta> arestas;

	public Grafo() {
		// TODO Auto-generated constructor stub
		vertices = new ArrayList<Vertice>();
		arestas = new ArrayList<Aresta>();
	}

	public List<Vertice> getVertices() {
		return vertices;
	}

	public List<Aresta> getArestas() {
		return arestas;
	}

	public void addVertice(Vertice v) {
		if (!vertices.contains(v))
			vertices.add(v);
		v.setGrafo(this);
	}

	public void addAresta(Vertice v1, Vertice v2, int peso, String nome) {
		if (contemVertice(v1) && contemVertice(v2)) {
			Aresta a = new Aresta(v1, v2, peso, nome);
			arestas.add(a);
			v1.addAresta(a);
			if (!orientado)
				v2.addAresta(a);
		}
	}

	public boolean contemVertice(Vertice v) {
		return vertices.contains(v);
	}

	public boolean contemAresta(Aresta a) {
		return arestas.contains(a);
	}

	public boolean isOrientado() {
		return orientado;
	}

	public void setOrientado(boolean o) {
		this.orientado = o;
	}
}
