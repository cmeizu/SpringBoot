package com.fintechsn.huahuadai.model.system.dto.user;

import com.fintechsn.huahuadai.model.system.SysRole;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class UserAddDTO {

    @Pattern(regexp = "^(\\w){4,16}$", message = "用户名应为[A-Za-z0-9_]组成的4-16位字符！")
    private String username;

    @Pattern(regexp = "^(\\w){6,18}$", message = "密码应为[A-Za-z0-9_]组成的6-18位字符！")
    private String password;

    @NotNull(message = "年龄不能为空")
    private Integer age;

    @NotNull(message = "状态标识不能为空")
    private Integer status;

    private String departmentId;

    @Size(min = 1, message = "请至少选择一个角色")
    private List<SysRole> roles;

}