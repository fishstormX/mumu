package entity;

public class Citylist {
		private int city_id;
		private String cityName;
		private String province;
		private int cid;
		private int tid;
		private String yid;				//ǰ0վλ
		private int lid;
		private String cityNameEn;
		public int getCity_id() {
			return city_id;
		}
		public void setCity_id(int city_id) {
			this.city_id = city_id;
		}
		public String getCityName() {
			return cityName;
		}
		public void setCityName(String cityName) {
			this.cityName = cityName;
		}
		public String getProvince() {
			return province;
		}
		public void setProvince(String province) {
			this.province = province;
		}
		public int getCid() {
			return cid;
		}
		public void setCid(int cid) {
			this.cid = cid;
		}
		public int getTid() {
			return tid;
		}
		public void setTid(int tid) {
			this.tid = tid;
		}
		public String getYid() {
			return yid;
		}
		public void setYid(String yid) {
			this.yid = yid;
		}
		public int getLid() {
			return lid;
		}
		public void setLid(int lid) {
			this.lid = lid;
		}
		public String getCityNameEn() {
			return cityNameEn;
		}
		public void setCityNameEn(String cityNameEn) {
			this.cityNameEn = cityNameEn;
		}
		@Override
		public String toString() {
			return "Citylist [city_id=" + city_id + ", cityName=" + cityName + ", province=" + province + ", cid=" + cid
					+ ", tid=" + tid + ", yid=" + yid + ", lid=" + lid + ", cityNameEn=" + cityNameEn + "]";
		}
		
}
