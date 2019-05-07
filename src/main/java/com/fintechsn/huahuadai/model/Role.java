package com.fintechsn.huahuadai.model;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * @version : Ver 1.0
 * @Role
 * @角色(role)
 */
@TableName("role")
public class Role implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * @备注:主键id
     * @字段:id BIGINT(19)
     */
    @TableId(type = IdType.AUTO)
    private Long id;


    /**
     * @备注:角色名称
     * @字段:title VARCHAR(55)
     */
    private String title;


    public Role() {
    }

    public Role(
            Long id
    ) {
        this.id = id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }
}
