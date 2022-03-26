package com.hugosql.regresionlineal;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author victo
 */
public class Platillosdata {
    String cadena;
    Integer IDPLATILLO;
    String PLATILLO;
    Integer TIPO;
    Double CALORIAS;
    
    public static List<Platillosdata> listaplatillos=new ArrayList<>();

    public Platillosdata() {
        cadena="";
        IDPLATILLO=0;
        PLATILLO="";
        TIPO=Covidesdata.CONSUMO_GRASAS;
        CALORIAS=0.0;
    }
    public Platillosdata(String cade) {
        cadena=cade;
        String[] partes=cadena.split(Librerias.COMMA);
        IDPLATILLO=Integer.valueOf(partes[0]);
        PLATILLO=partes[1].replace("'","");
        //TIPO=Integer.valueOf(partes[3]);
        TIPO=Covidesdata.CONSUMO_GRASAS;
        CALORIAS=Double.valueOf(partes[2]);
    }
    public void setDatos(String acadena) {
        this.cadena=acadena;
        int idx=cadena.indexOf("(")+1;
        idx=cadena.indexOf("(",idx);
        int fdx=cadena.length()-2;
        cadena=cadena.substring(idx+1,fdx);
        String[] partes=cadena.split(Librerias.COMMA);

        IDPLATILLO=Integer.valueOf(partes[0]);
        PLATILLO=partes[1];
        CALORIAS=Double.valueOf(partes[13]);
         //TEMPORAL
        TIPO=Covidesdata.CONSUMO_GRASAS;
        CALORIAS=100.0;
    }
    public String getDatos() {
        return IDPLATILLO+Librerias.COMMA
                + PLATILLO+Librerias.COMMA
                + CALORIAS
                ;
    }
    public Boolean esvalido() {
        Boolean pasa=PLATILLO!=null;
        if (pasa) pasa=PLATILLO.length()>0;
        if (pasa) pasa=!PLATILLO.equals("null");
        return pasa;
    }
    
    public static Integer getTipo(Integer iplatillo) {
        Integer itipo=0;
        for (int i=0;i<Platillosdata.listaplatillos.size();i++) {
            if (Platillosdata.listaplatillos.get(i).IDPLATILLO.equals(iplatillo)) {
                itipo=Platillosdata.listaplatillos.get(i).TIPO;
            }
        }
        return itipo;
    }
    public static Double getCalorias(Integer iplatillo) {
        Double rcalorias=0.0;
        for (int i=0;i<Platillosdata.listaplatillos.size();i++) {
            if (Platillosdata.listaplatillos.get(i).IDPLATILLO.equals(iplatillo)) {
                rcalorias=Platillosdata.listaplatillos.get(i).CALORIAS;
            }
        }
        return rcalorias;
    }

} //Platillosdata