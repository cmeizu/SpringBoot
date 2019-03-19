package com.fintechsn.huahuadai.service.system;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fintechsn.huahuadai.model.system.SysDepartment;
import com.fintechsn.huahuadai.model.system.dto.department.DepartmentAddDTO;
import com.fintechsn.huahuadai.model.system.dto.department.DepartmentUpdateDTO;
import com.fintechsn.huahuadai.model.system.dto.department.FindDepartmentDTO;

public interface SysDepartmentService extends IService<SysDepartment> {

    /**
     * 添加部门
     *
     * @param departmentAddDTO 部门数据DTO
     */
    void add(DepartmentAddDTO departmentAddDTO);

    /**
     * 更新部门数据
     *
     * @param id        用户id
     * @param updateDTO 部门数据DTO
     */
    void update(String id, DepartmentUpdateDTO updateDTO);

    /**
     * 列表展示
     *
     * @param findDepartmentDTO
     * @return
     */
    IPage<SysDepartment> list(FindDepartmentDTO findDepartmentDTO);

    /**
     * 获取部门信息
     *
     * @param departmentId
     * @return
     */
    SysDepartment getDepartment(String departmentId);

    /**
     * 删除部门信息
     *
     * @param departmentId
     */
    void deleteDepartment(String departmentId);
}
