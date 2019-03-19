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
public class CollectionOrderDTO {

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
     * 借款时间
     * DBType: Date
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
     * 催回时间
     * DBType: Date
     */
    @JsonSerialize(using = DateJsonSerializer.class)
    @JsonDeserialize(using = DateJsonDeserializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date collectionSuccTime;

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
     * 同盾 分数
     * DBType: String
     */
    private String tdScore;

    /**
     * 创建人
     * DBType: String
     */
    private String createBy;

    /**
     * 创建时间
     * DBType: Date
     */
    @JsonSerialize(using = DateJsonSerializer.class)
    @JsonDeserialize(using = DateJsonDeserializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    /**
     * 更新人
     * DBType: String
     */
    private String updateBy;

    /**
     * 更新时间
     * DBType: Date
     */
    @JsonSerialize(using = DateJsonSerializer.class)
    @JsonDeserialize(using = DateJsonDeserializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;

    /**
     * 备注
     * DBType: String
     */
    private String remarks;

    /**
     * 删除标记（0：正常 1：删除）
     * DBType: Integer
     */
    private Integer delFlag;

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
     * 分派时间
     * DBType: Date
     */
    @JsonSerialize(using = DateJsonSerializer.class)
    @JsonDeserialize(using = DateJsonDeserializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date assignmentTime;

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
     * 催收时间
     * DBType: Date
     */
    @JsonSerialize(using = DateJsonSerializer.class)
    @JsonDeserialize(using = DateJsonDeserializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date collectionTime;

    /**
     * 和催收公司关联关系
     * DBType: String
     */
    private String relationCompanyState;

    /**
     * 和催收员关联关系
     * DBType: String
     */
    private String relationCollectorState;

    /**
     * 是否是老用户
     * DBType: String
     */
    private Integer isOld;

}
