package ObligatorioAlgoritmos2;

import Retorno.Retorno;
import Retorno.Sistema;

public class Main {
	
	public static void main(String args[]) {
		
		Sistema sistema = new Sistema();
		
		Retorno ret = sistema.inicializarSistema(10);
		
		System.out.println(ret.resultado);
		
		String email = "fernandoperez1902@gmail.com";
		String nombre = "Falopa";
		
		ret = sistema.registrarUsuario(email, nombre);
		
		System.out.println(ret.resultado);
		
		/*email = "putita.com";
		
		nombre = "Genaro";
		
		ret = sistema.registrarUsuario(email, nombre);
		
		System.out.println(ret.resultado);
		System.out.println(ret.valorString);
		*/
		
		email = "gena.loprete@gmail.com";
		nombre = "Genaro";
		
		ret = sistema.registrarUsuario(email, nombre);
		
		
		email = "fernandoperez1902@gmail.com";
		ret = sistema.buscarUsuario(email);
		System.out.println(ret.valorEntero);
		
		sistema.listarUsuarios();
		
	}
}
