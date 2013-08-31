package util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class Logger {
	
	private static File logFile = null;
	private static boolean toWrite = false;
	public static void write(String text){
		if(isActive()){
			try{
			FileWriter fileWriter = new FileWriter(logFile,true);	
			System.out.println("1");
	        fileWriter.write(text+"\n");	
	    	System.out.println("2");
	        fileWriter.close();
	    	System.out.println("3");
			}catch(Exception ex){
				System.out.println("exception:"+ex.toString());
			}
		}
		
	}
	public static void activate(){
		toWrite = true;
	}//activate
	
	public static void deActivate(){
		toWrite = false;
	}
	public static boolean  isActive(){
		return toWrite;
	}
	public static void setPath(String path){
		logFile = new File(path);
		if(logFile.exists()){
			System.out.println("File exists....");
		}
	}
}
