/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObligatorioAlgoritmos2;

/**
 *
 * @author alumnoFI
 */
public class GrafoLista {

	public int size;
	public int cantNodos;
	public ListaAdyacencia[] listaAdy;
	public NodoPunto[] nodosUsados;

	public int[] camino;

	// constructor
	public GrafoLista(int n) {
		this.size = 0;
		this.cantNodos = n;
		this.listaAdy = new ListaAdyacencia[n + 1];

		for (int i = 0; i < n + 1; i++) {
			this.listaAdy[i] = new ListaAdyacencia();
		}
		this.nodosUsados = new NodoPunto[n + 1];
	}

	public void AgregarVertice(int n, NodoPunto nodo) {
		this.nodosUsados[n] = nodo;
		this.size++;
	}

	public void AgregarArista(int o, int d, int p) {
		this.listaAdy[o].insertarInicio(d, p); // metodo a crear
		this.listaAdy[d].insertarInicio(o, p);
		// si fuera bidereccionar hay que agregar tambien para el otro lado
	}

	public void EliminarVertice(int n) {
		this.nodosUsados[n].setUsado(false);
		this.size--;
		this.listaAdy[n] = new ListaAdyacencia();
		for (int i = 0; i < listaAdy.length; i++) {
			this.listaAdy[i].eliminar(n);
		}
	}

	public boolean EsVacio() {
		return this.size == 0;
	}

	public ListaAdyacencia VerticesAdyacents(int v) {
		return this.listaAdy[v];
	}

	public boolean sonAdyacentes(int a, int b) {
		return this.listaAdy[a].existe(b);
	}

	public NodoPunto ExisteVertice(int n) {
		return this.nodosUsados[n];
	}

	public NodoPunto ObtenerNodoPorCoord(double coordX, double coordY) {

		NodoPunto nodo = null;
		int i = 1;

		while (nodo == null && i <= size) {

			if (nodosUsados[i].getCoordX() == coordX && nodosUsados[i].getCoordY() == coordY) {

				nodo = nodosUsados[i];
				return nodo;
			}

			i++;
		}

		return nodo;
	}

	public int ObtenerPosicionPorCoordenadas(double coordX, double coordY) {

		int i = 1;

		while (i < this.size) {

			if (this.nodosUsados[i].getCoordX() == coordX && this.nodosUsados[i].getCoordY() == coordY) {
				return i;
			}

			i++;
		}

		if (this.nodosUsados[i].getCoordX() == coordX && this.nodosUsados[i].getCoordY() == coordY)
			return i;

		return -1;

	}

	public int[] caminoMinimo(int o) {
		// estructuras auxiliares
		int[] camino = new int[this.cantNodos + 1];
		boolean[] visitado = new boolean[this.cantNodos + 1];
		int[] costo = new int[this.cantNodos + 1];
		for (int i = 1; i <= this.cantNodos; i++) {
			if (i != o)
				if (this.sonAdyacentes(i, o)) {
					costo[i] = this.listaAdy[i].obtenerNodo(o).peso;
					camino[i] = o;
				} else {
					costo[i] = Integer.MAX_VALUE;
				}
		}
		visitado[o] = true;

		for (int i = 1; i < this.cantNodos; i++) {
			// vertice con la distancia mas corta no visitado
			int u = distanciaMasCorta(costo, visitado);
			visitado[u] = true;

			for (int j = 1; j <= this.cantNodos; j++) {
				if (this.sonAdyacentes(u, j) && !visitado[j]) {

					if (this.listaAdy[u].obtenerNodo(j).peso + costo[u] < costo[j]) {

						costo[j] = this.listaAdy[u].obtenerNodo(j).peso + costo[u];
						camino[j] = u;
					}
				}
			}
		}
		this.camino = camino; // actualizo array de caminos
		return costo;

	}

	private int distanciaMasCorta(int[] costos, boolean[] marcados) {
		int minimo = Integer.MAX_VALUE;
		int k = 0;
		for (int i = 1; i < costos.length; i++) {
			if (!marcados[i] && costos[i] < minimo) {
				minimo = costos[i];
				k = i;
			}
		}
		return k;
	}

	public int[] guardarCaminoMinimo(int destino, int origen, int previo[], int[] camino, int i) { // ver
		if (destino != 0 && (destino != origen)) {
			camino = guardarCaminoMinimo(previo[destino], origen, previo, camino, i + 1);
			camino[i] = (previo[destino]);
		}
		return camino;
	}

	public String imprimirCaminoMinimo(int[] camino, int destino) {
		int actual = 0;
		String mensaje = "";
		for (int i = camino.length - 1; i >= 0; i--) {
			actual = camino[i];
			if (actual != 0) {
				mensaje += (int) nodosUsados[actual].getCoordX() + ";" + (int) nodosUsados[actual].getCoordY() + "|";
			}
		}

		if (mensaje == "") {
			mensaje = "El monopatin se encuentra en la ubicacion del usuario.";
			return mensaje;
		}
		mensaje += (int) nodosUsados[destino].getCoordX() + ";" + (int) nodosUsados[destino].getCoordY() + "|";
		return mensaje;
	}

}