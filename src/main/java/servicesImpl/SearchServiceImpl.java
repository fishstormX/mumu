package servicesImpl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.TextPage;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import dao.CitylistDao;
import dao.HotelDao;
import entity.Citylist;
import entity.Hotel;
import entity.Mrooms;
import services.HotelService;
import services.SearchService;
@Service
public class SearchServiceImpl implements SearchService{
	@Autowired
	private CitylistDao citylistDao;
	@Autowired
	private EncodeServiceImpl encodeService;
	@Autowired
	private HotelDao hotelDao;
	@Autowired
	private HotelService hotelService;
	
	@Override
	public String getUrlInCtrip(String keyword, String city) {
		return null;
	}

	
	@Override
	public String getUrl(String hotelName, String city, int site,int page) {
		Citylist cityL=citylistDao.getByName(city);
		System.out.println("city:"+city);
		String cityNameEn=cityL.getCityNameEn();
		int cityCode=0; 
		String s="";
		String temp=""; 
		int tempi=0;
		int tempi2=0;
		Hotel hotel=hotelService.SelectByName(hotelName);
		switch(site) {
		case 1:						//携程 html	
			temp="http://hotels.ctrip.com/hotel/"+cityNameEn+cityL.getCid()+"/p"+page+"/k1"+hotelName;
			break;
		case 2:						//艺龙 html
			String sb="";
			String yid="";
			if(hotel.getYilongid()!=null&&!hotel.getYilongid().equals("")) 
			{
				yid=hotel.getYilongid();
				temp="http://hotel.elong.com/"+yid;		//页面
				break;
			}
			try {
				sb = getHtml("http://hotel.elong.com/search/list_cn_"+cityL.getYid()+".html?keywords="+URLEncoder.encode(hotelName,"utf-8") , 0 , false);
			
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			tempi=sb.indexOf("alt=\""+hotelName);
			tempi2=0;
			if(tempi>0) {			//同名酒店
				tempi=sb.indexOf("href=",tempi);
				tempi2=sb.indexOf("/",tempi+7);
				yid=sb.substring(tempi+7,tempi2);			//yid
				
				
				temp="http://hotel.elong.com/"+yid;		//页面
			}else {
						tempi=sb.indexOf("hotelCount");
						tempi2=sb.indexOf("</span>",tempi);
						if(sb.substring(tempi+12, tempi2).equals("1")) {			//数字1酒店
							tempi=sb.indexOf("href=",tempi);
							tempi2=sb.indexOf("/",tempi+7);
							yid=sb.substring(tempi+7,tempi2);			//yid
							temp="http://hotel.elong.com/"+yid;		//页面
							
						}else {
							tempi=sb.indexOf("alt");
							tempi2=sb.indexOf("\"",tempi+6);
							String name=sb.substring(tempi+5, tempi2);
							double ratio=encodeService.getSimilarityRatio(name, hotelName);
							while(tempi>0&&ratio<0.5) {
								tempi=sb.indexOf("alt",tempi2);
								tempi2=sb.indexOf("\"",tempi+6);
								name=sb.substring(tempi+5, tempi2);
								ratio=encodeService.getSimilarityRatio(sb.substring(tempi+5, tempi2), hotelName);
							}
							if(ratio<0.5) {
								yid="none";
								s=yid;
							}else {
							tempi=sb.indexOf("href=",tempi);
							tempi2=sb.indexOf("/",tempi+7);
							yid=sb.substring(tempi+7,tempi2);
							temp="http://hotel.elong.com/"+yid;		//页面
							}
						}
			}
			System.out.println(temp);
			hotelDao.updateYidByName(hotelName, yid);
			break;
		case 3:						//途牛 html
			String tid="";
			if(hotel.getTuniuid()!=null&&!hotel.getTuniuid().equals("")) 
			{
				tid=hotel.getTuniuid();
				temp="http://hotel.tuniu.com/detail/"+tid;
				break;
			}
			
			try {
				temp="http://hotel.tuniu.com/list?city="+cityL.getTid()+
				"&checkindate="+encodeService.getData(3,1)+
				"&checkoutdate="+encodeService.getData(3,2)+
				"&keyword="+URLEncoder.encode(hotelName,"utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			s=getHtml(temp, 10000, true);				///////
			System.out.println(temp+"   ???");
			tempi=s.indexOf(hotelName);
			tempi2=0;
			
			if(tempi>0) {
				tempi=s.indexOf("detail",tempi);
				tempi2=s.indexOf("\"",tempi);
				tid=s.substring(tempi+7,tempi2).replaceAll("\r|\n| ", "");	
			}else {
				tempi=s.indexOf("hotel-total");
				tempi2=s.indexOf("</span>",tempi);	
					if(s.substring(tempi+13,tempi2).replaceAll("\r|\n| ", "").equals("1"))
					{
						tempi=s.indexOf("alt",tempi);
						tempi=s.indexOf("detail",tempi);	
						tempi2=s.indexOf("\"",tempi);
						tid=s.substring(tempi+7,tempi2).replaceAll("\r|\n| ", "");
					}else{
						tempi=s.indexOf("alt");
						tempi2=s.indexOf("\"",tempi+6);
						String name=s.substring(tempi+5, tempi2);
						double ratio=encodeService.getSimilarityRatio(name, hotelName);
						System.out.println(name+ratio);
						while(tempi>0&&ratio<0.5) {
							tempi=s.indexOf("alt",tempi2);
							tempi2=s.indexOf("\"",tempi+6);
							name=s.substring(tempi+5, tempi2);
							ratio=encodeService.getSimilarityRatio(s.substring(tempi+5, tempi2), hotelName);
							System.out.println(name+ratio);
						}
							if(ratio<0.5) {
								tid="none";
								s=tid;
							}else {
								tempi=s.indexOf("detail",tempi);
								tempi2=s.indexOf("\"",tempi);
								tid=s.substring(tempi+7,tempi2);
							}
						} 
				}
				temp="http://hotel.tuniu.com/detail/"+tid;
				hotelDao.updateTidByName(hotelName, tid);
				break;
			}
		System.out.println(temp);
		return temp;
	}

	@Override
	public void setHotelDetail(String urlInCtrip) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getHtml(String url, int delay,boolean ifJsEnabled) {
		WebClient webClient = new WebClient(BrowserVersion.CHROME);  
        //2.设置连接的相关选项  
        webClient.getOptions().setUseInsecureSSL(false);  
        webClient.getOptions().setCssEnabled(false);  
        webClient.getOptions().setJavaScriptEnabled(ifJsEnabled);  
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setTimeout(100000);  
        //3.抓取页面  
        HtmlPage page=null;
		try {
			page = webClient.getPage(url);
	        webClient.waitForBackgroundJavaScript(delay);
		} catch (FailingHttpStatusCodeException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        String s= page.asXml();		
       // System.out.println(s); 
       //4.关闭模拟窗口  
        webClient.close();  
		return s;
	}

	@Override
	public String getSearchIndexJson(String city,int page,String keyWord) {
		String returnStr="";
				String url="";
				try {
					System.out.println(URLEncoder.encode(keyWord,"utf-8")+city+page);
					url = getUrl(URLEncoder.encode(keyWord,"utf-8"), city , 1 , page);
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
					System.out.println(keyWord+page+city);
				}
				String s=getHtml(url,0,false);
				int tempi=0;
				int tempi2=0;
				int tempi3=0;
				int tempi4=0;
				int i=0;
				int end=0;
				int index=0;
				 Hotel[] hotels = new Hotel[27]; 
				StringBuffer sb=new StringBuffer(s);
				StringBuffer temp=new StringBuffer("");
				while(end>=0) {
					
					Hotel hotel=new Hotel();
					i=sb.indexOf("hotel_new_list");
					sb.delete(0,i+2);
					end=sb.indexOf("hotel_new_list");
					tempi=sb.indexOf("img");
					tempi2=sb.indexOf(">",tempi);
					hotel.setImg(sb.substring(tempi-1,tempi2+1));
					
					
					//图片
					
					tempi=sb.indexOf("alt=",tempi);
					tempi2=sb.indexOf("\"",tempi+5);
					hotel.setHname(sb.substring(tempi+5,tempi2));
					//名称
					
					tempi=sb.indexOf("icon_desc_text");
					tempi=sb.indexOf(">",tempi);
					tempi2=sb.indexOf("&lt",tempi);
					if(tempi<end)
						hotel.setIntroduce(sb.substring(tempi+1,tempi2));	 			
					else
						hotel.setIntroduce("暂无介绍");
					//介绍
		
					tempi=sb.indexOf("】");
					tempi2=sb.indexOf("<",tempi);
					hotel.setAddress(sb.substring(tempi+1,tempi2-1).replaceAll("\r|\n| ",""));
					//地址
					tempi=sb.indexOf("/hotel/");
					tempi2=sb.indexOf("html",tempi);
					hotel.setCtripid(sb.substring(tempi+7,tempi2-1));
					//Ctrip_id
					tempi=sb.indexOf("hotel_value");
					tempi2=sb.indexOf("<",tempi);
						if(tempi>0) 
					hotel.setGrade(sb.substring(tempi+15,tempi2-1).replaceAll("\r|\n| ",""));
						else
					hotel.setGrade("没有评分");
						
						//评分
					tempi=sb.indexOf("J_price_lowList");
					tempi2=sb.indexOf("<",tempi);
						if(tempi>0) 
					hotel.setLprice(new Integer(sb.substring(tempi+17,tempi2-1).replaceAll("\r|\n| ", "")));
					//价格
					tempi=sb.indexOf("<b>");
					tempi2=sb.indexOf("<",tempi+1);
					if(tempi>0&&tempi<end)
						hotel.setTip1(sb.substring(tempi+17,tempi2-1));	
					else
						hotel.setTip1("");						
					//特殊标签
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
					hotel.setTip2(temp.toString());	
					//标签组1
					
					
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
					if(hotelDao.queryCByName(hotel.getHname())==0) {
						hotelDao.addHotel(hotel.getHname(), hotel.getAddress(), city, hotel.getImg(),
								hotel.getCtripid(), hotel.getIntroduce(), hotel.getGrade(), hotel.getTip1(), hotel.getTip2());
					}
					hotels[index]=hotel;
					index++;
				}
				for(int j=index;j<26;j++) {
					Hotel hotel=new Hotel();
					hotels[j]=hotel;
				}
				
				tempi=sb.indexOf("data-pagecount=");
				tempi2=sb.indexOf("\"",tempi+16);
				
				Hotel hotel=new Hotel();
				hotel.setGrade(sb.substring(tempi+16, tempi2));
				hotels[26]=hotel;
					String jsonString2 = JSON.toJSONString(hotels);
					
					return jsonString2;
	}

	@Override
	public String getHotelImgSrcJson(String hotelId) {
		String s=getHtml("http://hotels.ctrip.com/Domestic/tool/AjaxLoadPictureAlbum.aspx?hotel="+hotelId, 0 , false);
		StringBuffer sb=new StringBuffer(s);
		List<String> jsonList=new ArrayList<String>();
		System.out.println(sb);
		int tempi1=sb.indexOf("<img");
		int tempi2=0;
		while(tempi1>0) {
			tempi1=sb.indexOf("data-bigpic=",tempi1);
			tempi2=sb.indexOf("\"",tempi1+15);
			jsonList.add(sb.substring(tempi1+15,tempi2));			//大图url
			
			tempi1=sb.indexOf("alt=",tempi1);
			tempi2=sb.indexOf("\"",tempi1+5);
			jsonList.add(sb.substring(tempi1+5,tempi2));			//名称alt
			
			tempi1=sb.indexOf("_src=",tempi1);
			tempi2=sb.indexOf("\"",tempi1+7);
			jsonList.add(sb.substring(tempi1+8,tempi2));			//略缩图url
			
			tempi1=sb.indexOf("<img",tempi1);
		}
			return JSON.toJSONString(jsonList);  
	}

	@Override
	public String getDetailOnCtrip(String cid) {
		List<String> list=new ArrayList<String>();
		String s=getHtml("http://hotels.ctrip.com/hotel/"+cid+".html", 0 , false);
		int tempi=s.indexOf("data-index=\"0\"");
		tempi=s.indexOf("_src=",tempi);
		int tempi2=s.indexOf("\">",tempi);
		list.add(s.substring(tempi+8,tempi2));
		
		tempi=s.indexOf("<div class=\"special_label\">",s.indexOf("酒店介绍"));
		tempi2=s.indexOf("<div class=\"introduce_all layoutfix");
		StringBuffer detail=new StringBuffer(s.substring(tempi,tempi2));
		tempi=detail.indexOf("data-real=");
		tempi2=detail.indexOf("&lt",tempi);
		String tele=detail.substring(tempi+11,tempi2-2);							//联系方式
		list.add(tele);
		int tempi3=detail.indexOf("<span id=\"J_realContact\"");
		int tempi4=detail.indexOf("</span>",tempi3);
		detail=detail.replace(tempi3,tempi4+7,"");
		
		list.add(detail.toString());
		tempi =s.indexOf("入住和离店");
		tempi=s.indexOf("<td>",tempi);
		tempi2=s.indexOf("</td>",tempi);
		
		list.add(s.substring(tempi+4,tempi2).replaceAll("\r|\n| ", ""));
		tempi =s.indexOf("膳食安排");
		tempi=s.indexOf("<td>",tempi);
		tempi2=s.indexOf("</td>",tempi);
		
		list.add(s.substring(tempi+4,tempi2).replaceAll("\r|\n| ", ""));
		tempi =s.indexOf("可用支付方式");
		tempi=s.indexOf("<td>",tempi);
		tempi2=s.indexOf("</td>",tempi);
		tempi3=s.indexOf("&quot;>",tempi);
		detail=new StringBuffer("");
		while(tempi3>0&&tempi3<tempi2) {
			tempi4=s.indexOf("'",tempi3);
			detail.append(s.substring(tempi3+7,tempi4).replaceAll("境外发行信用卡 -- |&lt;/div>","")+"   ");
			tempi3=s.indexOf("&quot;>",tempi4);
		}
		list.add(detail.toString());
		
		return JSON.toJSONString(list);
	}

	@Override
	public String getDetailOnElong(String city, String hotelName) {
		String s=getHtml("http://hotel.elong.com/search/list_cn_"+citylistDao.getByName(city).getYid()+".html?keywords="+hotelName , 0 , false);
		System.out.println(s);
		StringBuffer sb=new StringBuffer(s);
		sb.delete(0, sb.indexOf(hotelName)+2);
		int tempi=sb.indexOf(hotelName);
		tempi=sb.indexOf("href=");
		int tempi2=sb.indexOf("/",tempi+6);
		System.out.println(sb.substring(tempi+6,tempi2));
		return null;
	}

	@Override
	public String getRoomOnCtrip(String cid) {
		String s=getHtml("https://m.ctrip.com/webapp/hotel/hoteldetail/"+cid+".html", 0 , true);
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
		String key="c_hotel_inland_detail_room_basic";
		
		int tempi=s.indexOf(key);
		if(tempi<0) { 
			key="c_hotel_inland_detail_room_sub";
			tempi=s.indexOf(key);
		}
		int tempi2=0;
		int start=tempi;
		List<Mrooms> roomList=new ArrayList();
		Mrooms room=null;
		while(tempi>0) {
			room=new Mrooms();
			tempi=s.indexOf("<h3>",tempi);
			start=tempi+5;
			tempi2=s.indexOf("</h3>",tempi);
			String name=s.substring(tempi+4,tempi2).replaceAll("\r|\n| ", "");
			
			room.setName(name);
			tempi=s.indexOf("<em>",tempi);
			tempi2=s.indexOf("</em>",tempi);
			room.setSize(s.substring(tempi+4,tempi2).replaceAll("\r|\n| ", ""));
			
			tempi=s.indexOf("<em>",tempi2);
			tempi2=s.indexOf("</em>",tempi);
			room.setBed(s.substring(tempi+4,tempi2).replaceAll("\r|\n| ", ""));
			
			tempi=s.indexOf("js-cas-p",tempi2);
			tempi=s.indexOf(">",tempi);
			tempi2=s.indexOf("</span>",tempi);			
			room.setCprice(s.substring(tempi+1,tempi2).replaceAll("\r|\n| ", ""));		//价格room-column js_baseroomtoggle
			
			tempi=s.indexOf("room-column js_baseroomtoggle",tempi2);
			tempi=s.indexOf("</div>",tempi);
			tempi2=s.indexOf("</div>",tempi+1);
			if(s.substring(tempi, tempi2).indexOf("订完")>0)
				room.setFinished(true);
			else
				room.setFinished(false);
			
			tempi=s.indexOf(name,tempi);
			
			tempi=s.indexOf("breakfast",tempi);
			tempi2=s.indexOf("&quot",tempi+20);			
			String breakfast=s.substring(tempi+22,tempi2);
			if(breakfast.equals("item")) {
				breakfast="尚不明确";
			}
				room.setBreakfast(breakfast);
			
			
			tempi=s.indexOf("http:",tempi);
			tempi2=s.indexOf(".jpg",tempi);			
			room.setImg(s.substring(tempi,tempi2+4));
			
			tempi=s.indexOf("可住",tempi);
			tempi2=s.indexOf("人",tempi);			//人数
			String person=s.substring(tempi+2,tempi2).replaceAll("\r|\n| ", "");
			room.setPerson(new Integer(s.substring(tempi+2,tempi2).replaceAll("\r|\n| ", "")));
			
			tempi=s.indexOf("name",tempi);
			tempi2=s.indexOf("&quot;",tempi+15);			
			String Fname=s.substring(tempi+17,tempi2);		//具体名字
			room.setcName((s.substring(tempi+17,tempi2)));
			roomList.add(room);
			
			tempi=s.indexOf(key,start);
		}
		System.out.println(JSON.toJSONString(roomList));
		return JSON.toJSONString(roomList);
	}


	@Override
	public String getRoomOnElong(String hotelName,String city,List<String> roomList) {
		String url=getUrl(hotelName,city,2,1);
		List<String> list=new ArrayList();
		if(!url.equals("none")) {
			String s=getHtml(url, 8000,true);
			int tempi=0;
			int tempi2=0;
			String name="";
			tempi=s.indexOf("u_nmb");
			tempi2=s.indexOf("</span>",tempi);
			String mark=s.substring(tempi+7,tempi2);
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
		
		return JSON.toJSONString(list);
	}


	@Override
	public String getRoomOnTuniu(String hotelName, String city, List<String> roomList) {
		String url=getUrl(hotelName,city,3,1);
		System.out.println(url);
		List<String> list=new ArrayList();
		if(!url.equals("none")) {
			String s=getHtml(url, 10000,true);						////////
			int tempi=0;
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
					tempi=s.indexOf("m1 fleft",tempi);
					tempi=s.indexOf("title=",tempi);
					tempi2=s.indexOf("\">",tempi+7);
					name=s.substring(tempi+7,tempi2).replaceAll("\r|\n| ", "");
					System.out.println(name);
					tempi=s.indexOf("data-hastaxorservicefee=",tempi);
					tempi=s.indexOf(">",tempi);
					tempi2=s.indexOf("</span>",tempi);
					System.out.println(s.substring(tempi+12,tempi2-7).replaceAll("\r|\n| ", "")+"¥</span>");
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
		System.out.println(JSON.toJSONString(list));
		return JSON.toJSONString(list);
		
	}

	
	
}
