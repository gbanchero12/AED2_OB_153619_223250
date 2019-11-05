package Retorno;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

// Version 1.1 - Correccion Listado Usuarios, faltaba Omar
//			   - Correccion metodo monopatines en zona
public class TestsSistema {

	private ISistema sistema;
	private Retorno retorno;
	
	@Before
	public void setUp() throws Exception {
		sistema = new Sistema();
	}

	@Test
	public void testInicializarYDestruirSistema() {
		assertEquals(Retorno.Resultado.OK, sistema.inicializarSistema(10).resultado);
		assertEquals(Retorno.Resultado.OK, sistema.destruirSistema().resultado);
	}

	
	// TESTS USUARIOS
	@Test
	public void testRegistroUsuarioOK() {
		sistema.inicializarSistema(10);
		//Datos de la prueba
		assertEquals(Retorno.Resultado.OK, sistema.registrarUsuario("omar@gmail.com", "Omar").resultado);	
		assertEquals(Retorno.Resultado.OK, sistema.registrarUsuario("jorge@gmail.com", "Jorge").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarUsuario("maria@gmail.com", "Maria").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarUsuario("ximena@gmail.com", "Ximena").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarUsuario("analia@gmail.com", "Analia").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarUsuario("nicolas@gmail.com", "Nicolas").resultado);
	}

	@Test
	public void testRegistroUsuarioError1() {
		sistema.inicializarSistema(10);
		//Formatos de Email incorrectos
		assertEquals(Retorno.Resultado.ERROR_1, 
				sistema.registrarUsuario("11111", "a").resultado); 
		assertEquals(Retorno.Resultado.ERROR_1, 
				sistema.registrarUsuario("aaaaa", "a").resultado);
		assertEquals(Retorno.Resultado.ERROR_1, 
				sistema.registrarUsuario("rrr@", "a").resultado);
		assertEquals(Retorno.Resultado.ERROR_1, 
				sistema.registrarUsuario("@wwwww", "a").resultado);
		assertEquals(Retorno.Resultado.ERROR_1, 
				sistema.registrarUsuario("a@b", "a").resultado);

	}
	
	@Test
	public void testRegistroUsuarioError2() {
		sistema.inicializarSistema(10);
		assertEquals(Retorno.Resultado.OK, sistema.registrarUsuario("maria@gmail.com", "Maria").resultado);
		assertEquals(Retorno.Resultado.ERROR_2, sistema.registrarUsuario("maria@gmail.com", "Maria").resultado);
	}
	

	@Test
	public void testBuscarUsuarioRaiz() {
		
		sistema.inicializarSistema(10);
		
		assertEquals(Retorno.Resultado.OK, sistema.registrarUsuario("omar@gmail.com", "Omar").resultado);	
		assertEquals(Retorno.Resultado.OK, sistema.registrarUsuario("jorge@gmail.com", "Jorge").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarUsuario("maria@gmail.com", "Maria").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarUsuario("ximena@gmail.com", "Ximena").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarUsuario("analia@gmail.com", "Analia").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarUsuario("nicolas@gmail.com", "Nicolas").resultado);
		
		retorno = sistema.buscarUsuario("omar@gmail.com");
		assertEquals(Retorno.Resultado.OK, retorno.resultado);
		assertEquals("omar@gmail.com;Omar", retorno.valorString);		
		
	}
	
	@Test
	public void testBuscarUsuarioRaizOrden() {
		
		sistema.inicializarSistema(10);
		
		assertEquals(Retorno.Resultado.OK, sistema.registrarUsuario("omar@gmail.com", "Omar").resultado);	
		assertEquals(Retorno.Resultado.OK, sistema.registrarUsuario("jorge@gmail.com", "Jorge").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarUsuario("maria@gmail.com", "Maria").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarUsuario("ximena@gmail.com", "Ximena").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarUsuario("analia@gmail.com", "Analia").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarUsuario("nicolas@gmail.com", "Nicolas").resultado);
		
		retorno = sistema.buscarUsuario("omar@gmail.com");
		assertEquals(Retorno.Resultado.OK, retorno.resultado);
		assertEquals("omar@gmail.com;Omar", retorno.valorString);
		assertEquals(1, retorno.valorEntero);
		
	}
	
