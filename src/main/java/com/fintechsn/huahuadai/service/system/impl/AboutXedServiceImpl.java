package com.fintechsn.huahuadai.service.system.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fintechsn.huahuadai.mapper.api.AboutXedMapper;
import com.fintechsn.huahuadai.model.api.AboutXed;
import com.fintechsn.huahuadai.service.system.AboutXedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AboutXedServiceImpl extends ServiceImpl<AboutXedMapper, AboutXed> implements AboutXedService {

    @Override
    public AboutXed aboutus(Integer type) {
        QueryWrapper qw = new QueryWrapper();
        qw.eq("type", type);
        return getOne(qw);
    }
}
