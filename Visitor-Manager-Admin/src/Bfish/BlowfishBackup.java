/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bfish;
import javax.swing.JOptionPane;
/**
 *
 * @author Anggun Dwiguna
 */
public final class BlowfishBackup {
    public long[] P =  new Subkey().pbox;
    public long[][] S = new Subkey().SBox;
    public long datal = 0, datar = 0;
    
//    String plain = JOptionPane.showInputDialog(null,"Masukkan keyword max 8 karakter");
//    String keyStr = JOptionPane.showInputDialog(null,"Masukkan kunci max 8 karakter");

    long L0 = 0, R0 = 0; //insialisasi string all-zero    
    
    public BlowfishBackup(String input, String kunci){
        input = "gunsta";
        kunci = "blow";
        
        //memberikan padding terhadap plain
        input = String.format("%-8s", input).replace(' ', '*');        
        byte[] keyByte = input.getBytes();
        
        System.out.println("ALGORITMA BLOWFISH\n");
        for (int i=0 ; i<18 ; ++i){                     //inisialisasi p[0] sampai p[17], dimana index ganjil XOR dengan 32bit kunci blok kiri
            P[i] ^= keyByte[i % input.length()];        //dan index genap XOR dengan 32bit kunci blok kanan
        //  System.out.println("P["+i+"] = "+ P[i]);
        }

        for (int i=0 ; i<18 ; i+=2) {       //lakukan enkripsi string all-zero dengan p-array yg telah dibuat sebelumnya
           encrypt(datal, datar);                //enkripsi blok L dan blok R
           P[i] = datal; P[i+1] = datar;          //output blok kiri = p[index ganjil] dan output blok kanan = p[index genap]
//           System.out.println("P["+i+"] = "+ P[i]);
        }
//        System.out.println("Berhasil inisialisasi P-array\n");

        for (int i=0 ; i<4 ; ++i){           //menyiapkan variabel looping untuk 4 SBOX dengan 256 entri 32-bit
           for (int j=0 ; j<256; j+=2) { 
              encrypt(datal, datar);               //enkripsi blok L dan R
              S[i][j] = datal; S[i][j+1] = datar;   //simpan pada SBOX[i] entry ke [j] untuk blok L dan [j+1] untuk R
           }
//           System.out.println("Berhasil update data s-box ke " + i + "\n");
        }
        
        //membagi plaintext menjadi 2 pecahan 32-bit data
        datal = StrtoIntHex(input.substring(0, 4));
        datar = StrtoIntHex(input.substring(4, 8));
        
        System.out.println("PLAIN L = " + input.substring(0,4) + " PLAIN R = " + input.substring(4,8));
        System.out.println("PLAIN L = " + datal + " PLAIN R = " + datar);
        
        encrypt(datal, datar);
        System.out.println("CIPHER L = " + datal + " CIPHER R = "+ datar);
        
        short[] cipher = longtobyte(datal, datar);   
        System.out.print("HASIL ENKRIPSI = ");
        for(short k: cipher){
            System.out.print((char)k);
        }
        System.out.println();
                
        decrypt(datal, datar);
        System.out.println("DECRYPT L = " + datal + " DECRYPT R = "+ datar);
        
        short[] dekrip = longtobyte(datal, datar);
        System.out.print("HASIL DEKRIPSI = ");
        for(short k: dekrip){
            System.out.print((char)k);
        }
        System.out.println();
     }
    
    public short[] longtobyte(long dataleft, long dataright){
        short[] ret = new short[8];
        ret[0] = (short)(dataleft >> 24);
        ret[1] = (short)(dataleft >> 16 & 0xff);
        ret[2] = (short)(dataleft >> 8 & 0xff);
        ret[3] = (short)(dataleft & 0xff);
        ret[4] = (short)(dataright >> 24);
        ret[5] = (short)(dataright >> 16 & 0xff);
        ret[6] = (short)(dataright >> 8 & 0xff);
        ret[7] = (short)(dataright & 0xff);
        return ret;
    }
            
    public long f(long x){
        long h;        
        try{
            long S1 = S[0][(int)(x >> 24)];
            long S2 = S[1][(int)(x >> 16 & 0xff)];
            long S3 = S[2][(int)(x >> 8 & 0xff)];
            long S4 = S[3][(int)(x & 0xff)];            
            h = S1 & S2 ^ S3 & S4;            
//            System.out.println("Hasil dari h = " + h);
            return h;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return 0;
    }
    
    public final void encrypt(long L, long R){
        for (int i=0 ; i<16 ; i += 2) {
            L ^= P[i];     // L = L XOR P[i]
            R ^= f(L);     // R = R XOR feistel(L)            
            R ^= P[i+1];   // R = R XOR P[i+1]
            L ^= f(R);     // L = L XOR feistel(R)
        }
        
        L ^= P[16];
        R ^= P[17];
        //lakukan swap untuk membatalkan last loop
        long temp = L;
        L = R; R = temp;
        datal = L;
        datar = R;
//        System.out.println("L = " + L + " R = " + R);
    }
    
    public void decrypt(long L, long R) {
        for (int i=16 ; i > 0 ; i -= 2) {
           L ^= P[i+1];
           R ^= f(L);
           R ^= P[i];
           L ^= f(R);
        }
        L ^= P[1];
        R ^= P[0];
        long temp = L;
        L = R; R = temp;
        datal = L;
        datar = R;
//        System.out.println("L = " + L + " R = " + R);
     }
    
    public int StrtoIntHex(String data){
        int ret;
        String hex="";
        for(byte b: data.getBytes()){
            hex+= Integer.toHexString(b);
        }        
        return Integer.parseInt(hex, 16);
    }
}
