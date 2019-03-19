package com.fintechsn.huahuadai.model.api.param;

import com.fintechsn.huahuadai.model.system.dto.SplitPageDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CollectionOrderListParam extends SplitPageDTO {
    /**
     * 催收订单id
     * DBType: String
     */
    private String collectionOrderId;
    /**
     * 借款人姓名
     */
    private String userName;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 催款状态
     */
    private String collectionStatus;
    /**
     * 催收结果
     */
    private String collectionResult;
    /**
     * 逾期等级
     */
    private String overdueGrade;
    /**
     * 催收公司
     */
    private String companyId;
    /**
     * 催收员id
     * DBType: String
     */
    private String collectorId;
    /**
     * 借款开始时间
     */
    private String loanTimeStart;
    /**
     * 借款结束时间
     */
    private String loanTimeEnd;
    /**
     * 催回开始时间
     */
    private String collectionSuccTimeStart;
    /**
     * 催回结束时间
     */
    private String collectionSuccTimeEnd;

}
