/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import com.fazecast.jSerialComm.SerialPort;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

/**
 *
 * @author Anggun Dwiguna
 */
public class SerialConn {
    SerialPort Port;
    OutputStream output;
    InputStream input;
    String str;
    
    public SerialConn(SerialPort session) {        
        Port = session;        
    }
    
    public boolean connectPort(){
        if(Port.openPort()){
            if(IOInit()){
                System.out.println("Berhasil terhubung");
                return true;
            }            
        }
        else{
            System.out.println("Gagal terhubung");
        }
        return false;
    }
    
    //fungsi mencegah masukan data ganda
    public void resetReadPointer(){
        Scanner data = new Scanner(input);
        while(data.hasNextLine()){
                data.nextLine();
            }
    }
    
    //menampung data yang sudah dicetak di arduino
    public String readByClick(){
        resetReadPointer();
        String readData = "";
        Port.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING, 2000, 2000);
        Scanner data = new Scanner(Port.getInputStream());
        while(data.hasNextLine()){
                readData += data.nextLine();
            }
        System.out.println("Berhasil baca = " + readData);
        Port.setComPortTimeouts(SerialPort.TIMEOUT_NONBLOCKING, 0, 0);        
        return readData;
    }
    
    //inisialisasi jalur keluar masuk data Arduino dan JAVA
    public boolean IOInit(){
        try{
            output = Port.getOutputStream();
            input = Port.getInputStream();
            System.out.println("IO Init Berhasil");
            return true;
        }catch(Exception e){
            System.out.println("IO Init Gagal");
        }
        return false;
    }
    
    //mengirim data melalui Outputstream yang sudah didefinisikan
    public void writeData(String str){
        for(int a = 0; a < str.length(); a++){
            try{
                output.write(str.charAt(a));
                System.out.println("Menulis: " + str.charAt(a));
                output.flush();
            }catch(IOException e){
                System.out.print(e.toString());
            }
        }
            try{
                output.write(10);
                output.flush();     
            }catch(IOException e){
                System.out.print(e.toString());
            }
            System.out.println("Berhasil kirim data");        
    }
        
    public void sendData(String str){
        for(int a = 0; a < str.length(); a++){
            try{
                output.write(str.charAt(a));
                System.out.println("Mengirim: " + str.charAt(a));
                output.flush();
            }catch(IOException e){
                System.out.print(e.toString());
            }
        }
    }
}
