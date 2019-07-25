package com.sanfoundry.setandstring;



import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class perulangan {

    public static void main(String[] args) {
        ArrayList<String> temp = new ArrayList<String>();
        ArrayList<String> tempBiner = new ArrayList<String>();
        ArrayList<String> tempPersen = new ArrayList<String>();
        ArrayList<String> tempBeda = new ArrayList<String>();
        String a = "";

        Scanner sc = new Scanner(System.in);
        bacaData bd = new bacaData();
        String msg = bd.getBacaData();
        System.out.print("");
        String keyVig = bd.getBacaKey();
        System.out.print("");
        System.out.print("Masukan Key Transposition : ");
        int keyTrans = sc.nextInt();
        System.out.print("Banyak Iterasi : ");

        //perulangan
        int iterasi = sc.nextInt();
        for (int i = 0; i < iterasi; i++) {
            msg = encrypt(msg, keyVig);
            msg = transposition(msg, keyTrans);
            temp.add(msg);
        }

        //convert chiperteks menjadi biner dan hamming distance
        for (int i = 0; i < temp.size(); i++) {
            char[] arrCharStr = temp.get(i).toCharArray();
            for (char e : arrCharStr) {
                a += "0" + String.valueOf(Integer.toBinaryString(e)) + " ";
            }

            tempBiner.add(a);
            a = "";

        }
        char[] arrBinerAsli = tempBiner.get(0).toCharArray();
        tempBeda.add("0");
        tempPersen.add("0%");
        double beda = 0;
        double jumBiner = 0;
        double persenBeda = 0;
        DecimalFormat df = new DecimalFormat("#.##");
        for (int i = 1; i < temp.size(); i++) {
            char[] arrBinerBaru = tempBiner.get(i).toCharArray();
            for (int j = 0; j < arrBinerAsli.length; j++) {
                if (arrBinerAsli[j] != arrBinerBaru[j]) {
                    beda += 1;
                }
                if (arrBinerAsli[j] != ' ') {
                    jumBiner++;
                }
            }
            persenBeda = beda / jumBiner * 100.0;
            tempBeda.add(String.valueOf(beda));
            tempPersen.add(String.valueOf(df.format(persenBeda)) + "%");

            beda = 0;
        }

        System.out.printf("%-9s %-20s %-110s %-10s %-10s %n", "Iterasi", "Chiper-Text", "Binary", "Kekaburan", "percentage");
        for (int i = 0; i < temp.size(); i++) {

            System.out.printf("%-9d %-20s %-110s %-10s %-10s %n", (i + 1), temp.get(i), tempBiner.get(i), tempBeda.get(i), tempPersen.get(i));

        }

    }

    //encrypt viginere
    public static String encrypt(String plainText, String key) {
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
