package com.bootdo.xypjtx.service;

import com.bootdo.xypjtx.domain.XzxkDO;

import java.util.List;
import java.util.Map;

/**
 * 信用评价体系_行政许可
 * 
 * @author null
 * @email null@null.com
 * @date 2019-12-04 08:01:21
 */
public interface XzxkService {
	
	XzxkDO get(Long xid);
	
	List<XzxkDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(XzxkDO xzxk);
	
	int update(XzxkDO xzxk);
	
	int remove(Long xid);
	
	int batchRemove(Long[] xids);
}
