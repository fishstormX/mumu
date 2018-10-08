package services;

public interface EncodeService {
		public String UEncode(String str);
		public boolean Telecode(String phone);
		public String getData(int i,int days);
		public String getChinese(String text,int num);
		public String getImg(String text);
		public String unicode2String(String unicode);
		public boolean isChinese(String text);
		public float getSimilarityRatio(String str, String target);
}
