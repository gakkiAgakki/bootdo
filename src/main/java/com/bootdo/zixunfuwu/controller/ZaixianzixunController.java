package com.bootdo.zixunfuwu.controller;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.zixunfuwu.domain.ZaixianzixunDO;
import com.bootdo.zixunfuwu.service.ZaixianzixunService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 在线咨询
 * 
 * @author null
 * @email null@null.com
 * @date 2019-10-18 11:16:27
 */
 
@Controller
@RequestMapping("/zixunfuwu/zaixianzixun")
public class ZaixianzixunController {
	@Autowired
	private ZaixianzixunService zaixianzixunService;
	
	@GetMapping()
	@RequiresPermissions("zixunfuwu:zaixianzixun:zaixianzixun")
	String Zaixianzixun(){
	    return "zixunfuwu/zaixianzixun/zaixianzixun";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("zixunfuwu:zaixianzixun:zaixianzixun")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ZaixianzixunDO> zaixianzixunList = zaixianzixunService.list(query);
		int total = zaixianzixunService.count(query);
		PageUtils pageUtils = new PageUtils(zaixianzixunList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("zixunfuwu:zaixianzixun:add")
	String add(){
	    return "zixunfuwu/zaixianzixun/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("zixunfuwu:zaixianzixun:edit")
	String edit(@PathVariable("id") Long id,Model model){
		ZaixianzixunDO zaixianzixun = zaixianzixunService.get(id);
		model.addAttribute("zaixianzixun", zaixianzixun);
	    return "zixunfuwu/zaixianzixun/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("zixunfuwu:zaixianzixun:add")
	public R save( ZaixianzixunDO zaixianzixun){
		if(zaixianzixunService.save(zaixianzixun)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("zixunfuwu:zaixianzixun:edit")
	public R update( ZaixianzixunDO zaixianzixun){
		zaixianzixunService.update(zaixianzixun);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("zixunfuwu:zaixianzixun:remove")
	public R remove( Long id){
		if(zaixianzixunService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("zixunfuwu:zaixianzixun:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		zaixianzixunService.batchRemove(ids);
		return R.ok();
	}
	/**
	 * 审核
	 */
	@PostMapping( "/batchShenhe")
	@ResponseBody
	@RequiresPermissions("zixunfuwu:zaixianzixun:shenhe")
	public R batchShenhe(@RequestParam("ids[]") Long[] ids){
		zaixianzixunService.batchShenHe(ids);
		return R.ok();
	}
	/**
	 * 取消审核
	 */
	@PostMapping( "/batchQuxiaoshenhe")
	@ResponseBody
	@RequiresPermissions("zixunfuwu:zaixianzixun:shenhe")
	public R batchQuxiaoshenhe(@RequestParam("ids[]") Long[] ids){
		zaixianzixunService.batchQuxiaoshenhe(ids);
		return R.ok();
	}
	
}
