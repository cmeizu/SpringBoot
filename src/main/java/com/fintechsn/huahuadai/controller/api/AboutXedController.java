package com.fintechsn.huahuadai.controller.api;

import com.fintechsn.huahuadai.common.bean.ResponseResult;
import com.fintechsn.huahuadai.service.api.AboutXedService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/aboutXed")
@Api(tags = {"关于我们"})
public class AboutXedController {

    @Autowired
    private AboutXedService aboutXedService;

    @PostMapping(value = "/aboutus/{type}")
    @ApiOperation(value = "关于我们和商务合作")
//    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "身份认证Token")
    public ResponseResult aboutus(@PathVariable(value = "type") Integer type) {
        return ResponseResult.succ(aboutXedService.aboutus(type));
    }

}
