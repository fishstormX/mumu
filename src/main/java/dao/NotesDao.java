package dao;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Param;


import entity.Notes;

public interface NotesDao {
	Notes queryByTitle(String title);
	void hotPlus(String title);
	void addNotes(String title,String author,String uri,Timestamp date);
	int countAll();
	List<Notes> querysByLimit(int start,int end);
	List<Notes> querysByLimitHot(int start,int end);
}
