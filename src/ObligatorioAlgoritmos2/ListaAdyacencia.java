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
public class ListaAdyacencia {

    int size;
    NodoLista inicio;

    public boolean EsVacia() {
        return (this.size == 0);
    }

    public void insertarInicio(int destino, int peso) {
        NodoLista nuevo = new NodoLista(destino, peso);
        if (this.EsVacia()) {
            inicio = nuevo;
        } else {
            nuevo.siguiente = inicio;
            inicio = nuevo;
        }
        size++;
    }

    public void eliminar(int n) {
        if (!this.EsVacia()) {
            NodoLista aux = this.inicio;
            if (aux.destino != n) {
                while (aux.siguiente != null) {
                    if (aux.siguiente.destino == n) {
                        aux.siguiente = aux.siguiente.siguiente;
                    }
                    aux = aux.siguiente;
                }
            } else {
                this.inicio = aux.siguiente;
            }
        }
    }

    public boolean existe(int n) {
        boolean encontro = false;
        if (this.EsVacia()) {
            return encontro;
        } else {
            NodoLista aux = this.inicio;
            {
                while (aux != null) {
                    if (aux.destino == n) {
                        encontro = true;
                    }
                    aux = aux.siguiente;
                }
            }
        }
        return encontro;
    }

    public NodoLista obtenerNodo (int destino) { 
        NodoLista aux = this.inicio;
        
        while (aux.siguiente != null) {
            if (aux.destino == destino)
                {return aux;}
            aux = aux.siguiente;
        }

        if (aux.destino == destino) { //ultimoElemento
            return aux;
        } else {
            return null;
        }
    }

}
