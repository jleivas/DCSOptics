/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reportes;

/**
 *
 * @author sdx
 */
public class ReportFicha {
    String date;
    String folio;
    String place;
    String dateHour;
    String name;
    String phone;
    String address;
    String comuna;
    String total;
    String abono;
    String saldo;
    String mar1;
    String cri1;
    String ode1;
    String odc1;
    String oda1;
    String oie1;
    String oic1;
    String oia1;
    String dp1;
    String e1;
    String c1;
    String p1;
    String mar2;
    String cri2;
    String add2;
    String ode2;
    String odc2;
    String oda2;
    String oie2;
    String oic2;
    String oia2;
    String dp2;
    String e2;
    String c2;
    String p2;
    String ob;
    String companyName;
    String web;
    String dir;
    String mail;
    String phones;
    String vm1;
    String vc1;
    String vm2;
    String vc2;
    String totales;
    String descuento;
    String abonos;
    String saldos;
    String companyPronombre;
    String companyRut;
    String companyGiro;
    String defaultMsg;
    String dspRut;
    String dspNombre;
    String dspFecha;

    public ReportFicha() {
    }

    public ReportFicha(String date, String folio, String place, String dateHour, String name, String phone, String address, String comuna, String total, String abono, String saldo, String mar1, String cri1, String ode1, String odc1, String oda1, String oie1, String oic1, String oia1, String dp1, String e1, String c1, String p1, String mar2, String cri2, String add2, String ode2, String odc2, String oda2, String oie2, String oic2, String oia2, String dp2, String e2, String c2, String p2, String ob, String companyName, String web, String dir, String mail, String phones, String vm1, String vc1, String vm2, String vc2, 
            String totales,String descuento, String abonos, String saldos, 
            String companyPronombre,
            String companyRut,
            String companyGiro,
            String defaulMsg,
            String dspRut,
            String dspNombre,
            String dspFecha
    ) {
        this.defaultMsg = defaulMsg;
        this.date = date;
        this.folio = folio;
        this.place = place;
        this.dateHour = dateHour;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.comuna = comuna;
        this.total = total;
        this.abono = abono;
        this.saldo = saldo;
        this.mar1 = mar1;
        this.cri1 = cri1;
        this.ode1 = ode1;
        this.odc1 = odc1;
        this.oda1 = oda1;
        this.oie1 = oie1;
        this.oic1 = oic1;
        this.oia1 = oia1;
        this.dp1 = dp1;
        this.e1 = e1;
        this.c1 = c1;
        this.p1 = p1;
        this.mar2 = mar2;
        this.cri2 = cri2;
        this.add2 = add2;
        this.ode2 = ode2;
        this.odc2 = odc2;
        this.oda2 = oda2;
        this.oie2 = oie2;
        this.oic2 = oic2;
        this.oia2 = oia2;
        this.dp2 = dp2;
        this.e2 = e2;
        this.c2 = c2;
        this.p2 = p2;
        this.ob = ob;
        this.companyName = companyName;
        this.web = web;
        this.dir = dir;
        this.mail = mail;
        this.phones = phones;
        this.vm1 = vm1;
        this.vc1 = vc1;
        this.vm2 = vm2;
        this.vc2 = vc2;
        this.totales = totales;
        this.descuento = descuento;
        this.abonos = abonos;
        this.saldos = saldos;
        this.companyPronombre = companyPronombre;
        this.companyRut = companyRut;
        this.companyGiro = companyGiro;
        this.dspFecha = dspFecha;
        this.dspNombre = dspNombre;
        this.dspRut = dspRut;
    }

    public void setDspRut(String dspRut) {
        this.dspRut = dspRut;
    }

    public void setDspNombre(String dspNombre) {
        this.dspNombre = dspNombre;
    }

    public void setDspFecha(String dspFecha) {
        this.dspFecha = dspFecha;
    }

    public String getDspRut() {
        return dspRut;
    }

    public String getDspNombre() {
        return dspNombre;
    }

    public String getDspFecha() {
        return dspFecha;
    }

    public void setDefaultMsg(String defaultMsg) {
        this.defaultMsg = defaultMsg;
    }

    public String getDefaultMsg() {
        return defaultMsg;
    }

    public void setDescuento(String descuento) {
        this.descuento = descuento;
    }

    public String getDescuento() {
        return descuento;
    }

    public void setCompanyGiro(String companyGiro) {
        this.companyGiro = companyGiro;
    }