	@Test
	public void testBuscarUsuarioHoja() {
		
		sistema.inicializarSistema(10);
		
		assertEquals(Retorno.Resultado.OK, sistema.registrarUsuario("omar@gmail.com", "Omar").resultado);	
		assertEquals(Retorno.Resultado.OK, sistema.registrarUsuario("jorge@gmail.com", "Jorge").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarUsuario("maria@gmail.com", "Maria").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarUsuario("ximena@gmail.com", "Ximena").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarUsuario("analia@gmail.com", "Analia").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarUsuario("nicolas@gmail.com", "Nicolas").resultado);
		
		retorno = sistema.buscarUsuario("nicolas@gmail.com");
		assertEquals(Retorno.Resultado.OK, retorno.resultado);
		assertEquals("nicolas@gmail.com;Nicolas", retorno.valorString);		
		
	}
	
	@Test
	public void testBuscarUsuarioInteriorOrden() {
		
		sistema.inicializarSistema(10);
		
		assertEquals(Retorno.Resultado.OK, sistema.registrarUsuario("omar@gmail.com", "Omar").resultado);	
		assertEquals(Retorno.Resultado.OK, sistema.registrarUsuario("jorge@gmail.com", "Jorge").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarUsuario("maria@gmail.com", "Maria").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarUsuario("ximena@gmail.com", "Ximena").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarUsuario("analia@gmail.com", "Analia").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarUsuario("nicolas@gmail.com", "Nicolas").resultado);
		
		retorno = sistema.buscarUsuario("maria@gmail.com");
		assertEquals(Retorno.Resultado.OK, retorno.resultado);
		assertEquals("maria@gmail.com;Maria", retorno.valorString);
		assertEquals(3, retorno.valorEntero);
		
	}
	
	@Test
	public void testBuscarUsuarioInterior() {
		
		sistema.inicializarSistema(10);
		
		assertEquals(Retorno.Resultado.OK, sistema.registrarUsuario("omar@gmail.com", "Omar").resultado);	
		assertEquals(Retorno.Resultado.OK, sistema.registrarUsuario("jorge@gmail.com", "Jorge").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarUsuario("maria@gmail.com", "Maria").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarUsuario("ximena@gmail.com", "Ximena").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarUsuario("analia@gmail.com", "Analia").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarUsuario("nicolas@gmail.com", "Nicolas").resultado);
		
		retorno = sistema.buscarUsuario("maria@gmail.com");
		assertEquals(Retorno.Resultado.OK, retorno.resultado);
		assertEquals("maria@gmail.com;Maria", retorno.valorString);		
		
	}
	
	@Test
	public void testBuscarUsuarioHojaOrden() {
		
		sistema.inicializarSistema(10);
		
		assertEquals(Retorno.Resultado.OK, sistema.registrarUsuario("omar@gmail.com", "Omar").resultado);	
		assertEquals(Retorno.Resultado.OK, sistema.registrarUsuario("jorge@gmail.com", "Jorge").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarUsuario("maria@gmail.com", "Maria").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarUsuario("ximena@gmail.com", "Ximena").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarUsuario("analia@gmail.com", "Analia").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarUsuario("nicolas@gmail.com", "Nicolas").resultado);
		
		retorno = sistema.buscarUsuario("nicolas@gmail.com");
		assertEquals(Retorno.Resultado.OK, retorno.resultado);
		assertEquals("nicolas@gmail.com;Nicolas", retorno.valorString);
		assertEquals(4, retorno.valorEntero);
		
	}
	
	@Test
	public void testListarUsuarios() {

		sistema.inicializarSistema(10);

		assertEquals(Retorno.Resultado.OK, sistema.registrarUsuario("omar@gmail.com", "Omar").resultado);	
		assertEquals(Retorno.Resultado.OK, sistema.registrarUsuario("jorge@gmail.com", "Jorge").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarUsuario("maria@gmail.com", "Maria").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarUsuario("ximena@gmail.com", "Ximena").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarUsuario("analia@gmail.com", "Analia").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarUsuario("nicolas@gmail.com", "Nicolas").resultado);
		
		retorno = sistema.listarUsuarios();
		assertEquals(Retorno.Resultado.OK, retorno.resultado);
		assertEquals("analia@gmail.com;Analia|jorge@gmail.com;Jorge|maria@gmail.com;Maria|nicolas@gmail.com;Nicolas|omar@gmail.com;Omar|ximena@gmail.com;Ximena",retorno.valorString);
		
	}

