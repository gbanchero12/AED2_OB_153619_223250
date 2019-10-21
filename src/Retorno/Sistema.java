package Retorno;

import Arbol.Arbol;
import Arbol.NodoUsuario;
import Retorno.Retorno.Resultado;
import ObligatorioAlgoritmos2.GrafoLista;
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

		// TODO crear árbol de usuarios

		arbolUsuarios = new Arbol();

		ret.resultado = Retorno.Resultado.OK;

		return ret;
	}

	@Override
	public Retorno destruirSistema() {

		Retorno ret = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);

		this.grafoSistema = null;

		// TODO destruir el árbol de usuarios
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
			ret.valorString = "La dirección de email no es una dirección válida";

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
				ret.valorString  = nodo.toString();
				ret.valorEntero = nodo.getCantidadElementosRecorridos();
				
			} else {

				ret.resultado = Retorno.Resultado.ERROR_2;
				ret.valorString = "El usuario no existe";
			}

		} else {
			
			ret.resultado = Retorno.Resultado.ERROR_1;
			ret.valorString = "La dirección de email no es una dirección válida";

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
		return new Retorno(Resultado.NO_IMPLEMENTADA);
	}

	@Override
	public Retorno registrarEsquina(double coordX, double coordY) {
		return new Retorno(Resultado.NO_IMPLEMENTADA);
	}

	@Override
	public Retorno registrarTramo(double coordXi, double coordYi, double coordXf, double coordYf, int metros) {
		return new Retorno(Resultado.NO_IMPLEMENTADA);
	}

	@Override
	public Retorno monopatinMasCercano(double coordX, double coordY) {
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
