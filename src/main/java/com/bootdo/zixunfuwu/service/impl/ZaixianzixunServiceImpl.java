package com.bootdo.zixunfuwu.service.impl;

import com.bootdo.zixunfuwu.dao.ZaixianzixunDao;
import com.bootdo.zixunfuwu.domain.ZaixianzixunDO;
import com.bootdo.zixunfuwu.service.ZaixianzixunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



@Service
public class ZaixianzixunServiceImpl implements ZaixianzixunService {
	@Autowired
	private ZaixianzixunDao zaixianzixunDao;
	
	@Override
	public ZaixianzixunDO get(Long id){
		return zaixianzixunDao.get(id);
	}
	
	@Override
	public List<ZaixianzixunDO> list(Map<String, Object> map){
		return zaixianzixunDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return zaixianzixunDao.count(map);
	}
	
	@Override
	public int save(ZaixianzixunDO zaixianzixun){
		return zaixianzixunDao.save(zaixianzixun);
	}
	
	@Override
	public int update(ZaixianzixunDO zaixianzixun){
		return zaixianzixunDao.update(zaixianzixun);
	}
	
	@Override
	public int remove(Long id){
		return zaixianzixunDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return zaixianzixunDao.batchRemove(ids);
	}

	@Override
	public int batchShenHe(Long[] ids) {
		return zaixianzixunDao.batchShenHe(ids);
	}

	@Override
	public int batchQuxiaoshenhe(Long[] ids) {
		return zaixianzixunDao.batchQuxiaoshenhe(ids);
	}

}
