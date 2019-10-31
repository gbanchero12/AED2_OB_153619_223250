/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObligatorioAlgoritmos2;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author alumnoFI
 */
public class GrafoLista {

    public int size;
    public int cantNodos;
    public ListaAdyacencia[] listaAdy;
    public NodoPunto[] nodosUsados;

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

        while (nodo == null && i < size) {

            if (nodosUsados[i].getCoordX() == coordX && nodosUsados[i].getCoordY() == coordY) {

                nodo = nodosUsados[i];
                return nodo;
            }

            i++;
        }

        return nodo;
        // evalua el ultimo caso?
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

    // devuelve el costo total para ir de un vertice a otro
    // para implementar en el TAD Grafo
    public ArrayList<int[]> costoCaminoMinimo(int o, int d) {
        // estructuras auxiliares
        int[] camino = new int[this.cantNodos + 1];
        boolean[] visitado = new boolean[this.cantNodos + 1];
        int[] costo = new int[this.cantNodos + 1];
        for (int i = 1; i <= this.cantNodos; i++) {
            if (i != o)
                if (this.sonAdyacentes(i, o)) {
                    costo[i] = this.listaAdy[i].obtenerNodoPorDestino(o).peso;
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

                    if (this.listaAdy[u].obtenerNodoPorDestino(j).peso + costo[u] < costo[j]) {

                        costo[j] = this.listaAdy[u].obtenerNodoPorDestino(j).peso + costo[u];
                        camino[j] = u;
                    }
                }
            }
        }
        ArrayList<int[]> retorno = new ArrayList<>();

        retorno.add(0, costo);
        retorno.add(1, camino);

        //print(camino, o);

        return retorno;

    }

    // implementar
    private int distanciaMasCorta(int[] costos, boolean[] marcados) {
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < costos.length; i++) { // siempre debe de ser '<' porque comparamos Integer.MaxVALUE
            if (!marcados[i] && costos[i] < min) {
                min = i;
            }
        }
        return min;
    }

    private void print(int parent[], int j)
{
    
    if (parent[j]==0)
        return;

    print(parent, parent[j]);

    System.out.print(j);
}

    public String guardarCaminoMinimo(int destino, int origen, int previo[], String camino) { // ver
        
            if (destino != 0 && (destino != origen)){
                camino += (" / " + destino + " >- " + previo[destino]);
                camino = guardarCaminoMinimo( previo[destino],  origen , previo,  camino);
            }

        return camino;
    }

	public List<NodoPunto> obtenerMonopatines() {

        List<NodoPunto> monopatines = new ArrayList<>();

        // guardo monopatines:
		for (NodoPunto monopatin : this.nodosUsados) {
			if(monopatin != null){
			if (monopatin.getTipo() == "monopatin") {
				monopatines.add(monopatin);
			}}
		}        
		return monopatines;
	}

}