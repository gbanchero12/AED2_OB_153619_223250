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
    
    int size;
    int cantNodos;
    ListaAdyacencia[] listaAdy;
    boolean[] nodosUsados;
    
    //constructor
    public GrafoLista(int n){
        this.size = 0;
        this.cantNodos = n;
        this.listaAdy = new ListaAdyacencia[n + 1];
        for (int i = 0; i < n; i++) {
            this.listaAdy[i] = new ListaAdyacencia();
        }
        this.nodosUsados = new boolean[n + 1];
    }
    
    public void AgregarVertice(int n){
        this.nodosUsados[n] = true;
        this.size++; 
    }
    
    public void AgregarArista(int o,int d ,int p){
        this.listaAdy[o].InsertarInicio(d , p); //metodo a crear
         this.listaAdy[d].InsertarInicio(o , p); 
        //si fuera bidereccionar hay que agregar tambien para el otro lado
    }
    
    public void EliminarVertice(int n){
        this.nodosUsados[n] = false;
        this.size--;
        this.listaAdy[n] = new ListaAdyacencia();
        for (int i = 0; i < listaAdy.length; i++) {
            this.listaAdy[i].Eliminar(n);
        }
    }
    
    public boolean EsVacio(){
        return this.size == 0;
    }
    
    public ListaAdyacencia VerticesAdyacents(int v){
        return this.listaAdy[v];
    }
    
    public boolean SonAdyacentes(int a , int b){
        return this.listaAdy[a].Existe(b);
    }
    
    public boolean ExisteVertice(int n){
        return this.nodosUsados[n];
    }
}
