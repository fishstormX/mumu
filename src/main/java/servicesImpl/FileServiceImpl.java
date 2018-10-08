package servicesImpl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import services.EncodeService;
import services.FileService;
@Service
public class FileServiceImpl implements FileService{
	
	@Autowired
	private EncodeService encodeService;

	@Override
	public Timestamp getTimestampNow() {
		Timestamp ts = new Timestamp(new Date().getTime());
		return ts;
	}

	@Override
	/*
	 * @param fileF 文件后缀
	 * */
	public String FileOutput(String path, String fileF,String text,String author,String title,Timestamp ts) {		
		String FileName=encodeService.getData(0,0)+"_"+new Random().nextInt(1000)+fileF;//新的文件名
		String data=encodeService.getData(1,0);
        String pathH=path+"/"+data+"/"+FileName;
        File temp= new File(path+"/"+data);
        File writename = new File(pathH);   
        try {
        	if(!temp.exists()  && !temp.isDirectory()){       
        		temp.mkdirs();  
        		System.out.println(path);
            }
	        writename.createNewFile(); // 创建新文件  
	        OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(writename),"UTF-8");
	        BufferedWriter out=new BufferedWriter(write);   
	        out.write("<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\" pageEncoding=\"UTF-8\"%>");
	        out.write("<h1>"+title+"</h1>");
	        out.write("<div style='position:relative;left:60%;font-weight:700'><span style='color:orange'>"+author+"</span>&nbsp;编辑于&nbsp;&nbsp;"+ts+"</div>");
	        out.write(text); 
	        out.flush(); // 把缓存区内容压入文件  
	        out.close(); // 最后记得关闭文件  
	    }catch(Exception e) 
        {
	    	e.printStackTrace();
        }
        return pathH;
	}

	@Override
	public String getUri(String path) {
		path=path.replace('\\', '/');
		int i=path.indexOf("mumu")+5;
		path=path.substring(i);
		int j=path.indexOf(".");
		return path.substring(0,j);
	}

	@Override
	public String readToString(String fileName) {
		String encoding = "UTF-8";  
        File file = new File(fileName);  
        Long filelength = file.length();  
        byte[] filecontent = new byte[filelength.intValue()];  
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
