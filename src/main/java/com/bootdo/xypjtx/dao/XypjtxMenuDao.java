package com.bootdo.xypjtx.dao;

import com.bootdo.xypjtx.domain.MenuDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 信用评价体系_菜单管理
 * @author null
 * @email null@null.com
 * @date 2019-11-25 09:44:38
 */
@Mapper
public interface XypjtxMenuDao {

	MenuDO get(Long menuId);
	
	List<MenuDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(MenuDO menu);
	
	int update(MenuDO menu);
	
	int remove(Long menu_id);
	
	int batchRemove(Long[] menuIds);
}
