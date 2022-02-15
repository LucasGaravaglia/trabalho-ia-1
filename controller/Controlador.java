package controller;

import java.io.File;
import java.util.List;

import model.Algoritmo;
import model.BuscaEmLargura;
import model.BuscaEmProfundidade;
import model.BuscaEmProfundidadeComRetrocesso;
import model.FazNada;
import model.GeradorDeImagemDeGrafo;
import model.Grafo;
import model.ReadGrafo;
import model.ResultadoAlgoritmo;
import model.TratadorDeResulltado;
import model.Vertice;
import view.TelaPrincipal;

public class Controlador implements TratadorDeResulltado {

	private ReadGrafo leitor = new ReadGrafo();
	private List<Algoritmo> algoritmos;
	private TelaPrincipal tela;
	private Grafo grafo;
	private GeradorDeImagemDeGrafo geradorDeImagem;

	public Controlador(List<Algoritmo> algoritmos) {
		this.algoritmos = algoritmos;
		algoritmos.forEach(a -> a.setTratadorDeResulltado(this));
	}

	private void exibirTela() {
		// TODO Auto-generated method stub
		if (tela == null)
			tela = new TelaPrincipal(this);

		tela.setVisible(true);
	}

	public void lerArquivo(File arquivo) {
		this.grafo = leitor.readFile(arquivo.getAbsolutePath());
		if (this.grafo == null) {
			tela.exibirMensagem("O arquivo não pode ser lido.");
			geradorDeImagem = null;
		} else {
			tela.atualizarVertices(grafo.getVertices());
			geradorDeImagem = new GeradorDeImagemDeGrafo(grafo);
			tela.setImagem(geradorDeImagem.gerarImagem(null, null));
		}
	}

	public List<Algoritmo> getAlgoritmos() {
		return this.algoritmos;
	}

	public void executar(String algoritmo, Vertice o, Vertice d) {
		new Thread(() -> {
			// Algoritmo alg = buscarAlgoritmoPorNome(algoritmo);
			buscarAlgoritmoPorNome(algoritmo).executar(grafo, o, d);
		}).start();
	}

	private Algoritmo buscarAlgoritmoPorNome(String nome) {
		for (Algoritmo a : algoritmos)
			if (a.toString().equals(nome))
				return a;
		return null;
	}

	public void avancar(String algoritmo) {
		Algoritmo alg = buscarAlgoritmoPorNome(algoritmo);
		try {
			synchronized (alg) {
				alg.notifyAll();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Controlador(List.of(new BuscaEmLargura(), new BuscaEmProfundidade(), new BuscaEmProfundidadeComRetrocesso(),
				new FazNada())).exibirTela();
	}

	@Override
	public void tratar(ResultadoAlgoritmo resultado) {
		// TODO Auto-generated method stub
		tela.setImagem(geradorDeImagem.gerarImagem(
				resultado.getVerticesPercorridos().get(resultado.getVerticesPercorridos().size() - 1),
				resultado.getArestasPercorridas()));
	}

}
