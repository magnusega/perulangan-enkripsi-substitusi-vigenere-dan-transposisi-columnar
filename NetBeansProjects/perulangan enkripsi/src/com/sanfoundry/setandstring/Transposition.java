/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sanfoundry.setandstring;

import java.util.ArrayList;
import java.util.Scanner;

public class Transposition {
	
	private static Scanner in = new Scanner(System.in);;

	public static void main(String[] args) {
		ArrayList<String> cipher = new ArrayList<String>();
		System.out.print("Enter your message: ");
		String msg = in.nextLine();
		String text = "";
		StringBuilder txt = new StringBuilder(text);
		System.out.print("Enter your Key: ");
		int key = in.nextInt();
		for (int i = 0; i < key; i++) {
			int pointer = i;
			String msg_char = Character.toString(msg.charAt(i));
			cipher.add(msg_char);
			while (pointer < msg.length()) {
				String pointer_char = Character.toString(msg.charAt(pointer));
				txt.append(cipher.get(i).replace(msg_char, pointer_char));
				pointer += key;
			}
		} System.out.println(txt);
	}
}
