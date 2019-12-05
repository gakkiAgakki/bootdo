package com.bootdo.common.dao;

import com.bootdo.common.domain.MokuaiDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 模块表
 * @author 123
 * @email 1992lcg@163.com
 * @date 2019-10-11 11:29:50
 */
@Mapper
public interface MokuaiDao {

	MokuaiDO get(Long id);
	
	List<MokuaiDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(MokuaiDO mokuai);
	
	int update(MokuaiDO mokuai);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
