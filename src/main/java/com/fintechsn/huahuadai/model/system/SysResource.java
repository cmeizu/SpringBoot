package com.fintechsn.huahuadai.model.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SysResource implements Serializable {

    @TableId(type = IdType.ID_WORKER_STR)
    private String id;

    private String name;

    private String parentId;

    private Short type;

    private String url;

    private String permission;

    private String color;

    private String icon;

    private Long sort;

    private Boolean verification;

    private Date createDate;

    @TableField(exist = false)
    private List<SysResource> children;

}
