package ObligatorioAlgoritmos2;

import Retorno.Retorno;
import Retorno.Sistema;

public class Main {
	
	public static void main(String args[]) {
		
		Sistema sistema = new Sistema();
		
		Retorno ret = sistema.inicializarSistema(6);
		
		System.out.println(ret.resultado);
		
		ret = sistema.registrarUsuario("emailEjemplo", "nombreEjemplo");
		
		sistema.listarUsuarios();

		sistema.registrarEsquina(1, 1);
		sistema.registrarEsquina(1, 2);
		sistema.registrarEsquina(1, 3);
		sistema.registrarEsquina(1, 4);
		sistema.registrarEsquina(1, 5);
		sistema.registrarEsquina(1, 6);
		
		sistema.registrarTramo(1, 1, 1, 6, 1); //hay que registrar los tramos en orden
		sistema.registrarTramo(1, 1, 1, 2, 1);

		sistema.registrarTramo(1, 2, 1, 3, 1);
		sistema.registrarTramo(1, 2, 1, 5, 2);

		sistema.registrarTramo(1, 3, 1, 4, 1);

		sistema.registrarTramo(1, 5, 1, 6, 1);

		sistema.registrarTramo(1, 6, 1, 4, 2);
		
		
		
		sistema.monopatinMasCercano(4, 5);
	}
}
