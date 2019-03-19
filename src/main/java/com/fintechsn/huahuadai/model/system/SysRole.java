package com.fintechsn.huahuadai.model.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SysRole implements Serializable {

    @TableId(type = IdType.ID_WORKER_STR)
    private String id;

    private String name;

    @TableField(exist = false)
    private List<SysResource> resources;

    private static final long serialVersionUID = 1L;

}