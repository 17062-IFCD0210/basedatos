package com.ipartek.formacion.basedatos.modelo;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ipartek.formacion.basedatos.bean.Persona;

public class TestDAOPersona {
	
	static DAOPersona dao = null;
	
	static String pNombre = "Junit4_test";
	static float pNota = 7;
	static String pTelefono = "123123123";
	
	static String pNombre_updated = "Junit4_test_updated";
	static float pNota_updated = 0;
	static String pTelefono_updated = "789789789";
	static Timestamp pFecha = new Timestamp(115, 12, 12, 12, 12, 12, 0);

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao = new DAOPersona();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		dao = null;
	}

	@Test
	public void test() {
					
		int num_alum1;		//Cantidad de registros iniciales en la tabla `test`
		int num_alum2;		//Cantidad de registros en la tabla `test` tras insertar uno nuevo	
		int id;		
		
		//Test para comprobar que al insertar un nuevo registro aumenta el ID
		Persona p_insert = new Persona(pNombre);
		p_insert.setNota(pNota);
		p_insert.setTelefono(pTelefono);
		
		num_alum1 = dao.getAll().size();
		id = dao.save(p_insert);
		num_alum2 = dao.getAll().size();	
		
		assertTrue("", num_alum1 == num_alum2-1);
		assertNotNull(id);
		
		
		//Test para comprobar que al obtener un registro por su ID lo devuelve correctamente
		Persona p_get;		
		p_get = (Persona)dao.getById(id);
		
		assertEquals(pNombre, p_get.getNombre());
		assertTrue(pNota == p_get.getNota());
		assertEquals(pTelefono, p_get.getTelefono());
		
		
		//Test para comprobar que actualiza el registro indentificado por su ID
		Persona p_update = new Persona(pNombre_updated);
		p_update.setId(id);
		p_update.setNota(pNota_updated);
		p_update.setTelefono(pTelefono_updated);
		p_update.setFecha(pFecha);
		
		assertTrue(dao.update(p_update));
		
		p_get = (Persona)dao.getById(id);
		
		assertEquals(pNombre_updated, p_get.getNombre());
		assertTrue(pNota_updated == p_get.getNota());
		assertEquals(pTelefono_updated, p_get.getTelefono());
		assertEquals(pFecha, p_get.getFecha());
		
		
		//Test para comprobar que elimina el registro
		assertTrue(dao.delete(id));
		assertTrue(num_alum1 == dao.getAll().size());		
		
	}

}
