package com.bootdo.xypjtx.service.impl;

import com.bootdo.common.domain.Tree;
import com.bootdo.common.utils.BuildTree;
import com.bootdo.common.utils.Query;
import com.bootdo.system.domain.UserDO;
import com.bootdo.xypjtx.dao.XypjtxDao;
import com.bootdo.xypjtx.domain.MenuDO;
import com.bootdo.xypjtx.domain.XyggDO;
import com.bootdo.xypjtx.service.XypjtxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by HASEE on 2019/11/28.
 */
@Service
public class XypjtxServiceImpl implements XypjtxService {

    @Autowired
    XypjtxDao xypjtxDao;

    @Override
    public List<Tree<MenuDO>> listMenuTree() {
        List<Tree<MenuDO>> trees = new ArrayList<Tree<MenuDO>>();
        List<MenuDO> menuDOs = xypjtxDao.listMenu();
        for (MenuDO sysMenuDO : menuDOs) {
            Tree<MenuDO> tree = new Tree<MenuDO>();
            tree.setId(sysMenuDO.getMenuId().toString());
            tree.setParentId(sysMenuDO.getParentId().toString());
            tree.setText(sysMenuDO.getName());
            Map<String, Object> attributes = new HashMap<>(16);
            attributes.put("url", sysMenuDO.getUrl());
            attributes.put("icon", sysMenuDO.getIcon());
            tree.setAttributes(attributes);
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        List<Tree<MenuDO>> list = BuildTree.buildList(trees, "0");
        return list;
    }

    /*@Override
    public List<XyggDO> getAll(Query query) {
        try {
            xxx;
        } catch (Exception e) {
        注解生效且不抛异常，暂未测试
        在try{}catch{}的catch中添加：
        在注解加@transactional
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return xypjtxDao.getAll(query);
    }*/
    @Override
    @Transactional
    public List<XyggDO> getAll(Query query) {

        return xypjtxDao.getAll(query);
    }

    @Override
    public int count(Query query) {
        return xypjtxDao.count(query);
    }

    @Override
    public List<String> getMenuPerms(Long userId) {
        return xypjtxDao.getMenuPerms(userId);
    }

    @Override
    public XyggDO get(Long cid) {
        return xypjtxDao.get(cid);
    }

    @Override
    public Long getDeptId() {
        return xypjtxDao.getDeptId();
    }

    @Override
    public int userSave(UserDO userDO) {
        return xypjtxDao.userSave(userDO);
    }

}
