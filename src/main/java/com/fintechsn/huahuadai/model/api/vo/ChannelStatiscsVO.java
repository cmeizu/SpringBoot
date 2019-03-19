package com.fintechsn.huahuadai.model.api.vo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChannelStatiscsVO extends Page {

    /**
     * 注册总用户数
     */
    private String registerNumTotal;
    /**
     * 安装总用户数
     */
    private String installNumTotal;
    /**
     * 申请总用户数
     */
    private String applyNumTotal;
    /**
     * 放贷总用户数
     */
    private String lendingNumTotal;

}
