package com.fintechsn.huahuadai.service.system.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fintechsn.huahuadai.common.exception.RequestException;
import com.fintechsn.huahuadai.mapper.system.SysRoleMapper;
import com.fintechsn.huahuadai.model.system.SysResource;
import com.fintechsn.huahuadai.model.system.SysRole;
import com.fintechsn.huahuadai.model.system.SysRoleResource;
import com.fintechsn.huahuadai.model.system.SysUserRole;
import com.fintechsn.huahuadai.model.system.dto.role.FindRoleDTO;
import com.fintechsn.huahuadai.model.system.dto.role.RoleAddDTO;
import com.fintechsn.huahuadai.model.system.dto.role.RoleUpdateDTO;
import com.fintechsn.huahuadai.service.shiro.ShiroService;
import com.fintechsn.huahuadai.service.system.SysRoleResourceService;
import com.fintechsn.huahuadai.service.system.SysRoleService;
import com.fintechsn.huahuadai.service.system.SysUserRoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private SysRoleResourceService sysRoleResourceService;
    @Autowired
    private ShiroService shiroService;

    @Override
    public List<SysRole> findAllRoleByUserId(String uid, Boolean hasResource) {
        QueryWrapper<SysUserRole> sysUserRoleWrapper = new QueryWrapper<>();
        sysUserRoleWrapper.eq("uid", uid);
        List<SysUserRole> userRoles = sysUserRoleService.list(sysUserRoleWrapper);
        List<SysRole> roles = new ArrayList<>();
        userRoles.forEach(v -> {
            SysRole role = getById(v.getRid());
            if (role != null) {
                if (hasResource) {
                    List<SysResource> permissions = sysRoleResourceService.findAllResourceByRoleId(role.getId());
                    role.setResources(permissions);
                }
            }
            roles.add(role);
        });
        return roles;
    }

    @Override
    public IPage<SysRole> list(FindRoleDTO findRoleDTO) {
        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("id");
        IPage<SysRole> rolePage = page(new Page<SysRole>(findRoleDTO.getPage(),
                findRoleDTO.getPageSize()), queryWrapper);
        if (findRoleDTO.getHasResource()) {
            if (rolePage.getRecords() != null) {
                rolePage.getRecords().forEach(v ->
                        v.setResources(sysRoleResourceService.findAllResourceByRoleId(v.getId())));
            }
        }
        return rolePage;
    }

    @Override
    public void add(RoleAddDTO addDTO) {
        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", addDTO.getName());
        SysRole role = this.getOne(queryWrapper);
        if (role != null) {
            throw RequestException.fail(
                    String.format("已经存在名称为 %s 的角色", addDTO.getName()));
        }
        role = new SysRole();
        BeanUtils.copyProperties(addDTO, role);
        try {
            this.save(role);
            for (SysResource sysResource : addDTO.getResources()) {
                sysRoleResourceService.save(SysRoleResource.builder()
                        .pid(sysResource.getId())
                        .rid(role.getId())
                        .build());
            }
        } catch (Exception e) {
            throw RequestException.fail("添加失败", e);
        }
    }

    @Override
    public void update(String rid, RoleUpdateDTO roleUpdateDTO) {
        SysRole role = getById(rid);
        if (role == null) {
            throw RequestException.fail("角色不存在！");
        }
        BeanUtils.copyProperties(roleUpdateDTO, role);
        try {
            this.updateById(role);
            QueryWrapper<SysRoleResource> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("rid", rid);
            sysRoleResourceService.remove(queryWrapper);
            for (SysResource sysResource : roleUpdateDTO.getResources()) {
                sysRoleResourceService.save(SysRoleResource.builder().pid(sysResource.getId()).rid(role.getId()).build());
            }
            this.updateCache(role, true, false);
        } catch (Exception e) {
            throw RequestException.fail("角色更新失败！", e);
        }
    }

    @Override
    public void updateCache(SysRole role, Boolean author, Boolean out) {
        QueryWrapper<SysUserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("rid", role.getId());
        queryWrapper.groupBy("uid");
        List<SysUserRole> sysUserRoles = sysUserRoleService.list(queryWrapper);
        List<String> userIdList = new ArrayList<>();
        if (sysUserRoles != null && sysUserRoles.size() > 0) {
            sysUserRoles.forEach(v -> userIdList.add(v.getUid()));
        }
        shiroService.clearAuthByUserIdCollection(userIdList, author, out);
    }

    @Override
    public void removeRole(String roleId) {
        SysRole role = this.getById(roleId);
        if (role == null) {
            throw RequestException.fail("角色不存在！");
        }
        try {
            this.removeById(roleId);
            this.updateCache(role, true, false);
        } catch (DataIntegrityViolationException e) {
            throw RequestException.fail(
                    String.format("请先解除角色为 %s 角色的全部用户！", role.getName()), e);
        } catch (Exception e) {
            throw RequestException.fail("角色删除失败！", e);
        }
    }
}
