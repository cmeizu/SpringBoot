package com.fintechsn.huahuadai.service.system;


import com.baomidou.mybatisplus.extension.service.IService;
import com.fintechsn.huahuadai.model.Admin;

import java.util.List;
import java.util.Map;

/**
 * @version : Ver 1.0
 * @AdminService
 * @管理员Service
 */
public interface AdminService extends IService<Admin> {

    List<Admin> selectPage(Map<String, Object> map);

    /**
     * 根据角色查询
     *
     * @param roleId
     * @return
     */
    List<Admin> selectCompanyByRoleId(String roleId);

    /**
     * 查询 催收员
     *
     * @param departmentId
     * @return
     */
    List<Admin> getAdminByDepartmentAndRole(String departmentId);

    /**
     * 查询 管理员和部门信息
     *
     * @param id
     * @return
     */
    Admin getDepartmentById(String id);
}
