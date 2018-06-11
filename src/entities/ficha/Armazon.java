/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.ficha;

import entities.abstractclasses.SyncStringId;
import java.util.Date;

/**
 *
 * @author Lenovo G470
 */
public class Armazon extends SyncStringId{
    
    private int tipo;
    private String marca;
    private String cristal;
    private String add;
    private String odA;
    private String odEsf;
    private String odCil;
    private String oiA;
    private String oiEsf;
    private String oiCil;
    private int dp;
    private int endurecido;
    private int capa;
    private int plusMax;
    private String IdFicha;

    public Armazon() {
    }

    public Armazon(String cod, int tipo, String marca, String cristal, String add, 
            String odA, String odEsf, String odCil, String oiA, String oiEsf, 
            String oiCil, int dp, int endurecido, int capa, int plusMax, String IdFicha, 
            int estado, Date lastUpdate, int lastHour) {
        setCod(cod);
        setTipo(tipo);
        setCristal(cristal);
        setAdd(add);
        setOdA(odA);
        setOdEsf(odEsf);
        setOdCil(odCil);
        setOiA(oiA);
        setOiEsf(oiEsf);
        setOiCil(oiCil);
        setDp(dp);
        setEndurecido(endurecido);
        setCapa(capa);
        setPlusMax(plusMax);
        setIdFicha(IdFicha);
        setMarca(marca);
        setEstado(estado);
        setLastUpdate(lastUpdate);
        setLastHour(lastHour);
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public void setCristal(String cristal) {
        this.cristal = cristal;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    public void setOdA(String od) {
        this.odA = od;
    }

    public void setOdEsf(String odEsf) {
        this.odEsf = odEsf;
    }

    public void setOdCil(String odCilA) {
        this.odCil = odCilA;
    }

    public void setOiA(String oi) {
        this.oiA = oi;
    }

    public void setOiEsf(String oiEsf) {
        this.oiEsf = oiEsf;
    }

    public void setOiCil(String oiCilA) {
        this.oiCil = oiCilA;
    }

    public void setDp(int dp) {
        this.dp = dp;
    }

    public void setEndurecido(int endurecido) {
        this.endurecido = endurecido;
    }

    public void setCapa(int capa) {
        this.capa = capa;
    }

    public void setPlusMax(int plusMax) {
        this.plusMax = plusMax;
    }

    public void setIdFicha(String IdFicha) {
        this.IdFicha = IdFicha;
    }
    
    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getTipo() {
        return tipo;
    }

    public String getCristal() {
        return cristal;
    }

    public String getAdd() {
        return add;
    }

    public String getOdA() {
        return odA;
    }

    public String getOdEsf() {
        return odEsf;
    }

    public String getOdCil() {
        return odCil;
    }

    public String getOiA() {
        return oiA;
    }

    public String getOiEsf() {
        return oiEsf;
    }

    public String getOiCil() {
        return oiCil;
    }

    public int getDp() {
        return dp;
    }

    public int getEndurecido() {
        return endurecido;
    }

    public int getCapa() {
        return capa;
    }

    public int getPlusMax() {
        return plusMax;
    }

    public String getMarca() {
        return marca;
    }
     
    public String getIdFicha() {
        return IdFicha;
    }

    @Override
    public String toString() {
        return "Armazon{" + "cod=" + getCod()+"\n"
                + ", tipo=" + tipo+"\n"  
                + ", cristal=" + cristal+"\n"  
                + ", add=" + add+"\n"  
                + ", od=" + odA+"\n"  
                + ", odEsf=" + odEsf+"\n"  
                + ", odCilA=" + odCil+"\n"  
                + ", oi=" + oiA+"\n" 
                + ", oiEsf=" + oiEsf+"\n"  
                + ", oiCilA=" + oiCil+"\n"  
                + ", dp=" + dp+"\n"  
                + ", endurecido=" + endurecido+"\n"  
                + ", capa=" + capa+"\n"  
                + ", plusMax=" + plusMax+"\n"
                + ", IdFicha=" + IdFicha+"\n"
                + ", estado=" + getEstado()+"\n"
                + ", lastUpdate=" + getLastUpdate() + '}';
    }
}
