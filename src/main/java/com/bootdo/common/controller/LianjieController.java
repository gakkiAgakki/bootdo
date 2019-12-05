package com.bootdo.common.controller;

import com.bootdo.common.domain.LianjieDO;
import com.bootdo.common.service.LianjieService;
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
 * 相关链接
 * 
 * @author null
 * @email null@null.com
 * @date 2019-10-17 18:23:25
 */
 
@Controller
@RequestMapping("/common/lianjie")
public class LianjieController {
	@Autowired
	private LianjieService lianjieService;
	
	@GetMapping()
	@RequiresPermissions("common:lianjie:lianjie")
	String Lianjie(){
	    return "common/lianjie/lianjie";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("common:lianjie:lianjie")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<LianjieDO> lianjieList = lianjieService.list(query);
		int total = lianjieService.count(query);
		PageUtils pageUtils = new PageUtils(lianjieList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("common:lianjie:add")
	String add(){
	    return "common/lianjie/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("common:lianjie:edit")
	String edit(@PathVariable("id") Long id,Model model){
		LianjieDO lianjie = lianjieService.get(id);
		model.addAttribute("lianjie", lianjie);
	    return "common/lianjie/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("common:lianjie:add")
	public R save( LianjieDO lianjie){
		if(lianjieService.save(lianjie)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("common:lianjie:edit")
	public R update( LianjieDO lianjie){
		lianjieService.update(lianjie);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("common:lianjie:remove")
	public R remove( Long id){
		if(lianjieService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("common:lianjie:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		lianjieService.batchRemove(ids);
		return R.ok();
	}
	
}
