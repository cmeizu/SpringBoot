package com.fintechsn.huahuadai.controller.system;

import com.fintechsn.huahuadai.common.bean.ResponseCode;
import com.fintechsn.huahuadai.common.bean.ResponseResult;
import com.fintechsn.huahuadai.model.system.dto.resource.ResourceDTO;
import com.fintechsn.huahuadai.service.system.SysResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/system/resource")
@Api(tags = {"资源管理"})
public class SysResourceController {

    @Autowired
    private SysResourceService sysResourceService;

    @PostMapping(value = {"/add"})
    @ApiOperation(value = "添加资源")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "身份认证Token")
    public ResponseResult add(@RequestBody @Validated @ApiParam("资源数据") ResourceDTO dto) {
        sysResourceService.add(dto);
        return ResponseResult.e(ResponseCode.OK);
    }

    @PostMapping(value = {"/update/{id}"})
    @ApiOperation(value = "更新资源")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "身份认证Token")
    public ResponseResult update(@PathVariable("id") @ApiParam("资源ID") String id,
                                 @RequestBody @Validated @ApiParam("资源数据") ResourceDTO dto) {
        sysResourceService.update(id, dto);
        return ResponseResult.e(ResponseCode.OK);
    }

    @PostMapping(value = {"/list"})
    @ApiOperation(value = "获取所有的资源列表")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "身份认证Token")
    public ResponseResult list() {
        return ResponseResult.e(ResponseCode.OK, sysResourceService.findAllResource());
    }

    @PostMapping(value = {"/remove/{id}"})
    @ApiOperation(value = "删除资源")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "身份认证Token")
    public ResponseResult remove(@PathVariable("id") @ApiParam("资源ID") String id) {
        sysResourceService.remove(id);
        return ResponseResult.e(ResponseCode.OK);
    }

}
