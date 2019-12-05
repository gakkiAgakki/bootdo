package com.bootdo.blog.service.impl;

import com.bootdo.blog.dao.XContentDao;
import com.bootdo.blog.domain.RysqDO;
import com.bootdo.blog.domain.XinxiDO;
import com.bootdo.blog.service.XContentService;
import com.bootdo.common.domain.FenleiDO;
import com.bootdo.common.domain.LianjieDO;
import com.bootdo.common.utils.Query;
import com.bootdo.zixunfuwu.domain.ZaixianzixunDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by HASEE on 2019/10/22.
 */
@Service
public class XContentServiceImpl implements XContentService {
    @Autowired
    private XContentDao XContentMapper;

    @Override
    public int count(Query query) {
        return XContentMapper.count(query);
    }

    @Override
    public int count1() {
        return XContentMapper.count1();
    }

    @Override
    public List<XinxiDO> list(Query query) {
        return XContentMapper.list(query);
    }

    @Override
    public List<FenleiDO> getFenlei(String mokuai) {
        return XContentMapper.getFenleilist(mokuai);
    }

    @Override
    public XinxiDO get(Long cid) {
        return XContentMapper.get(cid);
    }

    @Override
    public List<FenleiDO> getFenleiById(Long cid) {
        return XContentMapper.getFenleiById(cid);
    }

    @Override
    public List<XinxiDO> findPic(Query query) {
        return XContentMapper.findPic(query);
    }

    @Override
    public List<XinxiDO> getByMk(String mokuai) {
        return XContentMapper.getByMk(mokuai);
    }

    @Override
    public List<XinxiDO> getAll(Query query) {
        return XContentMapper.getAll(query);
    }

    @Override
    public List<FenleiDO> getFenleiByFenlei(String fenlei) {
        return XContentMapper.getFenleiByFenlei(fenlei);
    }

    @Override
    public FenleiDO getfenleiDo(String fenlei) {
        return XContentMapper.getFenleiDo(fenlei);
    }

    @Override
    public int rysqSave(RysqDO rysq) {
        return XContentMapper.rysqSave(rysq);
    }

    @Override
    public List<LianjieDO> getlianjie(String leixing) {
        return XContentMapper.getlianjie(leixing);
    }

    @Override
    public int zxzxSave(ZaixianzixunDO zaixianzixunDO) {
        return XContentMapper.zxzxSave(zaixianzixunDO);
    }

    @Override
    public List<ZaixianzixunDO> getZxzx(Query query) {
        return XContentMapper.getZxzx(query);
    }

    @Override
    public int ZxzxCount(Query query) {
        return XContentMapper.ZxzxCount(query);
    }

    @Override
    public ZaixianzixunDO getZxzxById(Long id) {
        return XContentMapper.getZxzxById(id);
    }

}
