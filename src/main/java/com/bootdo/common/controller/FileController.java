package com.bootdo.common.controller;

import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.domain.FileDO;
import com.bootdo.common.service.FileService;
import com.bootdo.common.utils.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文件上传
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-09-19 16:02:20
 */
@Controller
@RequestMapping("/common/sysFile")
public class FileController extends BaseController {

	@Autowired
	private FileService sysFileService;

	@Autowired
	private BootdoConfig bootdoConfig;

	@GetMapping()
	@RequiresPermissions("common:sysFile:sysFile")
	String sysFile(Model model) {
		Map<String, Object> params = new HashMap<>(16);
		return "common/file/file";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("common:sysFile:sysFile")
	public PageUtils list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		Query query = new Query(params);
		List<FileDO> sysFileList = sysFileService.list(query);
		int total = sysFileService.count(query);
		PageUtils pageUtils = new PageUtils(sysFileList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	// @RequiresPermissions("common:bComments")
	String add() {
		return "common/sysFile/add";
	}

	@GetMapping("/edit")
	// @RequiresPermissions("common:bComments")
	String edit(Long id, Model model) {
		FileDO sysFile = sysFileService.get(id);
		model.addAttribute("sysFile", sysFile);
		return "common/sysFile/edit";
	}

	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("common:info")
	public R info(@PathVariable("id") Long id) {
		FileDO sysFile = sysFileService.get(id);
		return R.ok().put("sysFile", sysFile);
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("common:save")
	public R save(FileDO sysFile) {
		if (sysFileService.save(sysFile) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("common:update")
	public R update(@RequestBody FileDO sysFile) {
		sysFileService.update(sysFile);

		return R.ok();
	}
	@RequestMapping("/download")
//	@RequiresPermissions("common:download")
//	@ResponseBody
	public ResponseEntity<byte[]> downloadFile(String path)
			throws IOException {
//		String filePath = path;
//		FileSystemResource file = new FileSystemResource(filePath);
//		HttpHeaders headers = new HttpHeaders();
//		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
//		headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getFilename()));
//		headers.add("Pragma", "no-cache");
//		headers.add("Expires", "0");
//
//		return ResponseEntity
//				.ok()
//				.headers(headers)
//				.contentLength(file.contentLength())
//				.contentType(MediaType.parseMediaType("application/octet-stream"))
//				.body(new InputStreamResource(file.getInputStream()));
		String classpath = this.getClass().getResource("/static/").getPath().replaceFirst("/", "");
		File f=new File( classpath + path);
		String filename = f.getName();
		InputStream in;
		ResponseEntity<byte[]> response=null ;
		try {
			in = new FileInputStream(f);
			byte[] b=new byte[in.available()];
			in.read(b);
			HttpHeaders headers = new HttpHeaders();
			filename = new String(filename.getBytes("gbk"),"iso8859-1");
			headers.add("Content-Disposition", "attachment;filename="+filename);
			HttpStatus statusCode=HttpStatus.OK;
			response = new ResponseEntity<byte[]>(b, headers, statusCode);
			in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	// @RequiresPermissions("common:remove")
	public R remove(Long id, HttpServletRequest request,String path) {
//		if ("test".equals(getUsername())) {
//			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
//		}
		//========================================
		// 构造临时路径来存储上传的文件
		// 这个路径相对当前应用的目录
		String uploadPath = this.getClass().getResource("/static/").getPath().replaceFirst("/", "");
		//==================================================
//		String fileName = bootdoConfig.getUploadPath() + sysFileService.get(id).getUrl().replace(uploadPath, "");
		String fileName = path;
		if (sysFileService.remove(id) > 0) {
			boolean b = FileUtil.deleteFile(uploadPath + fileName);
			System.out.println(uploadPath + fileName);
			if (!b) {
				return R.error("数据库记录删除成功，文件删除失败");
			}
			return R.ok();
		} else {
			return R.error();
		}
	}

	/**
	 * 删除
	 */
	@PostMapping("/batchRemove")
	@ResponseBody
	@RequiresPermissions("common:remove")
	public R remove(@RequestParam("ids[]") Long[] ids) {
//		if ("test".equals(getUsername())) {
//			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
//		}
		sysFileService.batchRemove(ids);
		return R.ok();
	}

	@ResponseBody
	@PostMapping("/upload")
	R upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
//		if ("test".equals(getUsername())) {
//			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
//		}
		String fileName = file.getOriginalFilename();
//		fileName = FileUtil.renameToUUID(fileName);
//========================================
		// 这个路径相对当前应用的目录
		String classpath = this.getClass().getResource("/static").getPath().replaceFirst("/", "");
		String uploadPath = classpath + "/upload/upFile/";
		// 如果目录不存在则创建
		File uploadDir = new File(uploadPath + fileName);
		// 检测是否存在目录
		if (!uploadDir.getParentFile().exists()) {
			uploadDir.getParentFile().mkdirs();
		}
//======================================
//		FileDO sysFile = new FileDO(FileType.fileType(fileName), "/files/" + fileName, new Date());
		FileDO sysFile = new FileDO(FileType.fileType(fileName), "/upload/upFile/" + fileName, new Date());
		sysFile.setFileName(fileName);
		try {
			FileUtil.uploadFile(file.getBytes(), uploadPath, fileName);
		} catch (Exception e) {
			return R.error();
		}

		if (sysFileService.save(sysFile) > 0) {
			return R.ok().put("fileName",sysFile.getUrl());
		}
		return R.error();
	}


}
