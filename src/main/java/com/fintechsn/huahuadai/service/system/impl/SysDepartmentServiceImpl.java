package com.fintechsn.huahuadai.service.system.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fintechsn.huahuadai.mapper.system.SysDepartmentMapper;
import com.fintechsn.huahuadai.model.system.SysDepartment;
import com.fintechsn.huahuadai.model.system.dto.department.DepartmentAddDTO;
import com.fintechsn.huahuadai.model.system.dto.department.DepartmentUpdateDTO;
import com.fintechsn.huahuadai.model.system.dto.department.FindDepartmentDTO;
import com.fintechsn.huahuadai.service.system.SysDepartmentService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SysDepartmentServiceImpl extends ServiceImpl<SysDepartmentMapper, SysDepartment> implements SysDepartmentService {

    @Override
    public void add(DepartmentAddDTO departmentAddDTO) {
        SysDepartment sysDepartment = new SysDepartment();
        BeanUtils.copyProperties(departmentAddDTO, sysDepartment);
        save(sysDepartment);
    }

    @Override
    public void update(String id, DepartmentUpdateDTO updateDTO) {
        SysDepartment sysDepartment = getById(id);
        BeanUtils.copyProperties(updateDTO, sysDepartment);
        updateById(sysDepartment);
    }

    @Override
    public IPage<SysDepartment> list(FindDepartmentDTO findDepartmentDTO) {
        return page(new Page<>(findDepartmentDTO.getPage(),
                findDepartmentDTO.getPageSize()));
    }

    @Override
    public SysDepartment getDepartment(String departmentId) {
        return getById(departmentId);
    }

    @Override
    public void deleteDepartment(String departmentId) {
        removeById(departmentId);
    }
}
