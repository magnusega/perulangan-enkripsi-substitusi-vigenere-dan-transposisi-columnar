/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skripsi;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.sanfoundry.setandstring.bacaData;
import static com.sanfoundry.setandstring.perulangan.encrypt;
import static com.sanfoundry.setandstring.perulangan.shift;
import static com.sanfoundry.setandstring.perulangan.transposition;

public class perulanganenkripsi {

 public static void main(String[] args) throws IOException {

        ArrayList<String> temp = new ArrayList<String>();
        ArrayList<String> tempBiner = new ArrayList<String>();
        ArrayList<String> tempPersen = new ArrayList<String>();
        ArrayList<String> tempBeda = new ArrayList<String>();
        String a = "";
        int keyTrans = 0;
        Scanner sc = new Scanner(System.in);
        String msg = hapusSpace(inputFile("pt.txt").toString());
        String keyVig = hapusSpace(inputFile("Key.txt").toString());
        System.out.print("");

        System.out.print("");
        boolean koreksi = true;
        while (koreksi) {   
            System.out.print("Masukan Key Transposition : ");
            keyTrans = sc.nextInt();
            if (msg.length() <= keyTrans) {
                koreksi = true;
                System.out.println("Key Transposisi harus lebih kecil dari jumlah karakter plaint text !");
            } else {
                koreksi = false;
            }
        }
        System.out.print("Banyak Iterasi : ");

        //perulangan
        temp.add(msg);
        int iterasi = sc.nextInt();
        long awalEnkrip = System.currentTimeMillis();
        for (int i = 0; i < iterasi; i++) {

            long awalEnkripIterasi = System.currentTimeMillis();
            msg = viginere(msg, keyVig);
            msg = transposition(msg, keyTrans);
            temp.add(msg);
            long akhirEnkripIterasi = System.currentTimeMillis();

            System.out.println("Runing Time Iterasi-" + (i + 1) + " : " + (akhirEnkripIterasi - awalEnkripIterasi) + " ms");
        }
        long akhirEnkrip = System.currentTimeMillis();
        System.out.println("Runing Time Total : " + (akhirEnkrip - awalEnkrip) + " ms");
        for (int i = 0; i < temp.size(); i++) {
            

            tempBiner.add(toBinary(temp.get(i)));
            

        }
            String arrBinerAsli = tempBiner.get(0); //temp 0 itu plainteks

        //char[] arrBinerBaru;
//        tempBeda.add("0");
//        tempPersen.add("0%");
        double beda = 0;
        double jumBiner = arrBinerAsli.length();
        double persenBeda = 0;
        DecimalFormat df = new DecimalFormat("#.##");

//        for (int i = 0; i < arrBinerAsli.length-1; i++) {
////            System.out.print(arrBinerAsli[i]); 
//        }
        for (int i = 1; i < temp.size(); i++) {
            beda=hammingDist(arrBinerAsli, tempBiner.get(i));
//            System.out.println(jumBiner);
            persenBeda = beda / jumBiner * 100.0;
            tempBeda.add(String.valueOf(beda));
            tempPersen.add(String.valueOf(df.format(persenBeda)) + "%");
      
        }

        System.out.printf("%-9s %-10s %-10s %n", "Iterasi", "Kekaburan", "percentage");
        for (int i = 0; i < tempBeda.size(); i++) {

            System.out.printf("%-9d %-10s %-10s %n", (i + 1), tempBeda.get(i), tempPersen.get(i));

        }

    }

    private static String toBinary(String s) {
        String temp = s;
        byte[] bytes = s.getBytes();

        for (byte b : bytes) {
        }
        StringBuilder binary = new StringBuilder();
        for (byte b : bytes) {
            int val = b;
            for (int i = 0; i < 8; i++) {
                binary.append((val & 128) == 0 ? 0 : 1);

                val <<= 1;
            }
        }
        return binary.toString();
    }

    static int hammingDist(String str1, String str2) {
        int i = 0, count = 0;
        while (i < str1.length()) {
            if (str1.charAt(i) != str2.charAt(i)) {
                count++;
            }
            i++;
        }
        return count;
    }

    private static String viginere(String plainText, String key) {
        plainText = plainText.toUpperCase().replaceAll("[^a-zA-Z]", "");
        key = key.toUpperCase();

        String cipher = "";
        for (int i = 0; i < plainText.length(); i++) {
            if (plainText.charAt(i) + shift(key, i) > 90) {
                cipher += (char) (plainText.charAt(i) + shift(key, i) - 26);
            } else {
                cipher += (char) ((plainText.charAt(i) + shift(key, i)));

            }
        }

        return cipher;
    }

    private static String transposition(String msg, int key) {
        ArrayList<String> cipher = new ArrayList<String>();
        String text = "";
        StringBuilder txt = new StringBuilder(text);
        for (int i = 0; i < key; i++) {
            int pointer = i;
            String msg_char = Character.toString(msg.charAt(i));
            cipher.add(msg_char);
            while (pointer < msg.length()) {
                String pointer_char = Character.toString(msg.charAt(pointer));
                txt.append(cipher.get(i).replace(msg_char, pointer_char));
                pointer += key;
            }
        }
        String hasil = String.valueOf(txt);
        return hasil;
    }

    public static StringBuilder inputFile(String file) {
        BufferedReader br = null;
        String sCurrentLine;

        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException ex) {
//            Logger.getLogger(enkripsi.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("File tidak ditemukan!");
            System.exit(0);

        }

        StringBuilder builder = new StringBuilder();
        try {
            while ((sCurrentLine = br.readLine()) != null) {
                builder.append(sCurrentLine).append("");
            }
        } catch (IOException ex) {
//            Logger.getLogger(enkripsi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return builder;
    }

    public static String hapusSpace(String data) {

        for (int i = 0; i < data.length(); i++) {
            if (data.charAt(i) == ' ') {
                data = charRemoveAt(data, i);
            }
        }
        return data;
    }

    public static String charRemoveAt(String str, int p) {
        return str.substring(0, p) + str.substring(p + 1);
    }
}
