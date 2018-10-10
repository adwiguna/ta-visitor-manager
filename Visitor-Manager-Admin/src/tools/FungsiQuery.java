/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import koneksi_db_comm.koneksi_db;
import javax.swing.JOptionPane;
/**
 *
 * @author Anggun Dwiguna
 */
public class FungsiQuery {
    Connection con;
    Statement stat;
    ResultSet rs;
    String sql;
        
    public ResultSet selectData(String kolom, String tabel, String kondisi){
        con = koneksi_db.con;
        stat = koneksi_db.stm;
        
        try{
            sql = "SELECT "+ kolom + " FROM " + tabel +" WHERE " + kondisi;
            rs = stat.executeQuery(sql);
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return rs;
    }
    
     public void cetak(String tabel, String kolom, String nilai){
         sql = "INSERT INTO " + tabel + "(" + kolom + ") VALUES(" + nilai + ")";
         System.out.println(sql);
     }
    
    
    public Boolean addData(String tabel, String kolom, String nilai){
        con = koneksi_db.con;
        stat = koneksi_db.stm;        
        try{
            sql = "INSERT INTO " + tabel + "(" + kolom + ") VALUES(" + nilai + ")";
            if(stat.executeUpdate(sql)==1){
                return true;
            }
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return false;
    }
    
    public Boolean updateData(String tabel, String data, String kondisi){
        con = koneksi_db.con;
        stat = koneksi_db.stm;
        
        try{
            sql = "UPDATE `" + tabel + "` SET "+ data + " WHERE " + kondisi;
            if(stat.executeUpdate(sql)==1){
                return true;
            }
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return false;
    }
    
    
    public Boolean deleteData(String tabel, String kondisi){
        con = koneksi_db.con;
        stat = koneksi_db.stm;
        
        try{
            sql = "DELETE FROM " + tabel + " WHERE " + kondisi;
            if(stat.executeUpdate(sql)==1){
                return true;
            }
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }                
        return false;        
    }
}