	// TESTS DEL MAPA
	@Test
	public void testRegistrarMonopatin_OK() {
		
		sistema.inicializarSistema(2);
		assertEquals(Retorno.Resultado.OK, sistema.registrarMonopatin("001", 1.0, 1.0).resultado);
		
	}

	@Test
	public void testRegistrarMonopatinError1() {
		
		sistema.inicializarSistema(2);
		assertEquals(Retorno.Resultado.OK, sistema.registrarMonopatin("001", 1.0, 1.0).resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarMonopatin("002", 2.0, 2.0).resultado);
		assertEquals(Retorno.Resultado.ERROR_1, sistema.registrarMonopatin("003", 3.0, 3.0).resultado);
	}

	@Test
	public void testRegistrarMonopatinError2() {
		
		sistema.inicializarSistema(10);
		assertEquals(Retorno.Resultado.OK, sistema.registrarMonopatin("001", 1.0, 1.0).resultado);
		assertEquals(Retorno.Resultado.ERROR_2, sistema.registrarMonopatin("002", 1.0, 1.0).resultado); // mismas coordenadas
	}

	@Test
	public void testRegistrarEsquina_OK() {
		
		sistema.inicializarSistema(2);
		assertEquals(Retorno.Resultado.OK, sistema.registrarEsquina(5.0, 5.0).resultado);

	}

	@Test
	public void testRegistrarEsquinaError1() {
	
		sistema.inicializarSistema(2);
		assertEquals(Retorno.Resultado.OK, sistema.registrarEsquina(5.0, 5.0).resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarEsquina(4.0, 4.0).resultado);
		assertEquals(Retorno.Resultado.ERROR_1, sistema.registrarEsquina(6.0, 6.0).resultado);
		
	}
	
	@Test
	public void testRegistrarEsquinaError2() {
	
		sistema.inicializarSistema(10);
		assertEquals(Retorno.Resultado.OK, sistema.registrarEsquina(5.0, 5.0).resultado);
		assertEquals(Retorno.Resultado.ERROR_2, sistema.registrarEsquina(5.0, 5.0).resultado);
		
	}
	
	@Test
	public void testRegistrarTramoOK() {
		
		sistema.inicializarSistema(10);
		sistema.registrarEsquina(1.0, 1.0);
		sistema.registrarEsquina(2.0, 2.0);
		sistema.registrarEsquina(4.0, 4.0);
		sistema.registrarEsquina(3.0, 3.0);
		
		assertEquals(Retorno.Resultado.OK, sistema.registrarTramo(1.0, 1.0, 2.0, 2.0, 10).resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarTramo(2.0, 2.0, 3.0, 3.0, 5).resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarTramo(3.0, 3.0, 4.0, 4.0, 3).resultado);
	}

	//Peso incorrecto
	@Test
	public void testRegistrarTramoError1() {
		
		sistema.inicializarSistema(10);
		sistema.registrarEsquina(1.0, 1.0);
		sistema.registrarEsquina(2.0, 2.0);
		sistema.registrarEsquina(4.0, 4.0);
		sistema.registrarEsquina(3.0, 3.0);
		
		assertEquals(Retorno.Resultado.ERROR_1, sistema.registrarTramo(1.0, 1.0, 2.0, 2.0, -1).resultado);
	}
	
	//Punto no existe
	@Test
	public void testRegistrarTramoError2() {
		
		sistema.inicializarSistema(10);
		sistema.registrarEsquina(1.0, 1.0);
		sistema.registrarEsquina(2.0, 2.0);
		sistema.registrarEsquina(4.0, 4.0);
		sistema.registrarEsquina(3.0, 3.0);
		
		assertEquals(Retorno.Resultado.ERROR_2, sistema.registrarTramo(9.0, 9.0, 2.0, 2.0, 10).resultado);
		assertEquals(Retorno.Resultado.ERROR_2, sistema.registrarTramo(3.0, 3.0, 8.0, 8.0, 10).resultado);
	}

