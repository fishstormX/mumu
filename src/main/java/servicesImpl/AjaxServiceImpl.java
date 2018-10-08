package servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import services.AjaxService;
import services.EncodeService;
import services.UsersService;

@Service
public class AjaxServiceImpl implements AjaxService{
	@Autowired 
	private UsersService usersService;
	@Autowired
	private EncodeService encodeService;
	
	@Override
	public int checkRegisterName(String name) {
		if(name==""||name==null)
			return 0;			//为空
		else if(usersService.IsNameExists(name))
			return 1;			//用户名存在
		else if(name.length()>10)
			return 2;			//用户名过长
		else
		return -1;
	}

	@Override
	public int checkRegisterTele(String tele) {
		if(tele==""||tele==null)
			return 0;									//号码空
		else if(!encodeService.Telecode(tele))
			return 1;				//号码未正则
		return -1;
	}

	@Override
	public int checkRegisterP(String ps1, String ps2) {
		if(ps1==""||ps1==null)
			return 0;			//首密码空
		else if(ps1.length()>16) 
			return 2;			//密码过长
		else if(!ps1.equals(ps2))
			return 1;			//密码不相同	
		return -1;
	}

	
}
