package com.bootdo.common.controller;

import com.bootdo.common.domain.MokuaiDO;
import com.bootdo.common.service.MokuaiService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 模块表
 * 
 * @author 123
 * @email 1992lcg@163.com
 * @date 2019-10-11 11:29:50
 */
 
@Controller
@RequestMapping("/common/mokuai")
public class MokuaiController {
	@Autowired
	private MokuaiService mokuaiService;
	
	@GetMapping()
	@RequiresPermissions("common:mokuai:mokuai")
	String Mokuai(){
	    return "common/mokuai/mokuai";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("common:mokuai:mokuai")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<MokuaiDO> mokuaiList = mokuaiService.list(query);
		int total = mokuaiService.count(query);
		PageUtils pageUtils = new PageUtils(mokuaiList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("common:mokuai:add")
	String add(){
	    return "common/mokuai/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("common:mokuai:edit")
	String edit(@PathVariable("id") Long id,Model model){
		MokuaiDO mokuai = mokuaiService.get(id);
		model.addAttribute("mokuai", mokuai);
	    return "common/mokuai/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("common:mokuai:add")
	public R save( MokuaiDO mokuai){
		if(mokuaiService.save(mokuai)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("common:mokuai:edit")
	public R update( MokuaiDO mokuai){
		mokuaiService.update(mokuai);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("common:mokuai:remove")
	public R remove( Long id){
		if(mokuaiService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("common:mokuai:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		mokuaiService.batchRemove(ids);
		return R.ok();
	}
	
}
