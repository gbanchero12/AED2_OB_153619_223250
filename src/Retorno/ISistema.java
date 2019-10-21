package Retorno;

public interface ISistema {

	 Retorno inicializarSistema (int maxPuntos);
	 
	 Retorno destruirSistema();
	 
	 Retorno registrarUsuario(String email, String nombre);

	 Retorno buscarUsuario(String email);
	 
	 Retorno listarUsuarios();
	 
	 Retorno registrarMonopatin(String chipId, double coordX,
			 double coordY);
	 
	 Retorno registrarEsquina(double coordX, double coordY);

	 Retorno registrarTramo(double coordXi, double coordYi, double
			 coordXf, double coordYf, int metros);
	 
	 Retorno monopatinMasCercano(double coordX, double coordY);

	 Retorno monopatinesEnZona(double coordX, double coordY);
	 
	 Retorno dibujarMapa();
	
}
