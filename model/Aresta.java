package model;

public class Aresta implements Comparable<Aresta> {
	private Vertice v1;
	private Vertice v2;
	private int peso;
	private String nome;

	public Aresta(Vertice v1, Vertice v2, int peso, String nome) {
		super();
		this.v1 = v1;
		this.v2 = v2;
		this.peso = peso;
		this.nome = nome;
	}

	public Vertice getV1() {
		return v1;
	}

	public void setV1(Vertice v1) {
		this.v1 = v1;
	}

	public Vertice getV2() {
		return v2;
	}

	public void setV2(Vertice v2) {
		this.v2 = v2;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj.getClass() == this.getClass()) {
			Aresta a = (Aresta) obj;
			if (this.v1 == a.v1 && this.v2 == a.v2)
				return true;
		}
		return false;

	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return v1.getNome() + " - " + v2.getNome();
	}

	@Override
	public int compareTo(Aresta a) {
		// TODO Auto-generated method stub
		return this.peso - a.peso;
	}
}
