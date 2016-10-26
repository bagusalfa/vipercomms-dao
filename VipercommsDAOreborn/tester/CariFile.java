package tester;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CariFile {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// System.out.println(System.getProperty("user.home"));
		// List all files in the home directory
		// File homedir = new File(System.getProperty("user.home"));

		// String[] allfiles = homedir.list();
		//
		// for (String string : allfiles) {
		// System.out.println(string);
		// }
		File homedir = new File("c:\\project\\guntur\\crack");

		File[] allfiles = homedir.listFiles(new FileFilter() {

			@Override
			public boolean accept(File pathname) {
				// TODO Auto-generated method stub
				return pathname.getName().toLowerCase().endsWith(".java");
			}
		});
		for (File file : allfiles) {
			if (file.isDirectory()) {
				System.out.println("---->" + file.toString());
				// printFile(file);
			} else {
				System.out.println("@" + file.toString());
				removeComment(file);
			}
		}
	}

	public static void removeComment(File file) {
		try {
			
			File homedir = new File("c:\\project\\guntur\\crack\\"+file.getName()+".out");
			BufferedReader in = new BufferedReader(new FileReader(file));
			BufferedWriter out = new BufferedWriter(
					new FileWriter(homedir));
			String line;
			while ((line = in.readLine()) != null) { // Read line, check for
														// end-of-file
				 // Print the line
				String baris = line;
				baris=baris.replace("*/", "~");
				String[] arBaris = baris.split("~");
				String tmp = "";
				if (arBaris.length > 1) {
					for (int i = 1; i < arBaris.length; i++) {
						tmp = tmp + arBaris[i];
					}
					baris = tmp;
				}
				System.out.println(baris);
				out.write(baris);
				out.newLine();
				
			}
			out.close();
			in.close(); // Always close a stream when you are done with it
			
			//homedir.renameTo(file);
		} catch (IOException e) {
			// Handle FileNotFoundException, etc. here
		}
	}

	public static void printFile(File file) {
		try {

			File[] allfiles = file.listFiles();
			for (File fileThis : allfiles) {
				if (fileThis.isDirectory()) {
					System.out.println("---->" + fileThis.toString());
					printFile(fileThis);
				} else {
					System.out.println("@" + fileThis.toString());
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
