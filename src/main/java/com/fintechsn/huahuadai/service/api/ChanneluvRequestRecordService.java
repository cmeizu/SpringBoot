package com.fintechsn.huahuadai.service.api;


import com.baomidou.mybatisplus.extension.service.IService;
import com.fintechsn.huahuadai.model.api.ChanneluvRequestRecord;

public interface ChanneluvRequestRecordService extends IService<ChanneluvRequestRecord> {
    /**
     * 渠道ip记录
     * @param loginName
     * @param remoteIp
     * @return
     */
    int addIpRecord(String loginName, String remoteIp);
}
