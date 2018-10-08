package utils;

import org.springframework.beans.factory.annotation.Autowired;

import services.HotelService;

public class Decode {
	 public static String decodeUnicode(String data2) {       
	        StringBuffer sb = new StringBuffer();  
	            
	        String[] hex = data2.split("\\\\u");  
	        
	        for (int i = 1; i < hex.length; i++) {  
	            int data = Integer.parseInt(hex[i], 16);  
	            sb.append((char) data);  
	        }  
	        return sb.toString();    
	     }   
	       
	    public static String decodeUnicode2(String dataStr) {       
	        int start = 0;       
	        int end = 0;       
	        final StringBuffer buffer = new StringBuffer();       
	        while (start > -1) {       
	            end = dataStr.indexOf("\\u", start + 2);       
	            String charStr = null;       
	            if (end == -1) {       
	                charStr = dataStr.substring(start + 2, dataStr.length());       
	            } else {       
	                charStr = dataStr.substring(start + 2, end);       
	            }       
	            char letter = (char) Integer.parseInt(charStr, 16);     
	            buffer.append(new Character(letter).toString());       
	            start = end;       
	        }       
	        return buffer.toString();       
	     }   
}
