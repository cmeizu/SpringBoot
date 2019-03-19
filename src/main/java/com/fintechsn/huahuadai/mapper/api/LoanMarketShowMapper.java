package com.fintechsn.huahuadai.mapper.api;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fintechsn.huahuadai.model.api.LoanMarketShow;
import com.fintechsn.huahuadai.model.api.param.LoanMarketConditionParam;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface LoanMarketShowMapper extends BaseMapper<LoanMarketShow> {
    List<LoanMarketShow> selectByLoanMarketShow();
    List<LoanMarketShow> findAllLoanMarketShow();

    void onByIdLoanMarketShow(Integer id);

    void offByIdLoanMarketShow(Integer id);

    List<LoanMarketShow> conditionSearch(LoanMarketConditionParam conditionParam);
}
