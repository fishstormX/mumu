package services;

import entity.Users;

public interface UsersService {
	boolean CheckLogin(String uname,String password);
	boolean IsNameExists(String uname);
	void addNew(String uname,String password,String city,String gender,String tele);
	Users getByName(String uname);
	boolean updatePassword(String uname,String password);
	void updateMessage(String uname,String city,String gender,String tele);
}
