package com.bootdo.common.service.impl;

import com.bootdo.common.dao.FenleiDao;
import com.bootdo.common.domain.FenleiDO;
import com.bootdo.common.domain.MokuaiDO;
import com.bootdo.common.service.FenleiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



@Service
public class FenleiServiceImpl implements FenleiService {
	@Autowired
	private FenleiDao fenleiDao;
	
	@Override
	public FenleiDO get(Long id){
		return fenleiDao.get(id);
	}
	
	@Override
	public List<FenleiDO> list(Map<String, Object> map){
		return fenleiDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return fenleiDao.count(map);
	}
	
	@Override
	public int save(FenleiDO fenlei){
		return fenleiDao.save(fenlei);
	}
	
	@Override
	public int update(FenleiDO fenlei){
		return fenleiDao.update(fenlei);
	}
	
	@Override
	public int remove(Long id){
		return fenleiDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return fenleiDao.batchRemove(ids);
	}

	@Override
	public List<MokuaiDO> getMokuai() {
		return fenleiDao.getMokuai();
	}

}
