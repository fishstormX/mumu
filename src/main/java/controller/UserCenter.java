package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import entity.Users;
import services.EncodeService;
import services.UsersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
@Controller
@RequestMapping("userCenter")
public class UserCenter {
	@Autowired 
	private UsersService usersService;
	@Autowired
	private EncodeService encodeService;
	
	private static String loginUserKey = "User78cd";
	private ModelAndView mav=null;

	
	@RequestMapping("")
	public ModelAndView check(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {
		Users u=(Users)session.getAttribute(loginUserKey);
		if(u==null) {
			System.out.println("用户中心非法操作Error101");
			mav = new ModelAndView("redirect:/index");
		}else {
			u=usersService.getByName(u.getUname());
			session.setAttribute(loginUserKey, u);
			System.out.println(u+"用户中心");
			mav = new ModelAndView("userCenter");
			mav.addObject("User",u);
		}
			return mav;
	}
	
	@RequestMapping("/getDetail")
	public ModelAndView getDetail(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {
		Users u=(Users)session.getAttribute(loginUserKey);
		System.out.println(request.getParameter("action")+"  "+request.getParameter("name"));
		if(u==null||!u.getUname().equals(request.getParameter("name"))) {
			System.out.println("非法操作Error102");
			mav = new ModelAndView("");
		}else {
			System.out.println(u+"访问"+request.getParameter("action"));
			mav = new ModelAndView("WEB-INF/pages/"+request.getParameter("action"));
			mav.addObject("User",u);
		}
  			return mav;
	}
	
	@RequestMapping("/MessageChange")
	public ModelAndView changeMessage(HttpServletRequest request, HttpServletResponse response,HttpSession session,
			@RequestParam(value="hw",required=false)String city,
			@RequestParam(value="ge",required=false)String gender,
			@RequestParam(value="tele",required=false)String tele) throws IOException {
			Users u=(Users)session.getAttribute(loginUserKey);
			city=encodeService.UEncode(city);
			gender=encodeService.UEncode(gender);
		System.out.println(city);
		if(u==null) {
			System.out.println("非法操作Error103");
			mav = new ModelAndView("");
		}else {
			System.out.println("修改信息"+city+gender+tele);
			usersService.updateMessage(u.getUname(), city, gender, tele);
			mav = new ModelAndView("success");
			mav.addObject("url","userCenter");
			mav.addObject("User",u);
			mav.addObject("Message","修改个人信息成功！");
		}
			return mav;
	}
	
	@RequestMapping("/PasswordChange")
	public ModelAndView changePassword(HttpServletRequest request, HttpServletResponse response,HttpSession session,
			@RequestParam(value="name",required=false)String name,
			@RequestParam(value="password2",required=false)String password) throws IOException {
			Users u=(Users)session.getAttribute(loginUserKey);
		if(u==null||!u.getUname().equals(name)) {
			System.out.println(u.getUname()+"  "+name);
			System.out.println("非法操作Error104");
			mav = new ModelAndView("");
		}else {
			usersService.updatePassword(name, password);
			mav = new ModelAndView("success");
			mav.addObject("url","userCenter");
			mav.addObject("User",u);
			mav.addObject("Message","修改密码成功！");
		} 
			return mav;
	}
}
