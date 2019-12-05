package com.bootdo.qzgs.service;

import com.bootdo.qzgs.domain.GsqdDO;
import com.bootdo.qzgs.domain.LeibeidanweiDO;

import java.util.List;
import java.util.Map;

/**
 * 公示清单
 * 
 * @author null
 * @email null@null.com
 * @date 2019-10-17 14:22:56
 */
public interface GsqdService {
	
	GsqdDO get(Long id);
	
	List<GsqdDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(GsqdDO gsqd);
	
	int update(GsqdDO gsqd);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

    List<LeibeidanweiDO> getLbOrBm();
}
