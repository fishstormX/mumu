package servicesImpl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.NotesDao;
import entity.Notes;
import services.NotesService;

@Service
public class NotesServiceImpl implements NotesService{

	@Autowired
	private NotesDao notesDao;
	
	@Override
	public Notes queryByTitle(String title) {
		return notesDao.queryByTitle(title);
	}

	@Override
	public void addNotes(String title, String author, String uri,Timestamp date) {
		
		notesDao.addNotes(title, author, uri,date);
	}

	@Override
	public int getCount() {
		return notesDao.countAll();
	}

	@Override
	public List<Notes> querysByLimit(int page) {
		return notesDao.querysByLimit((page-1)*10,page*10);
	}

	@Override
	public List<Notes> querysByLimitHot(int page) {
		return notesDao.querysByLimitHot((page-1)*10,page*10);
	}

	@Override
	public void hotPlus(String title) {
		notesDao.hotPlus(title);
	}

}
