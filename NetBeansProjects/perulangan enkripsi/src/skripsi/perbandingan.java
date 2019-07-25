/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skripsi;

import static skripsi.perulanganenkripsi.hapusSpace;
import static skripsi.perulanganenkripsi.inputFile;
import java.util.ArrayList;
import java.util.Scanner;
import static com.sanfoundry.setandstring.perulangan.shift;



public class perbandingan
{
        
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
    public static void main(String[] args)
    {
        ArrayList<String> temp = new ArrayList<String>();
        int keyTrans=0;
        Scanner sc = new Scanner(System.in);
        String msg = hapusSpace(inputFile("10kb.txt").toString());
        String keyVig = hapusSpace(inputFile("Key.txt").toString());
        System.out.print("Masukan Key Transposition : ");
        keyTrans = sc.nextInt();
        System.out.print("");
   
        long awalEnkripIterasi = System.currentTimeMillis();
        String encryptedMsg = viginere(msg, keyVig);
        String encryptedMsg1 = transposition(encryptedMsg, keyTrans);
        long akhirEnkripIterasi = System.currentTimeMillis();
        System.out.println("Runing Time "+ (akhirEnkripIterasi - awalEnkripIterasi) + " ms");
//        System.out.println("String: " + msg);
//        System.out.println("Encrypted message viginere: " + encryptedMsg);
//        System.out.println("Encrypted message viginere & transposisi: " + encryptedMsg1);
        System.out.println("Jumlah bit               : " + toBinary(encryptedMsg1).length());
                        System.out.println("Jumlah bit berubah       : " + hammingDist(toBinary(msg), toBinary(encryptedMsg1)));

                        double bitChange = hammingDist(toBinary(msg), toBinary(encryptedMsg1));
                        double bitTotal = toBinary(encryptedMsg1).length();

                        System.out.println("Avalanche Effect         : " + String.format("%.2f", (bitChange / bitTotal) * 100) + "%");
    }
}