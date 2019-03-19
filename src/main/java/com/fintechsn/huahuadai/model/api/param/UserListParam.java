package com.fintechsn.huahuadai.model.api.param;

import com.fintechsn.huahuadai.model.system.dto.SplitPageDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserListParam extends SplitPageDTO {

    private String phone;

    private String code;

    private String userName;

    private String password;

    private String channelId;

    private String app;

    private String version;

}
