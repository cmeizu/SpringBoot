package com.fintechsn.huahuadai.service.api.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fintechsn.huahuadai.constant.LinkUrlConstant;
import com.fintechsn.huahuadai.mapper.api.ChannelMapper;
import com.fintechsn.huahuadai.model.api.Channel;
import com.fintechsn.huahuadai.model.api.param.ChannelAddParam;
import com.fintechsn.huahuadai.model.api.param.ChannelListParam;
import com.fintechsn.huahuadai.service.api.ChannelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
public class ChannelServiceImpl extends ServiceImpl<ChannelMapper, Channel> implements ChannelService {

    @Autowired
    private ChannelService channelService;

    @Override
    public IPage<Channel> list(ChannelListParam channelListParam) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("del_flag","0");
        IPage<Channel> channelList = page(new Page(channelListParam.getPage(), channelListParam.getPageSize()),queryWrapper);
        return channelList;
    }

    @Override
    public List<Channel> listWithOutPage() {
        return list();
    }

    @Override
    public boolean add(ChannelAddParam channelAddParam) {
        QueryWrapper<Channel> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("login_name", channelAddParam.getLoginName());
        Channel oldChannel = getOne(queryWrapper);
        if (ObjectUtils.isNotEmpty(oldChannel)) {
            return false;
        }
        Channel channel = new Channel();
        BeanUtils.copyProperties(channelAddParam, channel);
        String linkUrl = LinkUrlConstant.LINK_URL + channel.getLoginName();
        channel.setLinkUrl(linkUrl);
        return save(channel);
    }

    @Override
    public boolean update(String id, ChannelAddParam channelAddParam) {
        Channel channel = getById(id);
        BeanUtils.copyProperties(channelAddParam, channel);
        return updateById(channel);
    }

    @Override
    public boolean delete(String id) {
        Channel channel = channelService.getById(id);
        channel.setDelFlag("1");
        return channelService.updateById(channel);
    }

    @Override
    public Channel getChannelById(String id) {
        return getById(id);
    }

    @Override
    public String onOroffByChannelId(String id) {
        String messgae = "";
        if (id != null) {
            Channel channel = channelService.getById(id);
            if (channel.getIsEnable().equals("0")) {
                channel.setIsEnable("1");
                messgae = "下架成功";
            } else {
                channel.setIsEnable("0");
                messgae = "上架成功";
            }
            channelService.updateById(channel);
        }
        return messgae;
    }

}
