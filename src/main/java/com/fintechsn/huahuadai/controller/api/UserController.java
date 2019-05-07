package com.fintechsn.huahuadai.controller.api;

import com.fintechsn.huahuadai.common.bean.ResponseResult;
import com.fintechsn.huahuadai.model.api.param.UserListParam;
import com.fintechsn.huahuadai.service.api.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/user")
@Api(tags = {"会员管理"})
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @ApiOperation(value = "用户注册")
//    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "身份认证Token")
    public ResponseResult register(@RequestBody UserListParam userListParam) {
        return userService.register(userListParam);
    }

    @PostMapping("/loginByPhone")
    @ApiOperation(value = "用户登录")
//    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "身份认证Token")
    public ResponseResult loginByPhone(@RequestBody UserListParam userListParam) {
        return userService.loginByPhone(userListParam);
    }

    @PostMapping("/loginOut/{phone}")
    @ApiOperation(value = "用户退出")
//    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "身份认证Token")
    public ResponseResult loginOut(@PathVariable(value = "phone") String phone) {
        return userService.loginOut(phone);
    }

    @PostMapping("/getPhoneCode/{phone}")
    @ApiOperation(value = "获取验证码")
//    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "身份认证Token")
    public ResponseResult getPhoneCode(@PathVariable(value = "phone") String phone) {
        return userService.getPhoneCode(phone);
    }

}
