package com.ipartek.formacion.basedatos.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.ipartek.formacion.basedatos.bean.Persona;

/**
 * DAO: Data Access Object Clase especializada en mapera una {@code Persona}
 * contra la Base Datos. Dispone de los metodos basicos para realizar las
 * operaciones de CRUD
 *
 * @author ur00
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

			while (rs.next()) {
				resul.add(mapeo(rs));
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
		int resul = 0;

		try {
			Persona p = (Persona) o;
			Connection con = DataBaseHelper.getConnection();
			String sqlFecha = "INSERT INTO `test` (`nombre`, `nota`, `telefono`,`fecha`) VALUES (?,?,?,?)";
			String sqlNoFecha = "INSERT INTO `test` (`nombre`, `nota`, `telefono`) VALUES (?,?,?)";
			PreparedStatement pst = null;
			if (p.getFecha() != null) {
				pst = con.prepareStatement(sqlFecha);
				pst.setTimestamp(4, p.getFecha());
			} else {
				pst = con.prepareStatement(sqlNoFecha);
			}

			pst.setString(1, p.getNombre());
			pst.setFloat(2, p.getNota());
			pst.setString(3, p.getTelefono());

			if (pst.executeUpdate() == 1) {
				ResultSet rsKeys = pst.getGeneratedKeys();
				if (rsKeys.next()) {
					resul = rsKeys.getInt(1);
				} else {
					throw new SQLException("No se ha podido generar ID");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataBaseHelper.closeConnection();
		}

		return resul;
	}

	@Override
	public Object getById(int id) {
		Object resul = new Object();
		try {
			Connection con = DataBaseHelper.getConnection();
			String sql = "SELECT * FROM `test` WHERE id=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				resul = mapeo(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataBaseHelper.closeConnection();
		}
		return resul;
	}

	@Override
	public boolean update(Object o) {

		int iResul = 0;
		Boolean resul = false;
		try {
			Persona p = (Persona) o;
			Connection con = DataBaseHelper.getConnection();
			String sql = "UPDATE `test` SET `nombre`=?, `nota`=?, `telefono` =?, `fecha` = ? WHERE `id`=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, p.getNombre());
			pst.setFloat(2, p.getNota());
			pst.setString(3, p.getTelefono());
			pst.setTimestamp(4, p.getFecha());
			pst.setInt(5, p.getId());

			iResul = pst.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataBaseHelper.closeConnection();
		}

		if (iResul != 0) {
			resul = true;
		}

		return resul;
	}

	@Override
	public boolean delete(int id) {
		boolean resul = false;
		try {
			Connection con = DataBaseHelper.getConnection();
			Statement st = con.createStatement();
			String sql = "DELETE FROM `test` where `id`=" + id;
			resul = st.execute(sql);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataBaseHelper.closeConnection();
		}
		return resul;
	}

	@Override
	public ArrayList<Object> getAprobados() {
		ArrayList<Object> resul = new ArrayList<Object>();
		try {
			Connection con = DataBaseHelper.getConnection();
			Statement st = con.createStatement();
			String sql = "SELECT * FROM `test` where `nota` >= 5 ";
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				resul.add(mapeo(rs));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataBaseHelper.closeConnection();
		}

		return resul;
	}

	@Override
	public ArrayList<Object> getSuspendidos() {
		ArrayList<Object> resul = new ArrayList<Object>();
		try {
			Connection con = DataBaseHelper.getConnection();
			Statement st = con.createStatement();
			String sql = "SELECT * FROM `test` where `nota` < 5 ";
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				resul.add(mapeo(rs));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataBaseHelper.closeConnection();
		}

		return resul;
	}

	/**
	 * Mapea un ResulSet a una Persona
	 *
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private Persona mapeo(ResultSet rs) throws SQLException {

		Persona p = new Persona(rs.getString("nombre"));
		p.setId(rs.getInt("id"));
		p.setFecha(rs.getTimestamp("fecha"));
		p.setTelefono(rs.getString("telefono"));
		p.setNota(rs.getFloat("nota"));

		return p;
	}

}
