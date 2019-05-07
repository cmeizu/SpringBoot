package com.fintechsn.huahuadai.controller.api;

import com.fintechsn.huahuadai.common.bean.ResponseCode;
import com.fintechsn.huahuadai.common.bean.ResponseResult;
import com.fintechsn.huahuadai.model.api.HomepageBanner;
import com.fintechsn.huahuadai.service.api.HomepageBannerService;
import com.fintechsn.huahuadai.service.api.HomepageListService;
import com.fintechsn.huahuadai.service.api.ProductIpRecordService;
import com.fintechsn.huahuadai.util.GetClientIp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@CrossOrigin
@RestController
@RequestMapping(value = "/api/product")
@Slf4j
@Api(tags = "产品列表")
public class ProductController {

    @Autowired
    private ProductIpRecordService ipRecordService;
    @Autowired
    private HomepageBannerService bannerService;
    @Autowired
    private HomepageListService listService;

    @PostMapping(value = {"/bannerList"})
    @ApiOperation(value = "首页banner列表")
//    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "身份认证Token")
    public ResponseResult bannerList() {
        List<HomepageBanner> list = bannerService.listEnable();
        return ResponseResult.succ(list);
    }

    @PostMapping("/addRecord")
    @ApiOperation(value = "贷超页面ip统计")
//    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "身份认证Token")
    public ResponseResult addRecord(Integer productId, HttpServletRequest request) {
        String ip = GetClientIp.getIpAddr(request);
        log.info("移动端ip:" + ip);
        if (productId == null) {
            return ResponseResult.fail("传递参数有误");
        }
        boolean flag = ipRecordService.addIpRecord(productId, ip);
        return ResponseResult.e(flag ? ResponseCode.OK : ResponseCode.FAIL);
    }

    @PostMapping("/homelist")
    @ApiOperation(value = "首页产品列表")
//    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "身份认证Token")
    public ResponseResult homelist() {
        return ResponseResult.succ(listService.homelist());
    }
}