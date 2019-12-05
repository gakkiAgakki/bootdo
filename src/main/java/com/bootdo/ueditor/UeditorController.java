package com.bootdo.ueditor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * Created by HASEE on 2019/10/14.
 */
@Controller
//@RequestMapping("/ueditor")
public class UeditorController {
//    @RequestMapping("/index")
//    private String showPage(){
//        return "blog/xinxi/add";
//    }

    @RequestMapping(value="/ueditor")
    @ResponseBody
    public String ueditor(HttpServletRequest request) {
        return PublicMsg.UEDITOR_CONFIG;
    }

    @RequestMapping(value="/imgUpload")
    @ResponseBody
    public Ueditor imgUpload(MultipartFile upfile) {
        Ueditor ueditor = new Ueditor();
        if (upfile.isEmpty()) {
            ueditor.setState("上传失败");
            return ueditor;
        }
        // 获取文件名
        String fileName = upfile.getOriginalFilename();
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //时间戳
        long time = System.currentTimeMillis() / 1000;
        // 这里我使用随机字符串来重新命名图片
        fileName = fileName.substring(0,fileName.indexOf(".")) + time + suffixName;

        // 这里的路径为Nginx的代理路径，这里是/data/images/xxx.png
        String classpath = this.getClass().getResource("/static").getPath().replaceFirst("/", "");

        File dest =  new File(classpath + "/ueditor/jsp/upload/image/" + fileName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            upfile.transferTo(dest);
            ueditor.setState("SUCCESS");
            ueditor.setOriginal(fileName);
            ueditor.setUrl("/ueditor/jsp/upload/image/" + fileName);
            ueditor.setTitle(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ueditor;
    }
    @RequestMapping(value="/videoUpload")
    @ResponseBody
    public Ueditor videoUpload(MultipartFile upfile) {
        Ueditor ueditor = new Ueditor();
        if (upfile.isEmpty()) {
            ueditor.setState("上传失败");
            return ueditor;
        }
        // 获取文件名
        String fileName = upfile.getOriginalFilename();
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //时间戳
        long time = System.currentTimeMillis() / 1000;
        // 这里我使用随机字符串来重新命名图片
        fileName = fileName.substring(0,fileName.indexOf(".")) + time + suffixName;

        // 这里的路径为Nginx的代理路径，这里是/data/images/xxx.png
        String classpath = this.getClass().getResource("/static").getPath().replaceFirst("/", "");

        File dest =  new File(classpath + "/ueditor/jsp/upload/video/" + fileName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            upfile.transferTo(dest);
            ueditor.setState("SUCCESS");
            ueditor.setOriginal(fileName);
            ueditor.setUrl("/ueditor/jsp/upload/video/" + fileName);
            ueditor.setTitle(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ueditor;
    }
    @RequestMapping(value="/fileUpload")
    @ResponseBody
    public Ueditor fileUpload(MultipartFile upfile) {
        Ueditor ueditor = new Ueditor();
        if (upfile.isEmpty()) {
            ueditor.setState("上传失败");
            return ueditor;
        }
        // 获取文件名
        String fileName = upfile.getOriginalFilename();
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //时间戳
        long time = System.currentTimeMillis() / 1000;
        // 这里我使用随机字符串来重新命名图片
        fileName = fileName.substring(0,fileName.indexOf(".")) + time + suffixName;

        // 这里的路径为Nginx的代理路径，这里是/data/images/xxx.png
        String classpath = this.getClass().getResource("/static").getPath().replaceFirst("/", "");
        /*在目录中添加日期字符串作为文件夹*/
        File dest =  new File(classpath + "/ueditor/jsp/upload/file/" + fileName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            upfile.transferTo(dest);
            ueditor.setState("SUCCESS");
            ueditor.setOriginal(fileName);
            ueditor.setUrl("/ueditor/jsp/upload/file/" + fileName);
            ueditor.setTitle(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ueditor;
    }
}
