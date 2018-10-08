package test;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.TextPage;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;

import dao.CitylistDao;
import dao.HotelDao;
import dao.NotesDao;
import dao.SpiderDao;
import dao.UsersDao;
import entity.Hotel;
import entity.Spider;
import entity.Users;
import services.EncodeService;
import services.FileService;
import services.NotesService;
import services.SearchService;
import services.UsersService;
import servicesImpl.UsersServiceImpl;

import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;

@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring配置文件
@ContextConfiguration({ "classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml" })
public class Testt {
	@Autowired
	private HotelDao hotelDao;
	@Autowired
	private SpiderDao spiderDao;

	@Autowired
	private UsersDao userDao;
	
	@Autowired 
	private FileService fileService;
	@Autowired 
	private UsersService usersService;
	@Autowired 
	private EncodeService encodeService;
	@Autowired 
	private NotesService notesService;
	@Autowired 
	private SearchService searchService;
	@Autowired 
	private NotesDao notesDao;
	@Autowired 
	private CitylistDao citylistDao;
	
	//@Test
	public void testSeast(){
		try {
            //创建client实例
            HttpClient client= HttpClients.createDefault();
            //创建httpget实例
            HttpPost post=new HttpPost("http://hotel.tuniu.com/ajax/hotelRooms?id=1872435839&checkindate=2018-04-27&checkoutdate=2018-04-28");
            
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();   
            nvps.add(new BasicNameValuePair("id", "1872435839")); 
            nvps.add(new BasicNameValuePair("checkindate", "2018-04-27")); 
            nvps.add(new BasicNameValuePair("checkoutdate", "2018-04-28")); 
            post.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36");
            //User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36
            post.setHeader("Cookie", "tuniuuser_citycode=MjcwMg%3D%3D; p_phone_400=4007-999-999; p_phone_level=0; p_global_phone=%2B0086-25-8685-9999; _tacau=MCwyNWVmNTc5Ny1jMWZlLWU3YzEtZDFmZS01NTNmOGQzZWYwZGUs; _tact=M2JiZDQwYTMtMWU4OS1iNjI0LWVlNWQtMzU1ZWNmNWMyNjYz; _tacz2=taccsr%3Dbaidu%7Ctacccn%3D%28organic%29%7Ctaccmd%3Dmkt_06002401%7Ctaccct%3D%2525E9%252580%252594%2525E7%252589%25259B%7Ctaccrt%3D%28none%29; _tacc=1; PageSwitch=1%2C213612736; __utmc=1; __utmz=1.1524711519.1.1.utmcsr=baidu|utmccn=brand|utmcmd=brand|utmctr=%E9%80%94%E7%89%9B; OLBSESSID=nqndg2qi74g2lp5q7ide40pvj2; MOBILE_APP_SETTING_OPEN-126=1; hotel_view_history_new_guid=3EDFFDB6-DC0A-968E-DA3B-BBB49B5AB381; UM_distinctid=162ffe5289c3f-00107353c60da8-b34356b-144000-162ffe5289d62e; __utma=1.1858562404.1524711519.1524711519.1524711519.1; __utmc=1; __utmz=1.1524711519.1.1.utmcsr=baidu|utmccn=brand|utmcmd=brand|utmctr=%E9%80%94%E7%89%9B; hotel_user_token=FBA45ED2258A8FD0D6EA6E37F5EFF4EB; rg_user_agent=Mozilla%2F5.0+%28Windows+NT+10.0%3B+Win64%3B+x64%29+AppleWebKit%2F537.36+%28KHTML%2C+like+Gecko%29+Chrome%2F65.0.3325.181+Safari%2F537.36; hotel_index_search_history=eyJfMjcwMiI6eyJjaGVja2luZGF0ZSI6IjIwMTgtNC0yNyIsImNoZWNrb3V0ZGF0ZSI6IjIwMTgtNC0yOCIsImNpdHlfaWQiOiIyNzAyIiwiY2l0eV9uYW1lIjoi6KW/5a6JIn19; Hm_lvt_3a8c303cfc611b527f4ba8a41b4a69ab=1524715243; tuniu_partner=MTQwMCwwLCwzMTExMWViZjMxNTgyMWUxOTcwZWE0YTAzNzZhMDRjMw%3D%3D; Hm_lvt_51d49a7cda10d5dd86537755f081cc02=1524711519,1524715503; Hm_lpvt_3a8c303cfc611b527f4ba8a41b4a69ab=1524716508; CNZZDATA5726564=cnzz_eid%3D90967479-1524708261-http%253A%252F%252Fhotel.tuniu.com%252F%26ntime%3D1524729873; _taca=1524711519202.1524730315230.1524733909668.4; _tacb=MDI5NjlkNjQtNDFjOS03YWM5LWQ3MDYtYzY4NTVmZjlmOWJl; __utma=1.1858562404.1524711519.1524711519.1524711519.1; __xsptplusUT_352=1; _pzfxuvpc=1524711518772%7C1419972674593657865%7C34%7C1524735141511%7C4%7C1028910920383905066%7C9485894343299816603; _pzfxsvpc=9485894343299816603%7C1524733910268%7C3%7Chttp%3A%2F%2Fhotel.tuniu.com%2Flist%2F2702p0s0b0%2F%3Fcheckindate%3D2018-4-27%26checkoutdate%3D2018-4-28; Hm_lpvt_51d49a7cda10d5dd86537755f081cc02=1524735142; rg_entrance=010000%2F003001%2F000013%2F000000; __xsptplus352=352.4.1524733909.1524735381.4%231%7Cbaidu%7Cbrand%7Cbrand%7C%25E9%2580%2594%25E7%2589%259B%7C%23%23c379fMuVzjI_mR_PQjdoTLIlu3g8rWXQ%23; __utmb=1.4.10.1524733910");
            //String sessionId = getSessionId();
            post.setHeader("hotel_user_token", "FBA45ED2258A8FD0D6EA6E37F5EFF4EB; rg_user_agent=Mozilla%2F5.0+%28Windows+NT+10.0%3B+Win64%3B+x64%29+AppleWebKit%2F537.36+%28KHTML%2C+like+Gecko%29+Chrome%2F65.0.3325.181+Safari%2F537.36");
           // post.setHeader("Content-Length","385846");
            post.setHeader("hotel_user_token", "FBA45ED2258A8FD0D6EA6E37F5EFF4EB; rg_user_agent=Mozilla%2F5.0+%28Windows+NT+10.0%3B+Win64%3B+x64%29+AppleWebKit%2F537.36+%28KHTML%2C+like+Gecko%29+Chrome%2F65.0.3325.181+Safari%2F537.36");
            
            post.setHeader("hotel_user_token", "FBA45ED2258A8FD0D6EA6E37F5EFF4EB; rg_user_agent=Mozilla%2F5.0+%28Windows+NT+10.0%3B+Win64%3B+x64%29+AppleWebKit%2F537.36+%28KHTML%2C+like+Gecko%29+Chrome%2F65.0.3325.181+Safari%2F537.36");
            
            post.setEntity(new UrlEncodedFormEntity(nvps,HTTP.UTF_8));  
            HttpResponse response=client.execute(post);
            int code = response.getStatusLine().getStatusCode();  
            //返回获取实体
            HttpEntity entity=response.getEntity();
            //获取网页内容，指定编码
            String web= EntityUtils.toString(entity,"UTF-8");
            //输出网页
            System.out.println(web+" "+code);

        } catch (IOException e) {
            e.printStackTrace();
	}
	}
	//@Test
	public void testQueryById() throws FailingHttpStatusCodeException, MalformedURLException, IOException{
		String url=searchService.getUrl(URLEncoder.encode("","utf-8"), "西安", 1 ,1);
		String s=searchService.getHtml(url,0);
		StringBuffer temp=null;
		int tempi=0;
		int tempi2=0;
		int tempi3=0;
		int tempi4=0;
		int i=0;
		int end=0;
		int index=0;
		Hotel[] hotels = new Hotel[25]; 
		  
		try {
			   File file = new File("C:\\Users\\一只\\Desktop\\m78.txt");
			    if (!file.exists()) {
			    file.createNewFile();
			   }

			   FileWriter fw = new FileWriter(file.getAbsoluteFile());
			   BufferedWriter bw = new BufferedWriter(fw);
			   bw.write(s);
			   bw.close();

			   System.out.println("Done");

			  } catch (IOException e) {
			   e.printStackTrace();
			  }
		
		StringBuffer sb=new StringBuffer(s);
		while(end>=0) {
			Hotel hotel=new Hotel();
			i=sb.indexOf("hotel_new_list");
			sb.delete(0,i+2);
			end=sb.indexOf("hotel_new_list");
			tempi=sb.indexOf("img");
			tempi2=sb.indexOf(">",tempi);
			//System.out.println(tempi+" "+tempi2);
			hotel.setImg(sb.substring(tempi-1,tempi2+1));
			//System.out.println(sb.substring(tempi-1,tempi2+1));	 			//图片
			tempi=sb.indexOf("alt=",tempi);
			tempi2=sb.indexOf("\"",tempi+5);
			hotel.setHname(sb.substring(tempi+5,tempi2));
			//System.out.println(sb.substring(tempi+5,tempi2));				//名称
			//System.out.println(i);
			tempi=sb.indexOf("icon_desc_text");
			tempi=sb.indexOf(">",tempi);
			tempi2=sb.indexOf("&lt",tempi);
			if(tempi<end)
				hotel.setIntroduce(sb.substring(tempi+1,tempi2));	 			//介绍
			else
				hotel.setIntroduce("暂无介绍");
			
			
			tempi=sb.indexOf("】");
			tempi2=sb.indexOf("<",tempi);
			//System.out.println(sb.substring(tempi+1,tempi2-1).replaceAll("\r|\n| ",""));	 			//地址
			hotel.setAddress(sb.substring(tempi+1,tempi2-1).replaceAll("\r|\n| ",""));
			tempi=sb.indexOf("hotel_value");
			tempi2=sb.indexOf("<",tempi);
			//System.out.println(sb.substring(tempi+15,tempi2-1).replaceAll("\r|\n| ",""));	 			//评分
			hotel.setGrade(sb.substring(tempi+15,tempi2-1).replaceAll("\r|\n| ",""));
			
			tempi=sb.indexOf("J_price_lowList");
			tempi2=sb.indexOf("<",tempi);
			System.out.println("图价格"+sb.substring(tempi+17,tempi2-1).replaceAll("\r|\n| ", ""));					//价格
			
			tempi=sb.indexOf("<b>");
			tempi2=sb.indexOf("<",tempi+1);
			if(tempi>0&&tempi<end)
				System.out.println(sb.substring(tempi+3,tempi2).replaceAll("\r|\n|  ", ""));					
			
			tempi=sb.indexOf("/hotel/");
			tempi2=sb.indexOf("html",tempi);
			System.out.println((sb.substring(tempi+7,tempi2-1)));											//id
			
			tempi=sb.indexOf("special_label");
			tempi2=sb.indexOf("</span>",tempi);
			tempi4=tempi;
			tempi3=sb.indexOf("i_label",tempi);
			temp=new StringBuffer("");
			while(tempi3<tempi2&&tempi3>0) {
				tempi3=sb.indexOf("i_label",tempi4);
				tempi4=sb.indexOf("</i>",tempi3);
				if(tempi4<tempi2&&tempi3>0)
					temp.append(sb.substring(tempi3+9, tempi4).replaceAll("\r|\n| ", "")).append(" ");
			}
		//	hotel.setServices(temp.toString().replaceAll("可礼品卡支付",""));
			//提供服务
			System.out.println(temp.toString());
			
			tempi=sb.indexOf("icon_list\"");
			tempi2=sb.indexOf("</div>",tempi);
			tempi4=tempi;
			tempi3=sb.indexOf("title=",tempi);
			temp=new StringBuffer("");
			while(tempi3<tempi2&&tempi3>0) {
				tempi3=sb.indexOf("title=",tempi4);
				tempi4=sb.indexOf("\"",tempi3+8);
				temp.append(sb.substring(tempi3+7, tempi4)).append(" ");
				tempi=temp.indexOf("客户点评");
				if(tempi>0)
				temp.delete(tempi,temp.length()-1);
			}
			hotel.setServices(temp.toString().replaceAll("可礼品卡支付",""));
			//提供服务
			System.out.println(temp.toString());
			
			hotels[index]=hotel;
			index++;System.out.println(index);
		}
			String jsonString2 = JSON.toJSONString(hotels);  
			System.out.println("jsonString2:" + jsonString2);  
			
			tempi=sb.indexOf("data-pagecount=");
			tempi2=sb.indexOf("\"",tempi+16);
			System.out.println(sb.substring(tempi+16, tempi2)); 						//页码数
		
			
			String urll=searchService.getUrl(URLEncoder.encode("","utf-8"), "重庆", 1, 1);
			System.out.println(urll);
		List<Hotel> hotel = JSON.parseArray(jsonString2, Hotel.class);  
		System.out.println(hotel);
	}

