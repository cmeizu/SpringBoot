package com.fintechsn.huahuadai.service.api;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fintechsn.huahuadai.model.api.LoanMarketShow;
import com.fintechsn.huahuadai.model.api.param.LoanMarketConditionParam;

import java.util.List;

public interface LoanMarketShowService extends IService<LoanMarketShow> {
    List<LoanMarketShow> selectByLoanMarketShow();

    /**
     * 贷超上下架
     *
     * @param id
     * @return
     */
    String onOroffByIdLoanMarketShow(Integer id);

    /**
     * 所有贷超列表
     *
     * @return
     */
    List<LoanMarketShow> findAllLoanMarketShow();

    /**
     * 按条件获取贷超列表
     */
    List<LoanMarketShow> conditionSearch(LoanMarketConditionParam conditionParam);
}
