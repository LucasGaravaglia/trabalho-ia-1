package model;

import controller.*;

public abstract class Algoritmo {

	private boolean executarPassoAPasso;
	protected ResultadoAlgoritmo resultado;
	protected TratadorDeResulltado tratadorDeResulltado;

	public void setTratadorDeResulltado(TratadorDeResulltado tratadorDeResulltado) {
		this.tratadorDeResulltado = tratadorDeResulltado;
	}

	public abstract void executar(Grafo grafo, Vertice origem, Vertice destino);

	public boolean isExecutarPassoAPasso() {
		return executarPassoAPasso;
	}

	public void setExecutarPassoAPasso(boolean executarPassoAPasso) {
		this.executarPassoAPasso = executarPassoAPasso;
	}

	public final ResultadoAlgoritmo getResultado() {
		return resultado;
	}

	protected final void aguardar() {
		synchronized (this) {
			try {
				if (executarPassoAPasso) {
					if (tratadorDeResulltado != null)
						tratadorDeResulltado.tratar(resultado);
					wait();
				} else {
					if (resultado.isFinalizado())
						tratadorDeResulltado.tratar(resultado);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
