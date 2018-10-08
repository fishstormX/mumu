package dao;

import entity.Citylist;

public interface CitylistDao {
		void addOne(String cityName,String province,int cid,int tid
				,int yid,int lid,String cityNameEn);
		void upDateCidBycityName(String cityName,int cid);
		void upDateTidBycityName(String cityName,int tid);
		void upDateYidBycityNameEn(String cityName,String yid);
		void addLessOne(String cityName,String cityNameEn);
		int ifExist(String cityNameEn);
		Citylist getByName(String cityName);
}
