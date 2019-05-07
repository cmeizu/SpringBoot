package com.fintechsn.huahuadai.model.api.param;

import lombok.Data;

@Data
public class SettletOrderParam {
    /**
     * 订单id
     */
    private String id;
    /**
     * 结清金额
     */
    private String money;
}
