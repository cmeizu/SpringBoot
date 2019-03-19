package com.fintechsn.huahuadai.model.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SysUserDepartment implements Serializable {

    @TableId(type = IdType.ID_WORKER_STR)
    private String id;

    private String uid;

    private String departmentid;
}
