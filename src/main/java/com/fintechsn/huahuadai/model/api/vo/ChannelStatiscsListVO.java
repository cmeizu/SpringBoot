package com.fintechsn.huahuadai.model.api.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChannelStatiscsListVO {

    /**
     * 渠道ID
     */
    private String channelId;
    /**
     * 渠道名称
     */
    private String channelName;
    /**
     * 渠道注册人数
     */
    private String registerNum;
    /**
     * 渠道安装人数
     */
    private String installNum;
    /**
     * 渠道申请用户数
     */
    private String applyNum;
    /**
     * 渠道房贷数量
     */
    private String lendingNum;
    /**
     * 渠道uv数
     */
    private String uvNum;
}