	//@Test
	public void testQueryAll2() throws Exception { 
		String url=searchService.getUrl("西安天朗时代大酒店","西安",2,1);
		List<String> list=new ArrayList();
		if(!url.equals("none")) {
			String s=getHtml(url, 10000,true);
			int tempi=0;
			int tempi2=0;
			String name="";
			String price="";
			for(String room:roomList) {
				tempi=s.indexOf(room);
				if(tempi>0) {
					tempi=s.indexOf("ht_name",tempi);
					tempi=s.indexOf("title=",tempi);
					tempi=s.indexOf("\">",tempi+7);
					tempi2=s.indexOf("</span>",tempi+7);
					name=s.substring(tempi+6,tempi2).replaceAll("\r|\n| ", "");
					System.out.println(name);
					tempi=s.indexOf("ht_pri_num",tempi);
					tempi2=s.indexOf("</span>",tempi);
					list.add(name+"<span class='orangePrice'>"+s.substring(tempi+12,tempi2)+"</span>");
					
				}else {
					list.add("没有数据");
				}
				
			}}
		
	}
	
	@Test
	public void testQueryAll() throws Exception {
		String hotelName="西安钟楼饭店";
		String city="西安";
		//String url=searchService.getUrl(hotelName,city,3,1);
		String s=fileService.readToString("C:\\Users\\一只\\Desktop\\m78.txt");
		s=encodeService.unicode2String(s);
		System.out.println(s);
		//List<String> list=new ArrayList();
		//if(!url.equals("none")) {
			// s=searchService.getHtml("", 5000,true);
			
			try {
				   File file = new File("C:\\Users\\一只\\Desktop\\m78.txt");
				    if (!file.exists()) {
				    file.createNewFile();
				   }
				   FileWriter fw = new FileWriter(file.getAbsoluteFile());
				   BufferedWriter bw = new BufferedWriter(fw);
				   bw.write(s);
				   bw.close();

				   System.out.println("Done");

				  } catch (IOException e) {
				   e.printStackTrace();
				  }
		
		/*int tempi=0;
		int tempi2=0;
		String name="";
		tempi=s.indexOf("<span class=\"a1\">");
		tempi=s.indexOf("<span class=\"a1\">",tempi+2);
		tempi=s.indexOf("<span class=\"a1\">",tempi+2);
		tempi2=s.indexOf("</span>",tempi);
		String mark=s.substring(tempi+17,tempi2).replaceAll("\r|\n| ", "");
		System.out.println(mark);
		for(String room:roomList) {
			tempi=Math.max(s.indexOf(room), s.indexOf(room.replaceAll("\\(", "（").replaceAll("\\)","）") ));
			if(tempi>0) {
				tempi=s.indexOf("ht_name",tempi);
				tempi=s.indexOf("title=",tempi);
				tempi=s.indexOf("\">",tempi+7);
				tempi2=s.indexOf("</span>",tempi+7);
				name=s.substring(tempi+6,tempi2).replaceAll("\r|\n| ", "");
				System.out.println(name);
				tempi=s.indexOf("ht_pri_num",tempi);
				tempi2=s.indexOf("</span>",tempi);
				System.out.println(s.substring(tempi+12,tempi2)+"¥</span>");
				list.add(name+"<br><span class='orangePrice'>"+s.substring(tempi+12,tempi2).replaceAll("\r|\n| ", "")+"¥</span>");
			}else {
				list.add("没有数据");
			}
			
		}
		list.add(mark);
			
	}else {
		for(String room:roomList) 
		{
		list.add("无此酒店");
		}
		list.add("无此酒店");
	}
	*/
	//return JSON.toJSONString(list);
}
		
		
		
	
	
