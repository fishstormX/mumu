package dao;

import entity.Users;

public interface UsersDao {
	Users queryByName(String uname);
	void insertOne(String uname,String password,String city,String gender,String tele);
	void updatePasswordByName(String uname,String password);
	void updateMessageByName(String uname,String city,String gender,String tele);
	String getMarkByName(String uname);
	void setMarkByName(String uname,String mark);
}
