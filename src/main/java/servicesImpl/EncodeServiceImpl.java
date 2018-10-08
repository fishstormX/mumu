package servicesImpl;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;
import services.EncodeService;
@Service
public class EncodeServiceImpl implements EncodeService{

	private int compare(String str, String target)
    {
        int d[][];              // 矩阵
        int n = str.length();
        int m = target.length();
        int i;                  // 遍历str的
        int j;                  // 遍历target的
        char ch1;               // str的
        char ch2;               // target的
        int temp;               // 记录相同字符,在某个矩阵位置值的增量,不是0就是1
        if (n == 0) { return m; }
        if (m == 0) { return n; }
        d = new int[n + 1][m + 1];
        for (i = 0; i <= n; i++)
        {                       // 初始化第一列
            d[i][0] = i;
        }


        for (j = 0; j <= m; j++)
        {                       // 初始化第一行
            d[0][j] = j;
        }


        for (i = 1; i <= n; i++)
        {                       // 遍历str
            ch1 = str.charAt(i - 1);
            // 去匹配target
            for (j = 1; j <= m; j++)
            {
                ch2 = target.charAt(j - 1);
                if (ch1 == ch2 || ch1 == ch2+32 || ch1+32 == ch2)
                {
                    temp = 0;
                } else
                {
                    temp = 1;
                }
                // 左边+1,上边+1, 左上角+temp取最小
                d[i][j] = min(d[i - 1][j] + 1, d[i][j - 1] + 1, d[i - 1][j - 1] + temp);
            }
        }
        return d[n][m];
    }


    private int min(int one, int two, int three)
    {
        return (one = one < two ? one : two) < three ? one : three;
    }


    /**
     * 获取两字符串的相似度
     */

    @Override
    public float getSimilarityRatio(String str, String target)
    {
        //去除空白字符、换行、标点符号
        String regex = "[\\pP\\p{Punct}\\s|(|)|·]";
        return 1 - (float) compare(str.replaceAll(regex, ""), target.replaceAll(regex, "")) / Math.max(str.length(), target.length());
    }

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public String UEncode(String str) {
		String s="";
		try {
			s=new String(str.getBytes("iso-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		return s;
	}

	@Override
	public boolean Telecode(String phone) {
		String regex = "^[1][3,4,5,7,8,x][0-9,x]{9}$";
        	return Pattern.matches(regex, phone);
	}

	@Override
	public String getData(int i,int days) {
		Date day=new Date(); 
		SimpleDateFormat df=null;
		switch(i) { 
		case 0:
			df = new SimpleDateFormat("yyyyMMdd_HHmmss"); 
			break;
		case 1:
			df = new SimpleDateFormat("yyyyMMdd_HH");
			break;
		case 2:
			df= new SimpleDateFormat("yyyy-MM-dd");
			break;
		case 3:
			df= new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			cal.setTime(day);
			cal.add(Calendar.DATE, days);
			day=cal.getTime();
		} 
		return df.format(day);
	}

	@Override
	public String getChinese(String text, int num) {
			String reg = "[^\u4e00-\u9fa5]";
			text = text.replaceAll(reg, "");
		if(text.length()<num) {
			return text.substring(0,text.length()-1);
		}else
		return text.substring(0,num);
	}

	@Override
	public String getImg(String text) {
		if(text.indexOf("<img")<0) {
			return "/pics/nophoto.jpg";
		}
			String temp=text.substring(text.indexOf("src="));
			String temp2=temp.substring(5);
	        String result=temp2.substring(0,temp2.indexOf("\""));
		return result;
	}

	@Override
	public String unicode2String(String unicode) {
		int start = 0;     
        StringBuffer buffer = new StringBuffer(unicode);     
        String charStr = "";   
        while (start > -1) {     
            start = buffer.indexOf("\\u",start);     
            charStr = buffer.substring(start+2, start+6);     
            char letter = (char) Integer.parseInt(charStr, 16); // 16进制parse整形字符串
            buffer=buffer.replace(start, start+6,new Character(letter).toString());     
            start=buffer.indexOf("\\u",start+1);      
        }     
        return buffer.toString(); 
	    
	}

	@Override
	public boolean isChinese(String text) {
		 if (text == null) return false;  
	        for (char c : text.toCharArray()) {  
	            if (c >= 0x4E00 &&  c <= 0x9FA5) 
	            	return true;// 有一个中文字符就返回  
	        }  
	        return false;  
	}
	
	

	
}
