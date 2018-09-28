/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

/**
 *
 * @author Anggun Dwiguna
 */
public class Vardump {
    String id;
    String data;
    
    public void setData(String i, String d){
        data = d;
        id = i;
    }
    
    public String getData(){
        return data;
    }
    
    public String getId(){
        return id;
    }
}