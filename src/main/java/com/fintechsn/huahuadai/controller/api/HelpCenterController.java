package com.fintechsn.huahuadai.controller.api;

import com.fintechsn.huahuadai.common.bean.ResponseResult;
import com.fintechsn.huahuadai.model.api.HelpCenter;
import com.fintechsn.huahuadai.service.api.HelpCenterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@RestController
@RequestMapping(value = "/api/helpCenter")
@Api(tags = {"帮助中心"})
public class HelpCenterController {

    @Autowired
    private HelpCenterService helpCenterService;

    @PostMapping(value = "/list")
    @ApiOperation(value = "列表")
//    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "身份认证Token")
    public ResponseResult list() {
        return ResponseResult.succ(helpCenterService.list());
    }

}
