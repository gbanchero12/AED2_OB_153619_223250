package Arbol;

public class NodoUsuario {
	
    //Atributos
    private String email;
    private String nombre;
	private NodoUsuario der ;
	private NodoUsuario izq ;
	private int cantidadElementosRecorridos = 0;

    //Constructores
    public NodoUsuario(String email, String nombre){
        this.email = email;
        this.nombre = nombre;
        izq = null;
        der = null;
     }

    public NodoUsuario(String email, String nombre, NodoUsuario d, NodoUsuario i){
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
    NodoUsuario getDer(){
        return der;
    }
    void setDer(NodoUsuario d){
       der = d;
    }
    
    //Izquierdo
    NodoUsuario getIzq(){
        return izq;
    }
    void setIzq(NodoUsuario i){
        izq = i;
    }
    
    public int getCantidadElementosRecorridos() {
		return cantidadElementosRecorridos;
	}

	public void setCantidadElementosRecorridos(int cantidadElementosRecorridos) {
		this.cantidadElementosRecorridos = cantidadElementosRecorridos;
	}

}
