package com.fintechsn.huahuadai.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fintechsn.huahuadai.model.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @AdminMapper
 * @管理员Mapper
 * @version : Ver 1.0
 */
@Mapper
@Repository
public interface AdminMapper extends BaseMapper<Admin> {
    List<Admin> selectPage(Map<String, Object> map);

    /**
     * 根据角色id查询
     * @param roleId
     * @return
     */
    List<Admin> selectCompanyByRoleId(String roleId);

    /**
     * 根据部门查询催收员
     * @param departmentId
     * @return
     */
    List<Admin> getAdminByDepartmentAndRole(String departmentId);

    /**
     * 查询 管理员和部门信息
     * @param id
     * @return
     */
    Admin getDepartmentById(String id);
}
