package servicesImpl;

import services.SpiderService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import dao.SpiderDao;
import entity.Spider;

@Service
public class SpiderServiceImpl implements SpiderService{

	
	@Autowired
	private SpiderDao spiderDao;
	
	@Override
	public void addNew(String name,String url) {
		spiderDao.addNew(name,url);
	}
	@Override
	public  List<Spider> getList(){
		return spiderDao.queryAll();
	}
	
	
	
}
