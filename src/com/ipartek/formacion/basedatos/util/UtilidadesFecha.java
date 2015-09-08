package com.ipartek.formacion.basedatos.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;



public class UtilidadesFecha {

	/**
	 * Convierte una fecha en {@code String} a {@code Timestamp}
	 * @param sFecha {@code String} formato: "yyyy-MM-ddThh:mm"
	 * @return fecha formateada, null en caso de excepcion
	 */
	public static Timestamp stringToTimeStamp( String sFecha ){
		Timestamp resul = null;
		try{
			String fechaModificada = sFecha.replace("T"," ");
			fechaModificada+=":00";
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		    Date parsedDate = dateFormat.parse(fechaModificada);
		    resul = new java.sql.Timestamp(parsedDate.getTime());
		}catch(Exception e){
			e.printStackTrace();
		}		
		return resul;
	}
	
	/**
	 * Convierte una fecha en {@code Timestamp} a {@code String} para usarlo en las JSPs en un input type="datetime-local"	 
	 * @param tFecha {@code Timestamp} fecha en milisegundos
	 * @return fecha formateada "yyyy-MM-ddThh:mm" o "" si falla
	 */
	public static String timestampToString( Timestamp tFecha ){
		String resul = "";
		try{
		  //yyyy-mm-dd hh:mm:ss.fffffffff	
		  resul = tFecha.toString();
		  resul = resul.replace(" ", "T");
		  resul = resul.substring(0, 16);
		  
		}catch(Exception e){
			e.printStackTrace();
		}
		return resul;
	}
	
	
	
	
}
