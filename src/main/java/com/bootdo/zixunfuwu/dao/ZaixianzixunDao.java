package com.bootdo.zixunfuwu.dao;

import com.bootdo.zixunfuwu.domain.ZaixianzixunDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 在线咨询
 * @author null
 * @email null@null.com
 * @date 2019-10-18 11:16:27
 */
@Mapper
public interface ZaixianzixunDao {

	ZaixianzixunDO get(Long id);
	
	List<ZaixianzixunDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ZaixianzixunDO zaixianzixun);
	
	int update(ZaixianzixunDO zaixianzixun);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

    int batchShenHe(Long[] ids);

	int batchQuxiaoshenhe(Long[] ids);
}
