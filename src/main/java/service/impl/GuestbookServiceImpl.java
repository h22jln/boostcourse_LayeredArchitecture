package service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.GuestDao;
import dao.LogDao;
import dto.Guestbook;
import dto.Log;
import service.GuestbookService;

@Service
public class GuestbookServiceImpl implements GuestbookService{
	@Autowired
	GuestDao guestDao;
	
	@Autowired
	LogDao logDao;

	@Override
	@Transactional //read only
	public List<Guestbook> getGuestbooks(Integer start) {
		// 메서드가 수행되면 guestbook 목록 가져옴
		List<Guestbook>list = guestDao.selectAll(start, LIMIT);
		return list;
	}

	@Override
	@Transactional(readOnly = false)
	public int deleteGuestbook(Long id, String ip) {
		// id값 삭제
		int deleteCount = guestDao.deleteById(id);
		Log log = new Log();
		log.setIp(ip);
		log.setMethod("delete");
		log.setRegdate(new Date());
		logDao.insert(log);
		return deleteCount;
	}

	@Override
	@Transactional(readOnly = false)
	public Guestbook addGuestbook(Guestbook guestbook, String ip) {
		// 데이터 입력
		guestbook.setRegdate(new Date());
		Long id = guestDao.insert(guestbook);
		guestbook.setId(id);
		
		Log log = new Log();
		log.setIp(ip);
		log.setMethod("insert");
		log.setRegdate(new Date());
		logDao.insert(log);
		
		return guestbook;
	}

	@Override
	public int getCount() {
		// 전체 몇 건인지
		return guestDao.selectCount();
	}
	
	
}
