package com.bootdo.qzgs.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 类别与部门表
 * 
 * @author null
 * @email null@null.com
 * @date 2019-10-17 11:24:01
 */
public class LeibeidanweiDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private Long id;
	//名称
	private String name;
	//数据值
	private String value;
	//部门或类别
	private String leixing;
	//描述
	private String description;
	//备注信息
	private String remarks;

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
	 * 设置：名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：数据值
	 */
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * 获取：数据值
	 */
	public String getValue() {
		return value;
	}
	/**
	 * 设置：部门或类别
	 */
	public void setLeixing(String leixing) {
		this.leixing = leixing;
	}
	/**
	 * 获取：部门或类别
	 */
	public String getLeixing() {
		return leixing;
	}
	/**
	 * 设置：描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * 获取：描述
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * 设置：备注信息
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	/**
	 * 获取：备注信息
	 */
	public String getRemarks() {
		return remarks;
	}
}
