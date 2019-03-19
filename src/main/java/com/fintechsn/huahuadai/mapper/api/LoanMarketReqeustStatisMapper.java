package com.fintechsn.huahuadai.mapper.api;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fintechsn.huahuadai.model.api.LoanMarketReqeustStatis;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LoanMarketReqeustStatisMapper extends BaseMapper<LoanMarketReqeustStatis> {
    void updatePvByChannelId(@Param(value = "channelId") Integer channelId);

    void updatePvUvByChannelId(@Param(value = "channelId") Integer channelId);

    LoanMarketReqeustStatis selectByChannelId(Integer channelId);

    List<LoanMarketReqeustStatis> selectByList();
}
