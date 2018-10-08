package entity;

public class Hotel {
	/*
	 *@param ∂‘”¶±Ìhotel 
	 * 
	 * */
	public Hotel () {
		
	}
	private String hname;
	private String address;
	private long hotel_id;
	private String city;
	private	String room;
	private String tele;
	private String ctripid;
	private String tuniuid;
	private String lvmamaid;
	private String yilongid;
	private String img;
	private String services;
	private String introduce;
	private String grade;
	private int lprice;
	private String tip1="";
	private String tip2="";
	
	
	
	public String getServices() {
		return services;
	}
	public void setServices(String services) {
		this.services = services;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public String getTele() {
		return tele;
	}
	public void setTele(String tele) {
		this.tele = tele;
	}
	public String getCtripid() {
		return ctripid;
	}
	public void setCtripid(String ctripid) {
		this.ctripid = ctripid;
	}
	public String getTuniuid() {
		return tuniuid;
	}
	public void setTuniuid(String tuniuid) {
		this.tuniuid = tuniuid;
	}
	public String getLvmamaid() {
		return lvmamaid;
	}
	public void setLvmamaid(String lvmamaid) {
		this.lvmamaid = lvmamaid;
	}
	public String getYilongid() {
		return yilongid;
	}
	public void setYilongid(String yilongid) {
		this.yilongid = yilongid;
	}
	public String getHname() {
		return hname;
	}
	public void setHname(String hname) {
		this.hname = hname;
	}
	
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getLprice() {
		return lprice;
	}
	public void setLprice(int lprice) {
		this.lprice = lprice;
	}
	public String getTip1() {
		return tip1;
	}
	public void setTip1(String tip1) {
		this.tip1 = tip1;
	}
	public String getTip2() {
		return tip2;
	}
	public void setTip2(String tip2) {
		this.tip2 = tip2;
	}
	public long getHotel_id() {
		return hotel_id;
	}
	public void setHotel_id(long hotel_id) {
		this.hotel_id = hotel_id;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Override
	public String toString() {
		return "Hotel [hname=" + hname + ", address=" + address + ", hotel_id=" + hotel_id + ", city=" + city + ", room="
				+ room + ", tele=" + tele + ", ctripid=" + ctripid + ", tuniuid=" + tuniuid + ", lvmamaid=" + lvmamaid
				+ ", yilongid=" + yilongid + "]";
	}
	
	
}
