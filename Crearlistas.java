package com.hugosql.regresionlineal;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Crea listas dinámicas para realizar tanto la normalización
 * como el resto de las operaciones.
 *
 * @author Victor Hugo Silva Blancas
 */
public class Crearlistas {
    public Crearlistas() {
        
    }
    
    public void getListacovides() {
        Covidesdata.listacovides=new ArrayList<>();
        Covidesdata.SumaCasos=0.0;
        Covidesdata midato=new Covidesdata();
        try {
            File forigen=new File("casoscovid2021.csv");
            Scanner lector = new Scanner(forigen);
            boolean primera=true;
            while (lector.hasNextLine()) {
                String cade = lector.nextLine();
                if (primera) {
                    primera=false;
                    Covidesdata.setFechas(cade);
                } else {
                    midato=new Covidesdata(cade);
                    if (midato.esvalido() && midato.ESTADO.equals("Nacional")) {
                        midato.getSuma();
                        Covidesdata.listacovides.add(midato);
                    }
                }
            }
            //System.out.println(isuma);
            lector.close();
        } catch (Exception e) {
            System.out.println("getListacovides "+e.getLocalizedMessage());
        }
    }
    public void getListaplatillos() {
        Platillosdata.listaplatillos=new ArrayList<>();
        try {
            Platillosdata midato=new Platillosdata();
            
            File forigen=new File("lasdeliciasplatillos.csv");
            Scanner lector = new Scanner(forigen);
            boolean primera=true;
            int i=0;
            while (lector.hasNextLine()) {
                String cade = lector.nextLine();
                if (primera) primera=false;
                else {
                    midato=new Platillosdata(cade);
                    if (midato.esvalido()) {
                        //TEMPORAL
                        if ((i % 7 == 0)) midato.TIPO=Covidesdata.CONSUMO_BEBIDAS;
                        if ((i % 11 == 0)) midato.TIPO=Covidesdata.CONSUMO_POSTRES;
                        
                        Platillosdata.listaplatillos.add(midato);
                        i++;
                    }
                }
            }
            lector.close();
        } catch (Exception e) {
            System.out.println("getListaplatillos "+e.getLocalizedMessage());
        }
        if (Platillosdata.listaplatillos.size()>0) {
            Platillosdata.listaplatillos.sort(new Comparator<Platillosdata>() {
                @Override
                public int compare(Platillosdata o1, Platillosdata o2) {
                    return o1.PLATILLO.toUpperCase().compareTo(o2.PLATILLO.toUpperCase());
                }
            });
            /*
            //comprobando
            for (int i=0;i<Platillosdata.listaplatillos.size();i++) 
                System.out.println(Platillosdata.listaplatillos.get(i).PLATILLO);
            */
        }
    }
    public void getListacomandas() {
        Comandasdata.listacomandas=new ArrayList<>();
        Comandasdata midato=new Comandasdata();
        try {
            
            File forigen=new File("lasdeliciascomandas.csv");
            Scanner lector = new Scanner(forigen);
            boolean primera=true;
            while (lector.hasNextLine()) {
                String cade = lector.nextLine();
                if (primera) primera=false;
                else {
                    midato=new Comandasdata(cade);
                    if (midato.esvalido())
                        Comandasdata.listacomandas.add(midato);
                }
            }
            lector.close();
        } catch (Exception e) {
            System.out.println("getListacomandas "+e.getLocalizedMessage());
        }
        if (Comandasdata.listacomandas.size()>0) {
            Comandasdata.listacomandas.sort(new Comparator<Comandasdata>() {
                @Override
                public int compare(Comandasdata o1, Comandasdata o2) {
                    return o1.TIPO.compareTo(o2.TIPO);
                }
            });
            /*
            //comprobando
            for (int i=0;i<Comandasdata.listacomandas.size();i++) {
                midato=Comandasdata.listacomandas.get(i);
                System.out.println(midato.NOTA+","+midato.TIPO+","+midato.CALORIAS);
            }
            System.out.println(Comandasdata.listacomandas.size());
            */
        }
    }
    
    
}
