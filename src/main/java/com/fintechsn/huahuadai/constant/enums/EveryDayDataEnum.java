package com.fintechsn.huahuadai.constant.enums;

public enum EveryDayDataEnum {
    //用户状态
    DEFAULT(0, "正常"),
    APPLY_NUM(1, "已申请用户数"),
    LENDING_NUM(2, "已放贷用户数量"),
    REG_NUM(3, "注册用户数量");

    private int code;
    private String name;

    EveryDayDataEnum(int code, String name) {
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
        for (EveryDayDataEnum e : EveryDayDataEnum.values()) {
            if (code == e.code) {
                return e.name;
            }
        }
        return "";
    }
}
