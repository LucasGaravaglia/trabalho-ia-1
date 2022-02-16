package model;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class BuscaEmLargura extends Algoritmo {

	@Override
	public void executar(Grafo grafo, Vertice o, Vertice d) {
		// TODO Auto-generated method stub
		guardarTempo();
		Queue<Vertice> fila = new ArrayBlockingQueue<Vertice>(grafo.getVertices().size());

		resultado = new ResultadoAlgoritmo(o, d);

		if (o == d) {
			resultado.finalizar();
			super.aguardar();
			return;
		}
		
		super.aguardar();
		guardarTempo();
		
		fila.offer(o);
		while (!fila.isEmpty()) {
			Vertice v = fila.poll();

			for (Aresta a : v.getArestas()) {
				Vertice adj = v == a.getV1() ? a.getV2() : a.getV1();
				if (!resultado.getVerticesPercorridos().contains(adj)) {
					resultado.atualizar(adj, a);
					fila.offer(adj);
					if (adj == d) {
						resultado.finalizar();
						super.aguardar();
						return;
					}
					super.aguardar();
					guardarTempo();
				}
			}

		}
		resultado.finalizar();
		super.aguardar();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Busca em largura";
	}

}
