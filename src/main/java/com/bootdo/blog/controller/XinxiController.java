package com.bootdo.blog.controller;

import com.bootdo.blog.domain.XinxiDO;
import com.bootdo.blog.service.XinxiService;
import com.bootdo.common.domain.FenleiDO;
import com.bootdo.common.domain.FileDO;
import com.bootdo.common.domain.MokuaiDO;
import com.bootdo.common.service.FileService;
import com.bootdo.common.utils.*;
import com.bootdo.system.domain.UserDO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 信息表
 * 
 * @author null
 * @email null@null.com
 * @date 2019-10-14 15:01:29
 */
 
@Controller
@RequestMapping("/blog/xinxi")
public class XinxiController {
	@Autowired
	private XinxiService xinxiService;
	@Autowired
	private FileService sysFileService;
	
	@GetMapping()
	@RequiresPermissions("blog:xinxi:xinxi")
	String Xinxi(){
	    return "blog/xinxi/xinxi";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("blog:xinxi:xinxi")
	public PageUtils list(@RequestParam Map<String, Object> params ){

		//查询列表数据
        Query query = new Query(params);
		List<XinxiDO> xinxiList = xinxiService.list(query);
		int total = xinxiService.count(query);
		PageUtils pageUtils = new PageUtils(xinxiList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("blog:xinxi:add")
	String add(){
	    return "blog/xinxi/add";
	}

	@GetMapping("/edit/{cid}")
	@RequiresPermissions("blog:xinxi:edit")
	String edit(@PathVariable("cid") Long cid,Model model){
		XinxiDO xinxi = xinxiService.get(cid);
		model.addAttribute("xinxi", xinxi);
	    return "blog/xinxi/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("blog:xinxi:add")
	public R save( XinxiDO xinxi){
		if(xinxiService.save(xinxi)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("blog:xinxi:edit")
	public R update( XinxiDO xinxi){
		xinxiService.update(xinxi);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("blog:xinxi:remove")
	public R remove( Long cid){
		if(xinxiService.remove(cid)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("blog:xinxi:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] cids){
		xinxiService.batchRemove(cids);
		return R.ok();
	}
	/**
	 * 审核
	 */
	@PostMapping( "/batchShenHe")
	@ResponseBody
	@RequiresPermissions("blog:xinxi:shenhe")
	public R batchShenHe(@RequestParam("ids[]") Long[] cids){
		xinxiService.batchShenHe(cids);
		return R.ok();
	}
	/**
	 * 取消审核
	 */
	@PostMapping( "/batchQuxiaoshenhe")
	@ResponseBody
	@RequiresPermissions("blog:xinxi:shenhe")
	public R batchQuxiaoshenhe(@RequestParam("ids[]") Long[] cids){
		xinxiService.batchQuxiaoshenhe(cids);
		return R.ok();
	}
	/**
	 * 置顶
	 */
	@PostMapping( "/batchZhiding")
	@ResponseBody
	@RequiresPermissions("blog:xinxi:shenhe")
	public R batchZhiding(@RequestParam("ids[]") Long[] cids){
		xinxiService.batchZhiding(cids);
		return R.ok();
	}
	/**
	 * 取消置顶
	 */
	@PostMapping( "/batchQuxiaoZhiding")
	@ResponseBody
	@RequiresPermissions("blog:xinxi:shenhe")
	public R batchQuxiaoZhiding(@RequestParam("ids[]") Long[] cids){
		xinxiService.batchQuxiaoZhiding(cids);
		return R.ok();
	}
	/**
	 * 加载模块与用户权限列表
	 */
	@PostMapping( "/mokuai")
	@ResponseBody
//	@RequiresPermissions("blog:xinxi:shenhe")
	List<MokuaiDO> mokuai(Model model){
		List<MokuaiDO> mokuai =  xinxiService.getMokuai();
		/*
		* 根据用户来查询权限
		* */
		UserDO user = ShiroUtils.getUser();
//		System.out.println(user.getName() +"================"+ user.getUserId());
		List<String> perms = xinxiService.getMenuPerms(user.getUserId());
		List<MokuaiDO> mokuaiDOS = new ArrayList<>();
        for (MokuaiDO mk: mokuai) {
            if (perms.contains(mk.getValue())){
                mokuaiDOS.add(mk);
            }
        }
        return mokuaiDOS;
	}
	/**
	 * 加载分类列表
	 */
	@PostMapping( "/fenlei")
	@ResponseBody
//	@RequiresPermissions("blog:xinxi:shenhe")
	List<FenleiDO> fenlei(Model model, @RequestParam("parentId") BigInteger parentId){
		List<FenleiDO> fenlei =  xinxiService.getFenlei(parentId);
		return fenlei;
	}
	/*
	*首页图片上传
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
		String uploadPath = classpath + "/upload/syImg/";
		// 如果目录不存在则创建
		File uploadDir = new File(uploadPath + fileName);
		// 检测是否存在目录
		if (!uploadDir.getParentFile().exists()) {
			uploadDir.getParentFile().mkdirs();
		}
//======================================
		FileDO sysFile = new FileDO(FileType.fileType(fileName), "/upload/syImg/" + fileName, new Date());
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
