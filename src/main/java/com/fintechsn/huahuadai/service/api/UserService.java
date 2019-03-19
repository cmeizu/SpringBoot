package com.fintechsn.huahuadai.service.api;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fintechsn.huahuadai.common.bean.ResponseResult;
import com.fintechsn.huahuadai.model.api.User;
import com.fintechsn.huahuadai.model.api.param.UserListParam;

public interface UserService extends IService<User> {
    /**
     * 用户注册
     * @param userListParam
     * @return
     */
    ResponseResult register(UserListParam userListParam);

    /**
     * 手机号登录
     * @param userListParam
     * @return
     */
    ResponseResult loginByPhone(UserListParam userListParam);

    /**
     * 用户登出
     * @param phone
     * @return
     */
    ResponseResult loginOut(String phone);

    /**
     * 获取验证码
     * @param phone
     * @return
     */
    ResponseResult getPhoneCode(String phone);
}
