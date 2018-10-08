package servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.SpiderDao;
import dao.UsersDao;
import entity.Users;
import services.UsersService;
@Service
public class UsersServiceImpl implements UsersService{
	@Autowired
	private UsersDao usersDao;
	
	@Override
	public boolean CheckLogin(String uname, String password) {			//用户登录校验
		Users user=usersDao.queryByName(uname);
		System.out.println(uname);
		if(user!=null&&uname.equals(user.getUname())&&password.equals(user.getPassword()))
			return true;
		else
			return false;
	}

	@Override
	public boolean IsNameExists(String uname) {
		Users user=usersDao.queryByName(uname);
		if(user==null)
			return false;
		else 
			return true;
	}

	@Override
	public void addNew(String uname, String password, String hw, String gender, String tele) {
		usersDao.insertOne(uname, password, hw, gender, tele);
	}

	@Override
	public Users getByName(String uname) {
		Users user=usersDao.queryByName(uname);
		return user;
	}

	@Override
	public boolean updatePassword(String uname, String password) {
			usersDao.updatePasswordByName(uname, password);
			return true;
	}

	@Override
	public void updateMessage(String uname, String city, String gender, String tele) {
		usersDao.updateMessageByName(uname,city,gender,tele);
	}
	
}
