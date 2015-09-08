package com.ipartek.formacion.basedatos.utilidades;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilFecha {
		
	/**
	 * Metodo que parsea un String a un Timestamp
	 * @param 	fecha en formato {@code String}
	 * @return 	retorna la fecha en formato {@code Timestamp} 
	 * @throws 	ParseException
	 */
	public static Timestamp parse_string_timestamp(String fecha) throws ParseException {				
	    Timestamp resul;
	    
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
	    Date parsedTimeStamp = dateFormat.parse(fecha);
	    resul = new Timestamp(parsedTimeStamp.getTime());
	    
	    return resul;
	}
		
	
	/**
	 * Comvierte una fecha obtenida en un <input type="datatime-local"> String a Timestamp
	 * @param sfecha {@code String} con formato: "yyyy-MM-ddThh:mm"
	 * @return  {@code Timestamp} fecha formateada, null en caso de exception.
	 */
	public static Timestamp datetime_StringToTimestamp(String sfecha){
		Timestamp resul = null;
		try {
			String fecha = sfecha.replace("T", " ");
			fecha += ":00";
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-ddThh:mm");
		    Date parsedTimeStamp = dateFormat.parse(fecha);
		    resul = new Timestamp(parsedTimeStamp.getTime());			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resul;
	}
	
	

}


