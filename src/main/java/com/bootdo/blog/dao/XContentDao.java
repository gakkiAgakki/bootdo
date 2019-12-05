package com.bootdo.blog.dao;

import com.bootdo.blog.domain.RysqDO;
import com.bootdo.blog.domain.XinxiDO;
import com.bootdo.common.domain.FenleiDO;
import com.bootdo.common.domain.LianjieDO;
import com.bootdo.common.utils.Query;
import com.bootdo.zixunfuwu.domain.ZaixianzixunDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by HASEE on 2019/10/22.
 */
@Mapper
public interface XContentDao {

    int count(Query query);

    List<XinxiDO> list(Query query);

    List<FenleiDO> getFenleilist(@Param("mokuai") String mokuai);

    XinxiDO get(Long cid);

    List<FenleiDO> getFenleiById(@Param("cid") Long cid);

    List<XinxiDO> findPic(Query query);

    List<XinxiDO> getByMk(@Param("mokuai")String mokuai);

    int count1();

    List<XinxiDO> getAll(Query query);

    List<FenleiDO> getFenleiByFenlei(@Param("name") String fenlei);

    FenleiDO getFenleiDo(@Param("name")String fenlei);

    int rysqSave(RysqDO rysq);

    List<LianjieDO> getlianjie(@Param("leixing") String leixing);

    int zxzxSave(ZaixianzixunDO zaixianzixunDO);

    List<ZaixianzixunDO> getZxzx(Query query);

    int ZxzxCount(Query query);

    ZaixianzixunDO getZxzxById(@Param("id") Long id);

}
