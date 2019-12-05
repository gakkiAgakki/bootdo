package com.bootdo.qzgs.service;

import com.bootdo.qzgs.domain.LeibeidanweiDO;

import java.util.List;
import java.util.Map;

/**
 * 类别与部门表
 * 
 * @author null
 * @email null@null.com
 * @date 2019-10-17 11:24:01
 */
public interface LeibeidanweiService {
	
	LeibeidanweiDO get(Long id);
	
	List<LeibeidanweiDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(LeibeidanweiDO leibeidanwei);
	
	int update(LeibeidanweiDO leibeidanwei);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
