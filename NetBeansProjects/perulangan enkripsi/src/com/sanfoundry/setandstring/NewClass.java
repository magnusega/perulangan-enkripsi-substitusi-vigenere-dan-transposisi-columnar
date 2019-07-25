/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sanfoundry.setandstring;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import skripsi.perulanganenkripsi;

/**
 *
 * @author yoga
 */
public class NewClass {
    public static void main(String[] args) {
        perulanganenkripsi s=new perulanganenkripsi();
        String w = s.inputFile("Key.txt").toString();
        
        String a = s.inputFile("SampleTextFile_50kb.txt").toString();
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) == '.' || a.charAt(i) == ',' || a.charAt(i) == ';') {
                a = charRemoveAt(a, i);
            }
        }
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("file.txt"));
            writer.write(a);

        } catch (IOException e) {
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
            }
        }
        System.out.println(a);
    }
public static String hapusSpace(String data){
        
        for (int i = 0; i < data.length(); i++) {
            if (data.charAt(i) == ' ' ) {
                data = charRemoveAt(data, i);
            }
        }
        return data;
    }
     public static String charRemoveAt(String str, int p) {
        return str.substring(0, p) + str.substring(p + 1);
    }
    }
