package com.fintechsn.huahuadai.mapper.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fintechsn.huahuadai.model.system.SysDepartment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SysDepartmentMapper extends BaseMapper<SysDepartment> {
}
