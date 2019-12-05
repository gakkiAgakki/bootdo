package com.bootdo.common.dao;

import com.bootdo.common.domain.LianjieDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 相关链接
 * @author null
 * @email null@null.com
 * @date 2019-10-17 18:23:25
 */
@Mapper
public interface LianjieDao {

	LianjieDO get(Long id);
	
	List<LianjieDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(LianjieDO lianjie);
	
	int update(LianjieDO lianjie);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
