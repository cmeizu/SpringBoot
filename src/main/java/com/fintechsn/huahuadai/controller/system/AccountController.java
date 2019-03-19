package com.fintechsn.huahuadai.controller.system;

import com.fintechsn.huahuadai.common.annotation.JwtClaim;
import com.fintechsn.huahuadai.common.bean.ResponseCode;
import com.fintechsn.huahuadai.common.bean.ResponseResult;
import com.fintechsn.huahuadai.model.system.dto.SignInDTO;
import com.fintechsn.huahuadai.service.system.SysUserService;
import com.fintechsn.huahuadai.shiro.jwt.JwtToken;
import io.swagger.annotations.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = {"/account"})
@Api(tags = {"账户相关"})
public class AccountController {

    @Autowired
    private SysUserService sysUserService;

    @PostMapping(value = {"/sign-in2"})
    @ApiOperation(value = "登录2")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", required = true, value = "账号", dataType = "string", paramType = "form", defaultValue = ""),
            @ApiImplicitParam(name = "password", required = true, value = "密码", dataType = "string", paramType = "form", defaultValue = ""),
    })
    public ResponseResult signIn(String userName, String password) {
        SignInDTO signInDTO = new SignInDTO();
        signInDTO.setUsername(userName);
        signInDTO.setPassword(password);
        sysUserService.signIn(signInDTO);
        return ResponseResult.e(ResponseCode.SIGN_IN_OK, ((JwtToken) SecurityUtils.getSubject().getPrincipal()).getToken());
    }

    @PostMapping(value = {"/sign-in"})
    @ApiOperation(value = "登录")
    public ResponseResult signIn(@RequestBody @Validated @ApiParam(value = "登录数据", required = true)
                                         SignInDTO signInDTO) {
        sysUserService.signIn(signInDTO);
        return ResponseResult.e(ResponseCode.SIGN_IN_OK, ((JwtToken) SecurityUtils.getSubject().getPrincipal()).getToken());
    }

    @PostMapping(value = "/logout")
    @ApiOperation(value = "注销登录")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "身份认证Token")
    public ResponseResult logout() {
        try {
            Subject subject = SecurityUtils.getSubject();
            subject.logout();
        } catch (Exception e) {
            return ResponseResult.e(ResponseCode.LOGOUT_FAIL);
        }
        return ResponseResult.e(ResponseCode.LOGOUT_OK);
    }

    @PostMapping(value = "/all-permission-tag")
    @ApiOperation(value = "获取所有的权限标示")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "身份认证Token")
    public ResponseResult<List<String>> getAllPermissionTag(@JwtClaim String t) {
        return ResponseResult.e(ResponseCode.OK, sysUserService.getAllPermissionTag());
    }
}
