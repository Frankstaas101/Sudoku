package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileData {

	int width = 0;
	int height = 0;
	int dimension;
	ArrayList<String> comments = new ArrayList<String>();
	
	public FileData() {
		
	}
	
	public void readFile(File name){
		try {
			BufferedReader in = new BufferedReader(new FileReader(name));
			
			String nextLine = in.readLine();
			
			while(nextLine != null) {
				//System.out.println(nextLine);
				
				// matches a comment format
				if (nextLine.matches("^c(\\s+).*")){
					comments.add(nextLine);
				}
				// first two numbers are the height and width
				else if (nextLine.matches("^[1-9]$")){
					if (width == 0) {
						width = new Integer(nextLine);
					}
					else if (height == 0 && width != 0) {
						width = new Integer(nextLine);
					}
				}
				// matches the pattern 
				else if (nextLine.matches("^[1-9\\s+]{" + width * height + "}$")){
					System.out.println(nextLine);
				}
				nextLine = in.readLine();
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("File was not found!");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("There was a problem with reading the file!");
			e.printStackTrace();
		}
		
	}
	
}


// REGEX TESTING
// Comment regex "^c(\s+).*"
//c 	test test test 9002423 ,.,.,*(@#*$(#@))
//333

///4 4 4 4 4 4