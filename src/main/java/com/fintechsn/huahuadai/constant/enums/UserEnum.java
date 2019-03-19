package com.fintechsn.huahuadai.constant.enums;

public enum UserEnum {

    //用户状态
    NORMAL(1, "正常"),
    BLACK(2, "黑名单"),
    FORBID(3, "禁用"),
    REFUSE(4, "拒绝"),
    LOANMARKET(5, "贷超");

    private int code;
    private String name;

    UserEnum(int code, String name) {
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
        for (UserEnum e : UserEnum.values()) {
            if (code == e.code) {
                return e.name;
            }
        }
        return "";
    }

}
