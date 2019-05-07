package com.fintechsn.huahuadai.service.api.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fintechsn.huahuadai.mapper.api.ChanneluvRequestRecordMapper;
import com.fintechsn.huahuadai.model.api.Channel;
import com.fintechsn.huahuadai.model.api.ChanneluvRequestRecord;
import com.fintechsn.huahuadai.service.api.ChannelService;
import com.fintechsn.huahuadai.service.api.ChanneluvRequestRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ChanneluvRequestRecordServiceImpl extends ServiceImpl<ChanneluvRequestRecordMapper, ChanneluvRequestRecord> implements ChanneluvRequestRecordService {
    @Autowired
    private ChanneluvRequestRecordMapper recordMapper;
    @Autowired
    private ChannelService channelService;

    /**
     * 渠道uv和pv统计
     *
     * @param remoteIp
     * @return
     */

    @Override
    public int addIpRecord(String loginName, String remoteIp) {
        ChanneluvRequestRecord requestRecord = new ChanneluvRequestRecord();
        QueryWrapper<Channel> qw = new QueryWrapper<>();
        qw.eq("login_name", loginName);
        Channel channel = channelService.getOne(qw);
        requestRecord.setRemoteIp(remoteIp);
        log.info("渠道id为:" + channel.getId());
        requestRecord.setChannelId(String.valueOf(channel.getId()));
        if (channel.getIsEnable().equals("1")) {
            requestRecord.setRemarks("下架");
            recordMapper.insert(requestRecord);
            return -1;
        } else {
            return recordMapper.insert(requestRecord);
        }
    }
}
