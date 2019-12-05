package com.bootdo.blog.service.impl;

import com.bootdo.blog.dao.RysqDao;
import com.bootdo.blog.domain.RysqDO;
import com.bootdo.blog.service.RysqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



@Service
public class RysqServiceImpl implements RysqService {
	@Autowired
	private RysqDao rysqDao;
	
	@Override
	public RysqDO get(Long cid){
		return rysqDao.get(cid);
	}
	
	@Override
	public List<RysqDO> list(Map<String, Object> map){
		return rysqDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return rysqDao.count(map);
	}
	
	@Override
	public int save(RysqDO rysq){
		return rysqDao.save(rysq);
	}
	
	@Override
	public int update(RysqDO rysq){
		return rysqDao.update(rysq);
	}
	
	@Override
	public int remove(Long cid){
		return rysqDao.remove(cid);
	}
	
	@Override
	public int batchRemove(Long[] cids){
		return rysqDao.batchRemove(cids);
	}

	@Override
	public void batchShenHe(Long[] cids) {
		rysqDao.batchShenhe(cids);
	}

	@Override
	public void batchQuxiaoshenhe(Long[] cids) {
		rysqDao.batchQuxiaoshenhe(cids);
	}

}
