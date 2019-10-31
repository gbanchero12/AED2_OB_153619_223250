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
public class NodoLista {
    int destino;
    int peso;
    NodoLista siguiente;

    public NodoLista(int destino, int peso) {
        this.destino = destino;
        this.peso = peso;
        this.siguiente = null;
    }
}
