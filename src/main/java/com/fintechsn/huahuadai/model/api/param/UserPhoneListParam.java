package com.fintechsn.huahuadai.model.api.param;

import com.fintechsn.huahuadai.model.system.dto.SplitPageDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserPhoneListParam extends SplitPageDTO {

    private String userId;

}
