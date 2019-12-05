package com.bootdo.common.service.impl;

import com.bootdo.common.dao.MokuaiDao;
import com.bootdo.common.domain.MokuaiDO;
import com.bootdo.common.service.MokuaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



@Service
public class MokuaiServiceImpl implements MokuaiService {
	@Autowired
	private MokuaiDao mokuaiDao;
	
	@Override
	public MokuaiDO get(Long id){
		return mokuaiDao.get(id);
	}
	
	@Override
	public List<MokuaiDO> list(Map<String, Object> map){
		return mokuaiDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return mokuaiDao.count(map);
	}
	
	@Override
	public int save(MokuaiDO mokuai){
		return mokuaiDao.save(mokuai);
	}
	
	@Override
	public int update(MokuaiDO mokuai){
		return mokuaiDao.update(mokuai);
	}
	
	@Override
	public int remove(Long id){
		return mokuaiDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return mokuaiDao.batchRemove(ids);
	}
	
}
