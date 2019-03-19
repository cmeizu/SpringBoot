package com.fintechsn.huahuadai.sms;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * Filename        YunpianSmsUtil.java
 * <p>
 * Description    云片发送模板短信
 * Copyright      Copyright (c) 2016-2022 All Rights Reserved.
 * Company       fintechsn.com Inc Inc.
 *
 * @author liangwp
 * @version 1.0
 * @date 2018/11/23 16:44
 */
public class YunpianSmsUtil {
    private static Logger log = LoggerFactory.getLogger(YunpianSmsUtil.class);

    private static final String YP_REQ_TPL_SINGLE_URL = "https://sms.yunpian.com/v2/sms/tpl_single_send.json";
    private static final String YP_APIKEY = "4b4331a83e070f2bf89b0b7d979df808";


    public static Boolean sendSmsCode(YTempletEnum template, String mobile, String[] arr) {
        String tplValue = null;
        if (arr != null) {
            tplValue = Arrays.stream(arr).map(str -> urlEncode("#code#", null) + "=" + urlEncode(str, null)).collect(Collectors.joining("&"));
        }
        return coreSms(template, mobile, tplValue);
    }


    public static Boolean sendSms(YTempletEnum template, String mobile, Map<String, Object> pmap) {
        String tplValue = null;
        if (pmap != null) {
            tplValue = getTplValue(pmap);
        }
        return coreSms(template, mobile, tplValue);
    }


    private static String getTplValue(Map<String, Object> params) {
        StringBuffer sb = new StringBuffer();
        for (String keyItem : params.keySet()) {
            if (params.get(keyItem) != null) {
                String value = String.valueOf(params.get(keyItem));
                if (!value.equals("")) {
                    sb.append("#" + keyItem + "#" + "=" + value + "&");
                }
            }
        }
        return sb.toString();
    }


    private static Boolean coreSms(YTempletEnum template, String mobile, String tplValue) {
        Map<String, String> params = new HashMap<>();
        params.put("apikey", YP_APIKEY);
        params.put("mobile", mobile);
        params.put("tpl_id", template.getCode());
        params.put("tpl_value", tplValue);
        //使用线程池异步发送短信
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String result = HttpUtil.post(YP_REQ_TPL_SINGLE_URL, params);
                    JSONObject json = JSON.parseObject(result);
                    if (json.getInteger("code") == 0) {
                        log.info("短信发送成功");
                    } else {
                        log.info(JSON.toJSONString(json));
                    }
                } catch (Exception e) {
                    log.error("error", e);
                }
            }
        });
        return true;
    }


    public static final String urlEncode(String text, String charset) {
        if (charset == null || "".equals(charset)) {
            charset = "UTF-8";
        }
        try {
            return URLEncoder.encode(text, charset);
        } catch (UnsupportedEncodingException e) {
            log.error("error", e);
        }
        return text;
    }


    public enum YTempletEnum {
        //短信模板枚举
        VERIFY("2733426", "验证码"),
        NOTIFY_THIRD_DAY("2733738", "还款日3天前发提醒通知短信"),
        NOTIFY_SECOND_DAY("2733732", "还款日2天前发提醒通知短信"),
        NOTIFY_NOW_DAY("2733742", "还款日当天前发提醒通知短信"),
        ORDER_EXAMINE_PASS("2733430", "订单审核通过发通知短信"),
        ORDER_SETTLED("2733434", "订单结清发通知短信"),
        CHANNEL_AUTHENTICATION_RATE("2733428", "渠道注册认证率预警知短信"),
        EARLY_WARNING("2733432", "预警短信"),
        ;
        private String code;
        private String desc;

        YTempletEnum(String code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public String getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }
    }
}

