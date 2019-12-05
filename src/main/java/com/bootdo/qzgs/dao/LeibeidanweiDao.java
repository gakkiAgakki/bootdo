package com.bootdo.qzgs.dao;

import com.bootdo.qzgs.domain.LeibeidanweiDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 类别与部门表
 * @author null
 * @email null@null.com
 * @date 2019-10-17 11:24:01
 */
@Mapper
public interface LeibeidanweiDao {

	LeibeidanweiDO get(Long id);
	
	List<LeibeidanweiDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(LeibeidanweiDO leibeidanwei);
	
	int update(LeibeidanweiDO leibeidanwei);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
