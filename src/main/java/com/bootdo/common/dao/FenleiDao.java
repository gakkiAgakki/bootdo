package com.bootdo.common.dao;

import com.bootdo.common.domain.FenleiDO;
import com.bootdo.common.domain.MokuaiDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 分类表
 * @author null
 * @email null@null.com
 * @date 2019-10-14 08:35:39
 */
@Mapper
public interface FenleiDao {

	FenleiDO get(Long id);
	
	List<FenleiDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(FenleiDO fenlei);
	
	int update(FenleiDO fenlei);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

    List<MokuaiDO> getMokuai();
}
