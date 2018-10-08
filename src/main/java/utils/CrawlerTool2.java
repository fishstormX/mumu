package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import entity.Spider;
import services.HotelService;
import services.SpiderService;
/*
 * @author  xiahy
 * */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml" })
public class CrawlerTool2 {
	@Autowired
	private SpiderService spiderService;
	@Autowired
	private HotelService hotelService;
	@Test
	public  void main() {
		try {
			StringBuilder data=new StringBuilder();
			hotelService.getById(0);
			//http://hotels.ctrip.com/hotel/450319.html
			List<Spider> list=spiderService.getList();
			System.out.println(list.get(1));
			
			//http://hotel.tuniu.com/detail/+id
			//http://hotels.ctrip.com/hotel/xi'an10/
			data=searchHotelC("西安钟楼饭店",1);
			int cid=hotelC(data,"西安钟楼饭店");
			System.out.println(cid);
			//	System.out.println(Tid);
		}catch(Exception es) {
			System.out.println("emm");
		}
	}
	
	public static StringBuilder searchHotelC(String s,int date) throws IOException
	{
		String urlStr = URLEncoder.encode(s, "utf-8");
		URL url=new URL("http://hotels.ctrip.com/hotel/450319.html");
		InputStream is=url.openStream();
		InputStreamReader isr=new InputStreamReader(is,"utf-8");
		BufferedReader br=new BufferedReader(isr);
		System.out.println("装填请求成功 正在读取数据...			信息来源：携程");
		StringBuilder data=new StringBuilder();
		String w="";
		while((w=br.readLine())!=null)
		{
		data.append(w);
		}
		System.out.println(data);
		return data;
	}

	public static int hotelC(StringBuilder data,String hotel) {
		int indexTem=data.indexOf(hotel);
		if(indexTem<0) {
			return 0;
		}
		data.delete(0,indexTem);
		int indexTem2=data.indexOf("HotelRoom.onNameNewClick(this)");
		//int indexTem22=data.indexOf("data-ctm");
		String tem=data.substring(indexTem2, indexTem2+13);
		return Integer.parseInt(tem);
	}
}
