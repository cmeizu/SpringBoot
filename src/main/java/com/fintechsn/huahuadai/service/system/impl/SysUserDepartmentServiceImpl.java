package com.fintechsn.huahuadai.service.system.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fintechsn.huahuadai.mapper.system.SysUserDepartmentMapper;
import com.fintechsn.huahuadai.model.api.dto.SysUserDepartmentDTO;
import com.fintechsn.huahuadai.model.system.SysUserDepartment;
import com.fintechsn.huahuadai.service.system.SysDepartmentService;
import com.fintechsn.huahuadai.service.system.SysUserDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SysUserDepartmentServiceImpl extends ServiceImpl<SysUserDepartmentMapper, SysUserDepartment> implements SysUserDepartmentService {

    @Autowired
    private SysDepartmentService sysDepartmentService;
    @Autowired
    private SysUserDepartmentMapper sysUserDepartmentMapper;

    @Override
    public void addUserDepartment(String userId, String departmentId) {
        SysUserDepartment sysUserDepartment = new SysUserDepartment();
        sysUserDepartment.setUid(userId);
        sysUserDepartment.setDepartmentid(departmentId);
        save(sysUserDepartment);
    }

    @Override
    public void updateUserDepartment(String userId, String departmentId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("uid", userId);
        SysUserDepartment sysUserDepartment = getOne(queryWrapper);
        sysUserDepartment.setDepartmentid(departmentId);
        updateById(sysUserDepartment);
    }

    @Override
    public List<SysUserDepartmentDTO> listByDepartmentId(String departmentId) {
        return sysUserDepartmentMapper.listByDepartmentId(departmentId);
    }

    @Override
    public SysUserDepartment getByUid(String uid) {
        QueryWrapper<SysUserDepartment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid", uid);
        return getOne(queryWrapper);
    }
}
