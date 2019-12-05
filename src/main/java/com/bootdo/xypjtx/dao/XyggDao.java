package com.bootdo.xypjtx.dao;

import com.bootdo.xypjtx.domain.XyggDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 信用公告
 * @author null
 * @email null@null.com
 * @date 2019-11-28 10:06:30
 */
@Mapper
public interface XyggDao {

	XyggDO get(Long cid);
	
	List<XyggDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(XyggDO xygg);
	
	int update(XyggDO xygg);
	
	int remove(Long cid);
	
	int batchRemove(Long[] cids);

    int batchShenHe(Long[] cids);

	int batchQuxiaoshenhe(Long[] cids);
}