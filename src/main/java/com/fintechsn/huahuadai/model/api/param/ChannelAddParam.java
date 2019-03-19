package com.fintechsn.huahuadai.model.api.param;

import lombok.Data;

@Data
public class ChannelAddParam {

    /**
     * '登录账号'
     */
    private String loginName;
    /**
     * '渠道商名称'
     */
    private String name;
    /**
     * '推广链接'
     */
    private String linkUrl;
    /**
     * '每单分成利润比例'
     */
    private String proportion;
    /**
     * '登录密码'
     */
    private String password;
    /**
     * '登录token'
     */
    private String token;
    /**
     * '总注册会员数'
     */
    private String memberCount;
    /**
     * '总申请金额'
     */
    private String applyMoney;
    /**
     * '总放款金额'
     */
    private String outMoney;
    /**
     * '总分成利润'
     */
    private String profit;
    /**
     * 状态: 1,正常 2,删除
     */
    private String status;
    /**
     * 扣量比例0-100
     */
    private int decrementRate;
    /**
     * '结算方式'
     */
    private String clearnFrom;
}
