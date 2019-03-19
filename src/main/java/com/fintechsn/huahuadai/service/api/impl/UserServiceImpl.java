package com.fintechsn.huahuadai.service.api.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fintechsn.huahuadai.common.bean.ResponseCode;
import com.fintechsn.huahuadai.common.bean.ResponseResult;
import com.fintechsn.huahuadai.constant.enums.EveryDayDataType;
import com.fintechsn.huahuadai.mapper.api.UserMapper;
import com.fintechsn.huahuadai.model.api.Channel;
import com.fintechsn.huahuadai.model.api.User;
import com.fintechsn.huahuadai.model.api.param.UserListParam;
import com.fintechsn.huahuadai.redis.CacheUtil;
import com.fintechsn.huahuadai.service.api.ChannelService;
import com.fintechsn.huahuadai.service.api.EveryDayDataService;
import com.fintechsn.huahuadai.service.api.UserService;
import com.fintechsn.huahuadai.sms.YunpianSmsUtil;
import com.fintechsn.huahuadai.util.MobileUtil;
import com.fintechsn.huahuadai.util.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private ChannelService channelService;
    @Autowired
    private CacheUtil cacheUtil;
    @Autowired
    private EveryDayDataService everyDayDataService;

    @Override
    public ResponseResult register(UserListParam userListParam) {
        //判断手机号格式
        if (!MobileUtil.isMobile(userListParam.getPhone())) {
            return ResponseResult.fail("请输入正确的手机号！");
        }
        if (!cacheUtil.hasKey(userListParam.getChannelId())) {
            return ResponseResult.fail("信息输入有误！");
        }
        if (!userListParam.getCode().equals(cacheUtil.get(userListParam.getPhone()))) {
            return ResponseResult.fail("验证码不对");
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper();
        queryWrapper.eq("phone", userListParam.getPhone());
        List<User> users = list(queryWrapper);
        if (users.size() > 0) {
            return ResponseResult.fail("该号码已经被注册");
        }
        User user = new User();
        //是否有渠道商
        if (StringUtils.isNotEmpty(userListParam.getChannelId())) {
            QueryWrapper<Channel> qw = new QueryWrapper<>();
            qw.eq("login_name", userListParam.getChannelId());
            qw.eq("status", 1);
            Channel channel = channelService.getOne(qw);
            channel.setMemberCount(channel.getMemberCount() + 1);
            channelService.updateById(channel);
            user.setChannelId(channel.getId());
            try {
                //扣量注册
                everyDayDataService.addEveryDayData(EveryDayDataType.TYPE.REG_NUM.getKey(), channel.getId());
            } catch (Exception e) {
                log.error("error", e);
            }
        }
        return ResponseResult.e(save(user) ? ResponseCode.OK : ResponseCode.FAIL);
    }

    @Override
    public ResponseResult getPhoneCode(String phone) {
        if (!MobileUtil.isMobile(phone)) {
            return ResponseResult.fail("请输入正确的手机号！");
        }
        String code = RandomUtils.randomString(6);
        try {
            String[] arr = {code};
            YunpianSmsUtil.sendSmsCode(YunpianSmsUtil.YTempletEnum.VERIFY, phone, arr);
        } catch (Exception e) {
            return ResponseResult.fail("发送验证码失败");
        }
        if (cacheUtil.hasKey(phone)) {
            cacheUtil.delkey(phone);
        }
        cacheUtil.set(phone, code, 120L);
        return ResponseResult.succ("获取验证码成功！");
    }

    @Override
    public ResponseResult loginByPhone(UserListParam userListParam) {
        QueryWrapper qw = new QueryWrapper();
        qw.eq("phone", userListParam.getPhone());
        //用户已存在
        if (list(qw).size() > 0) {
            boolean flag = userListParam.getCode().equals(cacheUtil.get(userListParam.getPhone()));
            return ResponseResult.e(flag ? ResponseCode.OK : ResponseCode.FAIL);
        } else {
            return ResponseResult.fail("用户未注册");
        }
    }

    @Override
    public ResponseResult loginOut(String phone) {
        cacheUtil.delkey(phone);
        return ResponseResult.e(ResponseCode.LOGOUT_OK);
    }
}
