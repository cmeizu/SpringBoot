package com.fintechsn.huahuadai.controller.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fintechsn.huahuadai.service.api.ChanneluvRequestRecordService;
import com.fintechsn.huahuadai.util.GetClientIp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
@RequestMapping(value = "/api/channelrecord")
@Api(tags = {"渠道商ip记录"})
public class ChanneluvRequestRecordController {
    @Autowired
    private ChanneluvRequestRecordService recordService;

    @PostMapping(value = {"/addIpRecord"})
    @ApiOperation(value = "记录ip地址")
    public void addIpRecord(String loginName, @RequestParam("callback") String callback,
                            HttpServletRequest request, HttpServletResponse resp) throws Exception {
        boolean flag = false;
        resp.setContentType("application/json; charset=utf-8");
        JSONObject data = new JSONObject();
        String requestIp = GetClientIp.getIpAddr(request);
        if (loginName == null) {
            data.put("code", 200);
            data.put("data", flag);
            data.put("msg", "loginName为空null");
            data.put("success", true);
            resp.getWriter().write(callback + "(" + JSON.toJSONString(data) + ")");
        }
        log.info("渠道loginName:" + loginName);
        if (recordService.addIpRecord(loginName, requestIp) > 0) {
            flag = true;
            data.put("code", 200);
            data.put("data", flag);
            data.put("msg", "在架渠道");
            data.put("success", true);
            resp.getWriter().write(callback + "(" + JSON.toJSONString(data) + ")");
        } else {
            data.put("code", 200);
            data.put("data", flag);
            data.put("msg", "下架渠道");
            data.put("success", true);
            resp.getWriter().write(callback + "(" + JSON.toJSONString(data) + ")");
        }
    }
}
