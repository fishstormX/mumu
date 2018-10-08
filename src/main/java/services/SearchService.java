package services;

import java.util.List;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public interface SearchService {
		String getUrlInCtrip(String keyword,String city);		//http://hotels.ctrip.com/hotel/xi'an10/k1中欧节点
		String getUrl(String hotelName,String city,int site,int page);			//搜索结果url(在不同的网站)
					//1 携程  2 艺龙  3途牛 4待定 
		void setHotelDetail(String urlInCtrip);					
		
		String getHtml(String url,int delay,boolean ifJsEnabled);			//提取页面内容(在不同的网站)
		
		String getSearchIndexJson(String city,int page,String keyWord);				//城市默认首页内容//最后一个对象的name为page
		
		String getDetailOnCtrip(String cid); 								//TODO
		String getDetailOnElong(String city,String hotelName);
				
		
		String getRoomOnCtrip(String cid);
		String getRoomOnElong(String hotelName,String city,List<String> roomList);
		String getRoomOnTuniu(String hotelName,String city,List<String> roomList);
		
		
		String getHotelImgSrcJson(String hotelId);
		//获取房型，获取就下单
		//http://hotel.elong.com/search/list_cn_1005.html?keywords=钟楼酒店
		//http://hotel.tuniu.com/list?city=2703&keyword=钟楼酒店
}
