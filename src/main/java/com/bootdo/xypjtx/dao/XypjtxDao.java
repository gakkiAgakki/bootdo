package com.bootdo.xypjtx.dao;

import com.bootdo.common.utils.Query;
import com.bootdo.system.domain.UserDO;
import com.bootdo.xypjtx.domain.MenuDO;
import com.bootdo.xypjtx.domain.XyggDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by HASEE on 2019/11/28.
 *
 */
@Mapper
public interface XypjtxDao {
    List<MenuDO> listMenu();

    int count(Query query);

    List<XyggDO> getAll(Query query);

    List<String> getMenuPerms(Long userId);

    XyggDO get(Long cid);

    Long getDeptId();

    int userSave(UserDO userDO);
}
