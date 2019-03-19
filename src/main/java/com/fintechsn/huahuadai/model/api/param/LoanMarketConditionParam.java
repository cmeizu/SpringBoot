package com.fintechsn.huahuadai.model.api.param;

import lombok.Data;

@Data
public class LoanMarketConditionParam {
    private String company = "";

    private String loanMarketName = "";

    private String settlementMethod = "";

    private String isEnable = "";
}
