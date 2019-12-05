package com.bootdo.xypjtx.service.impl;

import com.bootdo.common.domain.Tree;
import com.bootdo.common.utils.BuildTree;
import com.bootdo.xypjtx.dao.XypjtxMenuDao;
import com.bootdo.xypjtx.domain.MenuDO;
import com.bootdo.xypjtx.service.XypjtxMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Service
public class XypjtxMenuServiceImpl implements XypjtxMenuService {
	@Autowired
	private XypjtxMenuDao menuDao;
	
	@Override
	public MenuDO get(Long id){
		return menuDao.get(id);
	}
	
	@Override
	public List<MenuDO> list(Map<String, Object> map){
		return menuDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return menuDao.count(map);
	}
	
	@Override
	public int save(MenuDO menu){
		return menuDao.save(menu);
	}
	
	@Override
	public int update(MenuDO menu){
		return menuDao.update(menu);
	}
	
	@Override
	public int remove(Long menuId){
		return menuDao.remove(menuId);
	}
	
	@Override
	public int batchRemove(Long[] menuIds){
		return menuDao.batchRemove(menuIds);
	}

	@Override
	public Tree<MenuDO> getTree() {
		List<Tree<MenuDO>> trees = new ArrayList<Tree<MenuDO>>();
		List<MenuDO> menuDOs = menuDao.list(new HashMap<>(16));
		for (MenuDO sysMenuDO : menuDOs) {
			Tree<MenuDO> tree = new Tree<MenuDO>();
			tree.setId(sysMenuDO.getMenuId().toString());
			tree.setParentId(sysMenuDO.getParentId().toString());
			tree.setText(sysMenuDO.getName());
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		Tree<MenuDO> t = BuildTree.build(trees);
		return t;
	}

}
