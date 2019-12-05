package com.bootdo.xypjtx.service;

import com.bootdo.common.domain.Tree;
import com.bootdo.common.utils.Query;
import com.bootdo.system.domain.UserDO;
import com.bootdo.xypjtx.domain.MenuDO;
import com.bootdo.xypjtx.domain.XyggDO;

import java.util.List;

/**
 * Created by HASEE on 2019/11/28.
 */
public interface XypjtxService {
    List<Tree<MenuDO>> listMenuTree();

    List<XyggDO> getAll(Query query);

    int count(Query query);

    List<String> getMenuPerms(Long userId);

    XyggDO get(Long cid);

    Long getDeptId();

    int userSave(UserDO userDO);
}
