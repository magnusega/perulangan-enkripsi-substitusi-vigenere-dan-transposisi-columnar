package com.sanfoundry.setandstring;



import java.util.ArrayList;
import java.util.Scanner;

public class Vigenere {

    public static void main(String[] args) {
        ArrayList<String> temp = new ArrayList<String>();
        ArrayList<String> tempBiner = new ArrayList<String>();
        ArrayList<Integer> tempBeda = new ArrayList<Integer>();
        String a="";
        Scanner sc = new Scanner(System.in);
        System.out.print("Masukan Kata : ");
        String msg = sc.nextLine();
        System.out.print("");
        System.out.print("Masukan Key Vigenere : ");
        String keyVig = sc.nextLine();
        System.out.print("");
        System.out.print("Masukan Key Transposition : ");
        int keyTrans = sc.nextInt();
        System.out.print("Banyak Iterasi : ");
        
        //perulangan
        int iterasi = sc.nextInt();
        
        
        for (int i = 0; i < iterasi; i++) {
            
            msg = encrypt(msg, keyVig);
            
        //System.out.println("1 "+msg);
            msg = transposition(msg, keyTrans);
            temp.add(msg);
        }
        
        
        //convert chiperteks menjadi biner dan hamming distance
        for (int i = 0; i < temp.size(); i++) {
            char[] arrCharStr = temp.get(i).toCharArray();
            for (char e : arrCharStr){
                a+= "0"+String.valueOf(Integer.toBinaryString(e))+" ";
            }
            
            tempBiner.add(a);
            a="";
                
        }
        char[] arrBinerAsli = tempBiner.get(0).toCharArray();
        //char[] arrBinerBaru;
        tempBeda.add(0);
        int beda=0;
        for (int i = 1; i < temp.size(); i++) {
            char[] arrBinerBaru=tempBiner.get(i).toCharArray();
            for (int j = 0; j < arrBinerAsli.length; j++) {
                if (arrBinerAsli[j]!=arrBinerBaru[j]) {
                    beda+=1;
                }
            }
            tempBeda.add(beda);
            beda=0;
        }
        
        System.out.println("Iterasi\tChiper-Text\tBinary\tKekaburan");
         for (int i = 0; i < temp.size(); i++) {
            
                  System.out.println((i+1)+"\t"+temp.get(i)+"\t"+tempBiner.get(i)+"\t"+tempBeda.get(i));
             
            
        }

    }
    //encrypt viginere
    public static String encrypt(String plainText, String key) {
        //remove white spaces and non-letter characters and convert to uppercase
        plainText = plainText.toUpperCase().replaceAll("[^a-zA-Z]", "");
        key = key.toUpperCase();

        String cipher = "";

        //pad the plaintext with random characters in the end until the size is a multiple of 5
//        while (plainText.length() % 5 != 0) {
//            plainText += "X";
//        }

        for (int i = 0; i < plainText.length(); i++) {
            //split the ciphertext in blocks of 5 characters
//            if (i != 0 && i % 5 == 0) {
//                cipher += " ";
//            }

            if (plainText.charAt(i) + shift(key, i) > 90) {
                //subtract 26 to wraparound
                cipher += (char) (plainText.charAt(i) + shift(key, i) - 26);
            } else {
                //simply add the key to shift
                cipher += (char) ((plainText.charAt(i) + shift(key, i)));

            }
        }

        return cipher;
    }

    public static int shift(String key, int i) {
        return (key.charAt(i % key.length()) - 65);
    }
    //encrypt transposisi
    public static String transposition(String msg, int key) {
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

}
