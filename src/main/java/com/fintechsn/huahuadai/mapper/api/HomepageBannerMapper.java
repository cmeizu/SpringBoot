package com.fintechsn.huahuadai.mapper.api;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fintechsn.huahuadai.model.api.HomepageBanner;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface HomepageBannerMapper extends BaseMapper<HomepageBanner> {
}
