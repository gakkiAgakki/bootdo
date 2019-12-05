package com.bootdo.blog.service;

import com.bootdo.blog.domain.RysqDO;

import java.util.List;
import java.util.Map;

/**
 * 入园申请表
 * 
 * @author null
 * @email null@null.com
 * @date 2019-10-28 11:21:10
 */
public interface RysqService {
	
	RysqDO get(Long cid);
	
	List<RysqDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(RysqDO rysq);
	
	int update(RysqDO rysq);
	
	int remove(Long cid);
	
	int batchRemove(Long[] cids);

    void batchShenHe(Long[] cids);

	void batchQuxiaoshenhe(Long[] cids);
}
