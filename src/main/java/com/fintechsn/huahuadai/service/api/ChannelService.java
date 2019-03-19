package com.fintechsn.huahuadai.service.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fintechsn.huahuadai.model.api.Channel;
import com.fintechsn.huahuadai.model.api.param.ChannelAddParam;
import com.fintechsn.huahuadai.model.api.param.ChannelListParam;
import com.fintechsn.huahuadai.model.api.param.ChannelListStatisticeParam;
import com.fintechsn.huahuadai.model.api.vo.ChannelStatiscsVO;

import java.util.List;

public interface ChannelService extends IService<Channel> {

    IPage<Channel> list(ChannelListParam channelListParam);

    List<Channel> listWithOutPage();

    boolean add(ChannelAddParam channelAddParam);

    boolean update(String id,ChannelAddParam channelAddParam);

    boolean delete(String id);

    Channel getChannelById(String id);

    String onOroffByChannelId(String id);

}
