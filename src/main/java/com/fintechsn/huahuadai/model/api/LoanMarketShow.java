package com.fintechsn.huahuadai.model.api;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fintechsn.huahuadai.util.DateJsonDeserializer;
import com.fintechsn.huahuadai.util.DateJsonSerializer;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @version 1.0
 * @Loan_market_show
 * @贷超列表展示表(loan_market_show)
 */
@TableName("loan_market_show")
@Data
public class LoanMarketShow {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * ID INT(11)
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * @备注:权重
     * @字段:loan_market_weight INT(11);
     */
    private String loanMarketWeight;
    /**
     * @备注：贷超名称
     * @字段：loan_market_name VARCHAR(32)
     */
    private String loanMarketName;
    /**
     * @备注: 联系人
     * @字段: link_person VARCHAR(32)
     */
    private String linkPerson;
    /**
     * @备注: 联系电话
     * @字段: link_phone VARCHAR(45)
     */
    private String linkPhone;
    /**
     * @备注: 公司名称
     * @字段: company VARCHAR(32)
     */
    private String company;
    /**
     * @备注: 结算方式
     * @字段: settlement_method CHAR(5)
     */
    private String settlementMethod;
    /**
     * @备注: 结算单价
     * @字段: settlement_price decimal(11,2)
     */
    private BigDecimal settlementPrice;
    /**
     * @备注:贷超logo
     * @字段:loan_market_logo VARCHAR(255)
     */
    private String loanMarketLogo;
    /**
     * @备注:贷超链接
     * @字段:loan_market_url VARCHAR(512)
     */
    private String loanMarketUrl;
    /**
     * @备注:主标题
     * @字段:principalMark VARCHAR(255)
     */
    private String principalMark;
    /**
     * @备注:副标题
     * @字段:subHead VARCHAR(255)
     */
    private String subHead;
    /**
     * @备注:利率
     * @字段:rate VARCHAR(64)
     */
    private String rate;
    /**
     * @备注: 贷款期限
     * @字段: limit_days char(3)
     */
    private String limitDays;
    /**
     * @备注: 审核时长
     * @字段: audit_time
     */
    private String auditTime;
    /**
     * @备注: 产品介绍
     * @字段: introduce VARCHAR(128)
     */
    private String introduce;
    /**
     * @备注:额度
     * @字段:quota VARCHAR(64)
     */
    private String quota;
    /**
     * @备注：是否可用(0:可用１:不可用)
     * @字段:is_enable
     */
    private String isEnable;
    /**
     * @备注：创建人
     * @字段：create_by
     */
    private String createBy;
    /**
     * @备注:创建时间
     * @字段:create_date DATE(10)
     */
    @JsonSerialize(using = DateJsonSerializer.class)
    @JsonDeserialize(using = DateJsonDeserializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate = new Date();
    /**
     * @备注：更新者
     * @字段：update_by　VARCHAR(64)
     */
    private String updateBy;
    /**
     * @备注:更新时间
     * @字段:update_date DATETIME(19)
     */
    @JsonSerialize(using = DateJsonSerializer.class)
    @JsonDeserialize(using = DateJsonDeserializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate = new Date();
    /**
     * @备注：备注
     * @字段:remarks VARCHAR(255)
     */
    private String remarks;
    /**
     * @备注：删除标记　０:正常1:删除
     * @字段:del_flg CHAR(1)
     */
    private String delFlag;
}
