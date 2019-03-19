package com.fintechsn.huahuadai.service.system;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fintechsn.huahuadai.model.api.dto.SysUserDepartmentDTO;
import com.fintechsn.huahuadai.model.system.SysUserDepartment;

import java.util.List;

public interface SysUserDepartmentService extends IService<SysUserDepartment> {

    /**
     * 添加用户 部门关联
     *
     * @param userId
     * @param departmentId
     */
    void addUserDepartment(String userId, String departmentId);

    /**
     * 更新用户 部门关联
     *
     * @param userId
     * @param departmentId
     */
    void updateUserDepartment(String userId, String departmentId);

    /**
     * @param departmentId
     * @return
     */
    List<SysUserDepartmentDTO> listByDepartmentId(String departmentId);

    /**
     * 根据用户的查询部门关联
     *
     * @param uid
     * @return
     */
    SysUserDepartment getByUid(String uid);
}
