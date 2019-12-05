package com.bootdo.xypjtx.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 信用评价体系_行政许可
 * 
 * @author null
 * @email null@null.com
 * @date 2019-12-04 08:01:21
 */
public class XzxkDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//ID
	private Long xid;
	//主体名称
	private String ztmc;
	//统一社会信用代码
	private String tyshxydm;
	//工商登记证号
	private String gsdjzh;
	//法定代表人姓名
	private String fddbrxm;
	//项目名称
	private String xmmc;
	//行政许可决定书文号
	private String xzxkjdswh;
	//审批类别
	private String splb;
	//许可内容
	private String xknr;
	//许可生效期
	private Date xksxq;
	//许可截止期
	private Date xkjzq;
	//许可机关
	private String xkjg;
	//当前状态
	private String dqzt;
	//地方编码
	private String dfbm;
	//信息提供部门
	private String xxtgbm;
	//数据报送时间
	private Date sjbssj;
	//信用承诺书
	private String xycns;
	//地址
	private String dz;
	//守信次数
	private String shouxcs;
	//失信次数
	private String shixcs;
	//行政处罚次数
	private String xzcfcs;
	//审核
	private Integer sh;
	//提交者
	private String tjz;
	//备用1
	private String beiyong1;
	//备用2
	private String beiyong2;

	/**
	 * 设置：ID
	 */
	public void setXid(Long xid) {
		this.xid = xid;
	}
	/**
	 * 获取：ID
	 */
	public Long getXid() {
		return xid;
	}
	/**
	 * 设置：主体名称
	 */
	public void setZtmc(String ztmc) {
		this.ztmc = ztmc;
	}
	/**
	 * 获取：主体名称
	 */
	public String getZtmc() {
		return ztmc;
	}
	/**
	 * 设置：统一社会信用代码
	 */
	public void setTyshxydm(String tyshxydm) {
		this.tyshxydm = tyshxydm;
	}
	/**
	 * 获取：统一社会信用代码
	 */
	public String getTyshxydm() {
		return tyshxydm;
	}
	/**
	 * 设置：工商登记证号
	 */
	public void setGsdjzh(String gsdjzh) {
		this.gsdjzh = gsdjzh;
	}
	/**
	 * 获取：工商登记证号
	 */
	public String getGsdjzh() {
		return gsdjzh;
	}
	/**
	 * 设置：法定代表人姓名
	 */
	public void setFddbrxm(String fddbrxm) {
		this.fddbrxm = fddbrxm;
	}
	/**
	 * 获取：法定代表人姓名
	 */
	public String getFddbrxm() {
		return fddbrxm;
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
	 * 设置：行政许可决定书文号
	 */
	public void setXzxkjdswh(String xzxkjdswh) {
		this.xzxkjdswh = xzxkjdswh;
	}
	/**
	 * 获取：行政许可决定书文号
	 */
	public String getXzxkjdswh() {
		return xzxkjdswh;
	}
	/**
	 * 设置：审批类别
	 */
	public void setSplb(String splb) {
		this.splb = splb;
	}
	/**
	 * 获取：审批类别
	 */
	public String getSplb() {
		return splb;
	}
	/**
	 * 设置：许可内容
	 */
	public void setXknr(String xknr) {
		this.xknr = xknr;
	}
	/**
	 * 获取：许可内容
	 */
	public String getXknr() {
		return xknr;
	}
	/**
	 * 设置：许可生效期
	 */
	public void setXksxq(Date xksxq) {
		this.xksxq = xksxq;
	}
	/**
	 * 获取：许可生效期
	 */
	public Date getXksxq() {
		return xksxq;
	}
	/**
	 * 设置：许可截止期
	 */
	public void setXkjzq(Date xkjzq) {
		this.xkjzq = xkjzq;
	}
	/**
	 * 获取：许可截止期
	 */
	public Date getXkjzq() {
		return xkjzq;
	}
	/**
	 * 设置：许可机关
	 */
	public void setXkjg(String xkjg) {
		this.xkjg = xkjg;
	}
	/**
	 * 获取：许可机关
	 */
	public String getXkjg() {
		return xkjg;
	}
	/**
	 * 设置：当前状态
	 */
	public void setDqzt(String dqzt) {
		this.dqzt = dqzt;
	}
	/**
	 * 获取：当前状态
	 */
	public String getDqzt() {
		return dqzt;
	}
	/**
	 * 设置：地方编码
	 */
	public void setDfbm(String dfbm) {
		this.dfbm = dfbm;
	}
	/**
	 * 获取：地方编码
	 */
	public String getDfbm() {
		return dfbm;
	}
	/**
	 * 设置：信息提供部门
	 */
	public void setXxtgbm(String xxtgbm) {
		this.xxtgbm = xxtgbm;
	}
	/**
	 * 获取：信息提供部门
	 */
	public String getXxtgbm() {
		return xxtgbm;
	}
	/**
	 * 设置：数据报送时间
	 */
	public void setSjbssj(Date sjbssj) {
		this.sjbssj = sjbssj;
	}
	/**
	 * 获取：数据报送时间
	 */
	public Date getSjbssj() {
		return sjbssj;
	}
	/**
	 * 设置：信用承诺书
	 */
	public void setXycns(String xycns) {
		this.xycns = xycns;
	}
	/**
	 * 获取：信用承诺书
	 */
	public String getXycns() {
		return xycns;
	}
	/**
	 * 设置：地址
	 */
	public void setDz(String dz) {
		this.dz = dz;
	}
	/**
	 * 获取：地址
	 */
	public String getDz() {
		return dz;
	}
	/**
	 * 设置：守信次数
	 */
	public void setShouxcs(String shouxcs) {
		this.shouxcs = shouxcs;
	}
	/**
	 * 获取：守信次数
	 */
	public String getShouxcs() {
		return shouxcs;
	}
	/**
	 * 设置：失信次数
	 */
	public void setShixcs(String shixcs) {
		this.shixcs = shixcs;
	}
	/**
	 * 获取：失信次数
	 */
	public String getShixcs() {
		return shixcs;
	}
	/**
	 * 设置：行政处罚次数
	 */
	public void setXzcfcs(String xzcfcs) {
		this.xzcfcs = xzcfcs;
	}
	/**
	 * 获取：行政处罚次数
	 */
	public String getXzcfcs() {
		return xzcfcs;
	}
	/**
	 * 设置：审核
	 */
	public void setSh(Integer sh) {
		this.sh = sh;
	}
	/**
	 * 获取：审核
	 */
	public Integer getSh() {
		return sh;
	}
	/**
	 * 设置：提交者
	 */
	public void setTjz(String tjz) {
		this.tjz = tjz;
	}
	/**
	 * 获取：提交者
	 */
	public String getTjz() {
		return tjz;
	}
	/**
	 * 设置：备用1
	 */
	public void setBeiyong1(String beiyong1) {
		this.beiyong1 = beiyong1;
	}
	/**
	 * 获取：备用1
	 */
	public String getBeiyong1() {
		return beiyong1;
	}
	/**
	 * 设置：备用2
	 */
	public void setBeiyong2(String beiyong2) {
		this.beiyong2 = beiyong2;
	}
	/**
	 * 获取：备用2
	 */
	public String getBeiyong2() {
		return beiyong2;
	}
}
