package com.bootdo.common.controller;

import com.bootdo.common.domain.FenleiDO;
import com.bootdo.common.domain.MokuaiDO;
import com.bootdo.common.service.FenleiService;
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
 * 分类表
 * 
 * @author null
 * @email null@null.com
 * @date 2019-10-14 08:35:39
 */
 
@Controller
@RequestMapping("/common/fenlei")
public class FenleiController {
	@Autowired
	private FenleiService fenleiService;
	
	@GetMapping()
	@RequiresPermissions("common:fenlei:fenlei")
	String Fenlei(){
	    return "common/fenlei/fenlei";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("common:fenlei:fenlei")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<FenleiDO> fenleiList = fenleiService.list(query);
		int total = fenleiService.count(query);
		PageUtils pageUtils = new PageUtils(fenleiList, total);
		return pageUtils;
	}

	/**
	 * 查询分类所述模块
	 * @return
	 */
	@GetMapping("/mokuai")
	@ResponseBody
	List<MokuaiDO> mokuai(Model model){
		List<MokuaiDO> mokuai =  fenleiService.getMokuai();
		return mokuai;
	}

	@GetMapping("/add")
	@RequiresPermissions("common:fenlei:add")
	String add(){
	    return "common/fenlei/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("common:fenlei:edit")
	String edit(@PathVariable("id") Long id,Model model){
		FenleiDO fenlei = fenleiService.get(id);
		model.addAttribute("fenlei", fenlei);
	    return "common/fenlei/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("common:fenlei:add")
	public R save( FenleiDO fenlei){
		if(fenleiService.save(fenlei)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("common:fenlei:edit")
	public R update( FenleiDO fenlei){
		fenleiService.update(fenlei);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("common:fenlei:remove")
	public R remove( Long id){
		if(fenleiService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("common:fenlei:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		fenleiService.batchRemove(ids);
		return R.ok();
	}
	
}
