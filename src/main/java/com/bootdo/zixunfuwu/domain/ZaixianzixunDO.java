package com.bootdo.zixunfuwu.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 在线咨询
 * 
 * @author null
 * @email null@null.com
 * @date 2019-11-08 08:41:52
 */
public class ZaixianzixunDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private Long id;
	//标题
	private String title;
	//姓名
	private String xingming;
	//单位名称
	private String danweiname;
	//联系电话
	private String dianhua;
	//邮箱
	private String youxiang;
	//联系地址
	private String lianxidizhi;
	//内容
	private String zixunzhuti;
	//回复
	private String huifu;
	//审核
	private String shenhe;
	//咨询时间
	private Date zixunTime;
	//回复时间
	private Date huifuTime;
	//回复部门
	private String huifuBumen;

	/**
	 * 设置：编号
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：编号
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：标题
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：姓名
	 */
	public void setXingming(String xingming) {
		this.xingming = xingming;
	}
	/**
	 * 获取：姓名
	 */
	public String getXingming() {
		return xingming;
	}
	/**
	 * 设置：单位名称
	 */
	public void setDanweiname(String danweiname) {
		this.danweiname = danweiname;
	}
	/**
	 * 获取：单位名称
	 */
	public String getDanweiname() {
		return danweiname;
	}
	/**
	 * 设置：联系电话
	 */
	public void setDianhua(String dianhua) {
		this.dianhua = dianhua;
	}
	/**
	 * 获取：联系电话
	 */
	public String getDianhua() {
		return dianhua;
	}
	/**
	 * 设置：邮箱
	 */
	public void setYouxiang(String youxiang) {
		this.youxiang = youxiang;
	}
	/**
	 * 获取：邮箱
	 */
	public String getYouxiang() {
		return youxiang;
	}
	/**
	 * 设置：联系地址
	 */
	public void setLianxidizhi(String lianxidizhi) {
		this.lianxidizhi = lianxidizhi;
	}
	/**
	 * 获取：联系地址
	 */
	public String getLianxidizhi() {
		return lianxidizhi;
	}
	/**
	 * 设置：内容
	 */
	public void setZixunzhuti(String zixunzhuti) {
		this.zixunzhuti = zixunzhuti;
	}
	/**
	 * 获取：内容
	 */
	public String getZixunzhuti() {
		return zixunzhuti;
	}
	/**
	 * 设置：回复
	 */
	public void setHuifu(String huifu) {
		this.huifu = huifu;
	}
	/**
	 * 获取：回复
	 */
	public String getHuifu() {
		return huifu;
	}
	/**
	 * 设置：审核
	 */
	public void setShenhe(String shenhe) {
		this.shenhe = shenhe;
	}
	/**
	 * 获取：审核
	 */
	public String getShenhe() {
		return shenhe;
	}
	/**
	 * 设置：咨询时间
	 */
	public void setZixunTime(Date zixunTime) {
		this.zixunTime = zixunTime;
	}
	/**
	 * 获取：咨询时间
	 */
	public Date getZixunTime() {
		return zixunTime;
	}
	/**
	 * 设置：回复时间
	 */
	public void setHuifuTime(Date huifuTime) {
		this.huifuTime = huifuTime;
	}
	/**
	 * 获取：回复时间
	 */
	public Date getHuifuTime() {
		return huifuTime;
	}
	/**
	 * 设置：回复部门
	 */
	public void setHuifuBumen(String huifuBumen) {
		this.huifuBumen = huifuBumen;
	}
	/**
	 * 获取：回复部门
	 */
	public String getHuifuBumen() {
		return huifuBumen;
	}
}
