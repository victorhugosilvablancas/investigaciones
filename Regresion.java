package com.hugosql.regresionlineal;

/**
 * Clase mejorada cambiando los tipos primitivos por objetos
 * como <b>Double</b> por <b>Double</b> 
 * Los parámetros Double[] se reciben como java.util.List
 * 
 * @author Ángel Franco García, 2001
 */
public class Regresion {
    private Double[] x;
    private Double[] y;
    private int n;          //número de datos
    public Double a, b;    //pendiente y ordenada en el origen
    public Regresion() {
        x=new Double[0];
        y=new Double[0];
        n=0;
        a=0.0;
        b=0.0;
    }
    public void setValores(Double[] x, Double[] y) {
        this.x=x;
        this.y=y;
        n=x.length; //número de datos
    }
    public void setValoresBebidas(Covidesdata midato) {
        this.x=midato.casos;
        this.y=midato.bebidas;
        n=x.length; //número de datos
    }
    public void setValoresGrasas(Covidesdata midato) {
        this.x=midato.casos;
        this.y=midato.grasas;
        n=x.length; //número de datos
    }
    public void setValoresPostres(Covidesdata midato) {
        this.x=midato.casos;
        this.y=midato.postres;
        n=x.length; //número de datos
    }
    public void lineal(){
        Double pxy, sx, sy, sx2, sy2;
        pxy=sx=sy=sx2=sy2=0.0;
        for(int i=0; i<n; i++){
            sx+=x[i];
            sy+=y[i];
            sx2+=x[i]*x[i];
            sy2+=y[i]*y[i];
            pxy+=x[i]*y[i];
        }
        a=(n*pxy-sx*sy)/(n*sx2-sx*sx);
        b=(sy-b*sx)/n;
    }
    public Double correlacion(){
//valores medios
        Double suma=0.0;
        for(int i=0; i<n; i++){
            suma+=x[i];
        }
        Double mediaX=suma/n;

        suma=0.0;
        for(int i=0; i<n; i++){
            suma+=y[i];
        }
        Double mediaY=suma/n;
//coeficiente de correlación
        Double pxy, sx2, sy2;
        pxy=sx2=sy2=0.0;
        for(int i=0; i<n; i++){
            pxy+=(x[i]-mediaX)*(y[i]-mediaY);
            sx2+=(x[i]-mediaX)*(x[i]-mediaX);
            sy2+=(y[i]-mediaY)*(y[i]-mediaY);
        }
        return pxy/Math.sqrt(sx2*sy2);
    }
}
