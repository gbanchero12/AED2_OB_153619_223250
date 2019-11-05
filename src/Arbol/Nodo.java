package Arbol;

public class Nodo {
	
    //Atributos
    private String email;
    private String nombre;
	private Nodo der ;
	private Nodo izq ;
	private int cantidadElementosRecorridos = 0;

    //Constructores
    public Nodo(String email, String nombre){
        this.email = email;
        this.nombre = nombre;
        izq = null;
        der = null;
     }

    public Nodo(String email, String nombre, Nodo d, Nodo i){
        this.email = email;
        this.nombre = nombre;
        der = d;
        izq = i;
     }
    
    @Override
    public String toString() {
    	return this.getEmail().concat(";").concat(this.getNombre());
    }


    //Dato
    String getEmail(){
        return email;
    }
    void setEmail(String email){
        this.email = email;
    }
    
    String getNombre(){
    	return nombre;
    }
    void setNombre(String nombre){
    	this.nombre = nombre;
    }
    
    //Derecho
    Nodo getDer(){
        return der;
    }
    void setDer(Nodo d){
       der = d;
    }
    
    //Izquierdo
    Nodo getIzq(){
        return izq;
    }
    void setIzq(Nodo i){
        izq = i;
    }
    
    public int getCantidadElementosRecorridos() {
		return cantidadElementosRecorridos;
	}

	public void setCantidadElementosRecorridos(int cantidadElementosRecorridos) {
		this.cantidadElementosRecorridos = cantidadElementosRecorridos;
	}

}
