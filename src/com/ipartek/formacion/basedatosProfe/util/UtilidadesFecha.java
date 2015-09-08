package com.ipartek.formacion.basedatosProfe.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilidadesFecha {

	/**
	 * Convierte una fecha en {@code String} a {@code Timestamp}
	 * @param sFecha {@code String} formato "yyyy-MM-ddThh:mm"
	 * @return fecha formateada, null en caso de excepción
	 */
	public static Timestamp stringToTimeStamp(String sFecha){
		Timestamp resul = null;
		
		try{
			String FechaModificada = sFecha.replace("T", " ");
			FechaModificada += ":00";
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); //MM ya que sino serían minutos
		    Date parsedDate = dateFormat.parse(FechaModificada);
		    resul = new java.sql.Timestamp(parsedDate.getTime());
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return resul;
	}
	
	
	/**
	 * Convierte una fecha en {@code Timestamp} a {@code String} para usarlo en las JSPs para un input type="datetime-local"
	 * @param tFecha {@code Timestamp} fecha en milisegundos
	 * @return fecha formateada "yyyy-MM-ddThh:mm" o "" si falla
	 */
	public static String timestampToString(Timestamp tFecha){
		String resul = "";
		
		try{
			//yyyy-mm-dd hh:mm:ss.fffffffff
			resul = tFecha.toString();
			resul = resul.replace(" ",  "T");
			//resul = resul.split(".")[0]; o resul = resul.split("\\.")[0];//Hago un array de la cadena por "." y me lo guarda en un array pero me quedo el primer valor del array
			resul = resul.substring(0, 16);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return resul;
	}
}
