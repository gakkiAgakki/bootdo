package com.bootdo.blog.service;

import com.bootdo.blog.domain.XinxiDO;
import com.bootdo.common.domain.FenleiDO;
import com.bootdo.common.domain.MokuaiDO;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * 信息表
 * 
 * @author null
 * @email null@null.com
 * @date 2019-10-14 15:01:29
 */
public interface XinxiService {
	
	XinxiDO get(Long cid);
	
	List<XinxiDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(XinxiDO xinxi);
	
	int update(XinxiDO xinxi);
	
	int remove(Long cid);
	
	int batchRemove(Long[] cids);

	List<MokuaiDO> getMokuai();

	List<FenleiDO> getFenlei(BigInteger parentId);

	int batchShenHe(Long[] cids);

	int batchQuxiaoshenhe(Long[] cids);

    List<String> getMenuPerms(Long userId);

	int batchZhiding(Long[] cids);

	int batchQuxiaoZhiding(Long[] cids);
}
