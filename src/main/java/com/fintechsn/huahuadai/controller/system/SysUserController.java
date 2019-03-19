package com.fintechsn.huahuadai.controller.system;

import com.fintechsn.huahuadai.common.bean.ResponseCode;
import com.fintechsn.huahuadai.common.bean.ResponseResult;
import com.fintechsn.huahuadai.model.system.dto.user.FindUserDTO;
import com.fintechsn.huahuadai.model.system.dto.user.ResetPasswordDTO;
import com.fintechsn.huahuadai.model.system.dto.user.UserAddDTO;
import com.fintechsn.huahuadai.model.system.dto.user.UserUpdateDTO;
import com.fintechsn.huahuadai.service.system.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = {"/system/user"})
@Api(tags = {"用户管理"})
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @PostMapping(value = {"/add"})
    @ApiOperation(value = "添加用户")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "身份认证Token")
    public ResponseResult add(@RequestBody @Validated @ApiParam(value = "用户数据") UserAddDTO addDTO) {
        sysUserService.add(addDTO);
        return ResponseResult.e(ResponseCode.OK);
    }

    @PostMapping(value = {"/get/{id}"})
    @ApiOperation(value = "根据ID获取用户信息")
    public ResponseResult getById(@PathVariable("id") @ApiParam(value = "用户ID") String id) {
        return ResponseResult.e(ResponseCode.OK, sysUserService.findUserById(id, true));
    }

    @PostMapping(value = {"/update/{id}"})
    @ApiOperation(value = "更新用户")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "身份认证Token")
    public ResponseResult update(@PathVariable("id") @ApiParam(value = "用户标识ID") String id,
                                 @RequestBody @Validated @ApiParam(value = "用户数据") UserUpdateDTO updateDTO) {
        sysUserService.update(id, updateDTO);
        return ResponseResult.e(ResponseCode.OK);
    }

    @PostMapping(value = {"/list"})
    @ApiOperation(value = "分页获取用户数据")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "身份认证Token")
    public ResponseResult get(@RequestBody @Validated @ApiParam(value = "用户获取过滤条件") FindUserDTO findUserDTO) {
        return ResponseResult.e(ResponseCode.OK, sysUserService.getAllUserBySplitPage(findUserDTO));
    }

    @PostMapping(value = {"/remove/{id}"})
    @ApiOperation(value = "删除用户")
    public ResponseResult remove(@PathVariable("id") @ApiParam(value = "用户标识ID") String id) {
        sysUserService.removeUser(id);
        return ResponseResult.e(ResponseCode.OK);
    }

    @PostMapping(value = {"/reset-password"})
    @ApiOperation(value = "重置密码")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "身份认证Token")
    public ResponseResult resetPassword(@RequestBody
                                        @Validated @ApiParam(value = "用户及密码数据") ResetPasswordDTO dto) {
        sysUserService.resetPassword(dto);
        return ResponseResult.e(ResponseCode.OK);
    }

}
