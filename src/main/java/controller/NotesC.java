package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;


import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import entity.Notes;
import entity.Users;
import services.EncodeService;
import services.FileService;
import services.NotesService;
import services.UsersService;

@Controller
@RequestMapping("notes")
public class NotesC {
	@Autowired
	private EncodeService encodeService;
	@Autowired
	private FileService fileService;
	@Autowired
	private NotesService notesService;
	@Autowired 
	private UsersService usersService;
	
	private static String loginUserKey = "User78cd";
	private ModelAndView mav=null;
	
	
	
	@RequestMapping("")
	public ModelAndView indexF(HttpSession session,HttpServletRequest request) {
		
		System.out.println("游记"+session.getAttribute(loginUserKey));
		mav = new ModelAndView("notes");	
		String pageS=request.getParameter("page");
		String action=request.getParameter("action");
		List<Notes> notess;
		int page=0;
		if(pageS==null||pageS.equals("")) {
			page=1;
		}else {
			page=new Integer(pageS);
		}
		 double a = (double)notesService.getCount()/10;		
		 int pageNum = (int) Math.ceil(a);	
		 
		 mav.addObject("pageNum", pageNum);				//页码总数		
		 mav.addObject("page",page);		//当前页码
		 mav.addObject("action",action);		//当前页码
		 //查询符合条件的十个
		 if(action!=null&&action.equals("h")) {
			 notess=notesService.querysByLimitHot(page);
		 }else {
			 notess=notesService.querysByLimit(page);			//查询页码对应list
		 }
		 session.setAttribute("notess", notess);
		if (session.getAttribute(loginUserKey)!=null) {
			mav.addObject("User",session.getAttribute(loginUserKey));
		}
		return mav;
	}
	
	@RequestMapping("/ajaxEditor")
	public ModelAndView Najax2(HttpSession session,HttpServletRequest request) {
			mav = new ModelAndView("notes/editor");
  			return mav;
	}
	
	@RequestMapping("/ajaxNotes")
	public void Najax(HttpSession session,HttpServletRequest request,HttpServletResponse response) throws IOException {
			response.setContentType("application/json;charset=UTF-8");
	        response.setHeader("Pragma", "No-cache");
	        response.setHeader("Cache-Control", "no-cache");
	        response.setCharacterEncoding("UTF-8");
	        PrintWriter out = response.getWriter();
	        Notes n=null;
			int num=new Integer(request.getParameter("num"));
			List<Notes> notess=(List<Notes>)session.getAttribute("notess");
			if(notess.size()>num) {
				n=notess.get(num);
				String text=fileService.readToString(request.getSession().getServletContext().getRealPath(n.getUri()+".jsp"));
				System.out.println(request.getSession().getServletContext().getRealPath(n.getUri()+".jsp"));
				String s=encodeService.getChinese(text,250);
				String img=encodeService.getImg(text);
				String title=n.getTitle();
				String author=n.getAuthor();
				String uri=n.getUri();
				long hot=n.getHot();
		        out.print("{\"text\":\""+s+"\",\"imgsrc\":\""+img+"\",\"title\":\""+title+"\",\"author\":\""+author+"\",\"uri\":\""+uri+"\""
		        		+  ",\"hot\":\""+hot+"\"}");
				//out.print("{'text':'asduja','imgsrc':'asad'}");
				}
			else {
				out.print("{\"text\":\" \",\"imgsrc\":\"  \"}");
		        
			}
			out.flush();
	        out.close();
	}
	
	@RequestMapping("/ajaxNotesD")
	public ModelAndView Najax1(HttpSession session,HttpServletRequest request) {
			String uri=request.getParameter("uri");
			notesService.hotPlus(uri);
			System.out.println(uri);
			mav = new ModelAndView(uri);
  			return mav;	
	}
	
	@RequestMapping("/upload")
	 public ModelAndView uploadNotes(@RequestParam(value="text",required=false)String text,
			 @RequestParam(value="title",required=false)String title,
			 HttpSession session,HttpServletRequest request) throws UnsupportedEncodingException{
		Users u=null;
		if (session.getAttribute(loginUserKey)!=null) {
			mav = new ModelAndView("success");
			mav.addObject("User",session.getAttribute(loginUserKey));
			mav.addObject("url","/notes");
			mav.addObject("Message","游记上传成功");
			u=(Users)session.getAttribute(loginUserKey);
		}else {
			mav = new ModelAndView("success");
			mav.addObject("url","/index");
			mav.addObject("Message","请先进行登录操作");
		}
			text=encodeService.UEncode(text);
			title=encodeService.UEncode(title);
			Timestamp ts=fileService.getTimestampNow();
			String path=fileService.FileOutput(request.getSession().getServletContext().getRealPath("WEB-INF/notes"), ".jsp", text,u.getUname(),title,ts);
			String uri=fileService.getUri(path);
			notesService.addNotes(title, u.getUname(), uri, ts);
			return mav;
	}
	
	@RequestMapping("/upload/login")
	 public ModelAndView uploadNotes(@RequestParam(value="name",required=false)String uname,
			 @RequestParam(value="password",required=false)String password,
			 @RequestParam(value="text",required=false)String text,
			 HttpSession session) throws UnsupportedEncodingException{
			uname=encodeService.UEncode(uname);
			text=encodeService.UEncode(text);
			System.out.println("login了");
			if(usersService.CheckLogin(uname, password)){
				session.setAttribute(loginUserKey,usersService.getByName(uname));
				mav=new ModelAndView("/notes/editor");
			}else {
				}
			return mav;
	}
	
	@RequestMapping("picUpload")			//用于图片上传服务器图片库
	 public void uploadPicture(PrintWriter out,@RequestParam(value="uploadPic",required=false)MultipartFile file,
			    HttpServletRequest request){
			        File targetFile=null;
			        int code=1;
			        String fileName=file.getOriginalFilename();//获取文件名加后缀
			        if(fileName!=null&&fileName!=""){   
			            String path = request.getSession().getServletContext().getRealPath("notes/upload/imgs/"); //文件存储位置
			            String fileF = fileName.substring(fileName.lastIndexOf("."), fileName.length());//文件后缀
			            int result=0;
			            fileName=encodeService.getData(0,0)+"_"+new Random().nextInt(1000)+fileF;//新的文件名
			            String data=encodeService.getData(1,0);
			            path=path+data;
			            String returnPath="upload/imgs/"+data+"/"+fileName;
			            System.out.println(path);
			            //先判断文件是否存在
			            File file1 =new File(path); 
			            //如果文件夹不存在则创建    
			            if(!file1 .exists()  && !file1 .isDirectory()){       
			                file1 .mkdir();  
			            }
			            targetFile = new File(file1, fileName);
//			          targetFile = new File(path, fileName);
			            try {
			                file.transferTo(targetFile);
//			              msg=returnUrl+fileName;
			               // msg=returnUrl+"2011111/"+fileName;
			                code=0;
			            } catch (Exception e) {
			                e.printStackTrace();
			            }
			            out.write("{\"errno\": "+result+",\"data\":[\"/notes/" +returnPath+ "\"]}");
			            System.out.println("{\"errno\": "+result+",\"data\": [\"" +returnPath+ "\"]}");
			        }
			    }
			}
	
	

