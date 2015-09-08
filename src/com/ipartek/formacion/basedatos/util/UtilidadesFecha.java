package com.ipartek.formacion.basedatos.util;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class UtilidadesFecha {
	
	
	/**
	 * Convierte una fecha de {@code String} a {@code Timesamp}
	 * @param sFecha {@code String} formato: "yyyy-mm-ddThh:mm"
	 * @return fecha formateada, null en caso de excepcion
	 */
	public static Timestamp stringToTimeStamp(String sFecha){
		
		Timestamp resul=null;
		
		try{
			
			String fechaModificada=sFecha.replace("T", " ");
			
			 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
			    Date parsedDate = (Date) dateFormat.parse(fechaModificada);
			    resul = new java.sql.Timestamp(parsedDate.getTime());
		
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return resul;
		
		
	}
	
	
	
	

}
