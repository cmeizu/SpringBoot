package com.fintechsn.huahuadai.service.api.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fintechsn.huahuadai.mapper.api.UserBlackMapper;
import com.fintechsn.huahuadai.model.UserBlack;
import com.fintechsn.huahuadai.service.api.UserBlackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserBlackServiceImpl extends ServiceImpl<UserBlackMapper, UserBlack> implements UserBlackService {

    @Autowired
    private UserBlackMapper userBlackMapper;

}
