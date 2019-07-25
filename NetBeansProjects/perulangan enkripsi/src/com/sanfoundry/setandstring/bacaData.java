package com.sanfoundry.setandstring;


import java.io.FileInputStream;
import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author yoga
 */
public class bacaData {

    private String dataText;
    private String dataTextKey;

    public bacaData() {
        this.dataText = "";
        this.dataTextKey = "";
        FileInputStream finput = null;
        FileInputStream keyinput = null;
        int data = 0;

        //membuka file
        try {

            finput = new FileInputStream("hasil.txt");
            keyinput = new FileInputStream("Key.txt");
            
        } catch (IOException e) {
        }

        //membaca data dari dalam file dan menpilkanya ke layar
        try {

            while ((data = finput.read()) != -1) {

                //ketika di tampikan data di konversi ketipe char
//                System.out.print((char) data);
                this.dataText = this.dataText + String.valueOf((char) data);

            }
            while ((data = keyinput.read()) != -1) {

                //ketika di tampikan data di konversi ketipe char
//                System.out.print((char) data);
                this.dataTextKey = this.dataTextKey + String.valueOf((char) data);

            }


        } catch (IOException e) {
        }

        try {

            finput.close();

        } catch (IOException e) {
        }
//
//                System.out.println(this.dataText);

    }
    public String getBacaKey(){
        return this.dataTextKey;
    }
    public String getBacaData() {
        return this.dataText;
    }
}
