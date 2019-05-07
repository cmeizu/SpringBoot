package com.fintechsn.huahuadai.model.system.dto;

import lombok.Data;

import javax.validation.constraints.Pattern;

/**
 * @author licoy.cn
 * @version 2018/1/24
 */
@Data
public class SignInDTO {

    private String username;

    @Pattern(regexp = "^(\\w){6,18}$", message = "密码应为[A-Za-z0-9_]组成的6-18位字符！")
    private String password;

}
