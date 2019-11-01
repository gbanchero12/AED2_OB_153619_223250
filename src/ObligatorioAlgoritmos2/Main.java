package ObligatorioAlgoritmos2;

import Retorno.Retorno;
import Retorno.Sistema;

public class Main {
	
	public static void main(String args[]) {
		
		Sistema sistema = new Sistema();
		
		Retorno ret = sistema.inicializarSistema(7);
		
		System.out.println(ret.resultado);
		
		sistema.registrarUsuario("emailEjemplo@gmail.com", "nombreEjemplo");
		sistema.registrarUsuario("emailEjemplo2@gmail.com", "nombreEjemplo2");
		sistema.registrarUsuario("emailEjemplo3@gmail.com", "nombreEjemplo3");

		
		
		sistema.listarUsuarios(); 



		
		
		//sistema.registrarMonopatin("chipiId1", 1, 1);
		sistema.registrarEsquina(1, 1);
		sistema.registrarEsquina(1, 2);
		sistema.registrarEsquina(2, 3);
		
		sistema.registrarEsquina(1, 4);
		sistema.registrarEsquina(1, 5);
		sistema.registrarEsquina(1, 6);
		sistema.registrarEsquina(1, 7);
		//sistema.registrarMonopatin("chipId1", 1, 7);
		
				//           CoordX   CoordY Metros
		sistema.registrarTramo(1, 1, 	1, 6,	 1); 
		sistema.registrarTramo(1, 1, 	1, 2,	 1); 
		sistema.registrarTramo(1, 2, 	2, 3,	 1); 
		sistema.registrarTramo(1, 2, 	1, 5,	 1); 
		sistema.registrarTramo(2, 3, 	1, 4,	 1); 
		sistema.registrarTramo(1, 5, 	1, 6,	 1); 
		sistema.registrarTramo(1, 6, 	1, 4,	 1); 
		sistema.registrarTramo(1, 6,	1, 7,	 999);

		//sistema.grafoSistema.costoCaminoMinimo(1, 4);
		sistema.monopatinMasCercano(1, 7);
		//sistema.monopatinesEnZona(1, 1);
		
	}
}
