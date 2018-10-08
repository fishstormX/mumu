package dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import entity.Hotel;

public interface HotelDao {
	int queryCByName(String hname);
	Hotel queryByName(String hname);
	List<Hotel> queryAll(@Param("offset") int offset, @Param("limit") int limit);

	void addHotel(String hname,String address,String city,String img,String ctripid,String introduce,String grade,String tip1,String tip2);
	
	void addHotelA(String hname, String address, long hotel_id, String city, String room, String tele, String ctripid,
			String tuniuid, String lvmamaid, String yilongid);
	
	void updateByName(String hname,String ctripid,String tuniuid, String lvmamaid, String yilongid);
	void updateYidByName(String hname,String yilongid);
	void updateTidByName(String hname,String tuniuid);
}
