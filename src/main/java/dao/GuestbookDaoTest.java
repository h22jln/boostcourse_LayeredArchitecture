package dao;

import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.ApplicationConfig;
import dto.Guestbook;
import dto.Log;

public class GuestbookDaoTest {

	public static void main(String[] args) {
		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		GuestDao dao = ac.getBean(GuestDao.class);
		
		LogDao logDao = ac.getBean(LogDao.class);
		Log log = new Log();
		log.setIp("127.0.0.1");
		log.setMethod("insert");
		log.setRegdate(new Date());
		logDao.insert(log);

	}

}
