package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class TxtReadTools {
	
	
	public static void main(String[] args) {
		
		System.out.println(readToString("‪艺龙.txt"));
	}
	
	
	public static String readToString(String fileName) {  
        String encoding = "UTF-8";  
        fileName=fileName.replace("\\\\", "/"); 
        System.out.println(fileName);
        File file = new File(fileName); 
        System.out.println(file.getAbsolutePath());
        Long filelength = file.length();  
        byte[] filecontent = new byte[filelength.intValue()];  
        System.out.println(file.exists());
        System.out.println(12);
        try {  
            FileInputStream in = new FileInputStream(file);  
            in.read(filecontent);  
            in.close();  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        try {  
            return new String(filecontent, encoding);  
        } catch (UnsupportedEncodingException e) {  
            System.err.println("The OS does not support " + encoding);  
            e.printStackTrace();  
            return null;  
        }  
    }  
}
