package com.bootdo.qzgs.service.impl;

import com.bootdo.qzgs.dao.GsqdDao;
import com.bootdo.qzgs.domain.GsqdDO;
import com.bootdo.qzgs.domain.LeibeidanweiDO;
import com.bootdo.qzgs.service.GsqdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



@Service
public class GsqdServiceImpl implements GsqdService {
	@Autowired
	private GsqdDao gsqdDao;
	
	@Override
	public GsqdDO get(Long id){
		return gsqdDao.get(id);
	}
	
	@Override
	public List<GsqdDO> list(Map<String, Object> map){
		return gsqdDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return gsqdDao.count(map);
	}
	
	@Override
	public int save(GsqdDO gsqd){
		return gsqdDao.save(gsqd);
	}
	
	@Override
	public int update(GsqdDO gsqd){
		return gsqdDao.update(gsqd);
	}
	
	@Override
	public int remove(Long id){
		return gsqdDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return gsqdDao.batchRemove(ids);
	}

	@Override
	public List<LeibeidanweiDO> getLbOrBm() {
		return gsqdDao.getLbOrBm();
	}

}
