package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

import dao.UsersDao;
import entity.Hotel;
import entity.Users;
import services.EncodeService;
import services.HotelService;
import services.SearchService;
import services.UsersService;

@Controller
@RequestMapping("hotel")
public class HotelC {
	@Autowired 
	private UsersService usersService;
	@Autowired
	private EncodeService encodeService;
	@Autowired 
	private SearchService searchService;
	@Autowired 
	private HotelService hotelService;
	
	private static String loginUserKey = "User78cd";
	private static String HotelTPage = "hpmD712";
	private ModelAndView mav=null;
	@RequestMapping("hotelDetail")
	public ModelAndView hotelDetail(@RequestParam(value="cid",required=false)String cid,
			@RequestParam(value="city",required=false)String city,
			@RequestParam(value="name",required=false)String name,
			HttpSession session) {
		Hotel hotel=hotelService.SelectByName(name);
		System.out.println(city);
		
		mav=new ModelAndView("/hotelDetail");
		mav.addObject("hotel", hotel);
		mav.addObject("city", city);
		mav.addObject("cid", cid);
		if (session.getAttribute(loginUserKey)!=null) {
			mav.addObject("User",session.getAttribute(loginUserKey));
		}
		return mav;
	}
	
	@RequestMapping("hotelDetailAjaxC")
	public void hotelDetail(HttpSession session,HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        String cid=request.getParameter("cid");
        PrintWriter out = response.getWriter();
        out.print(searchService.getDetailOnCtrip(cid));
	}
	@RequestMapping("RoomAjaxC")
	public void roomC(HttpSession session,HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        String cid=request.getParameter("cid");
        PrintWriter out = response.getWriter();
        out.print(searchService.getRoomOnCtrip(cid));
	}
	@RequestMapping("RoomAjaxY")
	public void roomE(HttpSession session,HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        String name=request.getParameter("name");
        String city=request.getParameter("city");
        String list=request.getParameter("list");
        List<String> roomlist=JSON.parseArray(list,String.class);  
        System.out.println("yilong");
        PrintWriter out = response.getWriter();
        out.print(searchService.getRoomOnElong(name, city, roomlist));
	}
	
	@RequestMapping("RoomAjaxT")
	public void roomT(HttpSession session,HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        String name=request.getParameter("name");
        String city=request.getParameter("city");
        String list=request.getParameter("list");
        List<String> roomlist=JSON.parseArray(list,String.class);  
        System.out.println("tuniu");
        PrintWriter out = response.getWriter();
        out.print(searchService.getRoomOnTuniu(name, city, roomlist));
		
	}
	
	
	
	
	@RequestMapping("")
	public ModelAndView index(@RequestParam(value="city",required=false)String city,
			@RequestParam(value="page",required=false)String pageS,
			@RequestParam(value="keyword",required=false)String keyword,
			HttpSession session) {
		int page=1;
		if(pageS==null||pageS.equals("")) {
			page=1;
		}else {
			page=new Integer(pageS);
		}
		
		if(city==null||city.equals(""))
			city="北京";
		else if(!encodeService.isChinese(city)) { 
			city=encodeService.UEncode(city);
		}
		
		if(keyword==null||keyword.equals(""))
			keyword="";
		else if(!encodeService.isChinese(keyword))
			keyword=encodeService.UEncode(keyword);
		
		System.out.println(page+" "+keyword+"城市基准"+city);
		mav=new ModelAndView("/hotel");
		mav.addObject("city",city);
		mav.addObject("page",page);
		mav.addObject("keyword",keyword);
		
		if (session.getAttribute(loginUserKey)!=null) {
			mav.addObject("User",session.getAttribute(loginUserKey));
		}
		
			//String pageT=searchService.getSearchIndexJson(city, page, keyword)[1];
			//session.setAttribute(HotelTPage, pageT);
		
		//mav.addObject("totalPage",session.getAttribute(HotelTPage));
		return mav;
	}
	
	@RequestMapping("getDetail")
	public void ajaxA(HttpSession session,HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        String city=request.getParameter("city");
        int page=new Integer(request.getParameter("page"));
        String keyword=request.getParameter("keyword");
        PrintWriter out = response.getWriter();
        out.print(searchService.getSearchIndexJson(city, page, keyword));
	}
	
	@RequestMapping("getUByName")
	public void gerUrl(HttpSession session,HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String hotelName=request.getParameter("hotel");
        Hotel hotel=hotelService.SelectByName(hotelName);		
				out.print("{\"url\":\""+"hotel/hotelDetail?cid="+hotel.getCtripid()+"&city="+hotel.getCity()+"&name="+hotelName+"\"}");
		
	}
	
	@RequestMapping("mark")
	public void mark(HttpSession session,HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String name=request.getParameter("name");
        if (session.getAttribute(loginUserKey)==null) {
        	out.print("{\"data\":\"请先登录\"}");
		}else {
			Users user=(Users)session.getAttribute(loginUserKey);
			if(hotelService.mark(name,user.getUname()))
				out.print("{\"data\":\"收藏成功！\"}");
			else 
				out.print("{\"data\":\"您已经收藏过这家酒店！\"}");
		}
	}
	
}
