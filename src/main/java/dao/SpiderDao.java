package dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import entity.Hotel;
import entity.Spider;

public interface SpiderDao {
	 void addNew(@Param("name")String name,@Param("url")String url);
	 List<Spider> queryAll();
}
