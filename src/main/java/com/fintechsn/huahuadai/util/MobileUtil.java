package com.fintechsn.huahuadai.util;

import org.apache.commons.lang.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Filename       MobileUtil.java
 * <p>
 * Description   验证手机号工具类
 * Copyright     Copyright (c) 2016-2022 All Rights Reserved.
 * Company       fintechzh.com Inc Inc.
 *
 * @author XXX
 * @version 1.0
 * @date 2018/11/1 16:44
 */
public class MobileUtil {

    /**
     * 手机号验证
     *
     * @param mobile
     * @return 验证通过返回true
     */
    public static boolean isMobile(String mobile) {
        if (StringUtils.isEmpty(mobile)) {
            return false;
        }
        String regExp = "^[1][3,4,5,6,7,8,9][0-9]{9}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(mobile);
        return m.matches();
    }
}