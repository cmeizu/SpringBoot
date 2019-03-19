package com.fintechsn.huahuadai.model.api.vo;

import lombok.Data;

import java.util.List;
@Data
public class LoanMarketStatiscsListVO {
    /**
     * 贷超ID
     */
    private Integer id;
    /**
     * 贷超名称
     */
    private String loanMarketName;
    /**
     * uv统计
     */
    private Integer uv;
    /**
     * pv统计
     */
    private Integer pv;

    /**
     * 贷超列表
     * @return
     */
    private List<LoanMarketStatiscsListVO> record;
}
