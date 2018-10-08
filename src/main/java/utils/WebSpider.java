package utils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import dao.SpiderDao;
import entity.Spider;
 /*		@author  夏华禹 
  * 
  */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml" })
public class WebSpider {
	
	
	
	@Autowired
	private SpiderDao spiderDao;
	
	@Test
	public  void main() {
		try {
			StringBuilder data=new StringBuilder();
		for(int i=49;i<52;i++) {
			data=searchHotel(i,0);
			WebSpider w=new WebSpider();
			hotel(data);
			//data=null;
			
		}
		}catch(IOException es) {
			System.out.println("emm");
		}
	}
	
	public static void main(String[] args) {
		try {
			searchHotel(1,0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static StringBuilder searchHotel(int page,int date) throws IOException
	{
		
		URL url=new URL("http://s.lvmama.com/hotel/C20180325O20180326P"+page+"U256?keyword=&mdd=西安#list#list") ;
		
		InputStream is=url.openStream();
		
		InputStreamReader isr=new InputStreamReader(is,"utf-8");
		
		BufferedReader br=new BufferedReader(isr);
		System.out.println("装填请求成功 正在读取数据...			信息来源：驴妈妈");
		StringBuilder data=new StringBuilder();
		String w="";
		while((w=br.readLine())!=null)
		{
		data.append(w);
		}
		return data;
	}
	
	public void hotel(StringBuilder data) {
		
		int indexTem;
		int indexTem2,indexTem22;
		int indexTem3,indexTem32;
		
		String dataTem;
		for(int i=0;i<20;i++)
		{
			indexTem=data.indexOf("prdLi clearfix");
			data.delete(0,indexTem+15);
			indexTem2=data.indexOf("name=");
			indexTem22=data.indexOf("\">");
			indexTem3=data.indexOf("href=");
			indexTem32=data.indexOf("\" onClick");
			System.out.println(data.substring(indexTem2+6,indexTem22)+" "+data.substring(indexTem3+6,indexTem32));
			spiderDao.addNew(data.substring(indexTem2+6,indexTem22),data.substring(indexTem3+6,indexTem32));
			//dataTem=data.substring(indexTem,indexTem+200);
		}
	}
}
