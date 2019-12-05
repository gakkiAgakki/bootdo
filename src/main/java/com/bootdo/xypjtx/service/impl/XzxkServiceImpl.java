package com.bootdo.xypjtx.service.impl;

import com.bootdo.xypjtx.dao.XzxkDao;
import com.bootdo.xypjtx.domain.XzxkDO;
import com.bootdo.xypjtx.service.XzxkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



@Service
public class XzxkServiceImpl implements XzxkService {
	@Autowired
	private XzxkDao xzxkDao;
	
	@Override
	public XzxkDO get(Long xid){
		return xzxkDao.get(xid);
	}
	
	@Override
	public List<XzxkDO> list(Map<String, Object> map){
		return xzxkDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return xzxkDao.count(map);
	}
	
	@Override
	public int save(XzxkDO xzxk){
		return xzxkDao.save(xzxk);
	}
	
	@Override
	public int update(XzxkDO xzxk){
		return xzxkDao.update(xzxk);
	}
	
	@Override
	public int remove(Long xid){
		return xzxkDao.remove(xid);
	}
	
	@Override
	public int batchRemove(Long[] xids){
		return xzxkDao.batchRemove(xids);
	}
	
}
