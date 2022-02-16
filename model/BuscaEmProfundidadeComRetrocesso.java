package model;

public class BuscaEmProfundidadeComRetrocesso extends Algoritmo {

	@Override
	public void executar(Grafo grafo, Vertice o, Vertice d) {
		// TODO Auto-generated method stub
		guardarTempo();
		resultado = new ResultadoAlgoritmo(o, d);

		if (o == d) {
			resultado.finalizar();
			super.aguardar();
			return;
		}

		super.aguardar();
		guardarTempo();
		
		buscaEmProfundidade(o, d);
		resultado.finalizar();
		super.aguardar();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Profundidade com retrocesso";
	}

	private void buscaEmProfundidade(Vertice o, Vertice d) {
		if (o == d)
			return;
		for (Aresta a : o.getArestas()) {
			if (resultado.getVerticesPercorridos().contains(d))
				return;
			if (!resultado.getArestasPercorridas().contains(a)) {
				if (o == a.getV1() && !resultado.getVerticesPercorridos().contains(a.getV2())) {
					resultado.atualizar(a.getV2(), a);
					super.aguardar();
					guardarTempo();
					buscaEmProfundidade(a.getV2(), d);
				} /*else if (o == a.getV2() && !resultado.getVerticesPercorridos().contains(a.getV1())) {
					resultado.atualizar(a.getV1(), a);
					buscaEmProfundidade(a.getV1(), d);
					super.aguardar();
				}*/
			}
		}
	}

}
