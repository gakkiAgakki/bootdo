package com.bootdo.zixunfuwu.service;

import com.bootdo.zixunfuwu.domain.ZaixianzixunDO;

import java.util.List;
import java.util.Map;

/**
 * 在线咨询
 * 
 * @author null
 * @email null@null.com
 * @date 2019-10-18 11:16:27
 */
public interface ZaixianzixunService {
	
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
