package com.fintechsn.huahuadai.mapper.api;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fintechsn.huahuadai.model.api.Channel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ChannelMapper
 * @Mapper
 * @version : Ver 1.0
 */
@Mapper
@Repository
public interface ChannelMapper extends BaseMapper<Channel> {

    List<Channel>  selectByCondition(Channel channel);

    int selectLoanUserCount(Long channelId);

    List<Channel> selectAll();

}
