package com.bootdo.common.service.impl;

import com.bootdo.common.dao.LianjieDao;
import com.bootdo.common.domain.LianjieDO;
import com.bootdo.common.service.LianjieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



@Service
public class LianjieServiceImpl implements LianjieService {
	@Autowired
	private LianjieDao lianjieDao;
	
	@Override
	public LianjieDO get(Long id){
		return lianjieDao.get(id);
	}
	
	@Override
	public List<LianjieDO> list(Map<String, Object> map){
		return lianjieDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return lianjieDao.count(map);
	}
	
	@Override
	public int save(LianjieDO lianjie){
		return lianjieDao.save(lianjie);
	}
	
	@Override
	public int update(LianjieDO lianjie){
		return lianjieDao.update(lianjie);
	}
	
	@Override
	public int remove(Long id){
		return lianjieDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return lianjieDao.batchRemove(ids);
	}
	
}
