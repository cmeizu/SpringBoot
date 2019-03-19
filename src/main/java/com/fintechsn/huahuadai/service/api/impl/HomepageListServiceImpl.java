package com.fintechsn.huahuadai.service.api.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fintechsn.huahuadai.mapper.api.HomepageListMapper;
import com.fintechsn.huahuadai.model.api.HomepageList;
import com.fintechsn.huahuadai.service.api.HomepageListService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomepageListServiceImpl extends ServiceImpl<HomepageListMapper, HomepageList> implements HomepageListService {
    @Override
    public List<HomepageList> homelist() {
        QueryWrapper qw = new QueryWrapper();
        qw.eq("is_enable", "0");
        List<HomepageList> list = list(qw);
        return list;
    }
}
