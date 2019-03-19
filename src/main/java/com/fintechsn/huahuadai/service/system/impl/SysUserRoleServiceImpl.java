package com.fintechsn.huahuadai.service.system.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fintechsn.huahuadai.mapper.system.SysUserRoleMapper;
import com.fintechsn.huahuadai.model.system.SysUserRole;
import com.fintechsn.huahuadai.service.system.SysUserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {
}
