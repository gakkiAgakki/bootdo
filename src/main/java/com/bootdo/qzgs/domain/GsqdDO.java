package com.bootdo.qzgs.domain;

import java.io.Serializable;



/**
 * 公示清单
 * 
 * @author null
 * @email null@null.com
 * @date 2019-10-17 14:22:56
 */
public class GsqdDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private Long id;
	//事项名称
	private String title;
	//编码
	private String bianma;
	//职权类型
	private String zqlx;
	//行使主体
	private String xszt;
	//实施部门
	private String ssbm;
	//职权依据
	private String zqyj;
	//责任事项
	private String zrsx;
	//责任事项依据
	private String zrsxyj;
	//备注
	private String beizhu;
	//办事指南表
	private String bsznb;
	//运行流程图
	private String yxlct;
	//廉政风险防控图
	private String lzfxfkt;

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
	 * 设置：事项名称
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：事项名称
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：编码
	 */
	public void setBianma(String bianma) {
		this.bianma = bianma;
	}
	/**
	 * 获取：编码
	 */
	public String getBianma() {
		return bianma;
	}
	/**
	 * 设置：职权类型
	 */
	public void setZqlx(String zqlx) {
		this.zqlx = zqlx;
	}
	/**
	 * 获取：职权类型
	 */
	public String getZqlx() {
		return zqlx;
	}
	/**
	 * 设置：行使主体
	 */
	public void setXszt(String xszt) {
		this.xszt = xszt;
	}
	/**
	 * 获取：行使主体
	 */
	public String getXszt() {
		return xszt;
	}
	/**
	 * 设置：实施部门
	 */
	public void setSsbm(String ssbm) {
		this.ssbm = ssbm;
	}
	/**
	 * 获取：实施部门
	 */
	public String getSsbm() {
		return ssbm;
	}
	/**
	 * 设置：职权依据
	 */
	public void setZqyj(String zqyj) {
		this.zqyj = zqyj;
	}
	/**
	 * 获取：职权依据
	 */
	public String getZqyj() {
		return zqyj;
	}
	/**
	 * 设置：责任事项
	 */
	public void setZrsx(String zrsx) {
		this.zrsx = zrsx;
	}
	/**
	 * 获取：责任事项
	 */
	public String getZrsx() {
		return zrsx;
	}
	/**
	 * 设置：责任事项依据
	 */
	public void setZrsxyj(String zrsxyj) {
		this.zrsxyj = zrsxyj;
	}
	/**
	 * 获取：责任事项依据
	 */
	public String getZrsxyj() {
		return zrsxyj;
	}
	/**
	 * 设置：备注
	 */
	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}
	/**
	 * 获取：备注
	 */
	public String getBeizhu() {
		return beizhu;
	}
	/**
	 * 设置：办事指南表
	 */
	public void setBsznb(String bsznb) {
		this.bsznb = bsznb;
	}
	/**
	 * 获取：办事指南表
	 */
	public String getBsznb() {
		return bsznb;
	}
	/**
	 * 设置：运行流程图
	 */
	public void setYxlct(String yxlct) {
		this.yxlct = yxlct;
	}
	/**
	 * 获取：运行流程图
	 */
	public String getYxlct() {
		return yxlct;
	}
	/**
	 * 设置：廉政风险防控图
	 */
	public void setLzfxfkt(String lzfxfkt) {
		this.lzfxfkt = lzfxfkt;
	}
	/**
	 * 获取：廉政风险防控图
	 */
	public String getLzfxfkt() {
		return lzfxfkt;
	}
}
