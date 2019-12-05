package com.bootdo.blog.service;

import com.bootdo.blog.domain.RysqDO;
import com.bootdo.blog.domain.XinxiDO;
import com.bootdo.common.domain.FenleiDO;
import com.bootdo.common.domain.LianjieDO;
import com.bootdo.common.utils.Query;
import com.bootdo.zixunfuwu.domain.ZaixianzixunDO;

import java.util.List;

/**
 * Created by HASEE on 2019/10/22.
 */
public interface XContentService {
    int count(Query query);
    int count1();

    List<XinxiDO> list(Query query);

    List<FenleiDO> getFenlei(String mokuai);

    XinxiDO get(Long cid);

    List<FenleiDO> getFenleiById(Long cid);

    List<XinxiDO> findPic(Query query);

    List<XinxiDO> getByMk(String mokuai);

    List<XinxiDO> getAll(Query query);

    List<FenleiDO> getFenleiByFenlei(String fenlei);

    FenleiDO getfenleiDo(String fenlei);

    int rysqSave(RysqDO rysq);

    List<LianjieDO> getlianjie(String leixing);

    int zxzxSave(ZaixianzixunDO zaixianzixunDO);

    List<ZaixianzixunDO> getZxzx(Query query);

    int ZxzxCount(Query query);

    ZaixianzixunDO getZxzxById(Long id);

}
