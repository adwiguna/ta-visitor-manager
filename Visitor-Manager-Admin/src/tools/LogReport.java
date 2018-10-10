/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Anggun Dwiguna
 */
public class LogReport {
    String datetime;
    ResultSet rs;
    public LogReport(){
        datetime = getDateTime();
    }
    
    public String getDateTime(){
        String time;
        String date;
        Date d = new Date();
        SimpleDateFormat s = new SimpleDateFormat("HH:mm:ss");
        time = (s.format(d));
        s = new SimpleDateFormat("yyyy-MM-dd");
        date = (s.format(d));
        String datetime = date + " " + time;
//        System.out.println(datetime);
        return datetime;
    }
    
    public boolean report(String idLog, String subjek, String objek, String id_ruangan){
        String nama_ruangan = " ";        
        try{
            if(id_ruangan != null){
                rs = new FungsiQuery().selectData("nama_ruangan", "daftar_ruangan", "id_ruangan = '" + id_ruangan + "'");
                rs.next();
                nama_ruangan = rs.getString("nama_ruangan");
            }
            else{
                nama_ruangan = "";
            }
            
            if (objek == null){
                objek = "";
            }
            
            new FungsiQuery().addData("log_history", "`id_log`, `date_time`, `subjek`, `objek`, `nama_ruangan`",
                    "'" + idLog + "', '" + datetime + "', '" + subjek + "', '" + objek + "', '" + nama_ruangan + "'");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    
} 
