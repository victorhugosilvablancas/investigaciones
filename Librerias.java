package com.hugosql.regresionlineal;

import java.sql.Timestamp;

/**
 *
 * @author victo
 */
public class Librerias {
    public static final String INSERTA="Insert";
    public static final String DESECHABLE="DESECHABLE";
    public static final String COMMA=",";
    public static final int DIA=60*60*24*1000;
    public static final Timestamp ENEROUNO=Timestamp.valueOf("2021-01-01 01:01:01.1");
    public static final int ANUALIDAD=365;
    public static final String PERIODO_DE_ESTUDIO="2021";
    
    public Librerias() {
        
    }
    public static String getFecha(String cadena) {
        String afecha=cadena.replace("to_timestamp('","");
        afecha=afecha.replace("'","");
        String[] partes=afecha.split(" ");
        String[] fechas=partes[0].split("/");
        
        return "20"+fechas[2]+"-"+fechas[1]+"-"+fechas[0]+" "+partes[1];
    }
    public static String getFechacorta(int idia) {
        Timestamp f=new Timestamp(ENEROUNO.getTime()+idia*DIA);
        String afecha=f.toString();
        String[] partes=afecha.split(" ");
        String[] fechas=partes[0].split("-");
        return fechas[2]+"-"+fechas[1]+"-"+fechas[0];
    }
    public static String getFechacorta(Timestamp f) {
        String afecha=f.toString();
        String[] partes=afecha.split(" ");
        String[] fechas=partes[0].split("-");
        return fechas[2]+"-"+fechas[1]+"-"+fechas[0];
    }
}
