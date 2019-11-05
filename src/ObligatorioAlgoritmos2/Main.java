package ObligatorioAlgoritmos2;

import Retorno.Retorno;
import Retorno.Sistema;

public class Main {
	
	public static void main(String args[]) {
		
		Sistema sistema = new Sistema();		
		Retorno retorno = sistema.inicializarSistema(10);
		//registro usuarios
		sistema.registrarUsuario("gbanchero12@gmail.com", "Guillermo Banchero");
		sistema.registrarUsuario("fernandoperez1902@gmail.com", "Fernando Perez");
		sistema.registrarUsuario("ejemplo@gmail.com", "ejemplo");
		//registro monopatin
		sistema.registrarMonopatin("monopatinN°1", -34.916782, -56.164835);
		sistema.registrarMonopatin("monopatinN°2", -34.9100816, -56.1669126);
		//registro esquinas
		sistema.registrarEsquina(-34.916282, -56.163241);
		sistema.registrarEsquina(-34.917689, -56.169123);
		sistema.registrarEsquina(-34.911345, -56.169123);
		sistema.registrarEsquina(-34.914567, -56.169256);
		sistema.registrarEsquina(-34.911555, -56.169555);
		//registro tramos
		sistema.registrarTramo(-34.916282, -56.163241, -34.917689, -56.169123, 500);
		sistema.registrarTramo(-34.917689, -56.169123, -34.911345, -56.169123, 700);
		sistema.registrarTramo(-34.911345, -56.169123, -34.914567, -56.169256, 500);
		sistema.registrarTramo(-34.916282, -56.163241, -34.917689, -56.169123, 500);
		sistema.registrarTramo(-34.914567, -56.169256, -34.911555, -56.169555, 500);
		sistema.registrarTramo(-34.911555, -56.169555, -34.916782, -56.164835, 500);
		sistema.registrarTramo(-34.916782, -56.164835, -34.9100816,-56.166916, 500);
		//
		sistema.monopatinMasCercano(-34.916282, -56.163241);
		//sistema.monopatinesEnZona(1, 1);
		sistema.dibujarMapa();
		
		
	}
}
