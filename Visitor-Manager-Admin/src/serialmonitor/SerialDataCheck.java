/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serialmonitor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import koneksi_db_comm.koneksi_db;
import tools.*;

/**
 *
 * @author Anggun Dwiguna
 */
public class SerialDataCheck {
    String id_log;
    ResultSet rs;
    String NUID, ruanganID, roleID;
    
    public SerialDataCheck(String strNUID, String ruangan, String role) {        
        NUID = strNUID;
        ruanganID = ruangan;
        roleID = role;
    }
//    SELECT * FROM `daftar_tag` JOIN daftar_hakakses USING(id_role) WHERE id_role = '28' AND id_ruangan = '4'
    public boolean authorize(){
        System.out.println("NUID = " + NUID + "\nRuangan id = " + ruanganID);
        rs = new FungsiQuery().selectData("*", "daftar_tag JOIN daftar_hakakses USING(id_role)", "status_aktif = '1' AND NUID='"
                + NUID +"' AND id_ruangan = '" + ruanganID + "' AND id_role = '" + roleID + "'");                        
        try{            
            //jika autorisasi diterima
            if(rs.next()){
                String nama_pemilik = rs.getString("nama_pemilik");
                System.out.println(NUID + " melewati gerbang " + ruanganID);
                id_log = changeStatusRuang(true);
                new LogReport().report(id_log, nama_pemilik, NUID, ruanganID);
                return true;
            }
            //jika autorisasi ditolak
            else{
                rs = new FungsiQuery().selectData("nama_pemilik", "daftar_tag", "NUID = '" + NUID + "'");
                rs.next();
                String nama_pemilik = rs.getString("nama_pemilik");
                id_log = changeStatusRuang(false);
                new LogReport().report(id_log, nama_pemilik, NUID, ruanganID);
                return false;
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    private String changeStatusRuang(boolean verified){
        try{
            rs = new FungsiQuery().selectData("*", "daftar_tag", "NUID = '" + NUID + "'");
            rs.next();
            if(rs.getString("status_ruang").equals("1")){
                new FungsiQuery().updateData("daftar_tag", "status_ruang = '" + "0'", "NUID = '" + NUID + "'");
                if(verified)
                    return "TRUEOUT";
                return "FALSEOUT";
            }
            new FungsiQuery().updateData("daftar_tag", "status_ruang = '" + "1'", "NUID = '" + NUID + "'");            
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        if(verified)
            return "TRUEIN";
        return "FALSEIN";
    }
}
