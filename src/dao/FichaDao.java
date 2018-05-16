/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bd.BD;
import entities.Armazon;
import entities.Cliente;
import entities.Descuento;
import entities.Despacho;
import entities.Doctor;
import entities.Ficha;
import entities.Institucion;
import entities.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Lenovo G470
 */
public class FichaDao {
    
    public String agregar(Ficha ficha) throws SQLException, ClassNotFoundException{
        
        //ArrayList<Despacho> lista=listar(despacho.getId());
        //if(lista.size()>0){
        //return "Ya se encuentra registrado el despacho "+despacho.getNombre();
        //} 
        
        if(ficha != null){
            try {
                //////// validar valores nulos
                if(ficha.getCliente() == null){
                    Cliente cli = new Cliente();
                    cli.setRut("null");
                }
                if(ficha.getDoctor() == null){
                    Doctor doc = new Doctor();
                    doc.setRut("null");
                }
                if(ficha.getDescuento() == null){
                    Descuento des = new Descuento();
                    des.setId(0);
                }
                if(ficha.getInstitucion() == null){
                    Institucion ins = new Institucion();
                    ins.setId(0);
                }
                if(ficha.getDespacho() == null){
                    Despacho dd = new Despacho();
                    dd.setId(0);
                }
                //////// dar formato String a fecha
                java.util.Date fecha = ficha.getFecha();// crea una variable tipo Date
                java.sql.Date sqlfecha = new java.sql.Date(fecha.getTime());//la transforma a sql.Date
                java.util.Date fechaEntrega = ficha.getFechaEntrega();// crea una variable tipo Date
                java.sql.Date sqlfechaEntrega = new java.sql.Date(fechaEntrega.getTime());//la transforma a sql.Date
                /////// fin formato fecha
                PreparedStatement insert = BD.obtener().prepareStatement(
                        "INSERT INTO `ficha`("
                                + "`fch_id`,"
                                + " `fch_fecha`,"
                                + " `fch_fecha_entrega`,"
                                + " `fch_lugar_entrega`,"
                                + " `fch_hora_entrega`,"
                                + " `fch_valor_total`,"
                                + " `fch_saldo`,"
                                + " `fch_obs`,"
                                + " `fch_estado`,"
                                + " `cliente_cli_rut`,"
                                + " `doctor_doc_rut`,"
                                + " `descuento_des_id`,"
                                + " `institucion_ins_id`,"
                                + " `despacho_dsp_id`"
                                + ") VALUES('"
                                +ficha.getId()+"', '"
                                +sqlfecha+"', '"
                                +sqlfechaEntrega+"', '"
                                +ficha.getLugarEntrega()+"','"
                                +ficha.getHoraEntrega()+"','"
                                +ficha.getValorTotal()+"','"
                                +ficha.getSaldo()+"','"
                                +ficha.getObservacion()+"','"
                                +ficha.getEstado()+"','"
                                +ficha.getCliente().getRut()+"','"
                                +ficha.getDoctor().getRut()+"','"
                                +ficha.getDescuento().getId()+"','"
                                +ficha.getInstitucion().getId()+"','"
                                +ficha.getDespacho().getId()
                                +"')");
                if(insert.executeUpdate()!=0){
                    BD.cerrar();
                    return "Se ha agregado la ficha "+ficha.getId()+" correctamente";
                }
            } catch (Exception e) {
                BD.cerrar();
                return "Error :"+e;
            }
        }
        BD.cerrar();
        return "La ficha tiene datos vacios";
    }
    
