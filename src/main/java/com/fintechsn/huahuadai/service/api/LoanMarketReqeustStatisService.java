package com.fintechsn.huahuadai.service.api;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fintechsn.huahuadai.model.api.LoanMarketReqeustStatis;

import java.util.List;


public interface LoanMarketReqeustStatisService extends IService<LoanMarketReqeustStatis> {
    void updateRequestStatics(Integer channelId, String requestIp);

    List<LoanMarketReqeustStatis> selectList();
}
