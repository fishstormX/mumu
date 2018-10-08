package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import services.AjaxService;
import services.EncodeService;
import services.UsersService;

@Controller
@RequestMapping("")
public class RegisterAjax {
	@Autowired 
	private UsersService usersService;
	@Autowired
	private EncodeService encodeService;
	@Autowired
	private AjaxService ajaxService;
	
	@RequestMapping("register")
	public String Tregister(){
		return "register";
	}
	
	@RequestMapping("register/s")
	public String GetRegister(@RequestParam(value="uname",required=false)String uname,
			@RequestParam(value="password",required=false)String password,
			@RequestParam(value="hw_2",required=false)String city,
			@RequestParam(value="ge",required=false)String gender,
			@RequestParam(value="tele",required=false)String tele) throws IOException {
		uname=encodeService.UEncode(uname);
		city=encodeService.UEncode(city);
		gender=encodeService.UEncode(gender);
		usersService.addNew(uname, password, city, gender, tele);
		return "redirect:/index";
	}
	
	@RequestMapping("ajax")
	public void checkAjaxRJson(PrintWriter out,HttpServletRequest request, HttpServletResponse response) throws IOException {
		int iTele=ajaxService.checkRegisterTele(request.getParameter("tele"));
		int iName=ajaxService.checkRegisterName(request.getParameter("name"));
		int iPass=ajaxService.checkRegisterP(request.getParameter("p1"),request.getParameter("p2"));
		System.out.println(iTele+"");
		response.setContentType("application/json");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        out = response.getWriter();
        out.print("{\"name\":\""+iName+"\",\"pass\":\""+iPass+"\",\"tele\":\""+iTele+"\"}");
        out.flush();
        out.close();
	}
	
	@RequestMapping("ajaxt")
	public void checkTAjaxRJson(PrintWriter out,HttpServletRequest request, HttpServletResponse response) throws IOException {
		int iTele=ajaxService.checkRegisterTele(request.getParameter("tele"));
		response.setContentType("application/json");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        out = response.getWriter();
        out.print("{\"tele\":\""+iTele+"\"}");
        out.flush();
        out.close();
	}
	
	@RequestMapping("ajaxp")
	public void checkPAjaxRJson(PrintWriter out,HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {
		boolean b=usersService.CheckLogin(request.getParameter("uname"), request.getParameter("password0"));
		response.setContentType("application/json");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        out = response.getWriter();
        out.print("{\"flag\":\""+b+"\"}");
        out.flush();
        out.close();
	}
}
