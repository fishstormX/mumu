package services;

import java.util.List;

import org.springframework.stereotype.Service;

import entity.Hotel;

public interface HotelService {
	
	
	List<Hotel> getList();
	void addNew0(String hname, String adress, long hotel_id, String city, String room, String tele, String ctripid,
			String tuniuid, String lvmamaid, String yilongid);
	Hotel SelectByName(String name);
	void updateByName(String hname,String ctripid,String tuniuid, String lvmamaid, String yilongid);
	
	String getHotelDetail(String Hname,String cid,String city);
	
	boolean mark(String name,String user);
}
