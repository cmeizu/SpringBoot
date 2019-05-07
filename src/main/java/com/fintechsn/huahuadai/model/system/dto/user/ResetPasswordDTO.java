package com.fintechsn.huahuadai.model.system.dto.user;

import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class ResetPasswordDTO {

    private String uid;

    @Pattern(regexp = "^(\\w){6,18}$", message = "密码应为[A-Za-z0-9_]组成的6-18位字符！")
    private String password;


}
