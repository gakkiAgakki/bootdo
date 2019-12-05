package com.bootdo.xypjtx.controller;

import com.bootdo.common.annotation.Log;
import com.bootdo.common.domain.Tree;
import com.bootdo.common.utils.R;
import com.bootdo.xypjtx.domain.MenuDO;
import com.bootdo.xypjtx.service.XypjtxMenuService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 信用评价体系_菜单管理
 * 
 * @author null
 * @email null@null.com
 * @date 2019-11-25 09:44:38
 */
 
@Controller
@RequestMapping("/xypjtx/menu")
public class XypjtxMenuController {
	@Autowired
	private XypjtxMenuService menuService;
	
	@GetMapping()
	@RequiresPermissions("xypjtx:menu:menu")
	String Menu(){
	    return "xypjtx/menu/menu";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("xypjtx:menu:menu")
	List<MenuDO> list(@RequestParam Map<String, Object> params) {
		List<MenuDO> menus = menuService.list(params);
		return menus;
	}
	
	@GetMapping("/add/{pId}")
	@Log("信用评价体系_添加菜单")
	@RequiresPermissions("xypjtx:menu:add")
	String add(Model model, @PathVariable("pId") Long pId){
		model.addAttribute("pId",pId);
		if (pId == 0) {
			model.addAttribute("pName", "根目录");
		} else {
			model.addAttribute("pName", menuService.get(pId).getName());
		}
	    return "xypjtx/menu/add";
	}

	@Log("信用评价体系_编辑菜单")
	@GetMapping("/edit/{id}")
	@RequiresPermissions("xypjtx:menu:edit")
	String edit(@PathVariable("id") Long id,Model model){
		MenuDO menu = menuService.get(id);
		Long pId = menu.getParentId();
		model.addAttribute("pId",pId);
		if (pId == 0) {
			model.addAttribute("pName", "根目录");
		} else {
			model.addAttribute("pName", menuService.get(pId).getName());
		}
		model.addAttribute("menu", menu);
	    return "xypjtx/menu/edit";
	}
	
	/**
	 * 保存
	 */
	@Log("信用评价体系_保存菜单")
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("xypjtx:menu:add")
	public R save( MenuDO menu){
		if(menuService.save(menu)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@Log("信用评价体系_更新菜单")
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("xypjtx:menu:edit")
	public R update( MenuDO menu){
		if (menuService.update(menu) > 0) {
			return R.ok();
		} else {
			return R.error(1, "更新失败");
		}
	}
	
	/**
	 * 删除
	 */
	@Log("信用评价体系_删除菜单")
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("xypjtx:menu:remove")
	public R remove( Long id){
		if (menuService.remove(id) > 0) {
			return R.ok();
		} else {
			return R.error(1, "删除失败");
		}
	}
	
	/**
	 * 删除
	 */
	@Log("信用评价体系_批量删除菜单")
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("xypjtx:menu:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] menuIds){
		menuService.batchRemove(menuIds);
		return R.ok();
	}
	@GetMapping("/tree")
	@ResponseBody
	Tree<MenuDO> tree(){
		Tree<MenuDO> tree = menuService.getTree();
		return tree;
	}
}
