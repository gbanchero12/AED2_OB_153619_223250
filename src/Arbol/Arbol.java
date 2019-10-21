package Arbol;

public class Arbol {
	private NodoUsuario raiz;

	public Arbol() {
		this.raiz = null;
	}

	public NodoUsuario getRaiz() {
		return raiz;
	}

	public boolean esArbolVacio() {
		return (raiz == null);
	}

	public void mostrarPreOrder() {
		mostrarPreOrder(this.raiz);
	}

	public void mostrarPreOrder(NodoUsuario a) {
		if (a != null) {
			System.out.print(a.getEmail() + "   ");
			mostrarPreOrder(a.getIzq());
			mostrarPreOrder(a.getDer());
		}
	}

	public void mostrarInOrder() {
		mostrarInOrder(this.raiz);
	}

	public void mostrarInOrder(NodoUsuario a) {
		if (a != null) {
			mostrarInOrder(a.getIzq());
			System.out.print(a.getEmail() + "  ");
			mostrarInOrder(a.getDer());
		}
	}

	public void mostrarPosOrder() {
		mostrarPosOrder(this.raiz);
	}

	public void mostrarPosOrder(NodoUsuario a) {
		if (a != null) {
			mostrarPosOrder(a.getIzq());
			mostrarPosOrder(a.getDer());
			System.out.print(a.getEmail() + "  ");
		}
	}

	public boolean existeElemento(String email) {
		NodoUsuario nodo = obtenerElemento(email, raiz, 0);

		if (nodo != null) {
			return true;
		} else {
			return false;
		}
	}

	public boolean existe(String email, NodoUsuario a) {
		boolean existe;
		if (a == null)
			existe = false;
		else {
			if (email.compareTo(a.getEmail()) == 0)
				existe = true;
			else if (email.compareTo(a.getEmail()) < 0)
				existe = existe(email, a.getIzq());
			else
				existe = existe(email, a.getDer());
		}
		return existe;
	}

	public NodoUsuario obtenerElemento(String email, NodoUsuario nodo, int cantElementos) {
		if (nodo == null) {
			return nodo;
		} else {

			if (email.compareTo(nodo.getEmail()) == 0) {
				nodo.setCantidadElementosRecorridos(++cantElementos);
				return nodo;
			} else if (email.compareTo(nodo.getEmail()) < 0) {
				return obtenerElemento(email, nodo.getIzq(), ++cantElementos);
			} else {
				return obtenerElemento(email, nodo.getDer(), ++cantElementos);
			}
		}
	}


	public int cantNodos(NodoUsuario nodo) {
		int cont = 0;
		if (nodo != null) {
			cont += cantNodos(nodo.getIzq()); // cuenta subarbol izquierdo
			cont++; // contabilizar el nodo visitado
			cont += cantNodos(nodo.getDer()); // cuenta subarbol derecho

		}
		return cont;
	}

	public int obtenerPeso(NodoUsuario nodo) {
		int peso = 0;
		int peso_izq = 0;
		int peso_der = 0;

		if (nodo != null) {
			peso_izq = cantNodos(nodo.getIzq());
			peso_der = cantNodos(nodo.getDer());
			peso = peso_izq + peso_der;

		}
		return peso;
	}

	public void insertarElemento(String email, String nombre, NodoUsuario nodo) {
		NodoUsuario nuevo = null;

		if (this.esArbolVacio())
			this.raiz = new NodoUsuario(email, nombre);

		else if (email.compareTo(nodo.getEmail()) < 0) { // n < dato => insertaré en subárbol izq.
			if (nodo.getIzq() == null) {
				nuevo = new NodoUsuario(email, nombre);
				nodo.setIzq(nuevo);
			} else
				insertarElemento(email, nombre, nodo.getIzq());
		} else if (email.compareTo(nodo.getEmail()) > 0) { // n > dato => insertaré en subárbol derecho
			if (nodo.getDer() == null) {
				nuevo = new NodoUsuario(email, nombre);
				nodo.setDer(nuevo);
			} else
				insertarElemento(email, nombre, nodo.getDer());
		}
	}

	public int cantHojas(NodoUsuario nodo) {
		if (nodo.getDer() == null)
			if (nodo.getIzq() == null)
				return 1;
			else
				return cantHojas(nodo.getIzq());
		else if (nodo.getIzq() == null)
			return cantHojas(nodo.getDer());
		else
			return cantHojas(nodo.getIzq()) + cantHojas(nodo.getDer());
	}

	public NodoUsuario borrarMinimo(NodoUsuario nodo) {
		if (nodo == null)
			return nodo;

		if (nodo.getIzq() != null) {
			nodo.setIzq(borrarMinimo(nodo.getIzq()));
			return nodo;
		} else
			return nodo.getDer();
	}

	public void insertar(String email, String nombre) {
		raiz = insertar(email, nombre, raiz);
	}

	private NodoUsuario insertar(String email, String nombre, NodoUsuario a) {
		if (a == null)
			a = new NodoUsuario(email, nombre);
		else if (email.compareTo(a.getEmail()) < 0)
			a.setIzq(insertar(email, nombre, a.getIzq())); 
		else if (email.compareTo(a.getEmail()) > 0)
			a.setDer(insertar(email, nombre, a.getDer())); 
		return a;
	}

	public int altura() {
		// TO-DO
		return 0;
	}
	

    public void listar() {
        listar(this.raiz);
    }
 
    private void listar(NodoUsuario a) {
        if (a != null) {
            listar(a.getIzq());
            System.out.print(a.toString().concat("|"));
            listar(a.getDer());
        }
    }


}
