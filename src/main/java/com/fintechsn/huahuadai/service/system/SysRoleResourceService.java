package com.fintechsn.huahuadai.service.system;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fintechsn.huahuadai.model.system.SysResource;
import com.fintechsn.huahuadai.model.system.SysRoleResource;

import java.util.List;

public interface SysRoleResourceService extends IService<SysRoleResource> {
    List<SysResource> findAllResourceByRoleId(String rid);
}
