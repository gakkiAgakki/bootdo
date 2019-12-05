package com.bootdo.common.domain;

import java.io.Serializable;



/**
 * 相关链接
 * 
 * @author null
 * @email null@null.com
 * @date 2019-10-17 18:23:25
 */
public class LianjieDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private Long id;
	//链接名称
	private String title;
	//链接分类
	private String leixing;
	//链接地址
	private String url;
	//链接说明
	private String shuoming;

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
	 * 设置：链接名称
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：链接名称
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：链接分类
	 */
	public void setLeixing(String leixing) {
		this.leixing = leixing;
	}
	/**
	 * 获取：链接分类
	 */
	public String getLeixing() {
		return leixing;
	}
	/**
	 * 设置：链接地址
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * 获取：链接地址
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * 设置：链接说明
	 */
	public void setShuoming(String shuoming) {
		this.shuoming = shuoming;
	}
	/**
	 * 获取：链接说明
	 */
	public String getShuoming() {
		return shuoming;
	}
}
