package com.fintechsn.huahuadai.mapper.api;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fintechsn.huahuadai.model.api.EveryDayData;
import com.fintechsn.huahuadai.model.api.param.ChannelListStatisticeParam;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface EveryDayDataMapper extends BaseMapper<EveryDayData> {

    int addEveryDayData(EveryDayData everyDayData);

    int updateEveryDayData(EveryDayData everyDayData);

    //扣量
    int updateEveryDayDataBydecrementNum(EveryDayData everyDayData);

    EveryDayData getTodayEveryDayData(int type, long channelId);

    JSONObject getTimeEveryDayData(JSONObject parameter);

    JSONObject getTimeEveryDayData(ChannelListStatisticeParam channelListStatisticeParam);
}
