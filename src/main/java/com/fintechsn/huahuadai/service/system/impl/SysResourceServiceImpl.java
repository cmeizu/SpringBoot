package com.fintechsn.huahuadai.service.system.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fintechsn.huahuadai.common.exception.RequestException;
import com.fintechsn.huahuadai.mapper.system.SysResourceMapper;
import com.fintechsn.huahuadai.model.system.SysResource;
import com.fintechsn.huahuadai.model.system.dto.resource.ResourceDTO;
import com.fintechsn.huahuadai.service.shiro.ShiroService;
import com.fintechsn.huahuadai.service.system.SysResourceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class SysResourceServiceImpl extends ServiceImpl<SysResourceMapper, SysResource> implements SysResourceService {

    @Autowired
    private ShiroService shiroService;

    @Override
    public List<SysResource> findAllResource() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.isNull("parent_id");
        queryWrapper.orderByDesc("sort");
        List<SysResource> allResources = list(queryWrapper);
        if (allResources != null && allResources.size() > 0) {
            allResources.forEach(this::findAllChild);
        }
        return allResources;
    }

    @Override
    public void findAllChild(SysResource resource) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("parent_id", resource.getId());
        queryWrapper.orderByDesc("sort");
        List<SysResource> childrenResources = list(queryWrapper);
        resource.setChildren(childrenResources);
        if (childrenResources != null && childrenResources.size() > 0) {
            childrenResources.forEach(this::findAllChild);
        }
    }

    @Override
    public void add(ResourceDTO dto) {
        SysResource resource = new SysResource();
        BeanUtils.copyProperties(dto, resource);
        resource.setCreateDate(new Date());
        this.save(resource);
        shiroService.reloadPerms();
    }

    @Override
    public void update(String id, ResourceDTO dto) {
        SysResource resource = this.getById(id);
        if (resource == null)
            throw RequestException.fail("更新失败，不存在ID为" + id + "的资源");
        BeanUtils.copyProperties(dto, resource);
        this.updateById(resource);
        shiroService.reloadPerms();
    }

    @Override
    public void remove(String id) {
        SysResource sysResource = getById(id);
        if (sysResource == null)
            throw RequestException.fail("删除失败，不存在ID为" + id + "的资源");
        this.removeById(id);
        shiroService.reloadPerms();
    }


    @Override
    public SysResource getResourceAllParent(SysResource resource, Map<String, SysResource> cacheMap, Map<String, SysResource> cacheMap2) {
        return null;
    }
}
