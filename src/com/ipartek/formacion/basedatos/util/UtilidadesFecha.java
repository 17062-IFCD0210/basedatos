package com.ipartek.formacion.basedatos.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilidadesFecha {
	/**
	 * Convierte una fecha en {@code String} a {@code Timestamp}
	 * @param fecha {@code String} formato: 'yyyy-MM-dd hh:mm'
	 * @return fecha formateada, null en caso de excepcion
	 */
	public static Timestamp stringToTimestamp(String fecha) {
		Timestamp resul = null;

		try{
			String fechaModificada = fecha.replace("T", " ");
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		    Date parsedDate = dateFormat.parse(fechaModificada);
		    resul = new java.sql.Timestamp(parsedDate.getTime());
		} catch(Exception e) {
			e.printStackTrace();
		}
			
		return resul;
	}
	
	/**
	 * Convierte una fecha de {@code Timestamp} a {@code String}
	 * @param fecha {@code Timestamp} fecha en milisegundos
	 * @return fecha formateada 'yyyy-MM-ddThh:mm' o '' si falla
	 */
	public static String timestampToString(Timestamp fecha) {
		String resul = "";
		try {
			resul = fecha.toString();
			resul = resul.replace(" ", "T");
			//Pasamos el String a un array, y nos quedamos con la 1º parte (yyyy-MM-ddThh:mm)
			resul = resul.substring(0,16); 
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return resul;
	}
}
