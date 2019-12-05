package com.bootdo.blog.controller;

import com.bootdo.blog.domain.RysqDO;
import com.bootdo.blog.domain.XinxiDO;
import com.bootdo.blog.service.XContentService;
import com.bootdo.common.domain.FenleiDO;
import com.bootdo.common.domain.LianjieDO;
import com.bootdo.common.utils.*;
import com.bootdo.zixunfuwu.domain.ZaixianzixunDO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author bootdo 1992lc@163.com
 */
@RequestMapping("/xContent")
@Controller
public class XinxiContent {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	XContentService xContentService;

	@GetMapping()
	String xinxi() {
		return "blog/index/main";
	}

	@ResponseBody
//	@GetMapping("/open/list")
	@PostMapping("/open/list")
	public PageUtils list(@RequestParam Map<String, Object> params ){

		//查询列表数据
		Query query = new Query(params);
		List<XinxiDO> xinxiList = xContentService.list(query);
		int total = xContentService.count(query);
		PageUtils pageUtils = new PageUtils(xinxiList, total);
		return pageUtils;
	}
	@ResponseBody
	@GetMapping("/open/findPic")
	public PageUtils findPic(@RequestParam Map<String, Object> params ){

		//查询列表数据
		Query query = new Query(params);
		List<XinxiDO> xinxiList = xContentService.findPic(query);
		int total = xContentService.count(query);
		PageUtils pageUtils = new PageUtils(xinxiList, total);
		return pageUtils;
	}
	/**
	 * 加载分类列表
	 */
	@PostMapping( "/open/fenleiList")
	@ResponseBody
//	@RequiresPermissions("blog:xinxi:shenhe")
	List<FenleiDO> fenlei(Model model, @RequestParam("mokuai") String mokuai){
		List<FenleiDO> fenlei =  xContentService.getFenlei(mokuai);
		return fenlei;
	}
	@GetMapping("/open/post/{cid}")
	String post(@PathVariable("cid") Long cid, Model model) {
		XinxiDO xXinxiDO = xContentService.get(cid);

		if (xXinxiDO == null){
			return "error/404";
		}

		//根据id获取分类
		List<FenleiDO> fenlei = xContentService.getFenleiById(cid);
		model.addAttribute("xFenlei",fenlei);

		model.addAttribute("xContent", xXinxiDO);
		model.addAttribute("gtmCreate", DateUtils.format(xXinxiDO.getGtmCreate()));
		return "blog/index/post";
	}
//	@ResponseBody
	@GetMapping("/open/listByMk")
	String listByMk(@RequestParam(required=true,defaultValue="1")Integer page, Model model,
//								 @PathVariable("mokuai") String mokuai //){
					@RequestParam Map<String, Object> params ){
		//pageNum为页面数pageSize为数据条数
		PageHelper.startPage (page,10);
		int offset = (page-1)*5;
		int limit = page;
		params.put("offset",0);
		params.put("limit",5);
//		params.put("mokuai","园区概况");
		Query query = new Query(params);
//		List<XinxiDO> list = xContentService.list(query);
		List<XinxiDO> list = xContentService.getAll(query);
		int total = xContentService.count(query);
//		List<XinxiDO> list = xContentService.getByMk(mokuai,offset,limit);
//		int total = xContentService.count1();
		PageInfo<XinxiDO> p=new PageInfo<XinxiDO>(list);
		p.setTotal(total);

		String mokuai = (String) params.get("mokuai");
		String fenlei = (String) params.get("fenlei");
		if (mokuai != null && mokuai != ""){
			List<FenleiDO> xFenlei = xContentService.getFenlei(mokuai);
			model.addAttribute("xMokuai",mokuai);
			model.addAttribute("xFenlei",xFenlei);
		}
		if (fenlei != null && fenlei != ""){
			List<FenleiDO> xFenlei = xContentService.getFenleiByFenlei(fenlei);
			FenleiDO fenleiDO = xContentService.getfenleiDo(fenlei);
			model.addAttribute("xMokuai",fenleiDO.getMokuainame());
			model.addAttribute("xFenlei",xFenlei);
		}

		model.addAttribute("xContent",list);
		model.addAttribute("page",p);
//		model.addAttribute("mokuai",mokuai);
		model.addAttribute("fenlei",fenlei);
		return "blog/index/listByMk";
	}
	@GetMapping("/open/toRysq")
	String toRysq(Model model) {
		String fenlei = "入园申请";
		List<FenleiDO> xFenlei = xContentService.getFenleiByFenlei(fenlei);
		FenleiDO fenleiDO = xContentService.getfenleiDo(fenlei);
		model.addAttribute("fenlei",fenlei);
		model.addAttribute("xMokuai",fenleiDO.getMokuainame());
		model.addAttribute("xFenlei",xFenlei);

		return "blog/index/rysq";
	}
	@GetMapping("/open/toZaixianzixun")
	String toZaixianzixun() {
		return "blog/index/toZaixianzixun";
	}
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/open/rysqSave")
	public R save(RysqDO rysq,String verify,HttpServletRequest request){

		try {
			//从session中获取随机数
			String random = (String) request.getSession().getAttribute(RandomValidateCodeUtil.RANDOMCODEKEY);
			if (StringUtils.isBlank(verify)) {
				return R.error("请输入验证码");
			}
			if (random.equals(verify)) {
			} else {
				return R.error("请输入正确的验证码");
			}
		} catch (Exception e) {
			logger.error("验证码校验失败", e);
			return R.error("验证码校验失败");
		}

		if(xContentService.rysqSave(rysq)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/open/zaixianzixunSave")
	public R zxzxSave(ZaixianzixunDO zaixianzixunDO,String verify,HttpServletRequest request){

		try {
			//从session中获取随机数
			String random = (String) request.getSession().getAttribute(RandomValidateCodeUtil.RANDOMCODEKEY);
			if (StringUtils.isBlank(verify)) {
				return R.error("请输入验证码");
			}
			if (random.equals(verify)) {
			} else {
				return R.error("请输入正确的验证码");
			}
		} catch (Exception e) {
			logger.error("验证码校验失败", e);
			return R.error("验证码校验失败");
		}

		if(xContentService.zxzxSave(zaixianzixunDO)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 加载链接列表
	 */
	@GetMapping( "/open/lianjie")
	@ResponseBody
	List<LianjieDO> lianjie(Model model, @RequestParam("leixing") String leixing){
		List<LianjieDO> lianjie =  xContentService.getlianjie(leixing);
		return lianjie;
	}
	@GetMapping("/open/zixunhuifu")
	String zixunhuifu(@RequestParam(required=true,defaultValue="1")Integer page, Model model,
//								 @PathVariable("mokuai") String mokuai //){
					@RequestParam Map<String, Object> params ){
		//pageNum为页面数pageSize为数据条数
		PageHelper.startPage (page,5);
		int offset = (page-1)*5;
		int limit = page;
		params.put("offset",0);
		params.put("limit",5);
		Query query = new Query(params);
		List<ZaixianzixunDO> list = xContentService.getZxzx(query);
		int total = xContentService.ZxzxCount(query);
		PageInfo<ZaixianzixunDO> p= new PageInfo<>(list);
		p.setTotal(total);


		model.addAttribute("xZxzx",list);
		model.addAttribute("page",p);
		return "blog/index/listByZxzx";
	}
	@GetMapping("/open/zxzxByid/{id}")
	String zxzxByid(@PathVariable("id") Long id, Model model) {
		ZaixianzixunDO zaixianzixunDO = xContentService.getZxzxById(id);
		if (zaixianzixunDO == null){
			return "error/404";
		}
		model.addAttribute("xZxzx", zaixianzixunDO);
		return "blog/index/zxhf";
	}
	/**
	 * 生成验证码
	 */
	@GetMapping(value = "/open/getVerify")
	public void getVerify(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
			response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expire", 0);
			RandomValidateCodeUtil randomValidateCode = new RandomValidateCodeUtil();
			randomValidateCode.getRandcode(request, response);//输出验证码图片方法
		} catch (Exception e) {
			logger.error("获取验证码失败>>>> ", e);
		}
	}

}
