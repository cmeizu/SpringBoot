package com.fintechsn.huahuadai.controller.api;

import com.fintechsn.huahuadai.common.bean.ResponseResult;
import com.fintechsn.huahuadai.model.api.UserFeedback;
import com.fintechsn.huahuadai.service.api.UserFeedbackService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/userFeedback")
@Api(tags = {"用户反馈信息"})
public class UserFeedbackController {

    @Autowired
    private UserFeedbackService feedbackService;
    
//    @PostMapping(value = "/add")
//    public ResponseResult addAppFeedback(@RequestBody UserFeedback userFeedback) {
//        User user = userService.findLoginUser();
//        userFeedback.setUserId(user.getId());
//        userFeedback.setPhone(user.getPhone());
//        appFeedbackService.insert(appFeedback);
//        return
//    }
}
