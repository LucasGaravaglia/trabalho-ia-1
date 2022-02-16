package model;

import java.util.ArrayList;
import java.util.List;

public class ResultadoAlgoritmo {
	private List<Vertice> verticesPercorridos;
	private List<Aresta> arestasPercorridas;
	private boolean finalizado;
	private Vertice destino;
	private String informacoes;
	private long tempoGasto;

	public void setInformacoes(String informacoes) {
		this.informacoes = informacoes;
	}

	public String getInformacoes() {
		return this.informacoes;
	}

	public List<Vertice> getVerticesPercorridos() {
		return verticesPercorridos;
	}

	public void setVerticesPercorridos(List<Vertice> verticesPercorridos) {
		this.verticesPercorridos = verticesPercorridos;
	}

	public List<Aresta> getArestasPercorridas() {
		return arestasPercorridas;
	}

	public void setArestasPercorridas(List<Aresta> arestasPercorridas) {
		this.arestasPercorridas = arestasPercorridas;
	}

	public ResultadoAlgoritmo() {
		// TODO Auto-generated constructor stub
		this.verticesPercorridos = new ArrayList<>();
		this.arestasPercorridas = new ArrayList<>();
		this.informacoes = "";
	}

	public ResultadoAlgoritmo(Vertice verticeInicial, Vertice verticeAlvo) {
		// TODO Auto-generated constructor stub
		this.verticesPercorridos = new ArrayList<>();
		this.arestasPercorridas = new ArrayList<>();

		this.verticesPercorridos.add(verticeInicial);
		this.destino = verticeAlvo;
	}

	public int getDistancia() {
		int distancia = 0;
		for (Aresta a : arestasPercorridas)
			distancia += a.getPeso();
		return distancia;
	}

	public boolean isDestinoAlcancado() {
		return verticesPercorridos.contains(destino);
	}

	public int getQuantidadeVerticesPercorridos() {
		return verticesPercorridos.size();
	}

	public void atualizar(Vertice v, Aresta a) {
		this.verticesPercorridos.add(v);
		this.arestasPercorridas.add(a);
	}

	public void finalizar() {
		this.finalizado = true;
	}

	public boolean isFinalizado() {
		return this.finalizado;
	}
	
	public void somarTempo(long milissegundos)
	{
		this.tempoGasto += milissegundos;
	}
	
	public long getTempoGasto()
	{
		return this.tempoGasto;
	}
}
