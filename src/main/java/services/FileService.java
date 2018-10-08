package services;

import java.sql.Timestamp;



public interface FileService {
		Timestamp getTimestampNow();
		String FileOutput(String path, String fileF,String text,String author,String title,Timestamp now);
		String getUri(String path);
		String readToString(String fileName);
		
		
}
