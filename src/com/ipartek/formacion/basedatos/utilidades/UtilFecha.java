package com.ipartek.formacion.basedatos.utilidades;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilFecha {
		
	/**
	 * Metodo que parsea un String a un Timestamp
	 * @param 	fecha fecha en formato {@code String}
	 * @return 	retorna la fecha en formato {@code Timestamp} 
	 * @throws 	ParseException
	 */
	public static Timestamp parse_string_timestamp (String fecha) throws ParseException {				
	    Timestamp resul;
	    
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
	    Date parsedTimeStamp = dateFormat.parse(fecha);
	    resul = new Timestamp(parsedTimeStamp.getTime());
	    
	    return resul;
	}
		
	
}


