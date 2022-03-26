package com.hugosql.regresionlineal;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Scanner;

/**
 *
 * @author victo
 */
public class Extraedatos {
    
    public Extraedatos() {
        
    }
    
    public void Comandas() {
        try {
            File fdestino=new File("lasdeliciascomandas.csv");
            FileWriter fw=new FileWriter(fdestino);
            PrintWriter pw=new PrintWriter(fw);
            pw.println("IDNOTAMOV,FECHA,NOTA,IDPLATILLO,CODIGO,LINEA,PLATILLO,VIENEDE,PRECIO,COSTO,CANTIDAD,PRODUCIDO,IMPRESO");
            Comandasdata midato=new Comandasdata();
            
            File forigen=new File("notamov2021.sql");
            Scanner lector = new Scanner(forigen);
            int i=0;
            while (lector.hasNextLine()) {
                String cade = lector.nextLine();
                if (cade.contains(Librerias.INSERTA) && !cade.contains(Librerias.DESECHABLE)) {
                    midato=new Comandasdata();
                    midato.setDatos(cade);

                    pw.println(midato.getDatos());
                    i++;
                }
            }
            lector.close();
            pw.close();
            fw.close();
            System.out.println("Proceso Terminado "+i);
        } catch (Exception e) {
            System.out.println("Comandas: "+e.getLocalizedMessage());
        }
    }
    public void Platillos() {
        try {
            File fdestino=new File("lasdeliciasplatillos.csv");
            FileWriter fw=new FileWriter(fdestino);
            PrintWriter pw=new PrintWriter(fw);
            pw.println("IDPLATILLO,PLATILLO,CALORIAS");
            Platillosdata midato=new Platillosdata();
            
            File forigen=new File("platillos.sql");
            Scanner lector = new Scanner(forigen);
            int i=0;
            while (lector.hasNextLine()) {
                String cade = lector.nextLine();
                if (cade.contains(Librerias.INSERTA)) {
                    midato=new Platillosdata();
                    midato.setDatos(cade);

                    pw.println(midato.getDatos());
                    i++;
                }
            }
            lector.close();
            pw.close();
            fw.close();
            System.out.println("Proceso Terminado "+i);
        } catch (Exception e) {
            System.out.println("Platillos: "+e.getLocalizedMessage());
        }
    }
    public void Covides() {
        try {
            File fdestino=new File("casoscovid2021.csv");
            FileWriter fw=new FileWriter(fdestino);
            PrintWriter pw=new PrintWriter(fw);
            
            Covidesdata midato=new Covidesdata();
            
            File forigen=new File("casoscovidsalud.csv");
            Scanner lector = new Scanner(forigen);
            int i=0;
            boolean primero=true;
            while (lector.hasNextLine()) {
                String cade = lector.nextLine();
                if (primero) {
                    primero=false;
                    midato=new Covidesdata();
                    midato.setCabeza(cade);
                    //cabeza
                    pw.println(midato.getCabeza());
                } else {
                    midato=new Covidesdata();
                    midato.setDatos(cade);
                    
                    pw.println(midato.getDatos());
                    i++;
                }
            }
            lector.close();
            pw.close();
            fw.close();
            //System.out.println("Proceso Terminado "+i);
        } catch (Exception e) {
            System.out.println("Covides: "+e.getLocalizedMessage());
        }
    }
    public void Covides2() {
        try {
            File fdestino=new File("casoscovid2021.csv");
            FileWriter fw=new FileWriter(fdestino);
            PrintWriter pw=new PrintWriter(fw);
            String cabeza="cve_ent,poblacion,nombre,";
            for (int i=0;i<Librerias.ANUALIDAD;i++) {
                cabeza+=Librerias.getFechacorta(i)+Librerias.COMMA;
            }
            pw.println(cabeza);
            
            Covidesdata midato=new Covidesdata();
            
            File forigen=new File("casoscovidsalud.csv");
            Scanner lector = new Scanner(forigen);
            int i=0;
            boolean primero=true;
            while (lector.hasNextLine()) {
                String cade = lector.nextLine();
                if (primero) {
                    primero=false;
                } else {
                    midato=new Covidesdata();
                    midato.setDatos(cade);
                    
                    pw.println(midato.getDatos());
                    i++;
                }
            }
            lector.close();
            pw.close();
            fw.close();
            System.out.println("Proceso Terminado "+i);
        } catch (Exception e) {
            System.out.println("Covides2: "+e.getLocalizedMessage());
        }
    }
    
}
