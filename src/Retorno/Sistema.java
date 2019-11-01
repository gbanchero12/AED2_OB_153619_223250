package Retorno;

import java.util.ArrayList;
import java.util.List;

import Arbol.Arbol;
import Arbol.NodoUsuario;
import Retorno.Retorno.Resultado;
import ObligatorioAlgoritmos2.GrafoLista;
import ObligatorioAlgoritmos2.NodoPunto;
import ObligatorioAlgoritmos2.EmailValidator;

public class Sistema implements ISistema {

	public Arbol arbolUsuarios;
	public GrafoLista grafoSistema;

	private final int LIMITE_LISTADO_MONOPATINES = 1000;

	@Override
	public Retorno inicializarSistema(int maxPuntos) {

		Retorno ret = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);

		if (maxPuntos > 0)
			this.grafoSistema = new GrafoLista(maxPuntos);
		else {
			ret.resultado = Retorno.Resultado.ERROR_1;
			ret.valorString = "La cantidad de puntos debe ser mayor a 0";
		}

		arbolUsuarios = new Arbol();

		ret.resultado = Retorno.Resultado.OK;

		return ret;
	}

	@Override
	public Retorno destruirSistema() {

		Retorno ret = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);

		this.grafoSistema = null;

		arbolUsuarios = null;

		ret.resultado = Retorno.Resultado.OK;

		return ret;
	}

	@Override
	public Retorno registrarUsuario(String email, String nombre) {

		Retorno ret = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);

		EmailValidator validador = new EmailValidator();

		if (validador.validate(email)) {

			if (!arbolUsuarios.existeElemento(email)) {

				arbolUsuarios.insertar(email, nombre);
				ret.resultado = Retorno.Resultado.OK;

			} else {

				ret.resultado = Retorno.Resultado.ERROR_2;
				ret.valorString = "Ya existe un afiliado con ese email";
			}

		} else {

			ret.resultado = Retorno.Resultado.ERROR_1;
			ret.valorString = "La direcci�n de email no es una direcci�n v�lida";

		}

		return ret;
	}

	@Override
	public Retorno buscarUsuario(String email) {

		Retorno ret = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);

		EmailValidator validador = new EmailValidator();

		if (validador.validate(email)) {

			NodoUsuario nodo = arbolUsuarios.obtenerElemento(email, arbolUsuarios.getRaiz(), 0);

			if (nodo != null) {

				ret.resultado = Retorno.Resultado.OK;
				ret.valorString = nodo.toString();
				ret.valorEntero = nodo.getCantidadElementosRecorridos();

			} else {

				ret.resultado = Retorno.Resultado.ERROR_2;
				ret.valorString = "El usuario no existe";
			}

		} else {

			ret.resultado = Retorno.Resultado.ERROR_1;
			ret.valorString = "La direcci�n de email no es una direcci�n v�lida";

		}

		return ret;

	}

	@Override
	public Retorno listarUsuarios() {

		Retorno ret = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);

		arbolUsuarios.listar();

		ret.resultado = Retorno.Resultado.OK;

		return ret;

	}

	@Override
	public Retorno registrarMonopatin(String chipId, double coordX, double coordY) {

		Retorno ret = new Retorno(Resultado.NO_IMPLEMENTADA);

		NodoPunto nodo = new NodoPunto("monopatin", true, chipId, coordX, coordY);

		if (grafoSistema.size < grafoSistema.cantNodos) {

			if (grafoSistema.ObtenerNodoPorCoord(coordX, coordY) == null) {
				grafoSistema.AgregarVertice(grafoSistema.size + 1, nodo);
				ret.resultado = Resultado.OK;
			} else {

				ret.resultado = Resultado.ERROR_2;
				ret.valorString = "Ya existe un punto en las coordenadas x e y propuestas";

			}

		} else {
			ret.resultado = Resultado.ERROR_1;
			ret.valorString = "El sistema ya tiene registrada la cantidad m�xima";

		}

		return ret;

	}

	@Override
	public Retorno registrarEsquina(double coordX, double coordY) {

		Retorno ret = new Retorno(Resultado.NO_IMPLEMENTADA);

		NodoPunto nodo = new NodoPunto("esquina", true, null, coordX, coordY);

		if (grafoSistema.size < grafoSistema.cantNodos) {

			if (grafoSistema.ObtenerNodoPorCoord(coordX, coordY) == null) {
				grafoSistema.AgregarVertice(grafoSistema.size + 1, nodo);
				ret.resultado = Resultado.OK;
			} else {

				ret.resultado = Resultado.ERROR_2;
				ret.valorString = "Ya existe un punto en las coordenadas x e y propuestas";

			}

		} else {
			ret.resultado = Resultado.ERROR_1;
			ret.valorString = "El sistema ya tiene registrada la cantidad m�xima";

		}

		return ret;
	}

	@Override
	public Retorno registrarTramo(double coordXi, double coordYi, double coordXf, double coordYf, int metros) {

		if (metros <= 0)
			return new Retorno(Resultado.ERROR_1);

		int o = this.grafoSistema.ObtenerPosicionPorCoordenadas(coordXi, coordYi);// Recorre lista de nodosUsados
		int d = this.grafoSistema.ObtenerPosicionPorCoordenadas(coordXf, coordYf);

		if (o != -1 && d != -1) {

			if (this.grafoSistema.sonAdyacentes(o, d)) // Ya existe esa tramo
				return new Retorno(Resultado.ERROR_3);

			this.grafoSistema.AgregarArista(o, d, metros);
			return new Retorno(Resultado.OK);
		} else {
			// No se encontraron vertices con esas coordenadas
			return new Retorno(Resultado.ERROR_2);
		}
	}

	@Override
	public Retorno monopatinMasCercano(double coordX, double coordY) {
		Retorno ret = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
		ret.valorString = "Camino => Ubicacion Usuario: " + (int) coordX + ";" + (int) coordY + " -> ";
		NodoPunto[] nodosUsados = this.grafoSistema.nodosUsados;
		boolean encontre = false;

		// Vertice donde se ubica el usuario
		int ubicacionUsuario = this.grafoSistema.ObtenerPosicionPorCoordenadas(coordX, coordY);
		if (ubicacionUsuario == -1) {// la esquina no existe
			ret.resultado = Retorno.Resultado.ERROR_1;
			return ret;
		}
		int[] costosMinimos = this.grafoSistema.caminoMinimo(ubicacionUsuario);

		int minimo = Integer.MAX_VALUE;
		int destino = 0;
		int i = 1;
		while (i < nodosUsados.length) {
			if (nodosUsados[i].getTipo() == "monopatin") {
				encontre = true;
				if (costosMinimos[i] < minimo) {
					minimo = costosMinimos[i];
					destino = i;
				}
			}
			i++;
		}
		
		int [] caminoEfectivo = new int[this.grafoSistema.cantNodos];

		if (encontre) {
			caminoEfectivo = this.grafoSistema.guardarCaminoMinimo(destino, ubicacionUsuario, //guardo camino a recorrer
					this.grafoSistema.camino, caminoEfectivo ,0);			
			ret.valorEntero = minimo;
			ret.valorString += this.grafoSistema.imprimirCaminoMinimo(caminoEfectivo, destino); //imprimo mensaje con coordenadas del camino a realizar 
		} else {
			ret.valorString = "No se encontraron monopatines.";
			ret.resultado = Retorno.Resultado.ERROR_2;
		}
		System.out.println(ret.valorString + " costo " + minimo);
		//System.out.println(ret.resultado);
		return ret;
	}

	@Override
	public Retorno monopatinesEnZona(double coordX, double coordY) {
		Retorno ret = new Retorno(Resultado.OK);
		ret.valorString = "";
		// Vertice donde se ubica el usuario
		int ubicacionUsuario = this.grafoSistema.ObtenerPosicionPorCoordenadas(coordX, coordY);

		int[] costosMinimos = this.grafoSistema.caminoMinimo(ubicacionUsuario);

		NodoPunto[] nodosUsados = this.grafoSistema.nodosUsados;

		for (int i = 1; i < nodosUsados.length; i++) {
			if (nodosUsados[i].getTipo() == "monopatin") {
				if (costosMinimos[i] < this.LIMITE_LISTADO_MONOPATINES) {
					ret.valorString += (int) nodosUsados[i].getCoordX() + ";" + (int) nodosUsados[i].getCoordY() + "|";
				}
			}
		}
		System.out.println(ret.valorString);
		System.out.println(ret.resultado);
		return ret;
	}

	@Override
	public Retorno dibujarMapa() {
		return new Retorno(Resultado.NO_IMPLEMENTADA);
	}

}
