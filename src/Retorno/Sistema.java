package Retorno;

import Arbol.Arbol;
import Arbol.NodoUsuario;
import Retorno.Retorno.Resultado;
import ObligatorioAlgoritmos2.GrafoLista;
import ObligatorioAlgoritmos2.NodoPunto;
import ObligatorioAlgoritmos2.EmailValidator;

public class Sistema implements ISistema {

	public Arbol arbolUsuarios;
	public GrafoLista grafoSistema;

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

		ret.resultado = Resultado.OK;

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

			if (this.grafoSistema.SonAdyacentes(o, d)) // Ya existe esa tramo
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


		//Vertice donde se ubica el usuario
		int ubicacion = this.grafoSistema.ObtenerPosicionPorCoordenadas(coordX, coordY);


		//metodo auxiliar de solucion:
		//this.grafoSistema.caminoMinimo(ubicacion, (int) coordY);

		this.grafoSistema.caminoMinimo((int) coordX, (int) coordY);

		return new Retorno(Resultado.NO_IMPLEMENTADA);
	}

	@Override
	public Retorno monopatinesEnZona(double coordX, double coordY) {
		return new Retorno(Resultado.NO_IMPLEMENTADA);
	}

	@Override
	public Retorno dibujarMapa() {
		return new Retorno(Resultado.NO_IMPLEMENTADA);
	}

}
