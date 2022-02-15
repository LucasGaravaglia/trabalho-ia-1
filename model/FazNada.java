package model;

import java.util.Set;

public class FazNada extends Algoritmo {

	@Override
	public void executar(Grafo grafo, Vertice origem, Vertice destino) {
		// TODO Auto-generated method stub
		System.out.println("inicio");
		aguardar();
		System.out.println("fim");
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Faz nada";
	}

}
