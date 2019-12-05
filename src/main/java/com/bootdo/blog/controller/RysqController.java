package com.bootdo.blog.controller;

import com.bootdo.blog.domain.RysqDO;
import com.bootdo.blog.service.RysqService;
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
 * 入园申请表
 * 
 * @author null
 * @email null@null.com
 * @date 2019-10-28 11:21:10
 */
 
@Controller
@RequestMapping("/blog/rysq")
public class RysqController {
	@Autowired
	private RysqService rysqService;
	
	@GetMapping()
	@RequiresPermissions("blog:rysq:rysq")
	String Rysq(){
	    return "blog/rysq/rysq";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("blog:rysq:rysq")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<RysqDO> rysqList = rysqService.list(query);
		int total = rysqService.count(query);
		PageUtils pageUtils = new PageUtils(rysqList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("blog:rysq:add")
	String add(){
	    return "blog/rysq/add";
	}

	@GetMapping("/edit/{cid}")
	@RequiresPermissions("blog:rysq:edit")
	String edit(@PathVariable("cid") Long cid,Model model){
		RysqDO rysq = rysqService.get(cid);
		model.addAttribute("rysq", rysq);
	    return "blog/rysq/edit";
	}
	
	@GetMapping("/resetPwd/{cid}")
	@RequiresPermissions("blog:rysq:rysq")
	String resetPwd(@PathVariable("cid") Long cid,Model model){
		RysqDO rysq = rysqService.get(cid);
		model.addAttribute("rysq", rysq);
	    return "blog/rysq/resetPwd";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("blog:rysq:add")
	public R save( RysqDO rysq){
		if(rysqService.save(rysq)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("blog:rysq:edit")
	public R update( RysqDO rysq){
		rysqService.update(rysq);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("blog:rysq:remove")
	public R remove( Long cid){
		if(rysqService.remove(cid)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("blog:rysq:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] cids){
		rysqService.batchRemove(cids);
		return R.ok();
	}
	/**
	 * 审核
	 */
	@PostMapping( "/batchShenHe")
	@ResponseBody
	@RequiresPermissions("blog:rysq:shenhe")
	public R batchShenHe(@RequestParam("ids[]") Long[] cids){
		rysqService.batchShenHe(cids);
		return R.ok();
	}
	/**
	 * 取消审核
	 */
	@PostMapping( "/batchQuxiaoshenhe")
	@ResponseBody
	@RequiresPermissions("blog:rysq:shenhe")
	public R batchQuxiaoshenhe(@RequestParam("ids[]") Long[] cids){
		rysqService.batchQuxiaoshenhe(cids);
		return R.ok();
	}
}