	// Tramo ya existe
	@Test
	public void testRegistrarTramoError3() {
		
		sistema.inicializarSistema(10);
		sistema.registrarEsquina(1.0, 1.0);
		sistema.registrarEsquina(2.0, 2.0);
		sistema.registrarEsquina(4.0, 4.0);
		sistema.registrarEsquina(3.0, 3.0);
		
		assertEquals(Retorno.Resultado.OK, sistema.registrarTramo(1.0, 1.0, 2.0, 2.0, 10).resultado);
		assertEquals(Retorno.Resultado.ERROR_3, sistema.registrarTramo(1.0, 1.0, 2.0, 2.0, 10).resultado);
	}

	@Test
	public void testMonopatinMasCercanoOK1() {
		
		sistema.inicializarSistema(10);
		sistema.registrarUsuario("omar@gmail.com", "Omar");
		sistema.registrarMonopatin("005", 5.0, 5.0);
		sistema.registrarMonopatin("007", 7.0, 7.0);
		sistema.registrarEsquina(1.0, 1.0);
		sistema.registrarEsquina(2.0, 2.0);
		sistema.registrarEsquina(3.0, 3.0);
		sistema.registrarEsquina(4.0, 4.0);
		sistema.registrarEsquina(6.0, 6.0);
		
		sistema.registrarTramo(1.0, 1.0, 2.0, 2.0, 3);
		sistema.registrarTramo(1.0, 1.0, 3.0, 3.0, 7);
		sistema.registrarTramo(1.0, 1.0, 4.0, 4.0, 12);
		sistema.registrarTramo(1.0, 1.0, 6.0, 6.0, 1);
		
		sistema.registrarTramo(2.0, 2.0, 3.0, 3.0, 2);
		
		sistema.registrarTramo(3.0, 3.0, 4.0, 4.0, 1);
		sistema.registrarTramo(3.0, 3.0, 5.0, 5.0, 11);
		
		sistema.registrarTramo(4.0, 4.0, 5.0, 5.0, 3);
		sistema.registrarTramo(4.0, 4.0, 6.0, 6.0, 9);
		
		sistema.registrarTramo(6.0, 6.0, 5.0, 5.0, 10);
		sistema.registrarTramo(6.0, 6.0, 7.0, 7.0, 11);
		
		
		retorno = sistema.monopatinMasCercano(1.0, 1.0);
		assertEquals(Retorno.Resultado.OK, retorno.resultado);
		assertEquals(9, retorno.valorEntero);
		
	}
	
