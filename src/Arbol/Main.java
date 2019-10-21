package Arbol;

public class Main {

    public static void main(String[] args) {
        
    	// Observar las diferencias entre los dos métodos de inserción en el árbol.
        Arbol a = new Arbol();
        a.insertarElemento(10, a.getRaiz());
        a.insertarElemento(12, a.getRaiz());
        a.insertarElemento(15, a.getRaiz());
        a.insertarElemento(5, a.getRaiz());
        a.insertarElemento(8, a.getRaiz());
        a.insertarElemento(3, a.getRaiz());

        Arbol b = new Arbol();
        b.insertar(10);
        b.insertar(12);
        b.insertar(15);
        b.insertar(5);
        b.insertar(8);
        b.insertar(3);
        
        System.out.println("Arbol A:");
        a.mostrarInOrder();
        
        System.out.println();
        
        System.out.println("Arbol B:");
        b.mostrarInOrder();
        
        //Observar la diferencia en el listado de los datos según tipo de recorrido
        System.out.println();
        System.out.println();
        System.out.println("ABB en PreOrder");
        a.mostrarPreOrder();
        System.out.println();
        System.out.println("ABB en InOrder");
        a.mostrarInOrder();
        System.out.println();
        System.out.println("ABB en PosOrder");
        a.mostrarPosOrder();

        // Pertenece
        System.out.println();
        System.out.println("El dato 15 pertenece al árbol: "+a.existeElemento(15));
        System.out.println("El dato 4 pertenece al árbol: "+a.existeElemento(4));

        //Otros métodos
        System.out.println();
        Nodo x = a.obtenerElemento(3, a.getRaiz());
        System.out.println("Dato obtenido = " +  x.getDato());

        int num_elems = a.cantNodos(a.getRaiz());
        System.out.println("El arbol tiene " + num_elems + " elementos");

        int peso = a.obtenerPeso(a.getRaiz());
        System.out.println("Peso del arbol = " + peso);

        int hojas = a.cantHojas(a.getRaiz());
        System.out.println("Hojas del arbol = " + hojas);
        
        // Implemente el siguiente método definido en la clase Arbol
        int altura = a.altura();
        System.out.println("Altura del arbol = " + altura);
       
        //Borrar Mínimo
        a.borrarMinimo(a.getRaiz());
        System.out.println("Borré el mínimo");
        a.mostrarInOrder();
       
    }

}
