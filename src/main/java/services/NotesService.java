package services;

import java.sql.Timestamp;
import java.util.List;

import entity.Notes;

public interface NotesService {
	Notes queryByTitle(String title);
	List<Notes> querysByLimit(int page);
	List<Notes> querysByLimitHot(int page);
	void addNotes(String title,String author,String uri,Timestamp date);
	void hotPlus(String title);
	int getCount();
}
