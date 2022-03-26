package com.hugosql.regresionlineal;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author victo
 */
public class Covidesdata {
    String cadena;
    Integer IDENTIDAD;
    String ESTADO;
    Integer POBLACION;
    Double[] casos;
    Double[] bebidas;
    Double[] grasas;
    Double[] postres;
    
    public static List<Covidesdata> listacovides=new ArrayList<>();
    
    //normalización
    public static Double SumaCasos  =0.0;
    public static Double SumaBebidas=0.0;
    public static Double SumaGrasas =0.0;
    public static Double SumaPostres=0.0;
    public static String[] ListaDeFechas=new String[Librerias.ANUALIDAD];

    public Covidesdata() {
        cadena="";
        IDENTIDAD=0;
        ESTADO="";
        POBLACION=0;
        casos=new Double[Librerias.ANUALIDAD];
        bebidas=new Double[Librerias.ANUALIDAD];
        grasas=new Double[Librerias.ANUALIDAD];
        postres=new Double[Librerias.ANUALIDAD];
        for (int i=0;i<Librerias.ANUALIDAD;i++) {
            casos[i]=0.0;
            bebidas[i]=0.0;
            grasas[i]=0.0;
            postres[i]=0.0;
        }
    }
    public Covidesdata(String cade) {
        cadena=cade;
        String[] partes=cadena.split(Librerias.COMMA);
        IDENTIDAD=Integer.valueOf(partes[0]);
        ESTADO=partes[1].replace("'","");
        POBLACION=Integer.valueOf(partes[2]);
        
        casos=new Double[Librerias.ANUALIDAD];
        bebidas=new Double[Librerias.ANUALIDAD];
        grasas=new Double[Librerias.ANUALIDAD];
        postres=new Double[Librerias.ANUALIDAD];
        for (int i=0;i<Librerias.ANUALIDAD;i++) {
            casos[i]=0.0;
            bebidas[i]=0.0;
            grasas[i]=0.0;
            postres[i]=0.0;
        }
        int j=0;
        for (int i=0;i<Librerias.ANUALIDAD;i++) {
            casos[j]=Double.valueOf(partes[i+3]);
            j++;
        }
    }
    public static void setFechas(String cade) {
        String[] partes=cade.split(Librerias.COMMA);
        ListaDeFechas=new String[Librerias.ANUALIDAD];
        for (int i=0;i<Librerias.ANUALIDAD;i++) {
            ListaDeFechas[i]=partes[i+3];
        }
        /*
        //comprobando
        for (int i=0;i<Librerias.ANUALIDAD;i++) {
            System.out.println(ListaDeFechas[i]);
        }*/
    }
    public void getSuma() {
        for (int i=0;i<Librerias.ANUALIDAD;i++) {
            Covidesdata.SumaCasos+=casos[i];
        }
        
    }
    public static void NormalizandoCasos() {
        Covidesdata midato=Covidesdata.listacovides.get(0);
        for (int i=0;i<Librerias.ANUALIDAD;i++) {
            midato.casos[i]=midato.casos[i]/Covidesdata.SumaCasos;
            //System.out.printf("%.4f %n",midato.casos[i]);
        }
        Covidesdata.listacovides.set(0,midato);
        //comprobando
        /*
        for (int i=0;i<Librerias.ANUALIDAD;i++) {
            System.out.printf("%.4f %n",Covidesdata.listacovides.get(0).casos[i]);
        }
        System.out.println(Covidesdata.SumaAnual+":"+rantes+","+rsuma);*/
    }
    public static void NormalizandoConsumo() {
        //1.Sumando a cada fecha su concepto
        Covidesdata midato=Covidesdata.listacovides.get(0);
        for (int i=0;i<Librerias.ANUALIDAD;i++) {
            Comandasdata.getCalorias(i);
            midato.bebidas[i]+=Comandasdata.BebidasFecha;
            midato.grasas[i]+=Comandasdata.GrasasFecha;
            midato.postres[i]+=Comandasdata.PostresFecha;
            //System.out.println(i+":"+Covidesdata.ListaDeFechas[i]);
        }
        Covidesdata.listacovides.set(0,midato);
        
        //2.Totalizando
        Covidesdata.SumaBebidas=0.0;
        Covidesdata.SumaGrasas=0.0;
        Covidesdata.SumaPostres=0.0;
        midato=Covidesdata.listacovides.get(0);
        for (int i=0;i<Librerias.ANUALIDAD;i++) {
            Covidesdata.SumaBebidas+=midato.bebidas[i];
            Covidesdata.SumaGrasas+=midato.grasas[i];
            Covidesdata.SumaPostres+=midato.postres[i];
            //System.out.println(i+":"+Covidesdata.ListaDeFechas[i]);
        }
        /*
        System.out.println(Covidesdata.SumaBebidas);
        System.out.println(Covidesdata.SumaGrasas);
        System.out.println(Covidesdata.SumaPostres);*/
        
        //3.Normalizando
        //midato=Covidesdata.listacovides.get(0);
        for (int i=0;i<Librerias.ANUALIDAD;i++) {
            midato.bebidas[i]=midato.bebidas[i]/Covidesdata.SumaBebidas;
            midato.grasas[i]=midato.grasas[i]/Covidesdata.SumaGrasas;
            midato.postres[i]=midato.postres[i]/Covidesdata.SumaPostres;
            //System.out.printf("%.4f %n",midato.casos[i]);
        }
        
        /*
        //comprobando
        midato=Covidesdata.listacovides.get(0);
        String[] partes=Cabeza.split(Librerias.COMMA);
        for (int i=0;i<Librerias.ANUALIDAD;i++) {
            System.out.printf(partes[i+3]+" Bebidas %.4f ",midato.bebidas[i]);
            System.out.printf(" Grasas %.4f ",midato.grasas[i]);
            System.out.printf(" Postres %.4f %n",midato.postres[i]);
        }*/
    }
    public static void Graficacion() {
        Covidesdata midato=Covidesdata.listacovides.get(0);
        try {
            File fdestino=new File("graficacion.csv");
            FileWriter fw=new FileWriter(fdestino);
            PrintWriter pw=new PrintWriter(fw);
            pw.println("fecha,casos,bebidas,grasas,postres");
            String[] partes=Cabeza.split(Librerias.COMMA);
            for (int i=0;i<Librerias.ANUALIDAD;i++) {
                boolean pasa=midato.casos[i]>0;
                if (pasa) pasa=midato.bebidas[i]>0;
                if (pasa) pasa=midato.grasas[i]>0;
                if (pasa) pasa=midato.postres[i]>0;
                if (pasa)
                    pw.println(
                            partes[i+3]+Librerias.COMMA
                            + String.format("%.4f", midato.casos[i])+Librerias.COMMA
                            + String.format("%.4f", midato.bebidas[i])+Librerias.COMMA
                            + String.format("%.4f", midato.grasas[i])+Librerias.COMMA
                            + String.format("%.4f", midato.postres[i])
                            );
            }
            pw.close();
            fw.close();
            System.out.println("Gráfica Terminada");
        } catch (Exception e) {
            System.out.println("Graficacion: "+e.getLocalizedMessage());
        }
    } //Graficacion
    
