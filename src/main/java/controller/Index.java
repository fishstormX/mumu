package controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.EncodeService;
import services.UsersService;

@Controller
@RequestMapping("index")
public class Index {
	@Autowired 
	private UsersService usersService;
	@Autowired
	private EncodeService encodeService;
	
	private static String loginUserKey = "User78cd";
	private ModelAndView mav=null;
	
	@RequestMapping("")
	public ModelAndView indexF(HttpServletRequest request,HttpSession session) {
		System.out.println("用户访问操作"+session.getAttribute(loginUserKey));
		mav = new ModelAndView("index");
		if (session.getAttribute(loginUserKey)!=null) {
			mav.addObject("User",session.getAttribute(loginUserKey));
		}
		mav.addObject("message",request.getParameter("message"));
		return mav;
	}
	
	
	
	@RequestMapping("/login")
	public ModelAndView indexLogin(@RequestParam(value="name",required=false)String uname,@RequestParam(value="password",required=false)String password,HttpSession session) {
		System.out.println("登录操作");
		uname=encodeService.UEncode(uname);
		mav = new ModelAndView("redirect:/index");
		if(usersService.CheckLogin(uname, password)){
			mav.addObject("User",usersService.getByName(uname));
			session.setAttribute(loginUserKey,usersService.getByName(uname));
		}else {
			mav.addObject("message", "false");	
		}
		return mav;
	}
	
	@RequestMapping("/logout")
	public String indexLogout(HttpSession session) {
		System.out.println("登出操作");
		System.out.println(session.getAttribute("username"));
		session.removeAttribute(loginUserKey);
		return "redirect:/";
	}
}
