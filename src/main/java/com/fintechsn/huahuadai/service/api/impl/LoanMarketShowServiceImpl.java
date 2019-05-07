package com.fintechsn.huahuadai.service.api.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fintechsn.huahuadai.mapper.api.LoanMarketShowMapper;
import com.fintechsn.huahuadai.model.api.LoanMarketShow;
import com.fintechsn.huahuadai.model.api.param.LoanMarketConditionParam;
import com.fintechsn.huahuadai.service.api.LoanMarketShowService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
public class LoanMarketShowServiceImpl extends ServiceImpl<LoanMarketShowMapper, LoanMarketShow> implements LoanMarketShowService {

    @Autowired
    private LoanMarketShowMapper loanMarketShowMapper;

    @Override
    public List<LoanMarketShow> findAllLoanMarketShow() {
        return loanMarketShowMapper.findAllLoanMarketShow();
    }

    @Override
    public String onOroffByIdLoanMarketShow(Integer id) {
        String message = "";
        if (id != null) {
            LoanMarketShow loanMarketShow = loanMarketShowMapper.selectById(id);
            if (loanMarketShow.getIsEnable().equals("0")) {
                loanMarketShow.setIsEnable("1");
                message = "下架成功";
            } else {
                loanMarketShow.setIsEnable("0");
                message = "上架成功";
            }
            loanMarketShowMapper.updateById(loanMarketShow);
        }
        return message;
    }

    @Override
    public List<LoanMarketShow> selectByLoanMarketShow() {
        List<LoanMarketShow> result = loanMarketShowMapper.selectByLoanMarketShow();
        List<LoanMarketShow> list = new ArrayList<LoanMarketShow>();
        for (LoanMarketShow key : result) {
            if ("1".equals(key.getIsEnable())) {
                log.info("链接不可用");
            }
            if ("0".equals(key.getIsEnable())) {
                list.add(key);
            }
        }
        return list;
    }

    @Override
    public List<LoanMarketShow> conditionSearch(LoanMarketConditionParam conditionParam) {
        return loanMarketShowMapper.conditionSearch(conditionParam);
    }

}
