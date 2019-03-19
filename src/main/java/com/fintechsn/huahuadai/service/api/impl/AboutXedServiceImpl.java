package com.fintechsn.huahuadai.service.api.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fintechsn.huahuadai.mapper.api.AboutXedMapper;
import com.fintechsn.huahuadai.model.api.AboutXed;
import com.fintechsn.huahuadai.service.api.AboutXedService;
import org.springframework.stereotype.Service;

@Service
public class AboutXedServiceImpl extends ServiceImpl<AboutXedMapper, AboutXed> implements AboutXedService {

    @Override
    public AboutXed aboutus(Integer type) {
        QueryWrapper qw = new QueryWrapper();
        qw.eq("type", type);
        return getOne(qw);
    }
}
