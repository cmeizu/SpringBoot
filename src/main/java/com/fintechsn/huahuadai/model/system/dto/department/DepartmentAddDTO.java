package com.fintechsn.huahuadai.model.system.dto.department;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class DepartmentAddDTO {

    @TableId
    private String id;

    private String name;

}
