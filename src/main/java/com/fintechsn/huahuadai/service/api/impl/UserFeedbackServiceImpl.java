package com.fintechsn.huahuadai.service.api.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fintechsn.huahuadai.mapper.api.UserFeedbackMapper;
import com.fintechsn.huahuadai.model.api.UserFeedback;
import com.fintechsn.huahuadai.service.api.UserFeedbackService;
import org.springframework.stereotype.Service;

@Service
public class UserFeedbackServiceImpl extends ServiceImpl<UserFeedbackMapper, UserFeedback> implements UserFeedbackService {

}
