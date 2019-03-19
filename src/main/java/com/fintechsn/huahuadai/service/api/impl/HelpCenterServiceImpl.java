package com.fintechsn.huahuadai.service.api.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fintechsn.huahuadai.mapper.api.HelpCenterMapper;
import com.fintechsn.huahuadai.model.api.HelpCenter;
import com.fintechsn.huahuadai.service.api.HelpCenterService;
import org.springframework.stereotype.Service;

@Service
public class HelpCenterServiceImpl extends ServiceImpl<HelpCenterMapper, HelpCenter> implements HelpCenterService {
}
