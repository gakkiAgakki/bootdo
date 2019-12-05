package com.bootdo.xypjtx.service.impl;

import com.bootdo.xypjtx.dao.XyggDao;
import com.bootdo.xypjtx.domain.XyggDO;
import com.bootdo.xypjtx.service.XyggService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



@Service
public class XyggServiceImpl implements XyggService {
	@Autowired
	private XyggDao xyggDao;
	
	@Override
	public XyggDO get(Long cid){
		return xyggDao.get(cid);
	}
	
	@Override
	public List<XyggDO> list(Map<String, Object> map){
		return xyggDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return xyggDao.count(map);
	}
	
	@Override
	public int save(XyggDO xygg){
		return xyggDao.save(xygg);
	}
	
	@Override
	public int update(XyggDO xygg){
		return xyggDao.update(xygg);
	}
	
	@Override
	public int remove(Long cid){
		return xyggDao.remove(cid);
	}
	
	@Override
	public int batchRemove(Long[] cids){
		return xyggDao.batchRemove(cids);
	}

	@Override
	public int batchShenHe(Long[] cids) {
		return xyggDao.batchShenHe(cids);
	}

	@Override
	public int batchQuxiaoshenhe(Long[] cids) {
		return xyggDao.batchQuxiaoshenhe(cids);
	}

}
