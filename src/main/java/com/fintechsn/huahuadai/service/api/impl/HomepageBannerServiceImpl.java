package com.fintechsn.huahuadai.service.api.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fintechsn.huahuadai.mapper.api.HomepageBannerMapper;
import com.fintechsn.huahuadai.model.api.HomepageBanner;
import com.fintechsn.huahuadai.service.api.HomepageBannerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomepageBannerServiceImpl extends ServiceImpl<HomepageBannerMapper, HomepageBanner> implements HomepageBannerService {

    @Override
    public List<HomepageBanner> listEnable() {
        QueryWrapper qw = new QueryWrapper();
        qw.eq("is_enable", "0");
        List<HomepageBanner> list = list(qw);
        return list;
    }

}