    public String getCompanyGiro() {
        return companyGiro;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setCompanyPronombre(String companyPronombre) {
        this.companyPronombre = companyPronombre;
    }

    public void setCompanyRut(String companyRut) {
        this.companyRut = companyRut;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCompanyPronombre() {
        return companyPronombre;
    }

    public String getCompanyRut() {
        return companyRut;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setDateHour(String dateHour) {
        this.dateHour = dateHour;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setComuna(String comuna) {
        this.comuna = comuna;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public void setAbono(String abono) {
        this.abono = abono;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }

    public void setMar1(String mar1) {
        this.mar1 = mar1;
    }

    public void setCri1(String cri1) {
        this.cri1 = cri1;
    }

    public void setOde1(String ode1) {
        this.ode1 = ode1;
    }

    public void setOdc1(String odc1) {
        this.odc1 = odc1;
    }

    public void setOda1(String oda1) {
        this.oda1 = oda1;
    }

    public void setOie1(String oie1) {
        this.oie1 = oie1;
    }

    public void setOic1(String oic1) {
        this.oic1 = oic1;
    }

    public void setOia1(String oia1) {
        this.oia1 = oia1;
    }

    public void setDp1(String dp1) {
        this.dp1 = dp1;
    }

    public void setE1(String e1) {
        this.e1 = e1;
    }

    public void setC1(String c1) {
        this.c1 = c1;
    }

    public void setP1(String p1) {
        this.p1 = p1;
    }

    public void setMar2(String mar2) {
        this.mar2 = mar2;
    }

    public void setCri2(String cri2) {
        this.cri2 = cri2;
    }

    public void setAdd2(String add2) {
        this.add2 = add2;
    }

    public void setOde2(String ode2) {
        this.ode2 = ode2;
    }

    public void setOdc2(String odc2) {
        this.odc2 = odc2;
    }

    public void setOda2(String oda2) {
        this.oda2 = oda2;
    }

    public void setOie2(String oie2) {
        this.oie2 = oie2;
    }

    public void setOic2(String oic2) {
        this.oic2 = oic2;
    }

    public void setOia2(String oia2) {
        this.oia2 = oia2;
    }

    public void setDp2(String dp2) {
        this.dp2 = dp2;
    }

    public void setE2(String e2) {
        this.e2 = e2;
    }

    public void setC2(String c2) {
        this.c2 = c2;
    }

    public void setP2(String p2) {
        this.p2 = p2;
    }

    public void setOb(String ob) {
        this.ob = ob;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPhones(String phones) {
        this.phones = phones;
    }

    public void setVm1(String vm1) {
        this.vm1 = vm1;
    }

    public void setVc1(String vc1) {
        this.vc1 = vc1;
    }

    public void setVm2(String vm2) {
        this.vm2 = vm2;
    }

    public void setVc2(String vc2) {
        this.vc2 = vc2;
    }

    public void setTotales(String totales) {
        this.totales = totales;
    }

    public void setAbonos(String abonos) {
        this.abonos = abonos;
    }

    public void setSaldos(String saldos) {
        this.saldos = saldos;
    }

    public String getDate() {
        return date;
    }

    public String getFolio() {
        return folio;
    }

    public String getPlace() {
        return place;
    }

    public String getDateHour() {
        return dateHour;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getComuna() {
        return comuna;
    }

    public String getTotal() {
        return total;
    }

    public String getAbono() {
        return abono;
    }

    public String getSaldo() {
        return saldo;
    }

    public String getMar1() {
        return mar1;
    }

    public String getCri1() {
        return cri1;
    }

    public String getOde1() {
        return ode1;
    }

    public String getOdc1() {
        return odc1;
    }

    public String getOda1() {
        return oda1;
    }

    public String getOie1() {
        return oie1;
    }

    public String getOic1() {
        return oic1;
    }

    public String getOia1() {
        return oia1;
    }

    public String getDp1() {
        return dp1;
    }

    public String getE1() {
        return e1;
    }

    public String getC1() {
        return c1;
    }

    public String getP1() {
        return p1;
    }

    public String getMar2() {
        return mar2;
    }

    public String getCri2() {
        return cri2;
    }

    public String getAdd2() {
        return add2;
    }

    public String getOde2() {
        return ode2;
    }

    public String getOdc2() {
        return odc2;
    }

    public String getOda2() {
        return oda2;
    }

    public String getOie2() {
        return oie2;
    }

    public String getOic2() {
        return oic2;
    }

    public String getOia2() {
        return oia2;
    }

    public String getDp2() {
        return dp2;
    }

    public String getE2() {
        return e2;
    }

    public String getC2() {
        return c2;
    }

    public String getP2() {
        return p2;
    }

    public String getOb() {
        return ob;
    }

    public String getWeb() {
        return web;
    }

    public String getDir() {
        return dir;
    }

    public String getMail() {
        return mail;
    }

    public String getPhones() {
        return phones;
    }

    public String getVm1() {
        return vm1;
    }

    public String getVc1() {
        return vc1;
    }

    public String getVm2() {
        return vm2;
    }

    public String getVc2() {
        return vc2;
    }

    public String getTotales() {
        return totales;
    }

    public String getAbonos() {
        return abonos;
    }

    public String getSaldos() {
        return saldos;
    }
    
    
    
}
