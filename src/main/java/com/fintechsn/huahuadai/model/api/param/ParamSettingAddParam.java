package com.fintechsn.huahuadai.model.api.param;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fintechsn.huahuadai.util.DateJsonDeserializer;
import com.fintechsn.huahuadai.util.DateJsonSerializer;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class ParamSettingAddParam {

    /**
     * @备注:
     * @字段:id BIGINT(19)
     */
    private java.lang.Long id;

    /**
     * @备注:贷款期限
     * @字段:limit_days INT(10)
     */
    private java.lang.Integer limitDays;

    /**
     * @备注:贷款金额
     * @字段:min_borrow_money DECIMAL(33)
     */
    private java.math.BigDecimal minBorrowMoney;

    /**
     * @备注:
     * @字段:max_borrow_money DECIMAL(33)
     */
    private java.math.BigDecimal maxBorrowMoney;

    /**
     * @备注:利息 %
     * @字段:interest_percent DOUBLE(10)
     */
    private java.lang.Double interestPercent;

    /**
     * @备注:平台服务费
     * @字段:place_serve_percent DOUBLE(10)
     */
    private java.lang.Double placeServePercent;

    /**
     * @备注:信息认证费
     * @字段:msg_auth_percent DOUBLE(10)
     */
    private java.lang.Double msgAuthPercent;

    /**
     * @备注:风控服务费
     * @字段:risk_serve_percent DOUBLE(10)
     */
    private java.lang.Double riskServePercent;

    /**
     * @备注:风险准备金
     * @字段:risk_plan_percent DOUBLE(10)
     */
    private java.lang.Double riskPlanPercent;

    /**
     * @备注:容限期
     * @字段:allow_days INT(10)
     */
    private java.lang.Integer allowDays;

    /**
     * @备注:容限期日利率
     * @字段:allow_percent DOUBLE(10)
     */
    private java.lang.Double allowPercent;

    /**
     * @备注:逾期日利率
     * @字段:overdue_percent DOUBLE(10)
     */
    private java.lang.Double overduePercent;

    /**
     * @备注:
     * @字段:gmt_datetime DATETIME(19)
     */
    @JsonSerialize(using = DateJsonSerializer.class)
    @JsonDeserialize(using = DateJsonDeserializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date gmtDatetime = new java.util.Date();

    /**
     * @备注:
     * @字段:upt_datetime DATETIME(19)
     */
    @JsonSerialize(using = DateJsonSerializer.class)
    @JsonDeserialize(using = DateJsonDeserializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date uptDatetime = new java.util.Date();

    /**

     * @备注:状态
     * @字段:status INT(10)
     */
    private java.lang.Integer status;

}
