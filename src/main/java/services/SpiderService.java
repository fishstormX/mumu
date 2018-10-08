package services;

import java.util.List;

import org.springframework.stereotype.Service;

import entity.Spider;

public interface SpiderService {
	 void addNew(String name,String url);
	 List<Spider> getList();
}
