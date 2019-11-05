package Retorno;

import java.awt.Desktop;
import java.net.URL;
import Arbol.Arbol;
import Arbol.Nodo;
import Retorno.Retorno.Resultado;
import ObligatorioAlgoritmos2.GrafoLista;
import ObligatorioAlgoritmos2.NodoPunto;
import ObligatorioAlgoritmos2.EmailValidator;

public class Sistema implements ISistema {

	public Arbol usuarios;
	public GrafoLista grafo;

	private final int LIMITE_LISTADO_MONOPATINES = 1000;

	@Override
	public Retorno inicializarSistema(int maxPuntos) {
		Retorno ret = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
		usuarios = new Arbol();
		if (maxPuntos > 0)
			this.grafo = new GrafoLista(maxPuntos);
		else {
			ret.resultado = Retorno.Resultado.ERROR_1;
			ret.valorString = "Cantidad de puntos debe ser mayor a 0";
		}
		ret.resultado = Retorno.Resultado.OK;

		return ret;
	}

	@Override
	public Retorno destruirSistema() {
		Retorno ret = new Retorno(Retorno.Resultado.OK);
		usuarios = null;
		this.grafo = null;
		return ret;
	}

	@Override
	public Retorno registrarUsuario(String email, String nombre) {
		Retorno retorno = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
		EmailValidator validator = new EmailValidator();

		if (validator.validate(email)) {

			if (usuarios.existeElemento(email)) {
				retorno.valorString = "Ya existe email";
				retorno.resultado = Retorno.Resultado.ERROR_2;
			
			} else {
				usuarios.insertar(email, nombre);
				retorno.resultado = Retorno.Resultado.OK;
			
			}

		} else {
			retorno.resultado = Retorno.Resultado.ERROR_1;
			retorno.valorString = "La direccion ingresada no es valida";
			
		}
		return retorno;
	}

	@Override
	public Retorno buscarUsuario(String email) {
		Retorno retorno = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
		EmailValidator validador = new EmailValidator();

		if (validador.validate(email)) {
			Nodo nodo = usuarios.obtenerElemento(email, usuarios.getRaiz(), 0);
			
			if (nodo != null) {
				retorno.resultado = Retorno.Resultado.OK;
				retorno.valorString = nodo.toString();
				retorno.valorEntero = nodo.getCantidadElementosRecorridos();
			} else {
				retorno.resultado = Retorno.Resultado.ERROR_2;
				retorno.valorString = "El usuario no existe";
				}
		} else {
			retorno.resultado = Retorno.Resultado.ERROR_1;
			retorno.valorString = "La direccion ingresada no es valida";
			}

		return retorno;
	}

	@Override
	public Retorno listarUsuarios() {

		Retorno retorno = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
		retorno.valorString = "";

		usuarios.listarUsuariosRecursivo(retorno);

		retorno.valorString = retorno.valorString
				.substring(0, retorno.valorString.length() - 1); // testSistema valida que el ultimo elemento sea distinto de |

		retorno.resultado = Retorno.Resultado.OK;
		return retorno;
	}

	@Override
	public Retorno registrarMonopatin(String chipId, double coordX, double coordY) {

		Retorno retorno = new Retorno(Resultado.NO_IMPLEMENTADA);

		NodoPunto nodo = new NodoPunto("monopatin", true, chipId, coordX, coordY, "Activo");

		if (grafo.size < grafo.cantNodos) {

			if (grafo.ObtenerNodoPorCoord(coordX, coordY) != null) {				
				retorno.resultado = Resultado.ERROR_2;
				retorno.valorString = "Ya existe un punto en las coordenadas x e y propuestas";
			} else {
				grafo.AgregarVertice(grafo.size + 1, nodo);
				retorno.resultado = Resultado.OK;
			}
		} else {
			retorno.resultado = Resultado.ERROR_1;
			retorno.valorString = "El sistema ya tiene registrada la cantidad m�xima";
		}
		return retorno;
	}

	@Override
	public Retorno registrarEsquina(double coordX, double coordY) {

		Retorno ret = new Retorno(Resultado.OK);

		NodoPunto nodo = new NodoPunto("esquina", true, null, coordX, coordY, "Activo");

		if (grafo.size < grafo.cantNodos) {

			if (grafo.ObtenerNodoPorCoord(coordX, coordY) == null) {
				grafo.AgregarVertice(grafo.size + 1, nodo);
			} else {
				ret.resultado = Resultado.ERROR_2;

				ret.valorString = "Ya existe un punto en las coordenadas ingresadas";
			}
		} else {
			ret.resultado = Resultado.ERROR_1;
			ret.valorString = "Ya estan registradas la maxima cantidad";
		}
		return ret;
	}

