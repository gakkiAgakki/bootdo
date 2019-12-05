package com.bootdo.qzgs.controller;

import com.bootdo.common.domain.FileDO;
import com.bootdo.common.service.FileService;
import com.bootdo.common.utils.*;
import com.bootdo.qzgs.domain.GsqdDO;
import com.bootdo.qzgs.domain.LeibeidanweiDO;
import com.bootdo.qzgs.service.GsqdService;
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
 * 公示清单
 * 
 * @author null
 * @email null@null.com
 * @date 2019-10-17 14:22:56
 */
 
@Controller
@RequestMapping("/qzgs/gsqd")
public class GsqdController {
	@Autowired
	private GsqdService gsqdService;
	@Autowired
	private FileService sysFileService;
	
	@GetMapping()
	@RequiresPermissions("qzgs:gsqd:gsqd")
	String Gsqd(){
	    return "qzgs/gsqd/gsqd";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("qzgs:gsqd:gsqd")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<GsqdDO> gsqdList = gsqdService.list(query);
		int total = gsqdService.count(query);
		PageUtils pageUtils = new PageUtils(gsqdList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("qzgs:gsqd:add")
	String add(){
	    return "qzgs/gsqd/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("qzgs:gsqd:edit")
	String edit(@PathVariable("id") Long id,Model model){
		GsqdDO gsqd = gsqdService.get(id);
		model.addAttribute("gsqd", gsqd);
	    return "qzgs/gsqd/edit";
	}
	@PostMapping("/lbOrbm")
//	@RequiresPermissions("qzgs:gsqd:edit")
	@ResponseBody
	List<LeibeidanweiDO> lbOrbm(){
		List<LeibeidanweiDO> lbdw = gsqdService.getLbOrBm();
	    return lbdw;
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("qzgs:gsqd:add")
	public R save( GsqdDO gsqd){
		if(gsqdService.save(gsqd)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("qzgs:gsqd:edit")
	public R update( GsqdDO gsqd){
		gsqdService.update(gsqd);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("qzgs:gsqd:remove")
	public R remove( Long id){
		if(gsqdService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("qzgs:gsqd:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		gsqdService.batchRemove(ids);
		return R.ok();
	}
	/*
	*图片上传
	* */
	@PostMapping("/uploadImg")
	@ResponseBody
	R upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		String fileName = file.getOriginalFilename();
		String[] allowTypes = new String[] { "png", "jpg", "jpeg", "gif", "bmp" };
		int splitIndex = fileName.lastIndexOf(".");
		String fileType = fileName.substring(splitIndex + 1);//doc、xls等
		//判断文件类型是否在以上内容之内
		Boolean CanUploaded = isValid(fileType, allowTypes);
		if (!CanUploaded) {
			return R.ok().put("fileUrl","文件类型异常！");
		}
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
		String uploadPath = classpath + "/upload/gsqdImg/";
		// 如果目录不存在则创建
		File uploadDir = new File(uploadPath + fileName);
		// 检测是否存在目录
		if (!uploadDir.getParentFile().exists()) {
			uploadDir.getParentFile().mkdirs();
		}
//======================================
		FileDO sysFile = new FileDO(FileType.fileType(fileName), "/upload/gsqdImg/" + fileName, new Date());
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

	public static boolean isValid(String contentType, String... allowTypes) {
		if (null == contentType || "".equals(contentType)) {
			return false;
		}
		for (String type : allowTypes) {
			if (contentType.indexOf(type) > -1) {
				return true;
			}
		}
		return false;
	}
}
