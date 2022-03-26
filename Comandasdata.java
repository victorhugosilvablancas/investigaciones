package com.hugosql.regresionlineal;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase para la configuración de los datos 
 * de las comandas del restaurante de comida rápida.
 * 
 * @author Victor Hugo Silva Blancas
 */
public class Comandasdata {
    String cadena;
    Integer IDNOTAMOV;
    Timestamp FECHA;
    Integer NOTA;
    Integer IDPLATILLO;
    String CODIGO;
    String LINEA;
    String PLATILLO;
    Integer VIENEDE;
    Double PRECIO;
    Double COSTO;
    Double CANTIDAD;
    Integer PRODUCIDO;
    Integer IMPRESO;
    String FECHACABEZAL;
    Integer TIPO;
    Double CALORIAS;
    
    public static List<Comandasdata> listacomandas=new ArrayList<>();

    public Comandasdata() {
        cadena="";
        IDNOTAMOV=0;
        FECHA=new Timestamp(System.currentTimeMillis());
        NOTA=0;
        IDPLATILLO=0;
        CODIGO="";
        LINEA="";
        PLATILLO="";
        VIENEDE=0;
        PRECIO=0.0;
        COSTO=0.0;
        CANTIDAD=0.0;
        PRODUCIDO=0;
        IMPRESO=0;
        FECHACABEZAL="";
        TIPO=Covidesdata.CONSUMO_GRASAS;
        CALORIAS=0.0;
    }
    public Comandasdata(String cade) {
        cadena=cade;
        String[] partes=cadena.split(Librerias.COMMA);
        
        IDNOTAMOV=Integer.valueOf(partes[0]);
        FECHACABEZAL=partes[1];
        NOTA=Integer.valueOf(partes[2]);
        IDPLATILLO=Integer.valueOf(partes[3]);
        CODIGO=partes[4];
        LINEA=partes[5];
        PLATILLO=partes[6];
        VIENEDE=Integer.valueOf(partes[7]);
        PRECIO=Double.valueOf(partes[8]);
        COSTO=Double.valueOf(partes[9]);
        CANTIDAD=Double.valueOf(partes[10]);
        PRODUCIDO=Integer.valueOf(partes[11]);
        IMPRESO=Integer.valueOf(partes[12]);
        TIPO=Platillosdata.getTipo(IDPLATILLO);
        CALORIAS=Platillosdata.getCalorias(IDPLATILLO);
    }
    public void setDatos(String acadena) {
        this.cadena=acadena;
        int idx=cadena.indexOf("(")+1;
        idx=cadena.indexOf("(",idx);
        int fdx=cadena.length()-2;
        cadena=cadena.substring(idx+1,fdx);
        String[] partes=cadena.split(Librerias.COMMA);
        if (partes.length==14) {
            //System.out.println(cadena);
            IDNOTAMOV=Integer.valueOf(partes[0]);

            String afecha=Librerias.getFecha(partes[1]);
            FECHA=Timestamp.valueOf(afecha);
            NOTA=Integer.valueOf(partes[3]);
            IDPLATILLO=Integer.valueOf(partes[4]);
            CODIGO=partes[5];
            LINEA=partes[6];
            PLATILLO=partes[7];
            VIENEDE=Integer.valueOf(partes[8]);
            PRECIO=Double.valueOf(partes[9]);
            COSTO=Double.valueOf(partes[10]);
            CANTIDAD=Double.valueOf(partes[11]);
            PRODUCIDO=Integer.valueOf(partes[12]);
            IMPRESO=Integer.valueOf(partes[13]);
        }
    }
    public String getDatos() {
        return IDNOTAMOV+Librerias.COMMA
                + Librerias.getFechacorta(FECHA)+Librerias.COMMA
                + NOTA+Librerias.COMMA
                + IDPLATILLO+Librerias.COMMA
                + CODIGO+Librerias.COMMA
                + LINEA+Librerias.COMMA
                + PLATILLO+Librerias.COMMA
                + VIENEDE+Librerias.COMMA
                + PRECIO+Librerias.COMMA
                + COSTO+Librerias.COMMA
                + CANTIDAD+Librerias.COMMA
                + PRODUCIDO+Librerias.COMMA
                + IMPRESO
                ;
    }
    
    public Boolean esvalido() {
        return CALORIAS>0;
    }
    
    public static Double BebidasFecha=0.0;
    public static Double GrasasFecha=0.0;
    public static Double PostresFecha=0.0;
    public static void getCalorias(Integer iposicion) {
        Comandasdata.BebidasFecha=0.0;
        Comandasdata.GrasasFecha=0.0;
        Comandasdata.PostresFecha=0.0;
        Comandasdata midato=new Comandasdata();
        for (int i=0;i<Comandasdata.listacomandas.size();i++) {
            midato=Comandasdata.listacomandas.get(i);
            if (midato.FECHACABEZAL.equals(Covidesdata.ListaDeFechas[iposicion])) {
                switch (midato.TIPO) {
                    case Covidesdata.CONSUMO_BEBIDAS:
                        Comandasdata.BebidasFecha+=midato.CALORIAS;
                        break;
                    case Covidesdata.CONSUMO_GRASAS:
                        Comandasdata.GrasasFecha+=midato.CALORIAS;
                        break;
                    case Covidesdata.CONSUMO_POSTRES:
                        Comandasdata.PostresFecha+=midato.CALORIAS;
                        break;
                }
            }
        }
    }
} //Comandasdata