package com.fintechsn.huahuadai.service.system.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fintechsn.huahuadai.mapper.system.SysRoleResourceMapper;
import com.fintechsn.huahuadai.model.system.SysResource;
import com.fintechsn.huahuadai.model.system.SysRoleResource;
import com.fintechsn.huahuadai.service.system.SysResourceService;
import com.fintechsn.huahuadai.service.system.SysRoleResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SysRoleResourceServiceImpl extends ServiceImpl<SysRoleResourceMapper, SysRoleResource> implements SysRoleResourceService {

    @Autowired
    private SysResourceService sysResourceService;

    @Override
    public List<SysResource> findAllResourceByRoleId(String rid) {
        QueryWrapper sysRoleResourceWrapper1 = new QueryWrapper();
        sysRoleResourceWrapper1.eq("rid", rid);
        List<SysRoleResource> rps = list(sysRoleResourceWrapper1);
        if (rps != null) {
            List<String> pids = new ArrayList<>();
            rps.forEach(v -> pids.add(v.getPid()));
            if (pids.size() == 0) {
                return null;
            }
            QueryWrapper sysRoleResourceWrapper2 = new QueryWrapper();
            sysRoleResourceWrapper2.in("id", pids);
            sysRoleResourceWrapper2.orderByAsc("sort");
            return sysResourceService.list(sysRoleResourceWrapper2);
        }
        return null;
    }
}