    public boolean agregarBoolean(Ficha ficha,int idSesion) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        
        //ArrayList<Despacho> lista=listar(despacho.getId());
        //if(lista.size()>0){
        //return "Ya se encuentra registrado el despacho "+despacho.getNombre();
        //} 
        if(ficha != null){
            try {
                //////// validar valores nulos
                if(ficha.getCliente() == null){
                    Cliente cli = new Cliente();
                    cli.setRut("null");
                    ficha.setCliente(cli);
                }
                if(ficha.getDoctor() == null){
                    Doctor doc = new Doctor();
                    doc.setRut("null");
                    ficha.setDoctor(doc);
                }
                if(ficha.getDescuento() == null){
                    Descuento des = new Descuento();
                    des.setId(0);
                    ficha.setDescuento(des);
                }
                if(ficha.getInstitucion() == null){
                    Institucion ins = new Institucion();
                    ins.setId(0);
                    ficha.setInstitucion(ins);
                }
                if(ficha.getDespacho() == null){
                    Despacho dd = new Despacho();
                    dd.setId(0);
                    ficha.setDespacho(dd);
                }
                //////// dar formato String a fecha
                
                java.util.Date fecha = ficha.getFecha();// crea una variable tipo Date
                java.sql.Date sqlfecha = new java.sql.Date(fecha.getTime());//la transforma a sql.Date
                java.util.Date fechaEntrega = ficha.getFechaEntrega();// crea una variable tipo Date
                java.sql.Date sqlfechaEntrega = new java.sql.Date(fechaEntrega.getTime());//la transforma a sql.Date
                /////// fin formato fecha
                PreparedStatement insert = BD.obtener().prepareStatement(
                        "INSERT INTO `ficha`("
                                + "`fch_id`,"
                                + " `fch_fecha`,"
                                + " `fch_fecha_entrega`,"
                                + " `fch_lugar_entrega`,"
                                + " `fch_hora_entrega`,"
                                + " `fch_valor_total`,"
                                + " `fch_saldo`,"
                                + " `fch_obs`,"
                                + " `fch_estado`,"
                                + " `cliente_cli_rut`,"
                                + " `doctor_doc_rut`,"
                                + " `descuento_des_id`,"
                                + " `institucion_ins_id`,"
                                + " `despacho_dsp_id`,"
                                + " `user_us_id`"
                                + ") VALUES('"
                                +ficha.getId()+"', '"
                                +sqlfecha+"', '"
                                +sqlfechaEntrega+"', '"
                                +ficha.getLugarEntrega()+"','"
                                +ficha.getHoraEntrega()+"','"
                                +ficha.getValorTotal()+"','"
                                +ficha.getSaldo()+"','"
                                +ficha.getObservacion()+"','"
                                +ficha.getEstado()+"','"
                                +ficha.getCliente().getRut()+"','"
                                +ficha.getDoctor().getRut()+"','"
                                +ficha.getDescuento().getId()+"','"
                                +ficha.getInstitucion().getId()+"','"
                                +ficha.getDespacho().getId()+"','"
                                +idSesion
                                +"')");
                if(insert.executeUpdate()!=0){
                    BD.cerrar();
                    return true;
                }
            } catch (SQLException | ClassNotFoundException e) {
                BD.cerrar();
                JOptionPane.showMessageDialog(null, "No se pudo completar la operación [1112.1]:"+e.getMessage(), "Error al guardar Ficha",JOptionPane.WARNING_MESSAGE);
                
                return false;
            }
        }
        BD.cerrar();
        return false;
    }
    
    public ArrayList<Ficha> listarPorSesion(int idFicha, int idSesion, int estado) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String sql="SELECT *,(SELECT doctor.doc_nombre from doctor where doctor.doc_rut=f.doctor_doc_rut) as 'doc_nombre',"
                + "(SELECT doctor.doc_telefono from doctor where doctor.doc_rut=f.doctor_doc_rut) as 'doc_telefono',"
                + "(SELECT doctor.doc_mail from doctor where doctor.doc_rut=f.doctor_doc_rut) as 'doc_mail',"
                + "(SELECT doctor.doc_estado from doctor where doctor.doc_rut=f.doctor_doc_rut) as 'doc_estado',"
                + "(SELECT descuento.des_nombre from descuento where f.descuento_des_id=descuento.des_id) as 'des_nombre',"
                + "(SELECT descuento.des_descripcion from descuento where f.descuento_des_id=descuento.des_id) as 'des_descripcion',"
                + "(SELECT descuento.des_porc from descuento where f.descuento_des_id=descuento.des_id) as 'des_porc',"
                + "(SELECT descuento.des_estado from descuento where f.descuento_des_id=descuento.des_id) as 'des_estado',"
                + "(SELECT institucion.ins_nombre from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_nombre',"
                + "(SELECT institucion.ins_telefono from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_telefono',"
                + "(SELECT institucion.ins_mail from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_mail',"
                + "(SELECT institucion.ins_direccion from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_direccion',"
                + "(SELECT institucion.ins_comuna from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_comuna',"
                + "(SELECT institucion.ins_ciudad from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_ciudad',"
                + "(SELECT institucion.ins_estado from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_estado',"
                + "(SELECT despacho.dsp_rut from despacho where f.despacho_dsp_id=despacho.dsp_id)  as 'dsp_rut',"
                + "(SELECT despacho.dsp_nombre from despacho where f.despacho_dsp_id=despacho.dsp_id)  as 'dsp_nombre',"
                + "(SELECT despacho.dsp_fecha from despacho where f.despacho_dsp_id=despacho.dsp_id)  as 'dsp_fecha',"
                + "(SELECT armazon.arm_id from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_id_cerca',"
                + "(SELECT armazon.arm_tipo from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_tipo_cerca',"
                + "(SELECT armazon.arm_marca from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_marca_cerca',"
                + "(SELECT armazon.arm_cristal from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_cristal_cerca',"
                + "(SELECT armazon.arm_add from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_add_cerca',"
                + "(SELECT armazon.arm_od_a from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_od_a_cerca',"
                + "(SELECT armazon.arm_od_esf from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_od_esf_cerca',"
                + "(SELECT armazon.arm_od_cil from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_od_cil_cerca',"
                + "(SELECT armazon.arm_oi_a from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_oi_a_cerca',"
                + "(SELECT armazon.arm_oi_esf from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_oi_esf_cerca',"
                + "(SELECT armazon.arm_oi_cil from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_oi_cil_cerca',"
                + "(SELECT armazon.arm_dp from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_dp_cerca',"
                + "(SELECT armazon.arm_endurecido from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_endurecido_cerca',"
                + "(SELECT armazon.arm_capa from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_capa_cerca',"
                + "(SELECT armazon.arm_plus_max from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_plus_max_cerca',"
                + "(SELECT armazon.arm_id from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_id_lejos',"
                + "(SELECT armazon.arm_tipo from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_tipo_lejos',"
                + "(SELECT armazon.arm_marca from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_marca_lejos',"
                + "(SELECT armazon.arm_cristal from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_cristal_lejos',"
                + "(SELECT armazon.arm_add from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_add_lejos',"
                + "(SELECT armazon.arm_od_a from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_od_a_lejos',"
                + "(SELECT armazon.arm_od_esf from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_od_esf_lejos',"
                + "(SELECT armazon.arm_od_cil from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_od_cil_lejos',"
                + "(SELECT armazon.arm_oi_a from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_oi_a_lejos',"
                + "(SELECT armazon.arm_oi_esf from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_oi_esf_lejos',"
                + "(SELECT armazon.arm_oi_cil from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_oi_cil_lejos',"
                + "(SELECT armazon.arm_dp from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_dp_lejos',"
                + "(SELECT armazon.arm_endurecido from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_endurecido_lejos',"
                + "(SELECT armazon.arm_capa from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_capa_lejos',"
                + "(SELECT armazon.arm_plus_max from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_plus_max_lejos', "
                + "(SELECT user.us_id from user where user.us_id=user_us_id) as us_id,"
                + "(SELECT user.us_nombre from user where user.us_id=user_us_id) as us_nombre,"
                + "(SELECT user.us_pass from user WHERE user.us_id=user_us_id) as us_pass,"
                + "(SELECT user.us_tipo from user WHERE user.us_id=user_us_id) as us_tipo,"
                + "(SELECT user.us_estado from user WHERE user.us_id=user_us_id) as us_estado"
                + "from ficha f, cliente cli where f.cliente_cli_rut=cli.cli_rut  and f.fch_id='"+idFicha+"' and f.user_us_id='"+idSesion+"' and f.fch_estado <> 0";
        if(estado == 0)
            sql="SELECT *,(SELECT doctor.doc_nombre from doctor where doctor.doc_rut=f.doctor_doc_rut) as 'doc_nombre',(SELECT doctor.doc_telefono from doctor where doctor.doc_rut=f.doctor_doc_rut) as 'doc_telefono',(SELECT doctor.doc_mail from doctor where doctor.doc_rut=f.doctor_doc_rut) as 'doc_mail',(SELECT doctor.doc_estado from doctor where doctor.doc_rut=f.doctor_doc_rut) as 'doc_estado',(SELECT descuento.des_nombre from descuento where f.descuento_des_id=descuento.des_id) as 'des_nombre',(SELECT descuento.des_descripcion from descuento where f.descuento_des_id=descuento.des_id) as 'des_descripcion',(SELECT descuento.des_porc from descuento where f.descuento_des_id=descuento.des_id) as 'des_porc',(SELECT descuento.des_estado from descuento where f.descuento_des_id=descuento.des_id) as 'des_estado',(SELECT institucion.ins_nombre from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_nombre',(SELECT institucion.ins_telefono from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_telefono',(SELECT institucion.ins_mail from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_mail',(SELECT institucion.ins_direccion from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_direccion',(SELECT institucion.ins_comuna from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_comuna',(SELECT institucion.ins_ciudad from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_ciudad',(SELECT institucion.ins_estado from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_estado',(SELECT despacho.dsp_rut from despacho where f.despacho_dsp_id=despacho.dsp_id)  as 'dsp_rut',(SELECT despacho.dsp_nombre from despacho where f.despacho_dsp_id=despacho.dsp_id)  as 'dsp_nombre',(SELECT despacho.dsp_fecha from despacho where f.despacho_dsp_id=despacho.dsp_id)  as 'dsp_fecha',(SELECT armazon.arm_id from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_id_cerca',(SELECT armazon.arm_tipo from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_tipo_cerca',(SELECT armazon.arm_marca from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_marca_cerca',(SELECT armazon.arm_cristal from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_cristal_cerca',(SELECT armazon.arm_add from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_add_cerca',(SELECT armazon.arm_od_a from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_od_a_cerca',(SELECT armazon.arm_od_esf from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_od_esf_cerca',(SELECT armazon.arm_od_cil from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_od_cil_cerca',(SELECT armazon.arm_oi_a from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_oi_a_cerca',(SELECT armazon.arm_oi_esf from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_oi_esf_cerca',(SELECT armazon.arm_oi_cil from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_oi_cil_cerca',(SELECT armazon.arm_dp from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_dp_cerca',(SELECT armazon.arm_endurecido from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_endurecido_cerca',(SELECT armazon.arm_capa from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_capa_cerca',(SELECT armazon.arm_plus_max from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_plus_max_cerca',(SELECT armazon.arm_id from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_id_lejos',(SELECT armazon.arm_tipo from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_tipo_lejos',(SELECT armazon.arm_marca from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_marca_lejos',(SELECT armazon.arm_cristal from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_cristal_lejos',(SELECT armazon.arm_add from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_add_lejos',(SELECT armazon.arm_od_a from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_od_a_lejos',(SELECT armazon.arm_od_esf from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_od_esf_lejos',(SELECT armazon.arm_od_cil from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_od_cil_lejos',(SELECT armazon.arm_oi_a from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_oi_a_lejos',(SELECT armazon.arm_oi_esf from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_oi_esf_lejos',(SELECT armazon.arm_oi_cil from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_oi_cil_lejos',(SELECT armazon.arm_dp from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_dp_lejos',(SELECT armazon.arm_endurecido from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_endurecido_lejos',(SELECT armazon.arm_capa from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_capa_lejos',(SELECT armazon.arm_plus_max from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_plus_max_lejos',"
                    + "(SELECT user.us_id from user where user.us_id=user_us_id) as us_id,"
                    + "(SELECT user.us_nombre from user where user.us_id=user_us_id) as us_nombre,"
                    + "(SELECT user.us_pass from user WHERE user.us_id=user_us_id) as us_pass,"
                    + "(SELECT user.us_tipo from user WHERE user.us_id=user_us_id) as us_tipo,"
                    + "(SELECT user.us_estado from user WHERE user.us_id=user_us_id) as us_estado"
                    + " from ficha f, cliente cli where f.cliente_cli_rut=cli.cli_rut  and f.fch_id='"+idFicha+"' and f.user_us_id='"+idSesion+"' and f.fch_estado = 0";
        if(estado == 4)
            sql="SELECT *,(SELECT doctor.doc_nombre from doctor where doctor.doc_rut=f.doctor_doc_rut) as 'doc_nombre',(SELECT doctor.doc_telefono from doctor where doctor.doc_rut=f.doctor_doc_rut) as 'doc_telefono',(SELECT doctor.doc_mail from doctor where doctor.doc_rut=f.doctor_doc_rut) as 'doc_mail',(SELECT doctor.doc_estado from doctor where doctor.doc_rut=f.doctor_doc_rut) as 'doc_estado',(SELECT descuento.des_nombre from descuento where f.descuento_des_id=descuento.des_id) as 'des_nombre',(SELECT descuento.des_descripcion from descuento where f.descuento_des_id=descuento.des_id) as 'des_descripcion',(SELECT descuento.des_porc from descuento where f.descuento_des_id=descuento.des_id) as 'des_porc',(SELECT descuento.des_estado from descuento where f.descuento_des_id=descuento.des_id) as 'des_estado',(SELECT institucion.ins_nombre from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_nombre',(SELECT institucion.ins_telefono from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_telefono',(SELECT institucion.ins_mail from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_mail',(SELECT institucion.ins_direccion from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_direccion',(SELECT institucion.ins_comuna from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_comuna',(SELECT institucion.ins_ciudad from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_ciudad',(SELECT institucion.ins_estado from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_estado',(SELECT despacho.dsp_rut from despacho where f.despacho_dsp_id=despacho.dsp_id)  as 'dsp_rut',(SELECT despacho.dsp_nombre from despacho where f.despacho_dsp_id=despacho.dsp_id)  as 'dsp_nombre',(SELECT despacho.dsp_fecha from despacho where f.despacho_dsp_id=despacho.dsp_id)  as 'dsp_fecha',(SELECT armazon.arm_id from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_id_cerca',(SELECT armazon.arm_tipo from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_tipo_cerca',(SELECT armazon.arm_marca from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_marca_cerca',(SELECT armazon.arm_cristal from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_cristal_cerca',(SELECT armazon.arm_add from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_add_cerca',(SELECT armazon.arm_od_a from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_od_a_cerca',(SELECT armazon.arm_od_esf from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_od_esf_cerca',(SELECT armazon.arm_od_cil from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_od_cil_cerca',(SELECT armazon.arm_oi_a from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_oi_a_cerca',(SELECT armazon.arm_oi_esf from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_oi_esf_cerca',(SELECT armazon.arm_oi_cil from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_oi_cil_cerca',(SELECT armazon.arm_dp from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_dp_cerca',(SELECT armazon.arm_endurecido from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_endurecido_cerca',(SELECT armazon.arm_capa from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_capa_cerca',(SELECT armazon.arm_plus_max from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_plus_max_cerca',(SELECT armazon.arm_id from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_id_lejos',(SELECT armazon.arm_tipo from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_tipo_lejos',(SELECT armazon.arm_marca from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_marca_lejos',(SELECT armazon.arm_cristal from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_cristal_lejos',(SELECT armazon.arm_add from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_add_lejos',(SELECT armazon.arm_od_a from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_od_a_lejos',(SELECT armazon.arm_od_esf from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_od_esf_lejos',(SELECT armazon.arm_od_cil from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_od_cil_lejos',(SELECT armazon.arm_oi_a from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_oi_a_lejos',(SELECT armazon.arm_oi_esf from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_oi_esf_lejos',(SELECT armazon.arm_oi_cil from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_oi_cil_lejos',(SELECT armazon.arm_dp from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_dp_lejos',(SELECT armazon.arm_endurecido from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_endurecido_lejos',(SELECT armazon.arm_capa from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_capa_lejos',(SELECT armazon.arm_plus_max from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_plus_max_lejos',"
                    + "(SELECT user.us_id from user where user.us_id=user_us_id) as us_id,"
                    + "(SELECT user.us_nombre from user where user.us_id=user_us_id) as us_nombre,"
                    + "(SELECT user.us_pass from user WHERE user.us_id=user_us_id) as us_pass,"
                    + "(SELECT user.us_tipo from user WHERE user.us_id=user_us_id) as us_tipo,"
                    + "(SELECT user.us_estado from user WHERE user.us_id=user_us_id) as us_estado"
                    + " from ficha f, cliente cli where f.cliente_cli_rut=cli.cli_rut  and f.fch_id='"+idFicha+"' and f.user_us_id='"+idSesion+"' and f.fch_estado = 4";
        if(idFicha==0){
         sql="SELECT *,(SELECT doctor.doc_nombre from doctor where doctor.doc_rut=f.doctor_doc_rut) as 'doc_nombre',(SELECT doctor.doc_telefono from doctor where doctor.doc_rut=f.doctor_doc_rut) as 'doc_telefono',(SELECT doctor.doc_mail from doctor where doctor.doc_rut=f.doctor_doc_rut) as 'doc_mail',(SELECT doctor.doc_estado from doctor where doctor.doc_rut=f.doctor_doc_rut) as 'doc_estado',(SELECT descuento.des_nombre from descuento where f.descuento_des_id=descuento.des_id) as 'des_nombre',(SELECT descuento.des_descripcion from descuento where f.descuento_des_id=descuento.des_id) as 'des_descripcion',(SELECT descuento.des_porc from descuento where f.descuento_des_id=descuento.des_id) as 'des_porc',(SELECT descuento.des_estado from descuento where f.descuento_des_id=descuento.des_id) as 'des_estado',(SELECT institucion.ins_nombre from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_nombre',(SELECT institucion.ins_telefono from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_telefono',(SELECT institucion.ins_mail from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_mail',(SELECT institucion.ins_direccion from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_direccion',(SELECT institucion.ins_comuna from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_comuna',(SELECT institucion.ins_ciudad from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_ciudad',(SELECT institucion.ins_estado from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_estado',(SELECT despacho.dsp_rut from despacho where f.despacho_dsp_id=despacho.dsp_id)  as 'dsp_rut',(SELECT despacho.dsp_nombre from despacho where f.despacho_dsp_id=despacho.dsp_id)  as 'dsp_nombre',(SELECT despacho.dsp_fecha from despacho where f.despacho_dsp_id=despacho.dsp_id)  as 'dsp_fecha',(SELECT armazon.arm_id from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_id_cerca',(SELECT armazon.arm_tipo from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_tipo_cerca',(SELECT armazon.arm_cristal from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_cristal_cerca',(SELECT armazon.arm_add from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_add_cerca',(SELECT armazon.arm_od_a from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_od_a_cerca',(SELECT armazon.arm_od_esf from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_od_esf_cerca',(SELECT armazon.arm_od_cil from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_od_cil_cerca',(SELECT armazon.arm_oi_a from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_oi_a_cerca',(SELECT armazon.arm_oi_esf from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_oi_esf_cerca',(SELECT armazon.arm_oi_cil from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_oi_cil_cerca',(SELECT armazon.arm_dp from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_dp_cerca',(SELECT armazon.arm_endurecido from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_endurecido_cerca',(SELECT armazon.arm_capa from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_capa_cerca',(SELECT armazon.arm_plus_max from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_plus_max_cerca',(SELECT armazon.arm_marca from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_marca_cerca',(SELECT armazon.arm_id from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_id_lejos',(SELECT armazon.arm_tipo from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_tipo_lejos',(SELECT armazon.arm_cristal from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_cristal_lejos',(SELECT armazon.arm_add from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_add_lejos',(SELECT armazon.arm_od_a from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_od_a_lejos',(SELECT armazon.arm_od_esf from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_od_esf_lejos',(SELECT armazon.arm_od_cil from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_od_cil_lejos',(SELECT armazon.arm_oi_a from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_oi_a_lejos',(SELECT armazon.arm_oi_esf from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_oi_esf_lejos',(SELECT armazon.arm_oi_cil from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_oi_cil_lejos',(SELECT armazon.arm_dp from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_dp_lejos',(SELECT armazon.arm_endurecido from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_endurecido_lejos',(SELECT armazon.arm_capa from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_capa_lejos',(SELECT armazon.arm_plus_max from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_plus_max_lejos',(SELECT armazon.arm_marca from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_marca_lejos',"
                 + "(SELECT user.us_id from user where user.us_id=user_us_id) as us_id,"
                + "(SELECT user.us_nombre from user where user.us_id=user_us_id) as us_nombre,"
                + "(SELECT user.us_pass from user WHERE user.us_id=user_us_id) as us_pass,"
                + "(SELECT user.us_tipo from user WHERE user.us_id=user_us_id) as us_tipo,"
                + "(SELECT user.us_estado from user WHERE user.us_id=user_us_id) as us_estado"
                 + " from ficha f, cliente cli where f.cliente_cli_rut=cli.cli_rut and f.user_us_id='"+idSesion+"' and f.fch_estado <> 0";
        }
        if(idFicha==-1){
        sql="SELECT *,(SELECT doctor.doc_nombre from doctor where doctor.doc_rut=f.doctor_doc_rut) as 'doc_nombre',(SELECT doctor.doc_telefono from doctor where doctor.doc_rut=f.doctor_doc_rut) as 'doc_telefono',(SELECT doctor.doc_mail from doctor where doctor.doc_rut=f.doctor_doc_rut) as 'doc_mail',(SELECT doctor.doc_estado from doctor where doctor.doc_rut=f.doctor_doc_rut) as 'doc_estado',(SELECT descuento.des_nombre from descuento where f.descuento_des_id=descuento.des_id) as 'des_nombre',(SELECT descuento.des_descripcion from descuento where f.descuento_des_id=descuento.des_id) as 'des_descripcion',(SELECT descuento.des_porc from descuento where f.descuento_des_id=descuento.des_id) as 'des_porc',(SELECT descuento.des_estado from descuento where f.descuento_des_id=descuento.des_id) as 'des_estado',(SELECT institucion.ins_nombre from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_nombre',(SELECT institucion.ins_telefono from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_telefono',(SELECT institucion.ins_mail from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_mail',(SELECT institucion.ins_direccion from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_direccion',(SELECT institucion.ins_comuna from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_comuna',(SELECT institucion.ins_ciudad from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_ciudad',(SELECT institucion.ins_estado from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_estado',(SELECT despacho.dsp_rut from despacho where f.despacho_dsp_id=despacho.dsp_id)  as 'dsp_rut',(SELECT despacho.dsp_nombre from despacho where f.despacho_dsp_id=despacho.dsp_id)  as 'dsp_nombre',(SELECT despacho.dsp_fecha from despacho where f.despacho_dsp_id=despacho.dsp_id)  as 'dsp_fecha',(SELECT armazon.arm_id from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_id_cerca',(SELECT armazon.arm_tipo from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_tipo_cerca',(SELECT armazon.arm_cristal from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_cristal_cerca',(SELECT armazon.arm_add from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_add_cerca',(SELECT armazon.arm_od_a from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_od_a_cerca',(SELECT armazon.arm_od_esf from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_od_esf_cerca',(SELECT armazon.arm_od_cil from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_od_cil_cerca',(SELECT armazon.arm_oi_a from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_oi_a_cerca',(SELECT armazon.arm_oi_esf from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_oi_esf_cerca',(SELECT armazon.arm_oi_cil from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_oi_cil_cerca',(SELECT armazon.arm_dp from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_dp_cerca',(SELECT armazon.arm_endurecido from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_endurecido_cerca',(SELECT armazon.arm_capa from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_capa_cerca',(SELECT armazon.arm_plus_max from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_plus_max_cerca',(SELECT armazon.arm_marca from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_marca_cerca',(SELECT armazon.arm_id from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_id_lejos',(SELECT armazon.arm_tipo from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_tipo_lejos',(SELECT armazon.arm_cristal from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_cristal_lejos',(SELECT armazon.arm_add from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_add_lejos',(SELECT armazon.arm_od_a from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_od_a_lejos',(SELECT armazon.arm_od_esf from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_od_esf_lejos',(SELECT armazon.arm_od_cil from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_od_cil_lejos',(SELECT armazon.arm_oi_a from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_oi_a_lejos',(SELECT armazon.arm_oi_esf from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_oi_esf_lejos',(SELECT armazon.arm_oi_cil from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_oi_cil_lejos',(SELECT armazon.arm_dp from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_dp_lejos',(SELECT armazon.arm_endurecido from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_endurecido_lejos',(SELECT armazon.arm_capa from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_capa_lejos',(SELECT armazon.arm_plus_max from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_plus_max_lejos',(SELECT armazon.arm_marca from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_marca_lejos',"
                + "(SELECT user.us_id from user where user.us_id=user_us_id) as us_id,"
                + "(SELECT user.us_nombre from user where user.us_id=user_us_id) as us_nombre,"
                + "(SELECT user.us_pass from user WHERE user.us_id=user_us_id) as us_pass,"
                + "(SELECT user.us_tipo from user WHERE user.us_id=user_us_id) as us_tipo,"
                + "(SELECT user.us_estado from user WHERE user.us_id=user_us_id) as us_estado"
                + " from ficha f, cliente cli where f.cliente_cli_rut=cli.cli_rut and f.user_us_id='"+idSesion+"' and f.fch_estado = 0";
        }
        PreparedStatement consulta = BD.obtener().prepareStatement(sql);
        ResultSet datos = consulta.executeQuery();
        ArrayList<Ficha> lista = new ArrayList<>();
        while (datos.next()) {
            
            Date fch_fecha= new Date();
            Date fch_fecha_entrega= new Date();
            Date dsp_fecha= new Date();
            SimpleDateFormat parseador = new SimpleDateFormat("dd-MM-yy");
            try {
                fch_fecha = datos.getDate("fch_fecha");
                fch_fecha_entrega = datos.getDate("f.fch_fecha_entrega");
                dsp_fecha = datos.getDate("dsp_fecha");//parseador.parse();
            } catch (Exception e) {
                System.out.println("se cayo al intentar convertir la fecha");   
            }
            // ficha
            int fch_valor_total=0;
            int fch_saldo=0;
            int fch_estado=0;
            try {fch_valor_total=datos.getInt("fch_valor_total");} catch (Exception e) { fch_valor_total=0;}
            try {fch_saldo=datos.getInt("fch_saldo");} catch (Exception e) { fch_saldo=0;}
            try {fch_estado=datos.getInt("fch_estado");} catch (Exception e) { fch_estado=0;}
            ////------------clase doctor
            String doctor_doc_rut="";
            String doc_nombre="";
            String doc_telefono="";
            String doc_mail="";
            try {doctor_doc_rut=datos.getString("doctor_doc_rut");} catch (Exception e) { doctor_doc_rut="";}
            try {doc_nombre=datos.getString("doc_nombre");} catch (Exception e) { doc_nombre="";}
            try {doc_telefono=datos.getString("doc_telefono");} catch (Exception e) { doc_telefono="";}
            try {doc_mail=datos.getString("doc_mail");} catch (Exception e) { doc_mail="";}
            //--int
            int doc_estado=0;
            try {doc_estado=datos.getInt("doc_estado");} catch (Exception e) { doc_estado=0;}
            ///-------------clace descuento
            String des_nombre="";
            String des_descripcion="";
            try {des_nombre=datos.getString("des_nombre");} catch (Exception e) { des_nombre="";}
            try {des_descripcion=datos.getString("des_descripcion");} catch (Exception e) { des_descripcion="";}
            //--int
            int descuento_des_id=0;
            int des_porc=0;
            int des_estado=0;
            try {descuento_des_id=datos.getInt("descuento_des_id");} catch (Exception e) { descuento_des_id=0;}
            try {des_porc=datos.getInt("des_porc");} catch (Exception e) { des_porc=0;}
            try {des_estado=datos.getInt("des_estado");} catch (Exception e) { des_estado=0;}
            ///------------------------institucion
            String ins_nombre="";
            String ins_telefono="";
            String ins_mail="";
            String ins_direccion="";
            String ins_comuna="";
            String ins_ciudad="";
            try {ins_nombre=datos.getString("ins_nombre");} catch (Exception e) { ins_nombre="";}
            try {ins_telefono=datos.getString("ins_telefono");} catch (Exception e) { ins_telefono="";}
            try {ins_mail=datos.getString("ins_mail");} catch (Exception e) { ins_mail="";}
            try {ins_direccion=datos.getString("ins_direccion");} catch (Exception e) { ins_direccion="";}
            try {ins_comuna=datos.getString("ins_comuna");} catch (Exception e) { ins_comuna="";}
            try {ins_ciudad=datos.getString("ins_ciudad");} catch (Exception e) { ins_ciudad="";}
            //int
            int institucion_ins_id=0;
            int ins_estado=0;
            try {institucion_ins_id=datos.getInt("institucion_ins_id");} catch (Exception e) { institucion_ins_id=0;}
            try {ins_estado=datos.getInt("ins_estado");} catch (Exception e) { ins_estado=0;}
            //despacho
            String dsp_rut="";
            String dsp_nombre="";
            try {dsp_rut=datos.getString("dsp_rut");} catch (Exception e) { dsp_rut="";}
            try {dsp_nombre=datos.getString("dsp_nombre");} catch (Exception e) { dsp_nombre="";}
            //--int
            int despacho_dsp_id=0;
            try {despacho_dsp_id=datos.getInt("despacho_dsp_id");} catch (Exception e) { despacho_dsp_id=0;}
            //armazon cerca
            String arm_marca_cerca="";
            String arm_cristal_cerca="";
            String arm_add_cerca="";
            String arm_od_a_cerca="";
            String arm_od_esf_cerca="";
            String arm_od_cil_cerca="";
            String arm_oi_a_cerca="";
            String arm_oi_esf_cerca="";
            String arm_oi_cil_cerca="";
            try {arm_marca_cerca=datos.getString("arm_marca_cerca");} catch (Exception e) { arm_marca_cerca="";}
            try {arm_cristal_cerca=datos.getString("arm_cristal_cerca");} catch (Exception e) { arm_cristal_cerca="";}
            try {arm_add_cerca=datos.getString("arm_add_cerca");} catch (Exception e) { arm_add_cerca="";}
            try {arm_od_a_cerca=datos.getString("arm_od_a_cerca");} catch (Exception e) { arm_od_a_cerca="";}
            try {arm_od_esf_cerca=datos.getString("arm_od_esf_cerca");} catch (Exception e) { arm_od_esf_cerca="";}
            try {arm_od_cil_cerca=datos.getString("arm_od_cil_cerca");} catch (Exception e) { arm_od_cil_cerca="";}
            try {arm_oi_a_cerca=datos.getString("arm_oi_a_cerca");} catch (Exception e) { arm_oi_a_cerca="";}
            try {arm_oi_esf_cerca=datos.getString("arm_oi_esf_cerca");} catch (Exception e) { arm_oi_esf_cerca="";}
            try {arm_oi_cil_cerca=datos.getString("arm_oi_cil_cerca");} catch (Exception e) { arm_oi_cil_cerca="";}
            //--int
            int arm_id_cerca=0;
            int arm_tipo_cerca=0;
            int arm_dp_cerca=0;
            int arm_endurecido_cerca=0;
            int arm_capa_cerca=0;
            int arm_plus_max_cerca=0;
            try {arm_id_cerca=datos.getInt("arm_id_cerca");} catch (Exception e) { arm_id_cerca=0;}
            try {arm_tipo_cerca=datos.getInt("arm_tipo_cerca");} catch (Exception e) { arm_tipo_cerca=0;}
            try {arm_dp_cerca=datos.getInt("arm_dp_cerca");} catch (Exception e) { arm_dp_cerca=0;}
            try {arm_endurecido_cerca=datos.getInt("arm_endurecido_cerca");} catch (Exception e) { arm_endurecido_cerca=0;}
            try {arm_capa_cerca=datos.getInt("arm_capa_cerca");} catch (Exception e) { arm_capa_cerca=0;}
            try {arm_plus_max_cerca=datos.getInt("arm_plus_max_cerca");} catch (Exception e) { arm_plus_max_cerca=0;}
            // armazon lejos
            String arm_marca_lejos="";
            String arm_cristal_lejos="";
            String arm_add_lejos="";
            String arm_od_a_lejos="";
            String arm_od_esf_lejos="";
            String arm_od_cil_lejos="";
            String arm_oi_a_lejos="";
            String arm_oi_esf_lejos="";
            String arm_oi_cil_lejos="";
            try {arm_marca_lejos=datos.getString("arm_marca_lejos");} catch (Exception e) { arm_marca_lejos="";}
            try {arm_cristal_lejos=datos.getString("arm_cristal_lejos");} catch (Exception e) { arm_cristal_lejos="";}
            try {arm_add_lejos=datos.getString("arm_add_lejos");} catch (Exception e) { arm_add_lejos="";}
            try {arm_od_a_lejos=datos.getString("arm_od_a_lejos");} catch (Exception e) { arm_od_a_lejos="";}
            try {arm_od_esf_lejos=datos.getString("arm_od_esf_lejos");} catch (Exception e) { arm_od_esf_cerca="";}
            try {arm_od_cil_lejos=datos.getString("arm_od_cil_lejos");} catch (Exception e) { arm_od_cil_lejos="";}
            try {arm_oi_a_lejos=datos.getString("arm_oi_a_lejos");} catch (Exception e) { arm_oi_a_lejos="";}
            try {arm_oi_esf_lejos=datos.getString("arm_oi_esf_lejos");} catch (Exception e) { arm_oi_esf_lejos="";}
            try {arm_oi_cil_lejos=datos.getString("arm_oi_cil_lejos");} catch (Exception e) { arm_oi_cil_lejos="";}
            //--int
            int arm_id_lejos=0;
            int arm_tipo_lejos=0;
            int arm_dp_lejos=0;
            int arm_endurecido_lejos=0;
            int arm_capa_lejos=0;
            int arm_plus_max_lejos=0;
            try {arm_id_lejos=datos.getInt("arm_id_lejos");} catch (Exception e) { arm_id_lejos=0;}
            try {arm_tipo_lejos=datos.getInt("arm_tipo_lejos");} catch (Exception e) { arm_tipo_lejos=0;}
            try {arm_dp_lejos=datos.getInt("arm_dp_lejos");} catch (Exception e) { arm_dp_lejos=0;}
            try {arm_endurecido_lejos=datos.getInt("arm_endurecido_lejos");} catch (Exception e) { arm_endurecido_lejos=0;}
            try {arm_capa_lejos=datos.getInt("arm_capa_lejos");} catch (Exception e) { arm_capa_lejos=0;}
            try {arm_plus_max_lejos=datos.getInt("arm_plus_max_lejos");} catch (Exception e) { arm_plus_max_lejos=0;}
            
            ////------------clase user
            int user_us_id=0;
            String user_us_nombre="";
            String user_us_pass="";
            int user_us_tipo=0;
            int user_us_estado=0;
            try {user_us_id=datos.getInt("us_id");} catch (Exception e) { user_us_id=0;}
            try {user_us_nombre=datos.getString("us_nombre");} catch (Exception e) { user_us_nombre="";}
            try {user_us_pass=datos.getString("us_pass");} catch (Exception e) { user_us_pass="";}
            try {user_us_tipo=datos.getInt("us_tipo");} catch (Exception e) { user_us_tipo=0;}
            try {user_us_estado=datos.getInt("us_estado");} catch (Exception e) { user_us_estado=0;}
           
            
            lista.add(new Ficha(
                      datos.getInt("fch_id")
                    , fch_fecha
                    , fch_fecha_entrega
                    , datos.getString("fch_lugar_entrega")
                    , datos.getString("fch_hora_entrega")
                    , fch_valor_total
                    , fch_saldo
                    , datos.getString("fch_obs")
                    , datos.getInt("fch_estado")
                    , new Cliente( datos.getString("cliente_cli_rut")
                                 , datos.getString("cli_nombre")
                                 , datos.getString("cli_telefono")
                                 , datos.getString("cli_email")
                                 , datos.getString("cli_direccion")
                                 , datos.getString("cli_comuna")
                                 , datos.getString("cli_ciudad")
                                 , datos.getInt("cli_sexo")
                                 , datos.getInt("cli_edad")
                                 , datos.getInt("cli_estado")  
                                 )
                    , new Doctor(
                                  doctor_doc_rut
                                 ,doc_nombre
                                 ,doc_telefono
                                 ,doc_mail
                                 ,doc_estado
                                 )
                    , new Descuento(
                                      descuento_des_id
                                    , des_nombre
                                    , des_descripcion
                                    , des_porc
                                    , des_estado
                                    )
                    , new Institucion(
                                        institucion_ins_id
                                      , ins_nombre
                                      , ins_telefono
                                      , ins_mail
                                      , ins_direccion
                                      , ins_comuna
                                      , ins_ciudad
                                      , ins_estado
                                     )
                    , new Despacho(
                                     despacho_dsp_id
                                   , dsp_rut
                                   , dsp_nombre
                                   , dsp_fecha
                                   , datos.getInt("fch_id")
                                  )
                    , new Armazon(
                                   arm_id_cerca
                                  ,arm_tipo_cerca
                                  ,arm_marca_cerca
                                  ,arm_cristal_cerca
                                  ,arm_add_cerca
                                  ,arm_od_a_cerca
                                  ,arm_od_esf_cerca
                                  ,arm_od_cil_cerca
                                  ,arm_oi_a_cerca
                                  ,arm_oi_esf_cerca
                                  ,arm_oi_cil_cerca
                                  ,arm_dp_cerca
                                  ,arm_endurecido_cerca
                                  ,arm_capa_cerca
                                  ,arm_plus_max_cerca
                                  ,datos.getInt("fch_id")
                                  )
                    , new Armazon(
                                   arm_id_lejos
                                  ,arm_tipo_lejos
                                  ,arm_marca_lejos
                                  ,arm_cristal_lejos
                                  ,arm_add_lejos
                                  ,arm_od_a_lejos
                                  ,arm_od_esf_lejos
                                  ,arm_od_cil_lejos
                                  ,arm_oi_a_lejos
                                  ,arm_oi_esf_lejos
                                  ,arm_oi_cil_lejos
                                  ,arm_dp_lejos
                                  ,arm_endurecido_lejos
                                  ,arm_capa_lejos
                                  ,arm_plus_max_lejos
                                  ,datos.getInt("fch_id")
                                  )
                    , new User(user_us_id, user_us_nombre, user_us_pass, user_us_tipo, user_us_estado)
                    )
            );
        }
        BD.cerrar();
        
        return lista;
    }
    
    public ArrayList<Ficha> listar(int idFicha,int estado) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        String sql="SELECT *,(SELECT doctor.doc_nombre from doctor where doctor.doc_rut=f.doctor_doc_rut) as 'doc_nombre',(SELECT doctor.doc_telefono from doctor where doctor.doc_rut=f.doctor_doc_rut) as 'doc_telefono',(SELECT doctor.doc_mail from doctor where doctor.doc_rut=f.doctor_doc_rut) as 'doc_mail',(SELECT doctor.doc_estado from doctor where doctor.doc_rut=f.doctor_doc_rut) as 'doc_estado',(SELECT descuento.des_nombre from descuento where f.descuento_des_id=descuento.des_id) as 'des_nombre',(SELECT descuento.des_descripcion from descuento where f.descuento_des_id=descuento.des_id) as 'des_descripcion',(SELECT descuento.des_porc from descuento where f.descuento_des_id=descuento.des_id) as 'des_porc',(SELECT descuento.des_estado from descuento where f.descuento_des_id=descuento.des_id) as 'des_estado',(SELECT institucion.ins_nombre from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_nombre',(SELECT institucion.ins_telefono from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_telefono',(SELECT institucion.ins_mail from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_mail',(SELECT institucion.ins_direccion from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_direccion',(SELECT institucion.ins_comuna from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_comuna',(SELECT institucion.ins_ciudad from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_ciudad',(SELECT institucion.ins_estado from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_estado',(SELECT despacho.dsp_rut from despacho where f.despacho_dsp_id=despacho.dsp_id)  as 'dsp_rut',(SELECT despacho.dsp_nombre from despacho where f.despacho_dsp_id=despacho.dsp_id)  as 'dsp_nombre',(SELECT despacho.dsp_fecha from despacho where f.despacho_dsp_id=despacho.dsp_id)  as 'dsp_fecha',(SELECT armazon.arm_id from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_id_cerca',(SELECT armazon.arm_tipo from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_tipo_cerca',(SELECT armazon.arm_marca from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_marca_cerca',(SELECT armazon.arm_cristal from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_cristal_cerca',(SELECT armazon.arm_add from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_add_cerca',(SELECT armazon.arm_od_a from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_od_a_cerca',(SELECT armazon.arm_od_esf from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_od_esf_cerca',(SELECT armazon.arm_od_cil from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_od_cil_cerca',(SELECT armazon.arm_oi_a from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_oi_a_cerca',(SELECT armazon.arm_oi_esf from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_oi_esf_cerca',(SELECT armazon.arm_oi_cil from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_oi_cil_cerca',(SELECT armazon.arm_dp from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_dp_cerca',(SELECT armazon.arm_endurecido from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_endurecido_cerca',(SELECT armazon.arm_capa from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_capa_cerca',(SELECT armazon.arm_plus_max from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_plus_max_cerca',(SELECT armazon.arm_id from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_id_lejos',(SELECT armazon.arm_tipo from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_tipo_lejos',(SELECT armazon.arm_marca from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_marca_lejos',(SELECT armazon.arm_cristal from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_cristal_lejos',(SELECT armazon.arm_add from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_add_lejos',(SELECT armazon.arm_od_a from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_od_a_lejos',(SELECT armazon.arm_od_esf from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_od_esf_lejos',(SELECT armazon.arm_od_cil from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_od_cil_lejos',(SELECT armazon.arm_oi_a from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_oi_a_lejos',(SELECT armazon.arm_oi_esf from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_oi_esf_lejos',(SELECT armazon.arm_oi_cil from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_oi_cil_lejos',(SELECT armazon.arm_dp from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_dp_lejos',(SELECT armazon.arm_endurecido from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_endurecido_lejos',(SELECT armazon.arm_capa from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_capa_lejos',(SELECT armazon.arm_plus_max from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_plus_max_lejos',"
                + "(SELECT user.us_id from user where user.us_id=user_us_id) as us_id,"
                + "(SELECT user.us_nombre from user where user.us_id=user_us_id) as us_nombre,"
                + "(SELECT user.us_pass from user WHERE user.us_id=user_us_id) as us_pass,"
                + "(SELECT user.us_tipo from user WHERE user.us_id=user_us_id) as us_tipo,"
                + "(SELECT user.us_estado from user WHERE user.us_id=user_us_id) as us_estado"
                + " from ficha f, cliente cli where f.cliente_cli_rut=cli.cli_rut  and f.fch_id='"+idFicha+"' and f.fch_estado <> 0";
        if(estado == 0)
            sql="SELECT *,(SELECT doctor.doc_nombre from doctor where doctor.doc_rut=f.doctor_doc_rut) as 'doc_nombre',(SELECT doctor.doc_telefono from doctor where doctor.doc_rut=f.doctor_doc_rut) as 'doc_telefono',(SELECT doctor.doc_mail from doctor where doctor.doc_rut=f.doctor_doc_rut) as 'doc_mail',(SELECT doctor.doc_estado from doctor where doctor.doc_rut=f.doctor_doc_rut) as 'doc_estado',(SELECT descuento.des_nombre from descuento where f.descuento_des_id=descuento.des_id) as 'des_nombre',(SELECT descuento.des_descripcion from descuento where f.descuento_des_id=descuento.des_id) as 'des_descripcion',(SELECT descuento.des_porc from descuento where f.descuento_des_id=descuento.des_id) as 'des_porc',(SELECT descuento.des_estado from descuento where f.descuento_des_id=descuento.des_id) as 'des_estado',(SELECT institucion.ins_nombre from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_nombre',(SELECT institucion.ins_telefono from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_telefono',(SELECT institucion.ins_mail from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_mail',(SELECT institucion.ins_direccion from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_direccion',(SELECT institucion.ins_comuna from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_comuna',(SELECT institucion.ins_ciudad from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_ciudad',(SELECT institucion.ins_estado from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_estado',(SELECT despacho.dsp_rut from despacho where f.despacho_dsp_id=despacho.dsp_id)  as 'dsp_rut',(SELECT despacho.dsp_nombre from despacho where f.despacho_dsp_id=despacho.dsp_id)  as 'dsp_nombre',(SELECT despacho.dsp_fecha from despacho where f.despacho_dsp_id=despacho.dsp_id)  as 'dsp_fecha',(SELECT armazon.arm_id from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_id_cerca',(SELECT armazon.arm_tipo from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_tipo_cerca',(SELECT armazon.arm_marca from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_marca_cerca',(SELECT armazon.arm_cristal from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_cristal_cerca',(SELECT armazon.arm_add from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_add_cerca',(SELECT armazon.arm_od_a from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_od_a_cerca',(SELECT armazon.arm_od_esf from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_od_esf_cerca',(SELECT armazon.arm_od_cil from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_od_cil_cerca',(SELECT armazon.arm_oi_a from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_oi_a_cerca',(SELECT armazon.arm_oi_esf from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_oi_esf_cerca',(SELECT armazon.arm_oi_cil from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_oi_cil_cerca',(SELECT armazon.arm_dp from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_dp_cerca',(SELECT armazon.arm_endurecido from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_endurecido_cerca',(SELECT armazon.arm_capa from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_capa_cerca',(SELECT armazon.arm_plus_max from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_plus_max_cerca',(SELECT armazon.arm_id from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_id_lejos',(SELECT armazon.arm_tipo from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_tipo_lejos',(SELECT armazon.arm_marca from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_marca_lejos',(SELECT armazon.arm_cristal from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_cristal_lejos',(SELECT armazon.arm_add from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_add_lejos',(SELECT armazon.arm_od_a from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_od_a_lejos',(SELECT armazon.arm_od_esf from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_od_esf_lejos',(SELECT armazon.arm_od_cil from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_od_cil_lejos',(SELECT armazon.arm_oi_a from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_oi_a_lejos',(SELECT armazon.arm_oi_esf from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_oi_esf_lejos',(SELECT armazon.arm_oi_cil from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_oi_cil_lejos',(SELECT armazon.arm_dp from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_dp_lejos',(SELECT armazon.arm_endurecido from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_endurecido_lejos',(SELECT armazon.arm_capa from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_capa_lejos',(SELECT armazon.arm_plus_max from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_plus_max_lejos',"
                    + "(SELECT user.us_id from user where user.us_id=user_us_id) as us_id,"
                + "(SELECT user.us_nombre from user where user.us_id=user_us_id) as us_nombre,"
                + "(SELECT user.us_pass from user WHERE user.us_id=user_us_id) as us_pass,"
                + "(SELECT user.us_tipo from user WHERE user.us_id=user_us_id) as us_tipo,"
                + "(SELECT user.us_estado from user WHERE user.us_id=user_us_id) as us_estado"
                    + " from ficha f, cliente cli where f.cliente_cli_rut=cli.cli_rut  and f.fch_id='"+idFicha+"' and f.fch_estado = 0";
        if(estado == 4)
            sql="SELECT *,(SELECT doctor.doc_nombre from doctor where doctor.doc_rut=f.doctor_doc_rut) as 'doc_nombre',(SELECT doctor.doc_telefono from doctor where doctor.doc_rut=f.doctor_doc_rut) as 'doc_telefono',(SELECT doctor.doc_mail from doctor where doctor.doc_rut=f.doctor_doc_rut) as 'doc_mail',(SELECT doctor.doc_estado from doctor where doctor.doc_rut=f.doctor_doc_rut) as 'doc_estado',(SELECT descuento.des_nombre from descuento where f.descuento_des_id=descuento.des_id) as 'des_nombre',(SELECT descuento.des_descripcion from descuento where f.descuento_des_id=descuento.des_id) as 'des_descripcion',(SELECT descuento.des_porc from descuento where f.descuento_des_id=descuento.des_id) as 'des_porc',(SELECT descuento.des_estado from descuento where f.descuento_des_id=descuento.des_id) as 'des_estado',(SELECT institucion.ins_nombre from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_nombre',(SELECT institucion.ins_telefono from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_telefono',(SELECT institucion.ins_mail from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_mail',(SELECT institucion.ins_direccion from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_direccion',(SELECT institucion.ins_comuna from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_comuna',(SELECT institucion.ins_ciudad from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_ciudad',(SELECT institucion.ins_estado from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_estado',(SELECT despacho.dsp_rut from despacho where f.despacho_dsp_id=despacho.dsp_id)  as 'dsp_rut',(SELECT despacho.dsp_nombre from despacho where f.despacho_dsp_id=despacho.dsp_id)  as 'dsp_nombre',(SELECT despacho.dsp_fecha from despacho where f.despacho_dsp_id=despacho.dsp_id)  as 'dsp_fecha',(SELECT armazon.arm_id from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_id_cerca',(SELECT armazon.arm_tipo from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_tipo_cerca',(SELECT armazon.arm_marca from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_marca_cerca',(SELECT armazon.arm_cristal from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_cristal_cerca',(SELECT armazon.arm_add from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_add_cerca',(SELECT armazon.arm_od_a from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_od_a_cerca',(SELECT armazon.arm_od_esf from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_od_esf_cerca',(SELECT armazon.arm_od_cil from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_od_cil_cerca',(SELECT armazon.arm_oi_a from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_oi_a_cerca',(SELECT armazon.arm_oi_esf from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_oi_esf_cerca',(SELECT armazon.arm_oi_cil from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_oi_cil_cerca',(SELECT armazon.arm_dp from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_dp_cerca',(SELECT armazon.arm_endurecido from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_endurecido_cerca',(SELECT armazon.arm_capa from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_capa_cerca',(SELECT armazon.arm_plus_max from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_plus_max_cerca',(SELECT armazon.arm_id from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_id_lejos',(SELECT armazon.arm_tipo from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_tipo_lejos',(SELECT armazon.arm_marca from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_marca_lejos',(SELECT armazon.arm_cristal from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_cristal_lejos',(SELECT armazon.arm_add from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_add_lejos',(SELECT armazon.arm_od_a from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_od_a_lejos',(SELECT armazon.arm_od_esf from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_od_esf_lejos',(SELECT armazon.arm_od_cil from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_od_cil_lejos',(SELECT armazon.arm_oi_a from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_oi_a_lejos',(SELECT armazon.arm_oi_esf from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_oi_esf_lejos',(SELECT armazon.arm_oi_cil from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_oi_cil_lejos',(SELECT armazon.arm_dp from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_dp_lejos',(SELECT armazon.arm_endurecido from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_endurecido_lejos',(SELECT armazon.arm_capa from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_capa_lejos',(SELECT armazon.arm_plus_max from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_plus_max_lejos',"
                    + "(SELECT user.us_id from user where user.us_id=user_us_id) as us_id,"
                + "(SELECT user.us_nombre from user where user.us_id=user_us_id) as us_nombre,"
                + "(SELECT user.us_pass from user WHERE user.us_id=user_us_id) as us_pass,"
                + "(SELECT user.us_tipo from user WHERE user.us_id=user_us_id) as us_tipo,"
                + "(SELECT user.us_estado from user WHERE user.us_id=user_us_id) as us_estado"
                    + " from ficha f, cliente cli where f.cliente_cli_rut=cli.cli_rut  and f.fch_id='"+idFicha+"' and f.fch_estado = 4";
        if(idFicha==0){
         sql="SELECT *,(SELECT doctor.doc_nombre from doctor where doctor.doc_rut=f.doctor_doc_rut) as 'doc_nombre',(SELECT doctor.doc_telefono from doctor where doctor.doc_rut=f.doctor_doc_rut) as 'doc_telefono',(SELECT doctor.doc_mail from doctor where doctor.doc_rut=f.doctor_doc_rut) as 'doc_mail',(SELECT doctor.doc_estado from doctor where doctor.doc_rut=f.doctor_doc_rut) as 'doc_estado',(SELECT descuento.des_nombre from descuento where f.descuento_des_id=descuento.des_id) as 'des_nombre',(SELECT descuento.des_descripcion from descuento where f.descuento_des_id=descuento.des_id) as 'des_descripcion',(SELECT descuento.des_porc from descuento where f.descuento_des_id=descuento.des_id) as 'des_porc',(SELECT descuento.des_estado from descuento where f.descuento_des_id=descuento.des_id) as 'des_estado',(SELECT institucion.ins_nombre from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_nombre',(SELECT institucion.ins_telefono from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_telefono',(SELECT institucion.ins_mail from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_mail',(SELECT institucion.ins_direccion from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_direccion',(SELECT institucion.ins_comuna from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_comuna',(SELECT institucion.ins_ciudad from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_ciudad',(SELECT institucion.ins_estado from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_estado',(SELECT despacho.dsp_rut from despacho where f.despacho_dsp_id=despacho.dsp_id)  as 'dsp_rut',(SELECT despacho.dsp_nombre from despacho where f.despacho_dsp_id=despacho.dsp_id)  as 'dsp_nombre',(SELECT despacho.dsp_fecha from despacho where f.despacho_dsp_id=despacho.dsp_id)  as 'dsp_fecha',(SELECT armazon.arm_id from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_id_cerca',(SELECT armazon.arm_tipo from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_tipo_cerca',(SELECT armazon.arm_cristal from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_cristal_cerca',(SELECT armazon.arm_add from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_add_cerca',(SELECT armazon.arm_od_a from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_od_a_cerca',(SELECT armazon.arm_od_esf from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_od_esf_cerca',(SELECT armazon.arm_od_cil from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_od_cil_cerca',(SELECT armazon.arm_oi_a from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_oi_a_cerca',(SELECT armazon.arm_oi_esf from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_oi_esf_cerca',(SELECT armazon.arm_oi_cil from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_oi_cil_cerca',(SELECT armazon.arm_dp from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_dp_cerca',(SELECT armazon.arm_endurecido from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_endurecido_cerca',(SELECT armazon.arm_capa from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_capa_cerca',(SELECT armazon.arm_plus_max from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_plus_max_cerca',(SELECT armazon.arm_marca from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_marca_cerca',(SELECT armazon.arm_id from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_id_lejos',(SELECT armazon.arm_tipo from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_tipo_lejos',(SELECT armazon.arm_cristal from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_cristal_lejos',(SELECT armazon.arm_add from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_add_lejos',(SELECT armazon.arm_od_a from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_od_a_lejos',(SELECT armazon.arm_od_esf from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_od_esf_lejos',(SELECT armazon.arm_od_cil from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_od_cil_lejos',(SELECT armazon.arm_oi_a from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_oi_a_lejos',(SELECT armazon.arm_oi_esf from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_oi_esf_lejos',(SELECT armazon.arm_oi_cil from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_oi_cil_lejos',(SELECT armazon.arm_dp from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_dp_lejos',(SELECT armazon.arm_endurecido from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_endurecido_lejos',(SELECT armazon.arm_capa from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_capa_lejos',(SELECT armazon.arm_plus_max from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_plus_max_lejos',(SELECT armazon.arm_marca from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_marca_lejos', "
                 + "(SELECT user.us_id from user where user.us_id=user_us_id) as us_id,"
                + "(SELECT user.us_nombre from user where user.us_id=user_us_id) as us_nombre,"
                + "(SELECT user.us_pass from user WHERE user.us_id=user_us_id) as us_pass,"
                + "(SELECT user.us_tipo from user WHERE user.us_id=user_us_id) as us_tipo,"
                + "(SELECT user.us_estado from user WHERE user.us_id=user_us_id) as us_estado"
                 + "from ficha f, cliente cli where f.cliente_cli_rut=cli.cli_rut and f.fch_estado <> 0";
        }
        if(idFicha==-1){
        sql="SELECT *,(SELECT doctor.doc_nombre from doctor where doctor.doc_rut=f.doctor_doc_rut) as 'doc_nombre',(SELECT doctor.doc_telefono from doctor where doctor.doc_rut=f.doctor_doc_rut) as 'doc_telefono',(SELECT doctor.doc_mail from doctor where doctor.doc_rut=f.doctor_doc_rut) as 'doc_mail',(SELECT doctor.doc_estado from doctor where doctor.doc_rut=f.doctor_doc_rut) as 'doc_estado',(SELECT descuento.des_nombre from descuento where f.descuento_des_id=descuento.des_id) as 'des_nombre',(SELECT descuento.des_descripcion from descuento where f.descuento_des_id=descuento.des_id) as 'des_descripcion',(SELECT descuento.des_porc from descuento where f.descuento_des_id=descuento.des_id) as 'des_porc',(SELECT descuento.des_estado from descuento where f.descuento_des_id=descuento.des_id) as 'des_estado',(SELECT institucion.ins_nombre from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_nombre',(SELECT institucion.ins_telefono from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_telefono',(SELECT institucion.ins_mail from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_mail',(SELECT institucion.ins_direccion from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_direccion',(SELECT institucion.ins_comuna from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_comuna',(SELECT institucion.ins_ciudad from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_ciudad',(SELECT institucion.ins_estado from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_estado',(SELECT despacho.dsp_rut from despacho where f.despacho_dsp_id=despacho.dsp_id)  as 'dsp_rut',(SELECT despacho.dsp_nombre from despacho where f.despacho_dsp_id=despacho.dsp_id)  as 'dsp_nombre',(SELECT despacho.dsp_fecha from despacho where f.despacho_dsp_id=despacho.dsp_id)  as 'dsp_fecha',(SELECT armazon.arm_id from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_id_cerca',(SELECT armazon.arm_tipo from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_tipo_cerca',(SELECT armazon.arm_cristal from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_cristal_cerca',(SELECT armazon.arm_add from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_add_cerca',(SELECT armazon.arm_od_a from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_od_a_cerca',(SELECT armazon.arm_od_esf from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_od_esf_cerca',(SELECT armazon.arm_od_cil from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_od_cil_cerca',(SELECT armazon.arm_oi_a from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_oi_a_cerca',(SELECT armazon.arm_oi_esf from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_oi_esf_cerca',(SELECT armazon.arm_oi_cil from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_oi_cil_cerca',(SELECT armazon.arm_dp from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_dp_cerca',(SELECT armazon.arm_endurecido from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_endurecido_cerca',(SELECT armazon.arm_capa from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_capa_cerca',(SELECT armazon.arm_plus_max from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_plus_max_cerca',(SELECT armazon.arm_marca from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_marca_cerca',(SELECT armazon.arm_id from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_id_lejos',(SELECT armazon.arm_tipo from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_tipo_lejos',(SELECT armazon.arm_cristal from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_cristal_lejos',(SELECT armazon.arm_add from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_add_lejos',(SELECT armazon.arm_od_a from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_od_a_lejos',(SELECT armazon.arm_od_esf from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_od_esf_lejos',(SELECT armazon.arm_od_cil from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_od_cil_lejos',(SELECT armazon.arm_oi_a from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_oi_a_lejos',(SELECT armazon.arm_oi_esf from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_oi_esf_lejos',(SELECT armazon.arm_oi_cil from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_oi_cil_lejos',(SELECT armazon.arm_dp from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_dp_lejos',(SELECT armazon.arm_endurecido from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_endurecido_lejos',(SELECT armazon.arm_capa from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_capa_lejos',(SELECT armazon.arm_plus_max from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_plus_max_lejos',(SELECT armazon.arm_marca from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_marca_lejos',"
                + "(SELECT user.us_id from user where user.us_id=user_us_id) as us_id,"
                + "(SELECT user.us_nombre from user where user.us_id=user_us_id) as us_nombre,"
                + "(SELECT user.us_pass from user WHERE user.us_id=user_us_id) as us_pass,"
                + "(SELECT user.us_tipo from user WHERE user.us_id=user_us_id) as us_tipo,"
                + "(SELECT user.us_estado from user WHERE user.us_id=user_us_id) as us_estado"
                + " from ficha f, cliente cli where f.cliente_cli_rut=cli.cli_rut and f.fch_estado = 0";
        }
        PreparedStatement consulta = BD.obtener().prepareStatement(sql);
        ResultSet datos = consulta.executeQuery();
        ArrayList<Ficha> lista = new ArrayList<>();
        while (datos.next()) {
            
            Date fch_fecha= new Date();
            Date fch_fecha_entrega= new Date();
            Date dsp_fecha= new Date();
            SimpleDateFormat parseador = new SimpleDateFormat("dd-MM-yy");
            try {
                fch_fecha = datos.getDate("fch_fecha");
                fch_fecha_entrega = datos.getDate("f.fch_fecha_entrega");
                dsp_fecha = datos.getDate("dsp_fecha");//parseador.parse();
            } catch (Exception e) {
                System.out.println("se cayo al intentar convertir la fecha");   
            }
            // ficha
            int fch_valor_total=0;
            int fch_saldo=0;
            int fch_estado=0;
            try {fch_valor_total=datos.getInt("fch_valor_total");} catch (Exception e) { fch_valor_total=0;}
            try {fch_saldo=datos.getInt("fch_saldo");} catch (Exception e) { fch_saldo=0;}
            try {fch_estado=datos.getInt("fch_estado");} catch (Exception e) { fch_estado=0;}
            ////------------clace doctor
            String doctor_doc_rut="";
            String doc_nombre="";
            String doc_telefono="";
            String doc_mail="";
            try {doctor_doc_rut=datos.getString("doctor_doc_rut");} catch (Exception e) { doctor_doc_rut="";}
            try {doc_nombre=datos.getString("doc_nombre");} catch (Exception e) { doc_nombre="";}
            try {doc_telefono=datos.getString("doc_telefono");} catch (Exception e) { doc_telefono="";}
            try {doc_mail=datos.getString("doc_mail");} catch (Exception e) { doc_mail="";}
            //--int
            int doc_estado=0;
            try {doc_estado=datos.getInt("doc_estado");} catch (Exception e) { doc_estado=0;}
            ///-------------clace descuento
            String des_nombre="";
            String des_descripcion="";
            try {des_nombre=datos.getString("des_nombre");} catch (Exception e) { des_nombre="";}
            try {des_descripcion=datos.getString("des_descripcion");} catch (Exception e) { des_descripcion="";}
            //--int
            int descuento_des_id=0;
            int des_porc=0;
            int des_estado=0;
            try {descuento_des_id=datos.getInt("descuento_des_id");} catch (Exception e) { descuento_des_id=0;}
            try {des_porc=datos.getInt("des_porc");} catch (Exception e) { des_porc=0;}
            try {des_estado=datos.getInt("des_estado");} catch (Exception e) { des_estado=0;}
            ///------------------------institucion
            String ins_nombre="";
            String ins_telefono="";
            String ins_mail="";
            String ins_direccion="";
            String ins_comuna="";
            String ins_ciudad="";
            try {ins_nombre=datos.getString("ins_nombre");} catch (Exception e) { ins_nombre="";}
            try {ins_telefono=datos.getString("ins_telefono");} catch (Exception e) { ins_telefono="";}
            try {ins_mail=datos.getString("ins_mail");} catch (Exception e) { ins_mail="";}
            try {ins_direccion=datos.getString("ins_direccion");} catch (Exception e) { ins_direccion="";}
            try {ins_comuna=datos.getString("ins_comuna");} catch (Exception e) { ins_comuna="";}
            try {ins_ciudad=datos.getString("ins_ciudad");} catch (Exception e) { ins_ciudad="";}
            //int
            int institucion_ins_id=0;
            int ins_estado=0;
            try {institucion_ins_id=datos.getInt("institucion_ins_id");} catch (Exception e) { institucion_ins_id=0;}
            try {ins_estado=datos.getInt("ins_estado");} catch (Exception e) { ins_estado=0;}
            //despacho
            String dsp_rut="";
            String dsp_nombre="";
            try {dsp_rut=datos.getString("dsp_rut");} catch (Exception e) { dsp_rut="";}
            try {dsp_nombre=datos.getString("dsp_nombre");} catch (Exception e) { dsp_nombre="";}
            //--int
            int despacho_dsp_id=0;
            try {despacho_dsp_id=datos.getInt("despacho_dsp_id");} catch (Exception e) { despacho_dsp_id=0;}
            //armazon cerca
            String arm_marca_cerca="";
            String arm_cristal_cerca="";
            String arm_add_cerca="";
            String arm_od_a_cerca="";
            String arm_od_esf_cerca="";
            String arm_od_cil_cerca="";
            String arm_oi_a_cerca="";
            String arm_oi_esf_cerca="";
            String arm_oi_cil_cerca="";
            try {arm_marca_cerca=datos.getString("arm_marca_cerca");} catch (Exception e) { arm_marca_cerca="";}
            try {arm_cristal_cerca=datos.getString("arm_cristal_cerca");} catch (Exception e) { arm_cristal_cerca="";}
            try {arm_add_cerca=datos.getString("arm_add_cerca");} catch (Exception e) { arm_add_cerca="";}
            try {arm_od_a_cerca=datos.getString("arm_od_a_cerca");} catch (Exception e) { arm_od_a_cerca="";}
            try {arm_od_esf_cerca=datos.getString("arm_od_esf_cerca");} catch (Exception e) { arm_od_esf_cerca="";}
            try {arm_od_cil_cerca=datos.getString("arm_od_cil_cerca");} catch (Exception e) { arm_od_cil_cerca="";}
            try {arm_oi_a_cerca=datos.getString("arm_oi_a_cerca");} catch (Exception e) { arm_oi_a_cerca="";}
            try {arm_oi_esf_cerca=datos.getString("arm_oi_esf_cerca");} catch (Exception e) { arm_oi_esf_cerca="";}
            try {arm_oi_cil_cerca=datos.getString("arm_oi_cil_cerca");} catch (Exception e) { arm_oi_cil_cerca="";}
            //--int
            int arm_id_cerca=0;
            int arm_tipo_cerca=0;
            int arm_dp_cerca=0;
            int arm_endurecido_cerca=0;
            int arm_capa_cerca=0;
            int arm_plus_max_cerca=0;
            try {arm_id_cerca=datos.getInt("arm_id_cerca");} catch (Exception e) { arm_id_cerca=0;}
            try {arm_tipo_cerca=datos.getInt("arm_tipo_cerca");} catch (Exception e) { arm_tipo_cerca=0;}
            try {arm_dp_cerca=datos.getInt("arm_dp_cerca");} catch (Exception e) { arm_dp_cerca=0;}
            try {arm_endurecido_cerca=datos.getInt("arm_endurecido_cerca");} catch (Exception e) { arm_endurecido_cerca=0;}
            try {arm_capa_cerca=datos.getInt("arm_capa_cerca");} catch (Exception e) { arm_capa_cerca=0;}
            try {arm_plus_max_cerca=datos.getInt("arm_plus_max_cerca");} catch (Exception e) { arm_plus_max_cerca=0;}
            // armazon lejos
            String arm_marca_lejos="";
            String arm_cristal_lejos="";
            String arm_add_lejos="";
            String arm_od_a_lejos="";
            String arm_od_esf_lejos="";
            String arm_od_cil_lejos="";
            String arm_oi_a_lejos="";
            String arm_oi_esf_lejos="";
            String arm_oi_cil_lejos="";
            try {arm_marca_lejos=datos.getString("arm_marca_lejos");} catch (Exception e) { arm_marca_lejos="";}
            try {arm_cristal_lejos=datos.getString("arm_cristal_lejos");} catch (Exception e) { arm_cristal_lejos="";}
            try {arm_add_lejos=datos.getString("arm_add_lejos");} catch (Exception e) { arm_add_lejos="";}
            try {arm_od_a_lejos=datos.getString("arm_od_a_lejos");} catch (Exception e) { arm_od_a_lejos="";}
            try {arm_od_esf_lejos=datos.getString("arm_od_esf_lejos");} catch (Exception e) { arm_od_esf_cerca="";}
            try {arm_od_cil_lejos=datos.getString("arm_od_cil_lejos");} catch (Exception e) { arm_od_cil_lejos="";}
            try {arm_oi_a_lejos=datos.getString("arm_oi_a_lejos");} catch (Exception e) { arm_oi_a_lejos="";}
            try {arm_oi_esf_lejos=datos.getString("arm_oi_esf_lejos");} catch (Exception e) { arm_oi_esf_lejos="";}
            try {arm_oi_cil_lejos=datos.getString("arm_oi_cil_lejos");} catch (Exception e) { arm_oi_cil_lejos="";}
            //--int
            int arm_id_lejos=0;
            int arm_tipo_lejos=0;
            int arm_dp_lejos=0;
            int arm_endurecido_lejos=0;
            int arm_capa_lejos=0;
            int arm_plus_max_lejos=0;
            try {arm_id_lejos=datos.getInt("arm_id_lejos");} catch (Exception e) { arm_id_lejos=0;}
            try {arm_tipo_lejos=datos.getInt("arm_tipo_lejos");} catch (Exception e) { arm_tipo_lejos=0;}
            try {arm_dp_lejos=datos.getInt("arm_dp_lejos");} catch (Exception e) { arm_dp_lejos=0;}
            try {arm_endurecido_lejos=datos.getInt("arm_endurecido_lejos");} catch (Exception e) { arm_endurecido_lejos=0;}
            try {arm_capa_lejos=datos.getInt("arm_capa_lejos");} catch (Exception e) { arm_capa_lejos=0;}
            try {arm_plus_max_lejos=datos.getInt("arm_plus_max_lejos");} catch (Exception e) { arm_plus_max_lejos=0;}
           
            ////------------clase user
            int user_us_id=0;
            String user_us_nombre="";
            String user_us_pass="";
            int user_us_tipo=0;
            int user_us_estado=0;
            try {user_us_id=datos.getInt("us_id");} catch (Exception e) { user_us_id=0;}
            try {user_us_nombre=datos.getString("us_nombre");} catch (Exception e) { user_us_nombre="";}
            try {user_us_pass=datos.getString("us_pass");} catch (Exception e) { user_us_pass="";}
            try {user_us_tipo=datos.getInt("us_tipo");} catch (Exception e) { user_us_tipo=0;}
            try {user_us_estado=datos.getInt("us_estado");} catch (Exception e) { user_us_estado=0;}
            
            lista.add(new Ficha(
                      datos.getInt("fch_id")
                    , fch_fecha
                    , fch_fecha_entrega
                    , datos.getString("fch_lugar_entrega")
                    , datos.getString("fch_hora_entrega")
                    , fch_valor_total
                    , fch_saldo
                    , datos.getString("fch_obs")
                    , datos.getInt("fch_estado")
                    , new Cliente( datos.getString("cliente_cli_rut")
                                 , datos.getString("cli_nombre")
                                 , datos.getString("cli_telefono")
                                 , datos.getString("cli_email")
                                 , datos.getString("cli_direccion")
                                 , datos.getString("cli_comuna")
                                 , datos.getString("cli_ciudad")
                                 , datos.getInt("cli_sexo")
                                 , datos.getInt("cli_edad")
                                 , datos.getInt("cli_estado")  
                                 )
                    , new Doctor(
                                  doctor_doc_rut
                                 ,doc_nombre
                                 ,doc_telefono
                                 ,doc_mail
                                 ,doc_estado
                                 )
                    , new Descuento(
                                      descuento_des_id
                                    , des_nombre
                                    , des_descripcion
                                    , des_porc
                                    , des_estado
                                    )
                    , new Institucion(
                                        institucion_ins_id
                                      , ins_nombre
                                      , ins_telefono
                                      , ins_mail
                                      , ins_direccion
                                      , ins_comuna
                                      , ins_ciudad
                                      , ins_estado
                                     )
                    , new Despacho(
                                     despacho_dsp_id
                                   , dsp_rut
                                   , dsp_nombre
                                   , dsp_fecha
                                   , datos.getInt("fch_id")
                                  )
                    , new Armazon(
                                   arm_id_cerca
                                  ,arm_tipo_cerca
                                  ,arm_marca_cerca
                                  ,arm_cristal_cerca
                                  ,arm_add_cerca
                                  ,arm_od_a_cerca
                                  ,arm_od_esf_cerca
                                  ,arm_od_cil_cerca
                                  ,arm_oi_a_cerca
                                  ,arm_oi_esf_cerca
                                  ,arm_oi_cil_cerca
                                  ,arm_dp_cerca
                                  ,arm_endurecido_cerca
                                  ,arm_capa_cerca
                                  ,arm_plus_max_cerca
                                  ,datos.getInt("fch_id")
                                  )
                    , new Armazon(
                                   arm_id_lejos
                                  ,arm_tipo_lejos
                                  ,arm_marca_lejos
                                  ,arm_cristal_lejos
                                  ,arm_add_lejos
                                  ,arm_od_a_lejos
                                  ,arm_od_esf_lejos
                                  ,arm_od_cil_lejos
                                  ,arm_oi_a_lejos
                                  ,arm_oi_esf_lejos
                                  ,arm_oi_cil_lejos
                                  ,arm_dp_lejos
                                  ,arm_endurecido_lejos
                                  ,arm_capa_lejos
                                  ,arm_plus_max_lejos
                                  ,datos.getInt("fch_id")
                                  )
                    , new User(user_us_id, user_us_nombre, user_us_pass, user_us_tipo, user_us_estado)
                    )
            );
        }
        BD.cerrar();
        
        return lista;
    }
    public ArrayList<Ficha> listarFechaSesion(Date fecha1, Date fecha2, int idSesion) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Date temp=new Date();//declaro una fecha temporal   
        if(fecha1.compareTo(fecha2)>0){
            /// pongo la fecha menor en fecha1 y la mayor fecha en fecha2 para que la consulta SQL resulte
            temp=fecha2;
            fecha2=fecha1;
            fecha1=temp;
        }
        
        DateFormat fechaHora = new SimpleDateFormat("yyyy-MM-dd");
        String primera = fechaHora.format(fecha1);
        String segunda = fechaHora.format(fecha2);
        String sql="SELECT *,(SELECT doctor.doc_nombre from doctor where doctor.doc_rut=f.doctor_doc_rut) as 'doc_nombre',(SELECT doctor.doc_telefono from doctor where doctor.doc_rut=f.doctor_doc_rut) as 'doc_telefono',(SELECT doctor.doc_mail from doctor where doctor.doc_rut=f.doctor_doc_rut) as 'doc_mail',(SELECT doctor.doc_estado from doctor where doctor.doc_rut=f.doctor_doc_rut) as 'doc_estado',(SELECT descuento.des_nombre from descuento where f.descuento_des_id=descuento.des_id) as 'des_nombre',(SELECT descuento.des_descripcion from descuento where f.descuento_des_id=descuento.des_id) as 'des_descripcion',(SELECT descuento.des_porc from descuento where f.descuento_des_id=descuento.des_id) as 'des_porc',(SELECT descuento.des_estado from descuento where f.descuento_des_id=descuento.des_id) as 'des_estado',(SELECT institucion.ins_nombre from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_nombre',(SELECT institucion.ins_telefono from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_telefono',(SELECT institucion.ins_mail from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_mail',(SELECT institucion.ins_direccion from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_direccion',(SELECT institucion.ins_comuna from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_comuna',(SELECT institucion.ins_ciudad from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_ciudad',(SELECT institucion.ins_estado from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_estado',(SELECT despacho.dsp_rut from despacho where f.despacho_dsp_id=despacho.dsp_id)  as 'dsp_rut',(SELECT despacho.dsp_nombre from despacho where f.despacho_dsp_id=despacho.dsp_id)  as 'dsp_nombre',(SELECT despacho.dsp_fecha from despacho where f.despacho_dsp_id=despacho.dsp_id)  as 'dsp_fecha',(SELECT armazon.arm_id from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_id_cerca',(SELECT armazon.arm_tipo from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_tipo_cerca',(SELECT armazon.arm_cristal from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_cristal_cerca',(SELECT armazon.arm_add from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_add_cerca',(SELECT armazon.arm_od_a from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_od_a_cerca',(SELECT armazon.arm_od_esf from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_od_esf_cerca',(SELECT armazon.arm_od_cil from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_od_cil_cerca',(SELECT armazon.arm_oi_a from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_oi_a_cerca',(SELECT armazon.arm_oi_esf from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_oi_esf_cerca',(SELECT armazon.arm_oi_cil from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_oi_cil_cerca',(SELECT armazon.arm_dp from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_dp_cerca',(SELECT armazon.arm_endurecido from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_endurecido_cerca',(SELECT armazon.arm_capa from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_capa_cerca',(SELECT armazon.arm_plus_max from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_plus_max_cerca',(SELECT armazon.arm_marca from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_marca_cerca',(SELECT armazon.arm_id from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_id_lejos',(SELECT armazon.arm_tipo from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_tipo_lejos',(SELECT armazon.arm_cristal from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_cristal_lejos',(SELECT armazon.arm_add from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_add_lejos',(SELECT armazon.arm_od_a from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_od_a_lejos',(SELECT armazon.arm_od_esf from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_od_esf_lejos',(SELECT armazon.arm_od_cil from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_od_cil_lejos',(SELECT armazon.arm_oi_a from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_oi_a_lejos',(SELECT armazon.arm_oi_esf from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_oi_esf_lejos',(SELECT armazon.arm_oi_cil from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_oi_cil_lejos',(SELECT armazon.arm_dp from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_dp_lejos',(SELECT armazon.arm_endurecido from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_endurecido_lejos',(SELECT armazon.arm_capa from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_capa_lejos',(SELECT armazon.arm_plus_max from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_plus_max_lejos',(SELECT armazon.arm_marca from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_marca_lejos',"
                + "(SELECT user.us_id from user where user.us_id=user_us_id) as us_id,"
                + "(SELECT user.us_nombre from user where user.us_id=user_us_id) as us_nombre,"
                + "(SELECT user.us_pass from user WHERE user.us_id=user_us_id) as us_pass,"
                + "(SELECT user.us_tipo from user WHERE user.us_id=user_us_id) as us_tipo,"
                + "(SELECT user.us_estado from user WHERE user.us_id=user_us_id) as us_estado"
                + " from ficha f, cliente cli where f.cliente_cli_rut=cli.cli_rut and f.fch_fecha BETWEEN '"+primera+"' and '"+segunda+"' and f.user_us_id='"+idSesion+"' ORDER BY f.fch_fecha DESC";
        
        PreparedStatement consulta = BD.obtener().prepareStatement(sql);
        ResultSet datos = consulta.executeQuery();
        ArrayList<Ficha> lista = new ArrayList<>();
        while (datos.next()) {
            if(datos.getInt("fch_estado")!=0){
            Date fch_fecha= new Date();
            Date fch_fecha_entrega= new Date();
            Date dsp_fecha= new Date();
            SimpleDateFormat parseador = new SimpleDateFormat("dd-MM-yy");
            try {
                fch_fecha = datos.getDate("fch_fecha");
                fch_fecha_entrega = datos.getDate("f.fch_fecha_entrega");
                dsp_fecha = datos.getDate("dsp_fecha");//parseador.parse();
            } catch (Exception e) {
                System.out.println("se cayo al intentar convertir la fecha");   
            }
            // ficha
            int fch_valor_total=0;
            int fch_saldo=0;
            int fch_estado=0;
            try {fch_valor_total=datos.getInt("fch_valor_total");} catch (Exception e) { fch_valor_total=0;}
            try {fch_saldo=datos.getInt("fch_saldo");} catch (Exception e) { fch_saldo=0;}
            try {fch_estado=datos.getInt("fch_estado");} catch (Exception e) { fch_estado=0;}
            ////------------clace doctor
            String doctor_doc_rut="";
            String doc_nombre="";
            String doc_telefono="";
            String doc_mail="";
            try {doctor_doc_rut=datos.getString("doctor_doc_rut");} catch (Exception e) { doctor_doc_rut="";}
            try {doc_nombre=datos.getString("doc_nombre");} catch (Exception e) { doc_nombre="";}
            try {doc_telefono=datos.getString("doc_telefono");} catch (Exception e) { doc_telefono="";}
            try {doc_mail=datos.getString("doc_mail");} catch (Exception e) { doc_mail="";}
            //--int
            int doc_estado=0;
            try {doc_estado=datos.getInt("doc_estado");} catch (Exception e) { doc_estado=0;}
            ///-------------clace descuento
            String des_nombre="";
            String des_descripcion="";
            try {des_nombre=datos.getString("des_nombre");} catch (Exception e) { des_nombre="";}
            try {des_descripcion=datos.getString("des_descripcion");} catch (Exception e) { des_descripcion="";}
            //--int
            int descuento_des_id=0;
            int des_porc=0;
            int des_estado=0;
            try {descuento_des_id=datos.getInt("descuento_des_id");} catch (Exception e) { descuento_des_id=0;}
            try {des_porc=datos.getInt("des_porc");} catch (Exception e) { des_porc=0;}
            try {des_estado=datos.getInt("des_estado");} catch (Exception e) { des_estado=0;}
            ///------------------------institucion
            String ins_nombre="";
            String ins_telefono="";
            String ins_mail="";
            String ins_direccion="";
            String ins_comuna="";
            String ins_ciudad="";
            try {ins_nombre=datos.getString("ins_nombre");} catch (Exception e) { ins_nombre="";}
            try {ins_telefono=datos.getString("ins_telefono");} catch (Exception e) { ins_telefono="";}
            try {ins_mail=datos.getString("ins_mail");} catch (Exception e) { ins_mail="";}
            try {ins_direccion=datos.getString("ins_direccion");} catch (Exception e) { ins_direccion="";}
            try {ins_comuna=datos.getString("ins_comuna");} catch (Exception e) { ins_comuna="";}
            try {ins_ciudad=datos.getString("ins_ciudad");} catch (Exception e) { ins_ciudad="";}
            //int
            int institucion_ins_id=0;
            int ins_estado=0;
            try {institucion_ins_id=datos.getInt("institucion_ins_id");} catch (Exception e) { institucion_ins_id=0;}
            try {ins_estado=datos.getInt("ins_estado");} catch (Exception e) { ins_estado=0;}
            //despacho
            String dsp_rut="";
            String dsp_nombre="";
            try {dsp_rut=datos.getString("dsp_rut");} catch (Exception e) { dsp_rut="";}
            try {dsp_nombre=datos.getString("dsp_nombre");} catch (Exception e) { dsp_nombre="";}
            //--int
            int despacho_dsp_id=0;
            try {despacho_dsp_id=datos.getInt("despacho_dsp_id");} catch (Exception e) { despacho_dsp_id=0;}
            //armazon cerca
            String arm_marca_cerca="";
            String arm_cristal_cerca="";
            String arm_add_cerca="";
            String arm_od_a_cerca="";
            String arm_od_esf_cerca="";
            String arm_od_cil_cerca="";
            String arm_oi_a_cerca="";
            String arm_oi_esf_cerca="";
            String arm_oi_cil_cerca="";
            try {arm_marca_cerca=datos.getString("arm_marca_cerca");} catch (Exception e) { arm_marca_cerca="";}
            try {arm_cristal_cerca=datos.getString("arm_cristal_cerca");} catch (Exception e) { arm_cristal_cerca="";}
            try {arm_add_cerca=datos.getString("arm_add_cerca");} catch (Exception e) { arm_add_cerca="";}
            try {arm_od_a_cerca=datos.getString("arm_od_a_cerca");} catch (Exception e) { arm_od_a_cerca="";}
            try {arm_od_esf_cerca=datos.getString("arm_od_esf_cerca");} catch (Exception e) { arm_od_esf_cerca="";}
            try {arm_od_cil_cerca=datos.getString("arm_od_cil_cerca");} catch (Exception e) { arm_od_cil_cerca="";}
            try {arm_oi_a_cerca=datos.getString("arm_oi_a_cerca");} catch (Exception e) { arm_oi_a_cerca="";}
            try {arm_oi_esf_cerca=datos.getString("arm_oi_esf_cerca");} catch (Exception e) { arm_oi_esf_cerca="";}
            try {arm_oi_cil_cerca=datos.getString("arm_oi_cil_cerca");} catch (Exception e) { arm_oi_cil_cerca="";}
            //--int
            int arm_id_cerca=0;
            int arm_tipo_cerca=0;
            int arm_dp_cerca=0;
            int arm_endurecido_cerca=0;
            int arm_capa_cerca=0;
            int arm_plus_max_cerca=0;
            try {arm_id_cerca=datos.getInt("arm_id_cerca");} catch (Exception e) { arm_id_cerca=0;}
            try {arm_tipo_cerca=datos.getInt("arm_tipo_cerca");} catch (Exception e) { arm_tipo_cerca=0;}
            try {arm_dp_cerca=datos.getInt("arm_dp_cerca");} catch (Exception e) { arm_dp_cerca=0;}
            try {arm_endurecido_cerca=datos.getInt("arm_endurecido_cerca");} catch (Exception e) { arm_endurecido_cerca=0;}
            try {arm_capa_cerca=datos.getInt("arm_capa_cerca");} catch (Exception e) { arm_capa_cerca=0;}
            try {arm_plus_max_cerca=datos.getInt("arm_plus_max_cerca");} catch (Exception e) { arm_plus_max_cerca=0;}
            // armazon lejos
            String arm_marca_lejos="";
            String arm_cristal_lejos="";
            String arm_add_lejos="";
            String arm_od_a_lejos="";
            String arm_od_esf_lejos="";
            String arm_od_cil_lejos="";
            String arm_oi_a_lejos="";
            String arm_oi_esf_lejos="";
            String arm_oi_cil_lejos="";
            try {arm_marca_lejos=datos.getString("arm_marca_lejos");} catch (Exception e) { arm_marca_lejos="";}
            try {arm_cristal_lejos=datos.getString("arm_cristal_lejos");} catch (Exception e) { arm_cristal_lejos="";}
            try {arm_add_lejos=datos.getString("arm_add_lejos");} catch (Exception e) { arm_add_lejos="";}
            try {arm_od_a_lejos=datos.getString("arm_od_a_lejos");} catch (Exception e) { arm_od_a_lejos="";}
            try {arm_od_esf_lejos=datos.getString("arm_od_esf_lejos");} catch (Exception e) { arm_od_esf_cerca="";}
            try {arm_od_cil_lejos=datos.getString("arm_od_cil_lejos");} catch (Exception e) { arm_od_cil_lejos="";}
            try {arm_oi_a_lejos=datos.getString("arm_oi_a_lejos");} catch (Exception e) { arm_oi_a_lejos="";}
            try {arm_oi_esf_lejos=datos.getString("arm_oi_esf_lejos");} catch (Exception e) { arm_oi_esf_lejos="";}
            try {arm_oi_cil_lejos=datos.getString("arm_oi_cil_lejos");} catch (Exception e) { arm_oi_cil_lejos="";}
            //--int
            int arm_id_lejos=0;
            int arm_tipo_lejos=0;
            int arm_dp_lejos=0;
            int arm_endurecido_lejos=0;
            int arm_capa_lejos=0;
            int arm_plus_max_lejos=0;
            try {arm_id_lejos=datos.getInt("arm_id_lejos");} catch (Exception e) { arm_id_lejos=0;}
            try {arm_tipo_lejos=datos.getInt("arm_tipo_lejos");} catch (Exception e) { arm_tipo_lejos=0;}
            try {arm_dp_lejos=datos.getInt("arm_dp_lejos");} catch (Exception e) { arm_dp_lejos=0;}
            try {arm_endurecido_lejos=datos.getInt("arm_endurecido_lejos");} catch (Exception e) { arm_endurecido_lejos=0;}
            try {arm_capa_lejos=datos.getInt("arm_capa_lejos");} catch (Exception e) { arm_capa_lejos=0;}
            try {arm_plus_max_lejos=datos.getInt("arm_plus_max_lejos");} catch (Exception e) { arm_plus_max_lejos=0;}
            ////------------clase user
            int user_us_id=0;
            String user_us_nombre="";
            String user_us_pass="";
            int user_us_tipo=0;
            int user_us_estado=0;
            try {user_us_id=datos.getInt("us_id");} catch (Exception e) { user_us_id=0;}
            try {user_us_nombre=datos.getString("us_nombre");} catch (Exception e) { user_us_nombre="";}
            try {user_us_pass=datos.getString("us_pass");} catch (Exception e) { user_us_pass="";}
            try {user_us_tipo=datos.getInt("us_tipo");} catch (Exception e) { user_us_tipo=0;}
            try {user_us_estado=datos.getInt("us_estado");} catch (Exception e) { user_us_estado=0;}
            
            lista.add(new Ficha(
                      datos.getInt("fch_id")
                    , fch_fecha
                    , fch_fecha_entrega
                    , datos.getString("fch_lugar_entrega")
                    , datos.getString("fch_hora_entrega")
                    , fch_valor_total
                    , fch_saldo
                    , datos.getString("fch_obs")
                    , datos.getInt("fch_estado")
                    , new Cliente( datos.getString("cliente_cli_rut")
                                 , datos.getString("cli_nombre")
                                 , datos.getString("cli_telefono")
                                 , datos.getString("cli_email")
                                 , datos.getString("cli_direccion")
                                 , datos.getString("cli_comuna")
                                 , datos.getString("cli_ciudad")
                                 , datos.getInt("cli_sexo")
                                 , datos.getInt("cli_edad")
                                 , datos.getInt("cli_estado")  
                                 )
                    , new Doctor(
                                  doctor_doc_rut
                                 ,doc_nombre
                                 ,doc_telefono
                                 ,doc_mail
                                 ,doc_estado
                                 )
                    , new Descuento(
                                      descuento_des_id
                                    , des_nombre
                                    , des_descripcion
                                    , des_porc
                                    , des_estado
                                    )
                    , new Institucion(
                                        institucion_ins_id
                                      , ins_nombre
                                      , ins_telefono
                                      , ins_mail
                                      , ins_direccion
                                      , ins_comuna
                                      , ins_ciudad
                                      , ins_estado
                                     )
                    , new Despacho(
                                     despacho_dsp_id
                                   , dsp_rut
                                   , dsp_nombre
                                   , dsp_fecha
                                   , datos.getInt("fch_id")
                                  )
                    , new Armazon(
                                   arm_id_cerca
                                  ,arm_tipo_cerca
                                  ,arm_marca_cerca
                                  ,arm_cristal_cerca
                                  ,arm_add_cerca
                                  ,arm_od_a_cerca
                                  ,arm_od_esf_cerca
                                  ,arm_od_cil_cerca
                                  ,arm_oi_a_cerca
                                  ,arm_oi_esf_cerca
                                  ,arm_oi_cil_cerca
                                  ,arm_dp_cerca
                                  ,arm_endurecido_cerca
                                  ,arm_capa_cerca
                                  ,arm_plus_max_cerca
                                  ,datos.getInt("fch_id")
                                  )
                    , new Armazon(
                                   arm_id_lejos
                                  ,arm_tipo_lejos
                                  ,arm_marca_lejos
                                  ,arm_cristal_lejos
                                  ,arm_add_lejos
                                  ,arm_od_a_lejos
                                  ,arm_od_esf_lejos
                                  ,arm_od_cil_lejos
                                  ,arm_oi_a_lejos
                                  ,arm_oi_esf_lejos
                                  ,arm_oi_cil_lejos
                                  ,arm_dp_lejos
                                  ,arm_endurecido_lejos
                                  ,arm_capa_lejos
                                  ,arm_plus_max_lejos
                                  ,datos.getInt("fch_id")
                                  )
                    , new User(user_us_id, user_us_nombre, user_us_pass, user_us_tipo, user_us_estado)
                    )
            );
            }
        }
        BD.cerrar();
        
        return lista;
    }
    
    public ArrayList<Ficha> listarFecha(Date fecha1,Date fecha2) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
    Date temp=new Date();//declaro una fecha temporal   
        if(fecha1.compareTo(fecha2)>0){
            /// pongo la fecha menor en fecha1 y la mayor fecha en fecha2 para que la consulta SQL resulte
            temp=fecha2;
            fecha2=fecha1;
            fecha1=temp;
        }
        
        DateFormat fechaHora = new SimpleDateFormat("yyyy-MM-dd");
        String primera = fechaHora.format(fecha1);
        String segunda = fechaHora.format(fecha2);
        String sql="SELECT *,(SELECT doctor.doc_nombre from doctor where doctor.doc_rut=f.doctor_doc_rut) as 'doc_nombre',(SELECT doctor.doc_telefono from doctor where doctor.doc_rut=f.doctor_doc_rut) as 'doc_telefono',(SELECT doctor.doc_mail from doctor where doctor.doc_rut=f.doctor_doc_rut) as 'doc_mail',(SELECT doctor.doc_estado from doctor where doctor.doc_rut=f.doctor_doc_rut) as 'doc_estado',(SELECT descuento.des_nombre from descuento where f.descuento_des_id=descuento.des_id) as 'des_nombre',(SELECT descuento.des_descripcion from descuento where f.descuento_des_id=descuento.des_id) as 'des_descripcion',(SELECT descuento.des_porc from descuento where f.descuento_des_id=descuento.des_id) as 'des_porc',(SELECT descuento.des_estado from descuento where f.descuento_des_id=descuento.des_id) as 'des_estado',(SELECT institucion.ins_nombre from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_nombre',(SELECT institucion.ins_telefono from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_telefono',(SELECT institucion.ins_mail from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_mail',(SELECT institucion.ins_direccion from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_direccion',(SELECT institucion.ins_comuna from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_comuna',(SELECT institucion.ins_ciudad from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_ciudad',(SELECT institucion.ins_estado from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_estado',(SELECT despacho.dsp_rut from despacho where f.despacho_dsp_id=despacho.dsp_id)  as 'dsp_rut',(SELECT despacho.dsp_nombre from despacho where f.despacho_dsp_id=despacho.dsp_id)  as 'dsp_nombre',(SELECT despacho.dsp_fecha from despacho where f.despacho_dsp_id=despacho.dsp_id)  as 'dsp_fecha',(SELECT armazon.arm_id from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_id_cerca',(SELECT armazon.arm_tipo from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_tipo_cerca',(SELECT armazon.arm_cristal from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_cristal_cerca',(SELECT armazon.arm_add from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_add_cerca',(SELECT armazon.arm_od_a from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_od_a_cerca',(SELECT armazon.arm_od_esf from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_od_esf_cerca',(SELECT armazon.arm_od_cil from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_od_cil_cerca',(SELECT armazon.arm_oi_a from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_oi_a_cerca',(SELECT armazon.arm_oi_esf from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_oi_esf_cerca',(SELECT armazon.arm_oi_cil from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_oi_cil_cerca',(SELECT armazon.arm_dp from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_dp_cerca',(SELECT armazon.arm_endurecido from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_endurecido_cerca',(SELECT armazon.arm_capa from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_capa_cerca',(SELECT armazon.arm_plus_max from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_plus_max_cerca',(SELECT armazon.arm_marca from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_marca_cerca',(SELECT armazon.arm_id from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_id_lejos',(SELECT armazon.arm_tipo from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_tipo_lejos',(SELECT armazon.arm_cristal from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_cristal_lejos',(SELECT armazon.arm_add from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_add_lejos',(SELECT armazon.arm_od_a from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_od_a_lejos',(SELECT armazon.arm_od_esf from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_od_esf_lejos',(SELECT armazon.arm_od_cil from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_od_cil_lejos',(SELECT armazon.arm_oi_a from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_oi_a_lejos',(SELECT armazon.arm_oi_esf from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_oi_esf_lejos',(SELECT armazon.arm_oi_cil from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_oi_cil_lejos',(SELECT armazon.arm_dp from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_dp_lejos',(SELECT armazon.arm_endurecido from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_endurecido_lejos',(SELECT armazon.arm_capa from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_capa_lejos',(SELECT armazon.arm_plus_max from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_plus_max_lejos',(SELECT armazon.arm_marca from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_marca_lejos',"
                + "(SELECT user.us_id from user where user.us_id=user_us_id) as us_id,"
                + "(SELECT user.us_nombre from user where user.us_id=user_us_id) as us_nombre,"
                + "(SELECT user.us_pass from user WHERE user.us_id=user_us_id) as us_pass,"
                + "(SELECT user.us_tipo from user WHERE user.us_id=user_us_id) as us_tipo,"
                + "(SELECT user.us_estado from user WHERE user.us_id=user_us_id) as us_estado"
                + " from ficha f, cliente cli where f.cliente_cli_rut=cli.cli_rut and f.fch_fecha BETWEEN '"+primera+"' and '"+segunda+"' ORDER BY f.fch_fecha DESC";
        
        PreparedStatement consulta = BD.obtener().prepareStatement(sql);
        ResultSet datos = consulta.executeQuery();
        ArrayList<Ficha> lista = new ArrayList<>();
        while (datos.next()) {
            if(datos.getInt("fch_estado")!=0){
            Date fch_fecha= new Date();
            Date fch_fecha_entrega= new Date();
            Date dsp_fecha= new Date();
            SimpleDateFormat parseador = new SimpleDateFormat("dd-MM-yy");
            try {
                fch_fecha = datos.getDate("fch_fecha");
                fch_fecha_entrega = datos.getDate("f.fch_fecha_entrega");
                dsp_fecha = datos.getDate("dsp_fecha");//parseador.parse();
            } catch (Exception e) {
                System.out.println("se cayo al intentar convertir la fecha");   
            }
            // ficha
            int fch_valor_total=0;
            int fch_saldo=0;
            int fch_estado=0;
            try {fch_valor_total=datos.getInt("fch_valor_total");} catch (Exception e) { fch_valor_total=0;}
            try {fch_saldo=datos.getInt("fch_saldo");} catch (Exception e) { fch_saldo=0;}
            try {fch_estado=datos.getInt("fch_estado");} catch (Exception e) { fch_estado=0;}
            ////------------clace doctor
            String doctor_doc_rut="";
            String doc_nombre="";
            String doc_telefono="";
            String doc_mail="";
            try {doctor_doc_rut=datos.getString("doctor_doc_rut");} catch (Exception e) { doctor_doc_rut="";}
            try {doc_nombre=datos.getString("doc_nombre");} catch (Exception e) { doc_nombre="";}
            try {doc_telefono=datos.getString("doc_telefono");} catch (Exception e) { doc_telefono="";}
            try {doc_mail=datos.getString("doc_mail");} catch (Exception e) { doc_mail="";}
            //--int
            int doc_estado=0;
            try {doc_estado=datos.getInt("doc_estado");} catch (Exception e) { doc_estado=0;}
            ///-------------clace descuento
            String des_nombre="";
            String des_descripcion="";
            try {des_nombre=datos.getString("des_nombre");} catch (Exception e) { des_nombre="";}
            try {des_descripcion=datos.getString("des_descripcion");} catch (Exception e) { des_descripcion="";}
            //--int
            int descuento_des_id=0;
            int des_porc=0;
            int des_estado=0;
            try {descuento_des_id=datos.getInt("descuento_des_id");} catch (Exception e) { descuento_des_id=0;}
            try {des_porc=datos.getInt("des_porc");} catch (Exception e) { des_porc=0;}
            try {des_estado=datos.getInt("des_estado");} catch (Exception e) { des_estado=0;}
            ///------------------------institucion
            String ins_nombre="";
            String ins_telefono="";
            String ins_mail="";
            String ins_direccion="";
            String ins_comuna="";
            String ins_ciudad="";
            try {ins_nombre=datos.getString("ins_nombre");} catch (Exception e) { ins_nombre="";}
            try {ins_telefono=datos.getString("ins_telefono");} catch (Exception e) { ins_telefono="";}
            try {ins_mail=datos.getString("ins_mail");} catch (Exception e) { ins_mail="";}
            try {ins_direccion=datos.getString("ins_direccion");} catch (Exception e) { ins_direccion="";}
            try {ins_comuna=datos.getString("ins_comuna");} catch (Exception e) { ins_comuna="";}
            try {ins_ciudad=datos.getString("ins_ciudad");} catch (Exception e) { ins_ciudad="";}
            //int
            int institucion_ins_id=0;
            int ins_estado=0;
            try {institucion_ins_id=datos.getInt("institucion_ins_id");} catch (Exception e) { institucion_ins_id=0;}
            try {ins_estado=datos.getInt("ins_estado");} catch (Exception e) { ins_estado=0;}
            //despacho
            String dsp_rut="";
            String dsp_nombre="";
            try {dsp_rut=datos.getString("dsp_rut");} catch (Exception e) { dsp_rut="";}
            try {dsp_nombre=datos.getString("dsp_nombre");} catch (Exception e) { dsp_nombre="";}
            //--int
            int despacho_dsp_id=0;
            try {despacho_dsp_id=datos.getInt("despacho_dsp_id");} catch (Exception e) { despacho_dsp_id=0;}
            //armazon cerca
            String arm_marca_cerca="";
            String arm_cristal_cerca="";
            String arm_add_cerca="";
            String arm_od_a_cerca="";
            String arm_od_esf_cerca="";
            String arm_od_cil_cerca="";
            String arm_oi_a_cerca="";
            String arm_oi_esf_cerca="";
            String arm_oi_cil_cerca="";
            try {arm_marca_cerca=datos.getString("arm_marca_cerca");} catch (Exception e) { arm_marca_cerca="";}
            try {arm_cristal_cerca=datos.getString("arm_cristal_cerca");} catch (Exception e) { arm_cristal_cerca="";}
            try {arm_add_cerca=datos.getString("arm_add_cerca");} catch (Exception e) { arm_add_cerca="";}
            try {arm_od_a_cerca=datos.getString("arm_od_a_cerca");} catch (Exception e) { arm_od_a_cerca="";}
            try {arm_od_esf_cerca=datos.getString("arm_od_esf_cerca");} catch (Exception e) { arm_od_esf_cerca="";}
            try {arm_od_cil_cerca=datos.getString("arm_od_cil_cerca");} catch (Exception e) { arm_od_cil_cerca="";}
            try {arm_oi_a_cerca=datos.getString("arm_oi_a_cerca");} catch (Exception e) { arm_oi_a_cerca="";}
            try {arm_oi_esf_cerca=datos.getString("arm_oi_esf_cerca");} catch (Exception e) { arm_oi_esf_cerca="";}
            try {arm_oi_cil_cerca=datos.getString("arm_oi_cil_cerca");} catch (Exception e) { arm_oi_cil_cerca="";}
            //--int
            int arm_id_cerca=0;
            int arm_tipo_cerca=0;
            int arm_dp_cerca=0;
            int arm_endurecido_cerca=0;
            int arm_capa_cerca=0;
            int arm_plus_max_cerca=0;
            try {arm_id_cerca=datos.getInt("arm_id_cerca");} catch (Exception e) { arm_id_cerca=0;}
            try {arm_tipo_cerca=datos.getInt("arm_tipo_cerca");} catch (Exception e) { arm_tipo_cerca=0;}
            try {arm_dp_cerca=datos.getInt("arm_dp_cerca");} catch (Exception e) { arm_dp_cerca=0;}
            try {arm_endurecido_cerca=datos.getInt("arm_endurecido_cerca");} catch (Exception e) { arm_endurecido_cerca=0;}
            try {arm_capa_cerca=datos.getInt("arm_capa_cerca");} catch (Exception e) { arm_capa_cerca=0;}
            try {arm_plus_max_cerca=datos.getInt("arm_plus_max_cerca");} catch (Exception e) { arm_plus_max_cerca=0;}
            // armazon lejos
            String arm_marca_lejos="";
            String arm_cristal_lejos="";
            String arm_add_lejos="";
            String arm_od_a_lejos="";
            String arm_od_esf_lejos="";
            String arm_od_cil_lejos="";
            String arm_oi_a_lejos="";
            String arm_oi_esf_lejos="";
            String arm_oi_cil_lejos="";
            try {arm_marca_lejos=datos.getString("arm_marca_lejos");} catch (Exception e) { arm_marca_lejos="";}
            try {arm_cristal_lejos=datos.getString("arm_cristal_lejos");} catch (Exception e) { arm_cristal_lejos="";}
            try {arm_add_lejos=datos.getString("arm_add_lejos");} catch (Exception e) { arm_add_lejos="";}
            try {arm_od_a_lejos=datos.getString("arm_od_a_lejos");} catch (Exception e) { arm_od_a_lejos="";}
            try {arm_od_esf_lejos=datos.getString("arm_od_esf_lejos");} catch (Exception e) { arm_od_esf_cerca="";}
            try {arm_od_cil_lejos=datos.getString("arm_od_cil_lejos");} catch (Exception e) { arm_od_cil_lejos="";}
            try {arm_oi_a_lejos=datos.getString("arm_oi_a_lejos");} catch (Exception e) { arm_oi_a_lejos="";}
            try {arm_oi_esf_lejos=datos.getString("arm_oi_esf_lejos");} catch (Exception e) { arm_oi_esf_lejos="";}
            try {arm_oi_cil_lejos=datos.getString("arm_oi_cil_lejos");} catch (Exception e) { arm_oi_cil_lejos="";}
            //--int
            int arm_id_lejos=0;
            int arm_tipo_lejos=0;
            int arm_dp_lejos=0;
            int arm_endurecido_lejos=0;
            int arm_capa_lejos=0;
            int arm_plus_max_lejos=0;
            try {arm_id_lejos=datos.getInt("arm_id_lejos");} catch (Exception e) { arm_id_lejos=0;}
            try {arm_tipo_lejos=datos.getInt("arm_tipo_lejos");} catch (Exception e) { arm_tipo_lejos=0;}
            try {arm_dp_lejos=datos.getInt("arm_dp_lejos");} catch (Exception e) { arm_dp_lejos=0;}
            try {arm_endurecido_lejos=datos.getInt("arm_endurecido_lejos");} catch (Exception e) { arm_endurecido_lejos=0;}
            try {arm_capa_lejos=datos.getInt("arm_capa_lejos");} catch (Exception e) { arm_capa_lejos=0;}
            try {arm_plus_max_lejos=datos.getInt("arm_plus_max_lejos");} catch (Exception e) { arm_plus_max_lejos=0;}
           
            ////------------clase user
            int user_us_id=0;
            String user_us_nombre="";
            String user_us_pass="";
            int user_us_tipo=0;
            int user_us_estado=0;
            try {user_us_id=datos.getInt("us_id");} catch (Exception e) { user_us_id=0;}
            try {user_us_nombre=datos.getString("us_nombre");} catch (Exception e) { user_us_nombre="";}
            try {user_us_pass=datos.getString("us_pass");} catch (Exception e) { user_us_pass="";}
            try {user_us_tipo=datos.getInt("us_tipo");} catch (Exception e) { user_us_tipo=0;}
            try {user_us_estado=datos.getInt("us_estado");} catch (Exception e) { user_us_estado=0;}
            
            lista.add(new Ficha(
                      datos.getInt("fch_id")
                    , fch_fecha
                    , fch_fecha_entrega
                    , datos.getString("fch_lugar_entrega")
                    , datos.getString("fch_hora_entrega")
                    , fch_valor_total
                    , fch_saldo
                    , datos.getString("fch_obs")
                    , datos.getInt("fch_estado")
                    , new Cliente( datos.getString("cliente_cli_rut")
                                 , datos.getString("cli_nombre")
                                 , datos.getString("cli_telefono")
                                 , datos.getString("cli_email")
                                 , datos.getString("cli_direccion")
                                 , datos.getString("cli_comuna")
                                 , datos.getString("cli_ciudad")
                                 , datos.getInt("cli_sexo")
                                 , datos.getInt("cli_edad")
                                 , datos.getInt("cli_estado")  
                                 )
                    , new Doctor(
                                  doctor_doc_rut
                                 ,doc_nombre
                                 ,doc_telefono
                                 ,doc_mail
                                 ,doc_estado
                                 )
                    , new Descuento(
                                      descuento_des_id
                                    , des_nombre
                                    , des_descripcion
                                    , des_porc
                                    , des_estado
                                    )
                    , new Institucion(
                                        institucion_ins_id
                                      , ins_nombre
                                      , ins_telefono
                                      , ins_mail
                                      , ins_direccion
                                      , ins_comuna
                                      , ins_ciudad
                                      , ins_estado
                                     )
                    , new Despacho(
                                     despacho_dsp_id
                                   , dsp_rut
                                   , dsp_nombre
                                   , dsp_fecha
                                   , datos.getInt("fch_id")
                                  )
                    , new Armazon(
                                   arm_id_cerca
                                  ,arm_tipo_cerca
                                  ,arm_marca_cerca
                                  ,arm_cristal_cerca
                                  ,arm_add_cerca
                                  ,arm_od_a_cerca
                                  ,arm_od_esf_cerca
                                  ,arm_od_cil_cerca
                                  ,arm_oi_a_cerca
                                  ,arm_oi_esf_cerca
                                  ,arm_oi_cil_cerca
                                  ,arm_dp_cerca
                                  ,arm_endurecido_cerca
                                  ,arm_capa_cerca
                                  ,arm_plus_max_cerca
                                  ,datos.getInt("fch_id")
                                  )
                    , new Armazon(
                                   arm_id_lejos
                                  ,arm_tipo_lejos
                                  ,arm_marca_lejos
                                  ,arm_cristal_lejos
                                  ,arm_add_lejos
                                  ,arm_od_a_lejos
                                  ,arm_od_esf_lejos
                                  ,arm_od_cil_lejos
                                  ,arm_oi_a_lejos
                                  ,arm_oi_esf_lejos
                                  ,arm_oi_cil_lejos
                                  ,arm_dp_lejos
                                  ,arm_endurecido_lejos
                                  ,arm_capa_lejos
                                  ,arm_plus_max_lejos
                                  ,datos.getInt("fch_id")
                                  )
                    , new User(user_us_id, user_us_nombre, user_us_pass, user_us_tipo, user_us_estado)
                    )
            );
            }
        }
        BD.cerrar();
        
        return lista;
    }

    public int obtenerUltimoId() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String sql="select * from ficha order by fch_id desc limit 1";
        
        
        PreparedStatement consulta = BD.obtener().prepareStatement(sql);
        ResultSet datos = consulta.executeQuery();
        int id = 0;
        while (datos.next()) {
            id = datos.getInt("fch_id");
        }
        BD.cerrar();
        
        return id;
    }

    public boolean eliminar(int idFicha) throws SQLException {
        if(idFicha > 0){
            try {
                PreparedStatement insert = BD.obtener().prepareStatement(
                        "UPDATE `ficha` SET"
                        + "`fch_estado`='0'"
                        + "WHERE `fch_id`='"+idFicha+"'");
                if(insert.executeUpdate()!=0){
                    BD.cerrar();
                    return true;
                }
            } catch (Exception e) {
                BD.cerrar();
                return false;
            }
        }
        BD.cerrar();
        return false;
    }
    
    private boolean tieneDespacho(int idFicha) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        String sql= "Select * from despacho WHERE  ficha_fch_id="+idFicha;
        PreparedStatement consulta = BD.obtener().prepareStatement(sql);
        ResultSet datos = consulta.executeQuery();
        while (datos.next()) {
            if(datos.getInt("dsp_id")>0)
                return true;
        }
        BD.cerrar();
        return false;
    }
    
    public boolean restaurar(int idFicha) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        int estado=1;
        for (Ficha temp : listar(idFicha, 0)) {
            if(temp.getSaldo() == 0)
                estado = 2;
        }
        if(tieneDespacho(idFicha))
            estado = 3;
        if(idFicha > 0){
            try {
                PreparedStatement insert = BD.obtener().prepareStatement(
                        "UPDATE `ficha` SET"
                        + "`fch_estado`='"+estado+"'"
                        + "WHERE `fch_id`='"+idFicha+"'");
                if(insert.executeUpdate()!=0){
                    BD.cerrar();
                    return true;
                }
            } catch (Exception e) {
                BD.cerrar();
                return false;
            }
        }
        BD.cerrar();
        return false;
    }
    public ArrayList<Ficha> listarPorPalabraSesion(String buscar, int idSesion, int estado) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String sql="SELECT cli_rut FROM cliente WHERE user_us_id='"+idSesion+"' and (cli_nombre LIKE '%"+buscar+"%' OR cli_rut LIKE '%"+buscar+"%')";
        
        
        PreparedStatement consulta = BD.obtener().prepareStatement(sql);
        ResultSet datos = consulta.executeQuery();
        int id = 0;
        String sql2 = "SELECT fch_id FROM ficha WHERE fch_estado <> 0 AND ";
        if(estado == 0)
               sql2 = "SELECT fch_id FROM ficha WHERE fch_estado = 0 AND ";
        int cont = 0;
        while (datos.next()) {
            if(cont == 0)
                sql2 = sql2 + "cliente_cli_rut = '"+datos.getString("cli_rut")+"' ";
            else
                sql2 = sql2 + "OR cliente_cli_rut = '"+datos.getString("cli_rut")+"' ";
            cont++;
        }
        consulta = BD.obtener().prepareStatement(sql2);
        datos = consulta.executeQuery();
        ArrayList<Ficha> lista = new ArrayList<>();
        while (datos.next()) {
            lista.addAll(listar(datos.getInt("fch_id"), estado));
        }
        BD.cerrar();
        
        return lista;
    }
    public ArrayList<Ficha> listarPorPalabra(String buscar, int estado) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String sql="SELECT cli_rut FROM cliente WHERE cli_nombre LIKE '%"+buscar+"%' OR cli_rut LIKE '%"+buscar+"%'";
        
        
        PreparedStatement consulta = BD.obtener().prepareStatement(sql);
        ResultSet datos = consulta.executeQuery();
        int id = 0;
        String sql2 = "SELECT fch_id FROM ficha WHERE fch_estado <> 0 AND ";
        if(estado == 0)
               sql2 = "SELECT fch_id FROM ficha WHERE fch_estado = 0 AND ";
        int cont = 0;
        while (datos.next()) {
            if(cont == 0)
                sql2 = sql2 + "cliente_cli_rut = '"+datos.getString("cli_rut")+"' ";
            else
                sql2 = sql2 + "OR cliente_cli_rut = '"+datos.getString("cli_rut")+"' ";
            cont++;
        }
        consulta = BD.obtener().prepareStatement(sql2);
        datos = consulta.executeQuery();
        ArrayList<Ficha> lista = new ArrayList<>();
        while (datos.next()) {
            lista.addAll(listar(datos.getInt("fch_id"), estado));
        }
        BD.cerrar();
        
        return lista;
    }

    public boolean actualizar(Ficha ficha) throws SQLException {
        if(ficha != null){
            int idDespacho = 0;
            if(ficha.getDespacho().getId() != 0){
                idDespacho = ficha.getDespacho().getId();
            }
            try {
                PreparedStatement insert = BD.obtener().prepareStatement(
                        "UPDATE `ficha` SET"
                        + "`fch_saldo`='"+ficha.getSaldo()+"'"
                        + ",`fch_estado`='"+ficha.getEstado()+"'"
                        + ",`despacho_dsp_id`='"+idDespacho+"'"
                        + "WHERE `fch_id`='"+ficha.getId()+"'");
                if(insert.executeUpdate()!=0){
                    BD.cerrar();
                    return true;
                }
            } catch (Exception e) {
                BD.cerrar();
                return false;
            }
        }
        BD.cerrar();
        return false;
    }
    
    public boolean actualizarObs(Ficha ficha) throws SQLException {
        if(ficha != null){
            int idDespacho = 0;
            if(ficha.getDespacho().getId() != 0){
                idDespacho = ficha.getDespacho().getId();
            }
            try {
                PreparedStatement insert = BD.obtener().prepareStatement(
                        "UPDATE `ficha` SET"
                        + "`fch_obs`='"+ficha.getObservacion()+"'"
                        + "WHERE `fch_id`='"+ficha.getId()+"'");
                if(insert.executeUpdate()!=0){
                    BD.cerrar();
                    return true;
                }
            } catch (Exception e) {
                BD.cerrar();
                return false;
            }
        }
        BD.cerrar();
        return false;
    }

    public ArrayList<Ficha> morosos(ArrayList<String> rutClientes) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Date hoy=new Date();
        DateFormat fechaHora = new SimpleDateFormat("yyyy-MM-dd");
        String fecha = fechaHora.format(hoy);
        
        
        
        String sql = "SELECT fch_id, fch_estado FROM ficha WHERE fch_estado = 1 AND fch_fecha_entrega < '"+fecha+"'";
        
        int cont = 0;
        for (String rutCliente : rutClientes) {
            if(cont == 0){
                sql = sql + " AND cliente_cli_rut = '"+rutCliente+"' ";
                cont++;
            }else
                sql = sql + " OR cliente_cli_rut = '"+rutCliente+"' ";
        }
        
        
        
        
        PreparedStatement consulta = BD.obtener().prepareStatement(sql);
        ResultSet datos = consulta.executeQuery();
        ArrayList<Ficha> lista = new ArrayList<>();
        while (datos.next()) {
            if(datos.getInt("fch_estado") == 1)
                lista.addAll(listar(datos.getInt("fch_id"), 1));
        }
        BD.cerrar();
        
        return lista;
    }


   
    public boolean garantia(int idFicha, String observacion , Date fech, String lugar, String hora) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        
        PreparedStatement insert = BD.obtener().prepareStatement("SELECT  `fch_obs` FROM `ficha` WHERE `fch_id`='"+idFicha+"'");
        ResultSet datos = insert.executeQuery();
        while (datos.next()) {
            String obs =datos.getString("fch_obs");
            if(obs.equals(observacion)){
            JOptionPane.showMessageDialog(null, "Es necesario que ingrese una nueva observación para generar la GARANTÍA", "Importante", JOptionPane.WARNING_MESSAGE);
            BD.cerrar();
            return false;
            }
        }
        
        java.util.Date fecha = fech;// crea una variable tipo Date
        java.sql.Date sqlfecha = new java.sql.Date(fecha.getTime());//la transforma a sql.Date
        
        insert = BD.obtener().prepareStatement("UPDATE `ficha` set `fch_estado` = '4', `fch_obs` = '"+observacion+"' ,`fch_fecha_entrega`='"+sqlfecha+"', fch_lugar_entrega='"+lugar+"', fch_hora_entrega='"+hora+"' WHERE `fch_id`='"+idFicha+"'");
        
        if(insert.executeUpdate()!=0){
            BD.cerrar();
            JOptionPane.showMessageDialog(null, "Se ha modificado el estado correctamente","Realizado",JOptionPane.INFORMATION_MESSAGE);
            return true;
        }else{
            BD.cerrar();
            JOptionPane.showMessageDialog(null, "No se ha podido realizar la operacion. \n Hubo un problema con la conexión al servidor","Error",JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
    }
    public ArrayList<Ficha> garantiasSesion(int idSesion) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String sql = "SELECT fch_id FROM ficha WHERE fch_estado = 4 and user_us_id='"+idSesion+"'";
        
        
        PreparedStatement consulta = BD.obtener().prepareStatement(sql);
        ResultSet datos = consulta.executeQuery();
        ArrayList<Ficha> lista = new ArrayList<>();
        while (datos.next()) {
            lista.addAll(listar(datos.getInt("fch_id"), 1));
        }
        BD.cerrar();
        
        return lista;
    }
    
    public ArrayList<Ficha> garantias() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        
        
        
        String sql = "SELECT fch_id FROM ficha WHERE fch_estado = 4";
        
        
        PreparedStatement consulta = BD.obtener().prepareStatement(sql);
        ResultSet datos = consulta.executeQuery();
        ArrayList<Ficha> lista = new ArrayList<>();
        while (datos.next()) {
            lista.addAll(listar(datos.getInt("fch_id"), 1));
        }
        BD.cerrar();
        
        return lista;
    }

    public int obtenerVendedor(int id) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String sql = "SELECT user_us_id FROM ficha WHERE fch_id = "+id;
        
        
        PreparedStatement consulta = BD.obtener().prepareStatement(sql);
        ResultSet datos = consulta.executeQuery();
        int v = 0;
        while (datos.next()) {
            v = datos.getInt("USER_US_ID");
        }
        BD.cerrar();
        
        return v;
    }

    public ArrayList<Ficha> listarRut(String rut) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String sql="SELECT *,(SELECT doctor.doc_nombre from doctor where doctor.doc_rut=f.doctor_doc_rut) as 'doc_nombre',(SELECT doctor.doc_telefono from doctor where doctor.doc_rut=f.doctor_doc_rut) as 'doc_telefono',(SELECT doctor.doc_mail from doctor where doctor.doc_rut=f.doctor_doc_rut) as 'doc_mail',(SELECT doctor.doc_estado from doctor where doctor.doc_rut=f.doctor_doc_rut) as 'doc_estado',(SELECT descuento.des_nombre from descuento where f.descuento_des_id=descuento.des_id) as 'des_nombre',(SELECT descuento.des_descripcion from descuento where f.descuento_des_id=descuento.des_id) as 'des_descripcion',(SELECT descuento.des_porc from descuento where f.descuento_des_id=descuento.des_id) as 'des_porc',(SELECT descuento.des_estado from descuento where f.descuento_des_id=descuento.des_id) as 'des_estado',(SELECT institucion.ins_nombre from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_nombre',(SELECT institucion.ins_telefono from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_telefono',(SELECT institucion.ins_mail from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_mail',(SELECT institucion.ins_direccion from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_direccion',(SELECT institucion.ins_comuna from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_comuna',(SELECT institucion.ins_ciudad from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_ciudad',(SELECT institucion.ins_estado from institucion where f.institucion_ins_id=institucion.ins_id)  as 'ins_estado',(SELECT despacho.dsp_rut from despacho where f.despacho_dsp_id=despacho.dsp_id)  as 'dsp_rut',(SELECT despacho.dsp_nombre from despacho where f.despacho_dsp_id=despacho.dsp_id)  as 'dsp_nombre',(SELECT despacho.dsp_fecha from despacho where f.despacho_dsp_id=despacho.dsp_id)  as 'dsp_fecha',(SELECT armazon.arm_id from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_id_cerca',(SELECT armazon.arm_tipo from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_tipo_cerca',(SELECT armazon.arm_marca from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_marca_cerca',(SELECT armazon.arm_cristal from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_cristal_cerca',(SELECT armazon.arm_add from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_add_cerca',(SELECT armazon.arm_od_a from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_od_a_cerca',(SELECT armazon.arm_od_esf from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_od_esf_cerca',(SELECT armazon.arm_od_cil from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_od_cil_cerca',(SELECT armazon.arm_oi_a from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_oi_a_cerca',(SELECT armazon.arm_oi_esf from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_oi_esf_cerca',(SELECT armazon.arm_oi_cil from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_oi_cil_cerca',(SELECT armazon.arm_dp from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_dp_cerca',(SELECT armazon.arm_endurecido from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_endurecido_cerca',(SELECT armazon.arm_capa from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_capa_cerca',(SELECT armazon.arm_plus_max from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=1)  as 'arm_plus_max_cerca',(SELECT armazon.arm_id from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_id_lejos',(SELECT armazon.arm_tipo from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_tipo_lejos',(SELECT armazon.arm_marca from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_marca_lejos',(SELECT armazon.arm_cristal from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_cristal_lejos',(SELECT armazon.arm_add from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_add_lejos',(SELECT armazon.arm_od_a from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_od_a_lejos',(SELECT armazon.arm_od_esf from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_od_esf_lejos',(SELECT armazon.arm_od_cil from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_od_cil_lejos',(SELECT armazon.arm_oi_a from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_oi_a_lejos',(SELECT armazon.arm_oi_esf from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_oi_esf_lejos',(SELECT armazon.arm_oi_cil from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_oi_cil_lejos',(SELECT armazon.arm_dp from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_dp_lejos',(SELECT armazon.arm_endurecido from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_endurecido_lejos',(SELECT armazon.arm_capa from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_capa_lejos',(SELECT armazon.arm_plus_max from armazon where armazon.ficha_fch_id=f.fch_id and armazon.arm_tipo=0)  as 'arm_plus_max_lejos',"
                + "(SELECT user.us_id from user where user.us_id=user_us_id) as us_id,"
                + "(SELECT user.us_nombre from user where user.us_id=user_us_id) as us_nombre,"
                + "(SELECT user.us_pass from user WHERE user.us_id=user_us_id) as us_pass,"
                + "(SELECT user.us_tipo from user WHERE user.us_id=user_us_id) as us_tipo,"
                + "(SELECT user.us_estado from user WHERE user.us_id=user_us_id) as us_estado"
                + " from ficha f, cliente cli where f.cliente_cli_rut=cli.cli_rut  and f.cliente_cli_rut='"+rut+"' and f.fch_estado <> 0";
        
        
        PreparedStatement consulta = BD.obtener().prepareStatement(sql);
        ResultSet datos = consulta.executeQuery();
        ArrayList<Ficha> lista = new ArrayList<>();
        while (datos.next()) {
            
            Date fch_fecha= new Date();
            Date fch_fecha_entrega= new Date();
            Date dsp_fecha= new Date();
            SimpleDateFormat parseador = new SimpleDateFormat("dd-MM-yy");
            try {
                fch_fecha = datos.getDate("fch_fecha");
                fch_fecha_entrega = datos.getDate("f.fch_fecha_entrega");
                dsp_fecha = datos.getDate("dsp_fecha");//parseador.parse();
            } catch (Exception e) {
                System.out.println("se cayo al intentar convertir la fecha");   
            }
            // ficha
            int fch_valor_total=0;
            int fch_saldo=0;
            int fch_estado=0;
            try {fch_valor_total=datos.getInt("fch_valor_total");} catch (Exception e) { fch_valor_total=0;}
            try {fch_saldo=datos.getInt("fch_saldo");} catch (Exception e) { fch_saldo=0;}
            try {fch_estado=datos.getInt("fch_estado");} catch (Exception e) { fch_estado=0;}
            ////------------clace doctor
            String doctor_doc_rut="";
            String doc_nombre="";
            String doc_telefono="";
            String doc_mail="";
            try {doctor_doc_rut=datos.getString("doctor_doc_rut");} catch (Exception e) { doctor_doc_rut="";}
            try {doc_nombre=datos.getString("doc_nombre");} catch (Exception e) { doc_nombre="";}
            try {doc_telefono=datos.getString("doc_telefono");} catch (Exception e) { doc_telefono="";}
            try {doc_mail=datos.getString("doc_mail");} catch (Exception e) { doc_mail="";}
            //--int
            int doc_estado=0;
            try {doc_estado=datos.getInt("doc_estado");} catch (Exception e) { doc_estado=0;}
            ///-------------clace descuento
            String des_nombre="";
            String des_descripcion="";
            try {des_nombre=datos.getString("des_nombre");} catch (Exception e) { des_nombre="";}
            try {des_descripcion=datos.getString("des_descripcion");} catch (Exception e) { des_descripcion="";}
            //--int
            int descuento_des_id=0;
            int des_porc=0;
            int des_estado=0;
            try {descuento_des_id=datos.getInt("descuento_des_id");} catch (Exception e) { descuento_des_id=0;}
            try {des_porc=datos.getInt("des_porc");} catch (Exception e) { des_porc=0;}
            try {des_estado=datos.getInt("des_estado");} catch (Exception e) { des_estado=0;}
            ///------------------------institucion
            String ins_nombre="";
            String ins_telefono="";
            String ins_mail="";
            String ins_direccion="";
            String ins_comuna="";
            String ins_ciudad="";
            try {ins_nombre=datos.getString("ins_nombre");} catch (Exception e) { ins_nombre="";}
            try {ins_telefono=datos.getString("ins_telefono");} catch (Exception e) { ins_telefono="";}
            try {ins_mail=datos.getString("ins_mail");} catch (Exception e) { ins_mail="";}
            try {ins_direccion=datos.getString("ins_direccion");} catch (Exception e) { ins_direccion="";}
            try {ins_comuna=datos.getString("ins_comuna");} catch (Exception e) { ins_comuna="";}
            try {ins_ciudad=datos.getString("ins_ciudad");} catch (Exception e) { ins_ciudad="";}
            //int
            int institucion_ins_id=0;
            int ins_estado=0;
            try {institucion_ins_id=datos.getInt("institucion_ins_id");} catch (Exception e) { institucion_ins_id=0;}
            try {ins_estado=datos.getInt("ins_estado");} catch (Exception e) { ins_estado=0;}
            //despacho
            String dsp_rut="";
            String dsp_nombre="";
            try {dsp_rut=datos.getString("dsp_rut");} catch (Exception e) { dsp_rut="";}
            try {dsp_nombre=datos.getString("dsp_nombre");} catch (Exception e) { dsp_nombre="";}
            //--int
            int despacho_dsp_id=0;
            try {despacho_dsp_id=datos.getInt("despacho_dsp_id");} catch (Exception e) { despacho_dsp_id=0;}
            //armazon cerca
            String arm_marca_cerca="";
            String arm_cristal_cerca="";
            String arm_add_cerca="";
            String arm_od_a_cerca="";
            String arm_od_esf_cerca="";
            String arm_od_cil_cerca="";
            String arm_oi_a_cerca="";
            String arm_oi_esf_cerca="";
            String arm_oi_cil_cerca="";
            try {arm_marca_cerca=datos.getString("arm_marca_cerca");} catch (Exception e) { arm_marca_cerca="";}
            try {arm_cristal_cerca=datos.getString("arm_cristal_cerca");} catch (Exception e) { arm_cristal_cerca="";}
            try {arm_add_cerca=datos.getString("arm_add_cerca");} catch (Exception e) { arm_add_cerca="";}
            try {arm_od_a_cerca=datos.getString("arm_od_a_cerca");} catch (Exception e) { arm_od_a_cerca="";}
            try {arm_od_esf_cerca=datos.getString("arm_od_esf_cerca");} catch (Exception e) { arm_od_esf_cerca="";}
            try {arm_od_cil_cerca=datos.getString("arm_od_cil_cerca");} catch (Exception e) { arm_od_cil_cerca="";}
            try {arm_oi_a_cerca=datos.getString("arm_oi_a_cerca");} catch (Exception e) { arm_oi_a_cerca="";}
            try {arm_oi_esf_cerca=datos.getString("arm_oi_esf_cerca");} catch (Exception e) { arm_oi_esf_cerca="";}
            try {arm_oi_cil_cerca=datos.getString("arm_oi_cil_cerca");} catch (Exception e) { arm_oi_cil_cerca="";}
            //--int
            int arm_id_cerca=0;
            int arm_tipo_cerca=0;
            int arm_dp_cerca=0;
            int arm_endurecido_cerca=0;
            int arm_capa_cerca=0;
            int arm_plus_max_cerca=0;
            try {arm_id_cerca=datos.getInt("arm_id_cerca");} catch (Exception e) { arm_id_cerca=0;}
            try {arm_tipo_cerca=datos.getInt("arm_tipo_cerca");} catch (Exception e) { arm_tipo_cerca=0;}
            try {arm_dp_cerca=datos.getInt("arm_dp_cerca");} catch (Exception e) { arm_dp_cerca=0;}
            try {arm_endurecido_cerca=datos.getInt("arm_endurecido_cerca");} catch (Exception e) { arm_endurecido_cerca=0;}
            try {arm_capa_cerca=datos.getInt("arm_capa_cerca");} catch (Exception e) { arm_capa_cerca=0;}
            try {arm_plus_max_cerca=datos.getInt("arm_plus_max_cerca");} catch (Exception e) { arm_plus_max_cerca=0;}
            // armazon lejos
            String arm_marca_lejos="";
            String arm_cristal_lejos="";
            String arm_add_lejos="";
            String arm_od_a_lejos="";
            String arm_od_esf_lejos="";
            String arm_od_cil_lejos="";
            String arm_oi_a_lejos="";
            String arm_oi_esf_lejos="";
            String arm_oi_cil_lejos="";
            try {arm_marca_lejos=datos.getString("arm_marca_lejos");} catch (Exception e) { arm_marca_lejos="";}
            try {arm_cristal_lejos=datos.getString("arm_cristal_lejos");} catch (Exception e) { arm_cristal_lejos="";}
            try {arm_add_lejos=datos.getString("arm_add_lejos");} catch (Exception e) { arm_add_lejos="";}
            try {arm_od_a_lejos=datos.getString("arm_od_a_lejos");} catch (Exception e) { arm_od_a_lejos="";}
            try {arm_od_esf_lejos=datos.getString("arm_od_esf_lejos");} catch (Exception e) { arm_od_esf_cerca="";}
            try {arm_od_cil_lejos=datos.getString("arm_od_cil_lejos");} catch (Exception e) { arm_od_cil_lejos="";}
            try {arm_oi_a_lejos=datos.getString("arm_oi_a_lejos");} catch (Exception e) { arm_oi_a_lejos="";}
            try {arm_oi_esf_lejos=datos.getString("arm_oi_esf_lejos");} catch (Exception e) { arm_oi_esf_lejos="";}
            try {arm_oi_cil_lejos=datos.getString("arm_oi_cil_lejos");} catch (Exception e) { arm_oi_cil_lejos="";}
            //--int
            int arm_id_lejos=0;
            int arm_tipo_lejos=0;
            int arm_dp_lejos=0;
            int arm_endurecido_lejos=0;
            int arm_capa_lejos=0;
            int arm_plus_max_lejos=0;
            try {arm_id_lejos=datos.getInt("arm_id_lejos");} catch (Exception e) { arm_id_lejos=0;}
            try {arm_tipo_lejos=datos.getInt("arm_tipo_lejos");} catch (Exception e) { arm_tipo_lejos=0;}
            try {arm_dp_lejos=datos.getInt("arm_dp_lejos");} catch (Exception e) { arm_dp_lejos=0;}
            try {arm_endurecido_lejos=datos.getInt("arm_endurecido_lejos");} catch (Exception e) { arm_endurecido_lejos=0;}
            try {arm_capa_lejos=datos.getInt("arm_capa_lejos");} catch (Exception e) { arm_capa_lejos=0;}
            try {arm_plus_max_lejos=datos.getInt("arm_plus_max_lejos");} catch (Exception e) { arm_plus_max_lejos=0;}
           
            ////------------clase user
            int user_us_id=0;
            String user_us_nombre="";
            String user_us_pass="";
            int user_us_tipo=0;
            int user_us_estado=0;
            try {user_us_id=datos.getInt("us_id");} catch (Exception e) { user_us_id=0;}
            try {user_us_nombre=datos.getString("us_nombre");} catch (Exception e) { user_us_nombre="";}
            try {user_us_pass=datos.getString("us_pass");} catch (Exception e) { user_us_pass="";}
            try {user_us_tipo=datos.getInt("us_tipo");} catch (Exception e) { user_us_tipo=0;}
            try {user_us_estado=datos.getInt("us_estado");} catch (Exception e) { user_us_estado=0;}
            
            lista.add(new Ficha(
                      datos.getInt("fch_id")
                    , fch_fecha
                    , fch_fecha_entrega
                    , datos.getString("fch_lugar_entrega")
                    , datos.getString("fch_hora_entrega")
                    , fch_valor_total
                    , fch_saldo
                    , datos.getString("fch_obs")
                    , datos.getInt("fch_estado")
                    , new Cliente( datos.getString("cliente_cli_rut")
                                 , datos.getString("cli_nombre")
                                 , datos.getString("cli_telefono")
                                 , datos.getString("cli_email")
                                 , datos.getString("cli_direccion")
                                 , datos.getString("cli_comuna")
                                 , datos.getString("cli_ciudad")
                                 , datos.getInt("cli_sexo")
                                 , datos.getInt("cli_edad")
                                 , datos.getInt("cli_estado")  
                                 )
                    , new Doctor(
                                  doctor_doc_rut
                                 ,doc_nombre
                                 ,doc_telefono
                                 ,doc_mail
                                 ,doc_estado
                                 )
                    , new Descuento(
                                      descuento_des_id
                                    , des_nombre
                                    , des_descripcion
                                    , des_porc
                                    , des_estado
                                    )
                    , new Institucion(
                                        institucion_ins_id
                                      , ins_nombre
                                      , ins_telefono
                                      , ins_mail
                                      , ins_direccion
                                      , ins_comuna
                                      , ins_ciudad
                                      , ins_estado
                                     )
                    , new Despacho(
                                     despacho_dsp_id
                                   , dsp_rut
                                   , dsp_nombre
                                   , dsp_fecha
                                   , datos.getInt("fch_id")
                                  )
                    , new Armazon(
                                   arm_id_cerca
                                  ,arm_tipo_cerca
                                  ,arm_marca_cerca
                                  ,arm_cristal_cerca
                                  ,arm_add_cerca
                                  ,arm_od_a_cerca
                                  ,arm_od_esf_cerca
                                  ,arm_od_cil_cerca
                                  ,arm_oi_a_cerca
                                  ,arm_oi_esf_cerca
                                  ,arm_oi_cil_cerca
                                  ,arm_dp_cerca
                                  ,arm_endurecido_cerca
                                  ,arm_capa_cerca
                                  ,arm_plus_max_cerca
                                  ,datos.getInt("fch_id")
                                  )
                    , new Armazon(
                                   arm_id_lejos
                                  ,arm_tipo_lejos
                                  ,arm_marca_lejos
                                  ,arm_cristal_lejos
                                  ,arm_add_lejos
                                  ,arm_od_a_lejos
                                  ,arm_od_esf_lejos
                                  ,arm_od_cil_lejos
                                  ,arm_oi_a_lejos
                                  ,arm_oi_esf_lejos
                                  ,arm_oi_cil_lejos
                                  ,arm_dp_lejos
                                  ,arm_endurecido_lejos
                                  ,arm_capa_lejos
                                  ,arm_plus_max_lejos
                                  ,datos.getInt("fch_id")
                                  )
                    , new User(user_us_id, user_us_nombre, user_us_pass, user_us_tipo, user_us_estado)
                    )
            );
        }
        BD.cerrar();
        
        return lista;
    }

    
    
}