	@Override
	public Retorno registrarTramo(double coordXi, double coordYi, double coordXf, double coordYf, int metros) {

		if (metros <= 0)
			return new Retorno(Resultado.ERROR_1);

		// Recorro lista de Nodos y retorno la posicion segun las coordenadas
		int desde = this.grafo.ObtenerPosicionPorCoordenadas(coordXi, coordYi);
		int hasta = this.grafo.ObtenerPosicionPorCoordenadas(coordXf, coordYf);

		if (desde == -1 || hasta == -1) {
			// No se encontraron vertices con esas coordenadas
			return new Retorno(Resultado.ERROR_2);

		} else {
			if (this.grafo.sonAdyacentes(desde, hasta)) // Ya existe ese tramo
				return new Retorno(Resultado.ERROR_3);

			this.grafo.AgregarArista(desde, hasta, metros);

			return new Retorno(Resultado.OK);
		}
	}

	@Override
	public Retorno monopatinMasCercano(double coordX, double coordY) {
		Retorno ret = new Retorno(Retorno.Resultado.OK);
		ret.valorString = "Camino => Ubicacion Usuario: " + (int) coordX + ";" + (int) coordY + " -> ";
		NodoPunto[] nodosUsados = this.grafo.nodosUsados;
		boolean encontre = false;

		// Vertice donde se ubica el usuario
		int ubicacionUsuario = this.grafo.ObtenerPosicionPorCoordenadas(coordX, coordY);
		if (ubicacionUsuario == -1) {// la esquina no existe
			ret.resultado = Retorno.Resultado.ERROR_1;
			return ret;
		}
		int[] costosMinimos = this.grafo.caminoMinimo(ubicacionUsuario);

		int minimo = Integer.MAX_VALUE;
		int destino = 0;
		int i = 1;
		while (i < nodosUsados.length) {
			if (nodosUsados[i] != null && nodosUsados[i].getTipo() == "monopatin") {
				encontre = true;
				if (costosMinimos[i] < minimo) {
					minimo = costosMinimos[i];
					destino = i;
				}
			}
			i++;
		}

		int[] caminoEfectivo = new int[this.grafo.cantNodos];
		if (encontre) {
			caminoEfectivo = this.grafo.guardarCaminoMinimo(destino, ubicacionUsuario, // guardo camino a
																								// recorrer
					this.grafo.camino, caminoEfectivo, 0);
			ret.valorEntero = minimo;
			ret.valorString += this.grafo.imprimirCaminoMinimo(caminoEfectivo, destino); // imprimo mensaje con
																								// coordenadas del
																								// camino a realizar
		} else {
			ret.valorString = "No se encontraron monopatines.";
			ret.resultado = Retorno.Resultado.ERROR_2;
		}
		System.out.println(ret.valorString + " costo " + minimo);
		return ret;
	}

	@Override
	public Retorno monopatinesEnZona(double coordX, double coordY) {
		Retorno ret = new Retorno(Resultado.OK);
		ret.valorString = "";
		// Vertice donde se ubica el usuario
		int ubicacionUsuario = this.grafo.ObtenerPosicionPorCoordenadas(coordX, coordY);

		int[] costosMinimos = this.grafo.caminoMinimo(ubicacionUsuario);

		NodoPunto[] nodosUsados = this.grafo.nodosUsados;

		for (int i = 1; i < nodosUsados.length; i++) {
			if (nodosUsados[i] != null && nodosUsados[i].getTipo() == "monopatin") {
				if (costosMinimos[i] < this.LIMITE_LISTADO_MONOPATINES) {
					ret.valorString += (int) nodosUsados[i].getCoordX() + ";" + (int) nodosUsados[i].getCoordY() + "|";
				}
			}
		}
		ret.valorString = ret.valorString.substring(0, ret.valorString.length() - 1);
		System.out.println(ret.valorString);
		System.out.println(ret.resultado);
		return ret;
	}

	@Override
	public Retorno dibujarMapa() {

		String url = getURLMapaPuntos();

		try {
			Desktop.getDesktop().browse(new URL(url).toURI());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new Retorno(Resultado.OK);
	}

	private String getURLMapaPuntos() {
		String url = "https://maps.googleapis.com/maps/api/staticmap?center=Montevideo,UY&zoom=14&size=800x800";
		NodoPunto[] nodosUsados = this.grafo.nodosUsados;
		String color = "";
		for (int i = 1; i < nodosUsados.length; i++) {
			if(nodosUsados[i] != null) {
				if (nodosUsados[i].getEstado() == "Activo")
					color = "green";
				if (nodosUsados[i].getEstado() == "Descargado")
					color = "yellow";
				if (nodosUsados[i].getEstado() == "Averiado")
					color = "red";

			if (nodosUsados[i].getTipo() == "monopatin") {
				url += "&markers=color:" + color + "%7Clabel:M%7C" + nodosUsados[i].getCoordX() + ","
						+ nodosUsados[i].getCoordY();
			} }
		}

		url += "&key=AIzaSyBxiN8NsAUD9OoLIrfrauQhs4PD73PGTMY";

		return url;
	}

}
