package com.ipartek.formacion.basedatos.modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.ipartek.formacion.basedatos.bean.Persona;

/**
 * DAO: Data Access Object Clase especializada en mapear una {@code Persona}
 * contra la BBDD. Dispone de los metodos basicos para realizar las operaciones
 * de CRUD.
 *
 * @author Degar
 *
 */
public class DAOPersona implements IDAOPersona {

	/**
	 * Recupera todas las Personas
	 *
	 * @return {@code ArrayList<Persona>} listado personas
	 */
	@Override
	public ArrayList<Object> getAll() {

		ArrayList<Object> resul = new ArrayList<Object>();
		try {
			Connection con = DataBaseHelper.getConnection();
			Statement st = con.createStatement();
			String sql = "SELECT * FROM `test` ";
			ResultSet rs = st.executeQuery(sql);

			// mapeo resultSet => ArrayList<Persona>
			Persona p = null;
			while (rs.next()) {

				p = new Persona(rs.getString("nombre"));
				p.setId(rs.getInt("id"));
				p.setFecha(rs.getTimestamp("fecha"));
				p.setTelefono(rs.getString("telefono"));
				p.setNota(rs.getFloat("nota"));

				resul.add(p);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataBaseHelper.closeConnection();
		}

		return resul;

	}

	@Override
	public int save(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Persona> getAprobados() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Persona> getSuspendidos() {
		// TODO Auto-generated method stub
		return null;
	}

}