	@Test
	public void testMonopatinMasCercanoOK2() {
		
		sistema.inicializarSistema(10);
		sistema.registrarUsuario("omar@gmail.com", "Omar");
		sistema.registrarMonopatin("005", 5.0, 5.0);
		sistema.registrarMonopatin("007", 7.0, 7.0);
		sistema.registrarEsquina(1.0, 1.0);
		sistema.registrarEsquina(2.0, 2.0);
		sistema.registrarEsquina(3.0, 3.0);
		sistema.registrarEsquina(4.0, 4.0);
		sistema.registrarEsquina(6.0, 6.0);
		
		sistema.registrarTramo(1.0, 1.0, 2.0, 2.0, 3);
		sistema.registrarTramo(1.0, 1.0, 3.0, 3.0, 7);
		sistema.registrarTramo(1.0, 1.0, 4.0, 4.0, 12);
		sistema.registrarTramo(1.0, 1.0, 6.0, 6.0, 1);
		
		sistema.registrarTramo(2.0, 2.0, 3.0, 3.0, 2);
		
		sistema.registrarTramo(3.0, 3.0, 4.0, 4.0, 1);
		sistema.registrarTramo(3.0, 3.0, 5.0, 5.0, 11);
		
		sistema.registrarTramo(4.0, 4.0, 5.0, 5.0, 3);
		sistema.registrarTramo(4.0, 4.0, 6.0, 6.0, 9);
		
		sistema.registrarTramo(6.0, 6.0, 5.0, 5.0, 10);
		sistema.registrarTramo(6.0, 6.0, 7.0, 7.0, 6);
		
		
		retorno = sistema.monopatinMasCercano(1.0, 1.0);
		assertEquals(Retorno.Resultado.OK, retorno.resultado);
		assertEquals(7, retorno.valorEntero);
		
	}

	
	@Test
	public void testMonopatinMasCercanoError1() {
		
		sistema.inicializarSistema(10);
		sistema.registrarUsuario("omar@gmail.com", "Omar");
		sistema.registrarMonopatin("005", 5.0, 5.0);
		sistema.registrarMonopatin("007", 7.0, 7.0);
		sistema.registrarEsquina(1.0, 1.0);
		sistema.registrarEsquina(2.0, 2.0);
		sistema.registrarEsquina(3.0, 3.0);
		sistema.registrarEsquina(4.0, 4.0);
		sistema.registrarEsquina(6.0, 6.0);
		
		sistema.registrarTramo(1.0, 1.0, 2.0, 2.0, 3);
		sistema.registrarTramo(1.0, 1.0, 3.0, 3.0, 7);
		sistema.registrarTramo(1.0, 1.0, 4.0, 4.0, 12);
		sistema.registrarTramo(1.0, 1.0, 6.0, 6.0, 1);
		
		sistema.registrarTramo(2.0, 2.0, 3.0, 3.0, 2);
		
		sistema.registrarTramo(3.0, 3.0, 4.0, 4.0, 1);
		sistema.registrarTramo(3.0, 3.0, 5.0, 5.0, 11);
		
		sistema.registrarTramo(4.0, 4.0, 5.0, 5.0, 3);
		sistema.registrarTramo(4.0, 4.0, 6.0, 6.0, 9);
		
		sistema.registrarTramo(6.0, 6.0, 5.0, 5.0, 10);
		sistema.registrarTramo(6.0, 6.0, 7.0, 7.0, 11);
		
		retorno = sistema.monopatinMasCercano(9.0, 9.0); // No existen coordenadas
		assertEquals(Retorno.Resultado.ERROR_1, retorno.resultado);
		
	}

	@Test
	public void testMonopatinMasCercanoError2() {
		
		sistema.inicializarSistema(10);
		sistema.registrarUsuario("omar@gmail.com", "Omar");
		sistema.registrarEsquina(5.0, 5.0);
		sistema.registrarEsquina(1.0, 1.0);
		sistema.registrarEsquina(2.0, 2.0);
		sistema.registrarEsquina(3.0, 3.0);
		sistema.registrarEsquina(4.0, 4.0);
		sistema.registrarEsquina(6.0, 6.0);
		
		sistema.registrarTramo(1.0, 1.0, 2.0, 2.0, 3);
		sistema.registrarTramo(1.0, 1.0, 3.0, 3.0, 7);
		sistema.registrarTramo(1.0, 1.0, 4.0, 4.0, 12);
		sistema.registrarTramo(1.0, 1.0, 6.0, 6.0, 1);
		
		sistema.registrarTramo(2.0, 2.0, 3.0, 3.0, 2);
		
		sistema.registrarTramo(3.0, 3.0, 4.0, 4.0, 1);
		sistema.registrarTramo(3.0, 3.0, 5.0, 5.0, 11);
		
		sistema.registrarTramo(4.0, 4.0, 5.0, 5.0, 3);
		sistema.registrarTramo(4.0, 4.0, 6.0, 6.0, 9);
		
		sistema.registrarTramo(6.0, 6.0, 5.0, 5.0, 10);


		retorno = sistema.monopatinMasCercano(1.0, 1.0); // No hay monopatines disponibles
		assertEquals(Retorno.Resultado.ERROR_2, retorno.resultado);
		
	}

