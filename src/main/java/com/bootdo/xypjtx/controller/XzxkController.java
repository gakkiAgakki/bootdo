package com.bootdo.xypjtx.controller;

import com.bootdo.common.domain.FileDO;
import com.bootdo.common.service.FileService;
import com.bootdo.common.utils.*;
import com.bootdo.system.domain.UserDO;
import com.bootdo.xypjtx.domain.XzxkDO;
import com.bootdo.xypjtx.service.XzxkService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 信用评价体系_行政许可
 * 
 * @author null
 * @email null@null.com
 * @date 2019-12-04 08:01:21
 */
 
@Controller
@RequestMapping("/xypjtx/xzxk")
public class XzxkController {
	@Autowired
	private XzxkService xzxkService;
	@Autowired
	private FileService sysFileService;
	
	@GetMapping()
	@RequiresPermissions("xypjtx:xzxk:xzxk")
	String Xzxk(){
	    return "xypjtx/xzxk/xzxk";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("xypjtx:xzxk:xzxk")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<XzxkDO> xzxkList = xzxkService.list(query);
		int total = xzxkService.count(query);
		PageUtils pageUtils = new PageUtils(xzxkList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("xypjtx:xzxk:add")
	String add(){
	    return "xypjtx/xzxk/add";
	}

	@GetMapping("/edit/{xid}")
	@RequiresPermissions("xypjtx:xzxk:edit")
	String edit(@PathVariable("xid") Long xid,Model model){
		XzxkDO xzxk = xzxkService.get(xid);
		String xksxq = DateUtils.format(xzxk.getXksxq());
		String xkjzq = DateUtils.format(xzxk.getXkjzq());
		String sjbssj = DateUtils.format(xzxk.getSjbssj());
		model.addAttribute("xksxq", xksxq);
		model.addAttribute("xkjzq", xkjzq);
		model.addAttribute("sjbssj", sjbssj);
		model.addAttribute("xzxk", xzxk);
	    return "xypjtx/xzxk/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("xypjtx:xzxk:add")
	public R save( XzxkDO xzxk){

		UserDO user = ShiroUtils.getUser();
		if (user.getUsername() == "" || user.getUsername() == null){
			return R.error("无法获取提交者的用户名");
		}
		String username = user.getUsername();
		xzxk.setTjz(username);

		if(xzxkService.save(xzxk)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("xypjtx:xzxk:edit")
	public R update( XzxkDO xzxk){
		xzxkService.update(xzxk);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("xypjtx:xzxk:remove")
	public R remove( Long xid){
		if(xzxkService.remove(xid)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("xypjtx:xzxk:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] xids){
		xzxkService.batchRemove(xids);
		return R.ok();
	}

	/*
	*附件上传
	* */
	@PostMapping("/uploadFile")
	@ResponseBody
	R uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		String fileName = file.getOriginalFilename();

//		fileName = FileUtil.renameToUUID(fileName);
//========================================
		// 获取文件的后缀名
		String suffixName = fileName.substring(fileName.lastIndexOf("."));
		//时间戳
		long time = System.currentTimeMillis() / 1000;
		// 这里我使用文件名，时间戳，后缀名来重新命名图片
		fileName = fileName.substring(0,fileName.indexOf(".")) + time + suffixName;
		// 这个路径相对当前应用的目录
		String classpath = this.getClass().getResource("/static").getPath().replaceFirst("/", "");
		String uploadPath = classpath + "/xzxkCns/chengnuoshu/";
		// 如果目录不存在则创建
		File uploadDir = new File(uploadPath + fileName);
		// 检测是否存在目录
		if (!uploadDir.getParentFile().exists()) {
			uploadDir.getParentFile().mkdirs();
		}
//======================================
		FileDO sysFile = new FileDO(FileType.fileType(fileName), "/xzxkCns/chengnuoshu/" + fileName, new Date());
		sysFile.setFileName(fileName);
		try {
			FileUtil.uploadFile(file.getBytes(), uploadPath, fileName);
		} catch (Exception e) {
			return R.error();
		}

		if (sysFileService.save(sysFile) > 0) {
			return R.ok().put("fileUrl",sysFile.getUrl());
		}
		return R.error();
	}


}
