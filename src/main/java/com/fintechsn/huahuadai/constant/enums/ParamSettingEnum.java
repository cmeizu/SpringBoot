package com.fintechsn.huahuadai.constant.enums;

public enum ParamSettingEnum {
    //用户状态
    ENABLE(1, "可用"),
    FORBID(2,"禁用"),
    DELETE(3,"删除");

    private int code;
    private String name;

    ParamSettingEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static String getName(int code) {
        for (ParamSettingEnum e : ParamSettingEnum.values()) {
            if (code == e.code) {
                return e.name;
            }
        }
        return "";
    }
}