	@Test
	public void testMonopatinesEnZona1() {
		
		sistema.inicializarSistema(10);
		sistema.registrarUsuario("omar@gmail.com", "Omar");
		sistema.registrarMonopatin("005", 5.0, 5.0);
		sistema.registrarMonopatin("007", 7.0, 7.0);
		sistema.registrarEsquina(1.0, 1.0);
		sistema.registrarEsquina(2.0, 2.0);
		sistema.registrarEsquina(3.0, 3.0);
		sistema.registrarEsquina(4.0, 4.0);
		sistema.registrarEsquina(6.0, 6.0);
		
		sistema.registrarTramo(1.0, 1.0, 2.0, 2.0, 300);
		sistema.registrarTramo(1.0, 1.0, 3.0, 3.0, 700);
		sistema.registrarTramo(1.0, 1.0, 4.0, 4.0, 1200);
		sistema.registrarTramo(1.0, 1.0, 6.0, 6.0, 100);
		
		sistema.registrarTramo(2.0, 2.0, 3.0, 3.0, 200);
		
		sistema.registrarTramo(3.0, 3.0, 4.0, 4.0, 100);
		sistema.registrarTramo(3.0, 3.0, 5.0, 5.0, 1100);
		
		sistema.registrarTramo(4.0, 4.0, 5.0, 5.0, 300);
		sistema.registrarTramo(4.0, 4.0, 6.0, 6.0, 900);
		
		sistema.registrarTramo(6.0, 6.0, 5.0, 5.0, 1000);
		sistema.registrarTramo(6.0, 6.0, 7.0, 7.0, 1100);
		
		retorno = sistema.monopatinesEnZona(1.0, 1.0); // Solo 1 monopatin en radio 1000 metros
		assertEquals(Retorno.Resultado.OK, retorno.resultado);
		assertEquals("5;5", retorno.valorString.replaceAll("\\.0", ""));	// El valorString debe tener "5.0;5.0" o "5;5"
	}

	@Test
	public void testMonopatinesEnZona2() {
		
		sistema.inicializarSistema(10);
		sistema.registrarUsuario("omar@gmail.com", "Omar");
		sistema.registrarMonopatin("005", 5.0, 5.0);
		sistema.registrarMonopatin("007", 7.0, 7.0);
		sistema.registrarEsquina(1.0, 1.0);
		sistema.registrarEsquina(2.0, 2.0);
		sistema.registrarEsquina(3.0, 3.0);
		sistema.registrarEsquina(4.0, 4.0);
		sistema.registrarEsquina(6.0, 6.0);
		
		sistema.registrarTramo(1.0, 1.0, 2.0, 2.0, 300);
		sistema.registrarTramo(1.0, 1.0, 3.0, 3.0, 700);
		sistema.registrarTramo(1.0, 1.0, 4.0, 4.0, 1200);
		sistema.registrarTramo(1.0, 1.0, 6.0, 6.0, 100);
		
		sistema.registrarTramo(2.0, 2.0, 3.0, 3.0, 200);
		
		sistema.registrarTramo(3.0, 3.0, 4.0, 4.0, 100);
		sistema.registrarTramo(3.0, 3.0, 5.0, 5.0, 1100);
		
		sistema.registrarTramo(4.0, 4.0, 5.0, 5.0, 300);
		sistema.registrarTramo(4.0, 4.0, 6.0, 6.0, 900);
		
		sistema.registrarTramo(6.0, 6.0, 5.0, 5.0, 1000);
		sistema.registrarTramo(6.0, 6.0, 7.0, 7.0, 600);
		
		retorno = sistema.monopatinesEnZona(1.0, 1.0); // 2 monopatines (5 y 7) en radio 1000 metros
		assertEquals(Retorno.Resultado.OK, retorno.resultado);
		assertTrue(retorno.valorString.replaceAll("\\.0", "").contains("5;5"));// El valorString debe tener "5.0;5.0" o "5;5"
		assertTrue(retorno.valorString.replaceAll("\\.0", "").contains("7;7"));// El valorString debe tener "7.0;7.0" o "7;7"
	}
	
	@Test
	public void testDibujarMapa() {
		
		sistema.inicializarSistema(10);
		sistema.registrarMonopatin("003",-34.9129446,-56.1484977); // Bar La Esquina
		sistema.registrarMonopatin("001", -34.7969082,-56.0693009); // Campe�n del Siglo 
		sistema.registrarMonopatin("002", -34.8844009,-56.1609289); // Bueno, el otro 
		sistema.registrarEsquina(-34.82,-56.10); // Esquina, no se muestra
		
		retorno = sistema.dibujarMapa();
		assertEquals(Retorno.Resultado.OK, retorno.resultado);
	}

	
}