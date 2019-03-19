package com.fintechsn.huahuadai.model.api.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fintechsn.huahuadai.util.DateJsonDeserializer;
import com.fintechsn.huahuadai.util.DateJsonSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollectionFeedbackDTO {
    /**
     * 催收账单号
     * DBType: String
     */
    private String collectionOrderId;
    /**
     * 用户id
     * DBType: String
     */
    private String userId;

    /**
     * 用户名
     * DBType: String
     */
    private String userName;

    /**
     * 手机
     * DBType: String
     */
    private String phone;

    /**
     * 借款订单号
     * DBType: String
     */
    private String orderId;

    /**
     * 借款金额
     * DBType: BigDecimal
     */
    private BigDecimal borrowMoney;

    /**
     * 应还金额
     * DBType: BigDecimal
     */
    private BigDecimal needPayMoney;

    /**
     * 催回金额
     * DBType: BigDecimal
     */
    private BigDecimal collectionMoney;

    /**
     * 借款时间 str
     * DBType: String
     */
    @JsonSerialize(using = DateJsonSerializer.class)
    @JsonDeserialize(using = DateJsonDeserializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date loanTime;

    /**
     * 逾期天数
     * DBType: int
     */
    private int overdueDays;

    /**
     * 逾期金额
     * DBType: BigDecimal
     */
    private BigDecimal overdueMoney;

    /**
     * 逾期等级 S1:逾期1-7天内;S2:逾期8-15天;S3:逾期16-30天;M2:逾期30天以上
     * DBType: String
     */
    private String overdueGrade;

    /**
     * 催收状态 1:待分派催收公司;2:待分派催收员;3:待催收;4:催收中;5:催收成功
     * DBType: String
     */
    private String collectionStatus;

    /**
     * 催收结果 0:还未进行催收;1:愿意转告;2:不愿意转告;3:第三方愿意代偿;4:无还款诚意;5:承诺还款;6:确认还款; 7:无法联系；8:其他
     * DBType: String
     */
    private String collectionResult;

    /**
     * 结清方式 0:未结清;1:全部结清;2:部分结清;3:续期
     * DBType: String
     */
    private String repayType;

    /**
     * 还款方式（0：主动还款；1：催收还款）
     * DBType: String
     */
    private String repayMethod;

    /**
     * 备注
     * DBType: String
     */
    private String remarks;

    /**
     * 催收公司ID
     * DBType: String
     */
    private String companyId;

    /**
     * 催收公司
     * DBType: String
     */
    private String companyName;

    /**
     * 催收员ID
     * DBType: String
     */
    private String collectorId;

    /**
     * 催收员
     * DBType: String
     */
    private String collectorName;

    /**
     * 催收时间 str
     * DBType: String
     */
    @JsonSerialize(using = DateJsonSerializer.class)
    @JsonDeserialize(using = DateJsonDeserializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date collectionTime;

    /**
     * 拨打结果 1:正常接听;2:无人接听;3:停机;4:空号;5:无法接通;6:其他
     * DBType: String
     */
    private String phoneResult;

}
