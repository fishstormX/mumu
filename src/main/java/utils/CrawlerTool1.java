package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
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
import services.SpiderService;
import servicesImpl.SpiderServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml" })
public class CrawlerTool1 {
	
	@Autowired
	private SpiderService spiderService;
	@Autowired
	private HotelService hotelService;
	@Test
	public  void main() {
		try {
			StringBuilder data=new StringBuilder();
			hotelService.getById(0);
			
			List<Spider> list=spiderService.getList();
			System.out.println(list.get(1));
			
			//http://hotel.tuniu.com/detail/+id
			//http://hotels.ctrip.com/hotel/xi'an10/
			//data=searchHotelT("西安钟楼饭店",1);
		//	int Tid=hotelT(data,"西安钟楼饭店");
		//	System.out.println(Tid);
		}catch(Exception es) {
			System.out.println("emm");
		}
	}
	
	public static StringBuilder searchHotelT(String s,int date) throws IOException
	{
		String urlStr = URLEncoder.encode(s, "utf-8");
		URL url=new URL("http://hotel.tuniu.com/ajax/list?search%5Bcity%5D=2702&search%5BcheckInDate%5D=2018-3-28&search%5BcheckOutDate%5D=2018-3-29&search%5Bkeyword%5D="+urlStr+"&search%5BcityCode%5D=2702&page=1") ;
		InputStream is=url.openStream();
		InputStreamReader isr=new InputStreamReader(is,"utf-8");
		BufferedReader br=new BufferedReader(isr);
		System.out.println("装填请求成功 正在读取数据...			信息来源：途牛");
		StringBuilder data=new StringBuilder();
		String w="";
		for(int i=0;i<2;i++)
		{
		w=br.readLine();
		data.append(w);
		}
		return data;
	}

	public static int hotelT(StringBuilder data,String hotel) {
		int indexTem3=data.indexOf("name");
		int indexTem32=data.indexOf("level");
		String name=Decode.decodeUnicode(data.substring(indexTem3+7,indexTem32-3));	
			if(!name.equals(hotel)) {		
				//检测酒店名
				System.out.println(name);
				return -1;
			}
			int indexTem=data.indexOf("id");
			data.delete(0,indexTem+4);
			int indexTem2=data.indexOf(",");		
		    String tem=data.substring(0, indexTem2);
		    return Integer.parseInt(tem);
	}
}

