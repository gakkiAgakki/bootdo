package com.bootdo.xypjtx.dao;

import com.bootdo.xypjtx.domain.XzxkDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 信用评价体系_行政许可
 * @author null
 * @email null@null.com
 * @date 2019-12-04 08:01:21
 */
@Mapper
public interface XzxkDao {

	XzxkDO get(Long xid);
	
	List<XzxkDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(XzxkDO xzxk);
	
	int update(XzxkDO xzxk);
	
	int remove(Long xId);
	
	int batchRemove(Long[] xids);
}
