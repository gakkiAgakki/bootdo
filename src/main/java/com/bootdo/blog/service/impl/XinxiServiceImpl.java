package com.bootdo.blog.service.impl;

import com.bootdo.blog.dao.XinxiDao;
import com.bootdo.blog.domain.XinxiDO;
import com.bootdo.blog.service.XinxiService;
import com.bootdo.common.domain.FenleiDO;
import com.bootdo.common.domain.MokuaiDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;



@Service
public class XinxiServiceImpl implements XinxiService {
	@Autowired
	private XinxiDao xinxiDao;
	
	@Override
	public XinxiDO get(Long cid){
		return xinxiDao.get(cid);
	}
	
	@Override
	public List<XinxiDO> list(Map<String, Object> map){
		return xinxiDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return xinxiDao.count(map);
	}
	
	@Override
	public int save(XinxiDO xinxi){
		return xinxiDao.save(xinxi);
	}
	
	@Override
	public int update(XinxiDO xinxi){
		return xinxiDao.update(xinxi);
	}
	
	@Override
	public int remove(Long cid){
		return xinxiDao.remove(cid);
	}
	
	@Override
	public int batchRemove(Long[] cids){
		return xinxiDao.batchRemove(cids);
	}

	@Override
	public List<MokuaiDO> getMokuai() {
		return xinxiDao.getMokuai();
	}

	@Override
	public List<FenleiDO> getFenlei(BigInteger parentId) {
		return xinxiDao.getFenlei(parentId);
	}

	@Override
	public int batchShenHe(Long[] cids) {
		return xinxiDao.batchShenHe(cids);
	}

	@Override
	public int batchQuxiaoshenhe(Long[] cids) {
		return xinxiDao.batchQuxiaoshenhe(cids);
	}

	@Override
	public List<String> getMenuPerms(Long userId) {
		return xinxiDao.getMenuPerms(userId);
	}
	@Override
	public int batchZhiding(Long[] cids) {
		return xinxiDao.batchZhiding(cids);
	}

	@Override
	public int batchQuxiaoZhiding(Long[] cids) {
		return xinxiDao.batchQuxiaoZhiding(cids);
	}

}
