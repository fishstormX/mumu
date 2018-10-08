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
import services.HotelService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml" })
public class getDetails {
		
	
	
	@Test
	public  void main() {
	
		
		try {
			StringBuilder data=new StringBuilder();
		
			data=searchHotel("7天连锁酒店（西安凤城四路店）",1);
			//webSpider w=new webSpider();
			hotel(data,"7天连锁酒店（西安临潼华清池店）");
			//data=null;
		
		}catch(IOException es) {
			System.out.println("emm");
		}
	}
	
	public static StringBuilder searchHotel(String s,int date) throws IOException
	{
		
		URL url=new URL("http://hotels.ctrip.com/hotel/xi'an10/k1"+s+"#ctm_ref=hod_hp_sb_lst") ;
		System.out.println("http://hotel.tuniu.com/list?city=2702&checkindate=2018-03-26&checkoutdate=2018-03-27&keyword="+s);
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

	public static void hotel(StringBuilder data,String hotel) {
		
		int indexTem;
		int indexTem2,indexTem22;
		int indexTem3,indexTem32;
		
		String dataTem;
		for(int i=0;i<20;i++)
		{
			indexTem=data.indexOf(hotel);
			data.delete(0,indexTem+1);
			indexTem2=data.indexOf("src=");
			indexTem22=data.indexOf("\">");
			System.out.println(data.substring(indexTem2+4,indexTem22));
			//spiderDao.addNew(data.substring(indexTem2+6,indexTem22),data.substring(indexTem3+6,indexTem32));
			//dataTem=data.substring(indexTem,indexTem+200);
		}
	}
}
