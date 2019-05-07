package com.fintechsn.huahuadai.service.api.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fintechsn.huahuadai.mapper.api.ChannelMapper;
import com.fintechsn.huahuadai.mapper.api.EveryDayDataMapper;
import com.fintechsn.huahuadai.model.api.Channel;
import com.fintechsn.huahuadai.model.api.EveryDayData;
import com.fintechsn.huahuadai.service.api.EveryDayDataService;
import com.fintechsn.huahuadai.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: zero
 * @date: 2018-11-06
 * @time: 19:45
 */
@Service
public class EveryDayDataServiceImpl extends ServiceImpl<EveryDayDataMapper, EveryDayData> implements EveryDayDataService {

    @Autowired
    private EveryDayDataMapper everyDayDataMapper;

    @Autowired
    public ChannelMapper channelMapper;

    @Override
    public void addEveryDayData(Integer type, Integer channelId) {


        EveryDayData everyDayData = everyDayDataMapper.getTodayEveryDayData(type, channelId);
        if (everyDayData != null && everyDayData.getId() != 0) {
            //扣量逻辑
            Channel c = new Channel();
            c.setId(channelId);
            List<Channel> list = channelMapper.selectByCondition(c);
            if (list != null && list.size() == 1) {
                Channel channel = list.get(0);
                int decrementRate = channel.getDecrementRate();
                decrementRate = decrementRate > 100 ? 100 : decrementRate;
                decrementRate = decrementRate < 0 ? 0 : decrementRate;
                int ran = (int) (Math.random() * 100 + 1);
                //扣量
                if (decrementRate > 0 && ran <= decrementRate) {
                    everyDayDataMapper.updateEveryDayDataBydecrementNum(everyDayData);
                } else {
                    everyDayDataMapper.updateEveryDayData(everyDayData);
                }
            }
        } else {
            everyDayData = new EveryDayData();
            everyDayData.setType(type);
            everyDayData.setDateTime(DateUtils.nowDateSimple());
            everyDayData.setChannelId(channelId);
            everyDayDataMapper.addEveryDayData(everyDayData);

        }
    }
}
