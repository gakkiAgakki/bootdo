package com.bootdo.qzgs.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.qzgs.domain.LeibeidanweiDO;
import com.bootdo.qzgs.service.LeibeidanweiService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 类别与部门表
 * 
 * @author null
 * @email null@null.com
 * @date 2019-10-17 11:24:01
 */
 
@Controller
@RequestMapping("/qzgs/leibeidanwei")
public class LeibeidanweiController {
	@Autowired
	private LeibeidanweiService leibeidanweiService;
	
	@GetMapping()
	@RequiresPermissions("qzgs:leibeidanwei:leibeidanwei")
	String Leibeidanwei(){
	    return "qzgs/leibeidanwei/leibeidanwei";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("qzgs:leibeidanwei:leibeidanwei")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<LeibeidanweiDO> leibeidanweiList = leibeidanweiService.list(query);
		int total = leibeidanweiService.count(query);
		PageUtils pageUtils = new PageUtils(leibeidanweiList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("qzgs:leibeidanwei:add")
	String add(){
	    return "qzgs/leibeidanwei/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("qzgs:leibeidanwei:edit")
	String edit(@PathVariable("id") Long id,Model model){
		LeibeidanweiDO leibeidanwei = leibeidanweiService.get(id);
		model.addAttribute("leibeidanwei", leibeidanwei);
	    return "qzgs/leibeidanwei/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("qzgs:leibeidanwei:add")
	public R save( LeibeidanweiDO leibeidanwei){
		if(leibeidanweiService.save(leibeidanwei)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("qzgs:leibeidanwei:edit")
	public R update( LeibeidanweiDO leibeidanwei){
		leibeidanweiService.update(leibeidanwei);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("qzgs:leibeidanwei:remove")
	public R remove( Long id){
		if(leibeidanweiService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("qzgs:leibeidanwei:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		leibeidanweiService.batchRemove(ids);
		return R.ok();
	}
	
}
