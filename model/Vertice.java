package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Vertice implements Comparable<Vertice> {
	private String nome;
	private List<Aresta> arestas;
	private Grafo grafo;
	private Integer distancia;
	private Vertice pai;

	public void setPai(Vertice pai) {
		this.pai = pai;
	}

	public Vertice getPai() {
		return this.pai;
	}

	public void setDistancia(int distance) {
		this.distancia = distance;
	}

	public Integer getDistancia() {
		return this.distancia;
	}

	public Grafo getGrafo() {
		return grafo;
	}

	public int compareTo(Vertice vertice) {
		if (this.getDistancia() < vertice.getDistancia())
			return -1;
		else if (this.getDistancia() == vertice.getDistancia())
			return 0;

		return 1;

	}

	public void setGrafo(Grafo grafo) {
		this.grafo = grafo;
	}

	public void setArestas(List<Aresta> arestas) {
		this.arestas = arestas;
	}

	public Vertice() {
		arestas = new ArrayList<Aresta>();
	}

	public Vertice(String name) {
		this.nome = name;
		arestas = new ArrayList<Aresta>();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Aresta> getArestas() {
		return arestas;
	}

	public Aresta getAresta(Vertice adj) {
		for (Aresta a : arestas)
			if (a.getV2() == adj || !grafo.isOrientado() && a.getV1() == adj)
				return a;
		return null;
	}

	public void addAresta(Aresta a) {
		if (a.getV1() == this || a.getV2() == this && !arestas.contains(a))
			arestas.add(a);
	}

	public List<Vertice> getAdjacentes() {
		List<Vertice> adjacentes = new ArrayList<Vertice>();
		for (Aresta a : arestas) {
			Vertice adj = this == a.getV1() ? a.getV2() : a.getV1();
			if (!adjacentes.contains(adj))
				adjacentes.add(adj);
		}
		return adjacentes;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return nome;
	}

}
