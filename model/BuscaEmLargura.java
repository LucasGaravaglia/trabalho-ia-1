package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;

public class BuscaEmLargura extends Algoritmo {

	@Override
	public void executar(Grafo grafo, Vertice o, Vertice d) {
		// TODO Auto-generated method stub
		Queue<Vertice> fila = new ArrayBlockingQueue<Vertice>(grafo.getVertices().size());

		resultado = new ResultadoAlgoritmo(o, d);

		if (o == d) {
			resultado.finalizar();
			super.aguardar();
			return;
		}
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
