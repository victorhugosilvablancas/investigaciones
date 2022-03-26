package com.hugosql.regresionlineal;

/**
 *
 * @author victo
 */
public class RegresionLineal {

    public static void main(String[] args) {
        //DATOS RECOLECTADOS
        new Extraedatos().Comandas();
        new Extraedatos().Platillos();
        new Extraedatos().Covides();
        
        //NORMALIZACIÓN
        new Crearlistas().getListaplatillos();
        new Crearlistas().getListacomandas();
        new Crearlistas().getListacovides();
        
        //Normalizando
        if (Covidesdata.listacovides.size()>0) {
            Covidesdata.NormalizandoCasos();
            Covidesdata.NormalizandoConsumo();
            Covidesdata.Graficacion();
            
            Covidesdata midato=Covidesdata.listacovides.get(0);
            Regresion regresion=new Regresion();
            regresion.setValoresBebidas(midato);
            regresion.lineal();
            System.out.printf("Casos y Bebidas, %.4f, %.4f, %.4f %n",regresion.a,regresion.b,regresion.correlacion());
            
            regresion.setValoresGrasas(midato);
            regresion.lineal();
            System.out.printf("Casos y Grasas, %.4f, %.4f, %.4f %n",regresion.a,regresion.b,regresion.correlacion());
            
            regresion.setValoresPostres(midato);
            regresion.lineal();
            System.out.printf("Casos y Postres, %.4f, %.4f, %.4f %n",regresion.a,regresion.b,regresion.correlacion());
        }
    }
}
