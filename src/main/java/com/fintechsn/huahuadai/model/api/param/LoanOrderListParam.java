package com.fintechsn.huahuadai.model.api.param;

import com.fintechsn.huahuadai.model.system.dto.SplitPageDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class LoanOrderListParam extends SplitPageDTO {

    private String userName;

    private String phone;
    /**
     * 订单状态
     */
    private String orderStatus;
    /**
     * 打款状态
     */
    private String giveStatus;
    /**
     * 还款状态
     */
    private String payStatus;
    /**
     * 订单开始时间
     */
    private String gmtDatetimeStart;
    /**
     * 订单开始时间
     */
    private String gmtDatetimeEnd;

    private String channelName;

    private String isOverdue;
    private String extendType;
    private String limitPayTimeStart;
    private String limitPayTimeEnd;
    private String realPayTimeStart;
    private String realPayTimeEnd;

}
