package com.bootdo.xypjtx.controller;

import com.bootdo.common.annotation.Log;
import com.bootdo.common.domain.Tree;
import com.bootdo.common.utils.*;
import com.bootdo.system.domain.UserDO;
import com.bootdo.xypjtx.domain.MenuDO;
import com.bootdo.xypjtx.domain.XyggDO;
import com.bootdo.xypjtx.service.XypjtxService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
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
 * Created by HASEE on 2019/11/28.
 *
 */
@RequestMapping("/xypjtx")
@Controller
public class XypjtxController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private ThreadLocal<String> methodName = new ThreadLocal<String>();

    @Autowired
    XypjtxService xypjtxService;

    @GetMapping("/open/qiyexinyongpingjia")
    String qiyexinyongpingjia(Model model) {
        List<Tree<MenuDO>> menus = xypjtxService.listMenuTree();
        this.methodName.set("jkl");
        System.out.println(this.methodName.get());
        model.addAttribute("menus", menus);
        return "/blog/xypjtx/index_v2";
    }
    @GetMapping("/open/main")
    String main() {
        return "/blog/xypjtx/main";
    }
    @GetMapping("/open/xyggList")
    String xyggList(@RequestParam(required=true,defaultValue="1")Integer page, Model model,
//								 @PathVariable("mokuai") String mokuai //){
                    @RequestParam Map<String, Object> params ){


        String ul = UserLogin();


        if(ul == "false"){
            return "/blog/xypjtx/login";
        }else if (ul == "0"){
            return "/error/403";
        }

        //pageNum为页面数pageSize为数据条数
        PageHelper.startPage (page,10);
        int offset = (page-1)*5;
        int limit = page;
        params.put("offset",0);
        params.put("limit",5);
        Query query = new Query(params);
        List<XyggDO> list = xypjtxService.getAll(query);
        int total = xypjtxService.count(query);
        PageInfo<XyggDO> p=new PageInfo<XyggDO>(list);
        p.setTotal(total);

        model.addAttribute("xyggList",list);
        model.addAttribute("page",p);
        return "blog/xypjtx/xyggList";
    }
    @GetMapping("/open/post/{cid}")
    String post(@PathVariable("cid") Long cid, Model model) {
        XyggDO xygg = xypjtxService.get(cid);
        if (xygg == null){
            return "error/404";
        }
        //根据id获取分类
//        List<FenleiDO> fenlei = xContentService.getFenleiById(cid);
//        model.addAttribute("xFenlei",fenlei);
        model.addAttribute("xygg", xygg);
        model.addAttribute("gtmCreate", DateUtils.format(xygg.getGtmCreate()));
        return "blog/xypjtx/post";
    }
/*登陆判断*/
    public String UserLogin(){
        UserDO user = ShiroUtils.getUser();
        this.methodName.set(Thread.currentThread().getStackTrace()[2].getMethodName());
        System.out.println("--------------"+this.methodName.get()+"-----------------");
        if (user == null){
            //没有登陆
            return "false";
        }
        List<String> perms = xypjtxService.getMenuPerms(user.getUserId());
        if(perms != null && perms.size() != 0){
//            for (String perm: perms) {
//                if (perm == roleByLogin){
//                    return  "true";
//                }
//            }
            String roleByLogin =Thread.currentThread().getStackTrace()[2].getMethodName();
            boolean contains = perms.contains(roleByLogin);
            if (contains){
                return "true";
            }else {
                return "0";
            }
        }
        return "false";
    }

    @GetMapping("/open/regist")
    String regist() {
        return "/blog/xypjtx/regist";
    }

    @ResponseBody
    @PostMapping("/open/registSave")
    R regist(UserDO userDO, String verify, HttpServletRequest request,Model model) {
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
        /*
        * 添加用户
        * */
            //获取第一个的部门
        Long deptId = xypjtxService.getDeptId();
        userDO.setStatus(0);
        userDO.setDeptId(deptId);
        userDO.setPassword(MD5Utils.encrypt(userDO.getUsername(), userDO.getPassword()));
        if(xypjtxService.userSave(userDO)>0){
            return R.ok();
        }

//        model.addAttribute("gtmCreate", DateUtils.format(xygg.getGtmCreate()));
//        model.addAttribute("gtmCreate", "test");
        return R.error();
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
    @Log("登录")
    @PostMapping("/open/login")
    @ResponseBody
    R ajaxLogin(String username, String password,String verify,HttpServletRequest request) {

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
        password = MD5Utils.encrypt(username, password);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            System.out.println("================="+this.methodName.get()+"================");
            return R.ok(this.methodName.get());
        } catch (AuthenticationException e) {
            return R.error("用户密码错误或用户未被启用");
        }
    }
}
