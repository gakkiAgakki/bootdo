package com.bootdo.blog.dao;

import com.bootdo.blog.domain.RysqDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 入园申请表
 * @author null
 * @email null@null.com
 * @date 2019-10-28 11:21:10
 */
@Mapper
public interface RysqDao {

	RysqDO get(Long cid);
	
	List<RysqDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(RysqDO rysq);
	
	int update(RysqDO rysq);
	
	int remove(Long cid);
	
	int batchRemove(Long[] cids);

    void batchShenhe(Long[] cids);

	void batchQuxiaoshenhe(Long[] cids);
}