    private static String Cabeza;
    private static List<Integer> Posiciones=new ArrayList<>();
    public void setCabeza(String acadena) {
        Cabeza="cve_ent,poblacion,nombre";
        Posiciones=new ArrayList<>();
        String[] campos=acadena.split(Librerias.COMMA);
        int k=3;
        for (int i=k;i<campos.length;i++) {
            if (campos[i].contains(Librerias.PERIODO_DE_ESTUDIO)) {
                Cabeza+=Librerias.COMMA+campos[i];
                Posiciones.add(i);
            }
        }
    }
    public String getCabeza() {
        return Cabeza;
    }
    public void setDatos(String acadena) {
        this.cadena=acadena;
        String[] campos=cadena.split(Librerias.COMMA);
        IDENTIDAD=Integer.valueOf(campos[0].replace("\"", ""));
        POBLACION=Integer.valueOf(campos[1].replace("\"", ""));
        ESTADO=campos[2].replace("\"", "'");
        int k=3 //entidad
            + (Librerias.ANUALIDAD+1) //año bisiesto 2020
            ;
        
        int j=0;
        for (int i=0;i<Posiciones.size();i++) {
            int iposicion=Posiciones.get(i);
            casos[j]=Double.valueOf(campos[iposicion]);
            j++;
        }
        /*
        int n=k+Librerias.ANUALIDAD;
        int j=0;
        for (int i=k;i<n;i++) {
            casos[j]=Double.valueOf(campos[i]);
            j++;
        }*/
    }
    public String getDatos() {
        String cade=IDENTIDAD+Librerias.COMMA
                + ESTADO+Librerias.COMMA
                + POBLACION
                ;
        for (int i=0;i<Librerias.ANUALIDAD;i++) {
            cade+=Librerias.COMMA+casos[i];
            //if ((i+1)==ANUALIDAD) cade+=casos[i];
            //else cade+=casos[i]+COMMA;
        }
        return cade;
    }
    public Boolean esvalido() {
        return true;
    }
    
    public static final int CONSUMO_BEBIDAS=-1;
    public static final int CONSUMO_GRASAS =0;
    public static final int CONSUMO_POSTRES=1;

} //Covidesdata