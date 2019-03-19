package com.fintechsn.huahuadai.mapper.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fintechsn.huahuadai.model.api.dto.SysUserDepartmentDTO;
import com.fintechsn.huahuadai.model.system.SysUserDepartment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SysUserDepartmentMapper extends BaseMapper<SysUserDepartment> {

    List<SysUserDepartmentDTO> listByDepartmentId(String departmentId);

}
