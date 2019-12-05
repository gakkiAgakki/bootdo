package com.bootdo.qzgs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.qzgs.dao.LeibeidanweiDao;
import com.bootdo.qzgs.domain.LeibeidanweiDO;
import com.bootdo.qzgs.service.LeibeidanweiService;



@Service
public class LeibeidanweiServiceImpl implements LeibeidanweiService {
	@Autowired
	private LeibeidanweiDao leibeidanweiDao;
	
	@Override
	public LeibeidanweiDO get(Long id){
		return leibeidanweiDao.get(id);
	}
	
	@Override
	public List<LeibeidanweiDO> list(Map<String, Object> map){
		return leibeidanweiDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return leibeidanweiDao.count(map);
	}
	
	@Override
	public int save(LeibeidanweiDO leibeidanwei){
		return leibeidanweiDao.save(leibeidanwei);
	}
	
	@Override
	public int update(LeibeidanweiDO leibeidanwei){
		return leibeidanweiDao.update(leibeidanwei);
	}
	
	@Override
	public int remove(Long id){
		return leibeidanweiDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return leibeidanweiDao.batchRemove(ids);
	}
	
}
