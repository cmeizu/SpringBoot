package com.fintechsn.huahuadai.model.api;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fintechsn.huahuadai.util.DateJsonDeserializer;
import com.fintechsn.huahuadai.util.DateJsonSerializer;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @version : Ver 1.0
 * @AboutXed
 * @(about_xed)
 */
@Data
@TableName("about_xed")
public class AboutXed implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * @备注:
     * @字段:id int(11)
     */
    @TableId(type = IdType.AUTO)
    private Integer id;


    /**
     * @备注:
     * @字段:about_us MEDIUMTEXT(16777215)
     */
    private String aboutUs;
    /**
     * @备注:1.关于我们 2.商务合作
     * @字段:type tinyint
     */
    private Integer type;

    /**
     * @备注:
     * @字段:serve_phone VARCHAR(255)
     */
    private String servePhone;


    /**
     * @备注:
     * @字段:wexin VARCHAR(255)
     */
    private String wexin;

    /**
     * @备注：创建人
     * @字段：create_by
     */
    private String createBy;
    /**
     * @备注:创建时间
     * @字段:create_date DATE(10)
     */
    @JsonSerialize(using = DateJsonSerializer.class)
    @JsonDeserialize(using = DateJsonDeserializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate = new Date();
    /**
     * @备注：更新者
     * @字段：update_by　VARCHAR(64)
     */
    private String updateBy;
    /**
     * @备注:更新时间
     * @字段:update_date DATETIME(19)
     */
    @JsonSerialize(using = DateJsonSerializer.class)
    @JsonDeserialize(using = DateJsonDeserializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate = new Date();
    /**
     * @备注：备注
     * @字段:remarks VARCHAR(255)
     */
    private String remarks;
    /**
     * @备注：删除标记　０:正常1:删除
     * @字段:del_flg CHAR(1)
     */
    private String delFlag;
}
