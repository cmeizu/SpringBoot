package com.fintechsn.huahuadai.mapper.api;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fintechsn.huahuadai.model.api.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @version : Ver 1.0
 * @UserMapper
 * @用户基本信息Mapper
 */
@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {
}
