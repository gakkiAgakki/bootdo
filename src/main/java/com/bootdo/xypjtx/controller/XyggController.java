package com.bootdo.xypjtx.controller;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.xypjtx.domain.XyggDO;
import com.bootdo.xypjtx.service.XyggService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 信用公告
 * 
 * @author null
 * @email null@null.com
 * @date 2019-11-28 10:06:30
 */
 
@Controller
@RequestMapping("/xypjtx/xygg")
public class XyggController {
	@Autowired
	private XyggService xyggService;
	
	@GetMapping()
	@RequiresPermissions("xypjtx:xygg:xygg")
	String Xygg(){
	    return "xypjtx/xygg/xygg";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("xypjtx:xygg:xygg")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<XyggDO> xyggList = xyggService.list(query);
		int total = xyggService.count(query);
		PageUtils pageUtils = new PageUtils(xyggList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("xypjtx:xygg:add")
	String add(){
	    return "xypjtx/xygg/add";
	}

	@GetMapping("/edit/{cid}")
	@RequiresPermissions("xypjtx:xygg:edit")
	String edit(@PathVariable("cid") Long cid,Model model){
		XyggDO xygg = xyggService.get(cid);
		model.addAttribute("xygg", xygg);
	    return "xypjtx/xygg/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("xypjtx:xygg:add")
	public R save( XyggDO xygg){
		if(xyggService.save(xygg)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("xypjtx:xygg:edit")
	public R update( XyggDO xygg){
		xyggService.update(xygg);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("xypjtx:xygg:remove")
	public R remove( Long cid){
		if(xyggService.remove(cid)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("xypjtx:xygg:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] cids){
		xyggService.batchRemove(cids);
		return R.ok();
	}
	/**
	 * 审核
	 */
	@PostMapping( "/batchShenHe")
	@ResponseBody
	@RequiresPermissions("xypjtx:xygg:shenhe")
	public R batchShenHe(@RequestParam("ids[]") Long[] cids){
		xyggService.batchShenHe(cids);
		return R.ok();
	}
	/**
	 * 取消审核
	 */
	@PostMapping( "/batchQuxiaoshenhe")
	@ResponseBody
	@RequiresPermissions("xypjtx:xygg:shenhe")
	public R batchQuxiaoshenhe(@RequestParam("ids[]") Long[] cids){
		xyggService.batchQuxiaoshenhe(cids);
		return R.ok();
	}
	
}
