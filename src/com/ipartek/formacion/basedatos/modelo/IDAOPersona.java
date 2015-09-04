package com.ipartek.formacion.basedatos.modelo;

import java.util.ArrayList;

import com.ipartek.formacion.basedatos.bean.Persona;

public interface IDAOPersona extends Persistable {

	ArrayList<Persona> getAprobados();

	ArrayList<Persona> getSuspendidos();

}
