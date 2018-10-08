package servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.HotelDao;
import dao.UsersDao;
import entity.Hotel;
import services.HotelService;


@Service  
public class HotelServiceImpl implements HotelService {

	

	// ◊¢»ÎService“¿¿µ
	@Autowired
	private HotelDao hotelDao;
	@Autowired
	private UsersDao usersDao;
	
	@Override
	public List<Hotel> getList() {
		return hotelDao.queryAll(0, 1000);
	}
	@Override
	public void addNew0(String hname, String adress, long hotel_id, String city, String room, String tele,
			String ctripid, String tuniuid, String lvmamaid, String yilongid) {
		hotelDao.addHotelA(hname,adress,hotel_id,city,room,tele,ctripid,tuniuid,lvmamaid,yilongid);
	}
	
	@Override
	public void updateByName(String hname, String ctripid, String tuniuid, String lvmamaid, String yilongid) {
		hotelDao.updateByName(hname,ctripid,tuniuid,lvmamaid,yilongid);
	}
	@Override
	public String getHotelDetail(String Hname, String cid, String city) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Hotel SelectByName(String name) {
		return hotelDao.queryByName(name);	
	}
	@Override
	public boolean mark(String name,String user) {
		String mark=usersDao.getMarkByName(user);
		if (mark==null||mark.equals("")) {
			usersDao.setMarkByName(user, name);
			return true;
		}
			
			String hotels[]=mark.split("\\|");
		for(String hotelN:hotels) {
			System.out.println(hotelN+" "+name);
			if(hotelN.equals(name))
				return false;
		}
		mark=mark+"|"+name;
		usersDao.setMarkByName(user, mark);
		return true;
	}

	

}