	//@Test
	public void testSet() throws Exception {
		WebClient webclient = new WebClient();

		HtmlPage page;
		HtmlPage page2;
		webclient.getOptions().setCssEnabled(false);

		webclient.getOptions().setJavaScriptEnabled(true);

        webclient.getOptions().setThrowExceptionOnScriptError(false);
		page = webclient.getPage("http://hotel.elong.com/32701005");

		DomNodeList<DomElement> domElements=page.getElementsByTagName("input");
		for(DomElement temp:domElements){

		if(temp.getAttribute("handle").equals("inDate")) {
			System.out.println(temp.toString());
				temp.setAttribute("value", "2018-05-01");
				temp.setAttribute("selectedvalue", "2018-05-01");
		}
			
		if(temp.getAttribute("handle").equals("outDate")) {
			System.out.println(temp.toString());
			temp.setAttribute("value", "2018-05-02");
			temp.setAttribute("selectedvalue", "2018-05-02");
			System.out.println(temp.toString());
			page2=temp.getParentNode().getParentNode().getNextElementSibling().click();
			webclient.waitForBackgroundJavaScript(10000);
			String s=page.asXml();
			try {
				   File file = new File("C:\\Users\\一只\\Desktop\\m78.txt");
				    if (!file.exists()) {
				    file.createNewFile();
				   }

				   FileWriter fw = new FileWriter(file.getAbsoluteFile());
				   BufferedWriter bw = new BufferedWriter(fw);
				   bw.write(s);
				   bw.close();

				   System.out.println("Done");

				  } catch (IOException e) {
				   e.printStackTrace();
				  }
			
		}
		
		
		
		
		
		
	}
		}
}
