package com.fintechsn.huahuadai.controller.api;

import com.fintechsn.huahuadai.common.bean.ResponseCode;
import com.fintechsn.huahuadai.common.bean.ResponseResult;
import com.fintechsn.huahuadai.model.api.param.ChannelAddParam;
import com.fintechsn.huahuadai.model.api.param.ChannelListParam;
import com.fintechsn.huahuadai.service.api.ChannelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/channel")
@Api(tags = {"渠道商管理"})
public class ChannelController {

    @Autowired
    private ChannelService channelService;

    /**
     * @param
     * @return 返回值JsonResp
     * @查询所有的用户列表
     */
    @PostMapping("/list")
    @ApiOperation(value = "渠道商列表")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "身份认证Token")
    public ResponseResult list(@RequestBody ChannelListParam channelListParam) {
        return ResponseResult.e(ResponseCode.OK, channelService.list(channelListParam));
    }

    @PostMapping(value = {"/add"})
    @ApiOperation(value = "添加渠道商")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "身份认证Token")
    public ResponseResult add(@RequestBody @Validated @ApiParam(value = "渠道商数据") ChannelAddParam channelAddParam) {
        boolean flag = channelService.add(channelAddParam);
        return ResponseResult.e(flag ? ResponseCode.OK : ResponseCode.FAIL);
    }

    @PostMapping(value = {"/get/{id}"})
    @ApiOperation(value = "根据ID获取渠道商")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "身份认证Token")
    public ResponseResult getChannelById(@PathVariable("id") @ApiParam(value = "渠道商ID") String id) {
        return ResponseResult.e(ResponseCode.OK, channelService.getChannelById(id));
    }

    @PostMapping(value = {"/update/{id}"})
    @ApiOperation(value = "修改渠道商")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "身份认证Token")
    public ResponseResult update(@PathVariable("id") @ApiParam(value = "渠道商标识ID") String id,
                                 @RequestBody @Validated @ApiParam(value = "渠道商数据") ChannelAddParam channelAddParam) {
        boolean flag = channelService.update(id, channelAddParam);
        return ResponseResult.e(flag ? ResponseCode.OK : ResponseCode.FAIL);
    }

    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除渠道商")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "身份认证Token")
    public ResponseResult delete(@PathVariable("id") @ApiParam(value = "渠道标识ID") String id) {

        boolean flag = channelService.delete(id);
        return ResponseResult.e(flag ? ResponseCode.OK : ResponseCode.FAIL);
    }

    @PostMapping("/onOroff/{id}")
    @ApiOperation(value = "渠道商上下架")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "身份认证Token")
    public ResponseResult onOroff(@PathVariable("id") @ApiParam(value = "渠道标识ID") String id) {
        return ResponseResult.succ(channelService.onOroffByChannelId(id));
    }
}
