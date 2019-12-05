package com.bootdo.blog.domain;

import java.io.Serializable;



/**
 * 入园申请表
 * 
 * @author null
 * @email null@null.com
 * @date 2019-10-28 11:21:10
 */
public class RysqDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long cid;
	//企业名称
	private String title;
	//法人代表
	private String frdb;
	//法人联系方式
	private String frlxfs;
	//项目联系人
	private String xmlxr;
	//项目联系人联系方式
	private String xmlxrlxfs;
	//电子邮箱
	private String dzyx;
	//网址
	private String wz;
	//项目名称
	private String xmmc;
	//项目总投资
	private String xmztz;
	//注册地址
	private String zcdz;
	//主要建设内容1
	private String zyjsnr1;
	//主要建设内容2
	private String zyjsnr2;
	//主要建设内容3
	private String zyjsnr3;
	//入驻方式_租赁
	private String rzfsZl;
	//入驻方式_新增
	private String rzfsXz;
	//效益_预计年产值
	private String xyYjncz;
	//效益_年销售收入
	private String xyNxssr;
	//效益_年利润
	private String xyNlr;
	//效益_年税收
	private String xyNss;
	//审核
	private String sh;

	/**
	 * 设置：
	 */
	public void setCid(Long cid) {
		this.cid = cid;
	}
	/**
	 * 获取：
	 */
	public Long getCid() {
		return cid;
	}
	/**
	 * 设置：企业名称
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：企业名称
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：法人代表
	 */
	public void setFrdb(String frdb) {
		this.frdb = frdb;
	}
	/**
	 * 获取：法人代表
	 */
	public String getFrdb() {
		return frdb;
	}
	/**
	 * 设置：法人联系方式
	 */
	public void setFrlxfs(String frlxfs) {
		this.frlxfs = frlxfs;
	}
	/**
	 * 获取：法人联系方式
	 */
	public String getFrlxfs() {
		return frlxfs;
	}
	/**
	 * 设置：项目联系人
	 */
	public void setXmlxr(String xmlxr) {
		this.xmlxr = xmlxr;
	}
	/**
	 * 获取：项目联系人
	 */
	public String getXmlxr() {
		return xmlxr;
	}
	/**
	 * 设置：项目联系人联系方式
	 */
	public void setXmlxrlxfs(String xmlxrlxfs) {
		this.xmlxrlxfs = xmlxrlxfs;
	}
	/**
	 * 获取：项目联系人联系方式
	 */
	public String getXmlxrlxfs() {
		return xmlxrlxfs;
	}
	/**
	 * 设置：电子邮箱
	 */
	public void setDzyx(String dzyx) {
		this.dzyx = dzyx;
	}
	/**
	 * 获取：电子邮箱
	 */
	public String getDzyx() {
		return dzyx;
	}
	/**
	 * 设置：网址
	 */
	public void setWz(String wz) {
		this.wz = wz;
	}
	/**
	 * 获取：网址
	 */
	public String getWz() {
		return wz;
	}
	/**
	 * 设置：项目名称
	 */
	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}
	/**
	 * 获取：项目名称
	 */
	public String getXmmc() {
		return xmmc;
	}
	/**
	 * 设置：项目总投资
	 */
	public void setXmztz(String xmztz) {
		this.xmztz = xmztz;
	}
	/**
	 * 获取：项目总投资
	 */
	public String getXmztz() {
		return xmztz;
	}
	/**
	 * 设置：注册地址
	 */
	public void setZcdz(String zcdz) {
		this.zcdz = zcdz;
	}
	/**
	 * 获取：注册地址
	 */
	public String getZcdz() {
		return zcdz;
	}
	/**
	 * 设置：主要建设内容1
	 */
	public void setZyjsnr1(String zyjsnr1) {
		this.zyjsnr1 = zyjsnr1;
	}
	/**
	 * 获取：主要建设内容1
	 */
	public String getZyjsnr1() {
		return zyjsnr1;
	}
	/**
	 * 设置：主要建设内容2
	 */
	public void setZyjsnr2(String zyjsnr2) {
		this.zyjsnr2 = zyjsnr2;
	}
	/**
	 * 获取：主要建设内容2
	 */
	public String getZyjsnr2() {
		return zyjsnr2;
	}
	/**
	 * 设置：主要建设内容3
	 */
	public void setZyjsnr3(String zyjsnr3) {
		this.zyjsnr3 = zyjsnr3;
	}
	/**
	 * 获取：主要建设内容3
	 */
	public String getZyjsnr3() {
		return zyjsnr3;
	}
	/**
	 * 设置：入驻方式_租赁
	 */
	public void setRzfsZl(String rzfsZl) {
		this.rzfsZl = rzfsZl;
	}
	/**
	 * 获取：入驻方式_租赁
	 */
	public String getRzfsZl() {
		return rzfsZl;
	}
	/**
	 * 设置：入驻方式_新增
	 */
	public void setRzfsXz(String rzfsXz) {
		this.rzfsXz = rzfsXz;
	}
	/**
	 * 获取：入驻方式_新增
	 */
	public String getRzfsXz() {
		return rzfsXz;
	}
	/**
	 * 设置：效益_预计年产值
	 */
	public void setXyYjncz(String xyYjncz) {
		this.xyYjncz = xyYjncz;
	}
	/**
	 * 获取：效益_预计年产值
	 */
	public String getXyYjncz() {
		return xyYjncz;
	}
	/**
	 * 设置：效益_年销售收入
	 */
	public void setXyNxssr(String xyNxssr) {
		this.xyNxssr = xyNxssr;
	}
	/**
	 * 获取：效益_年销售收入
	 */
	public String getXyNxssr() {
		return xyNxssr;
	}
	/**
	 * 设置：效益_年利润
	 */
	public void setXyNlr(String xyNlr) {
		this.xyNlr = xyNlr;
	}
	/**
	 * 获取：效益_年利润
	 */
	public String getXyNlr() {
		return xyNlr;
	}
	/**
	 * 设置：效益_年税收
	 */
	public void setXyNss(String xyNss) {
		this.xyNss = xyNss;
	}
	/**
	 * 获取：效益_年税收
	 */
	public String getXyNss() {
		return xyNss;
	}
	/**
	 * 设置：审核
	 */
	public void setSh(String sh) {
		this.sh = sh;
	}
	/**
	 * 获取：审核
	 */
	public String getSh() {
		return sh;
	}
}
