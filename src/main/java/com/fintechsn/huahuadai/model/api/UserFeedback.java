package com.fintechsn.huahuadai.model.api;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fintechsn.huahuadai.util.DateJsonDeserializer;
import com.fintechsn.huahuadai.util.DateJsonSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserFeedback implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id int(11)
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * user_id int(11)
     */
    private Integer userId;

    /**
     * 反馈类型
     * type INT(10)
     */
    private Integer type;


    /**
     * content MEDIUMTEXT(16777215)
     */
    private String content;

    /**
     * 手机号
     * phone VARCHAR(255)
     */
    private String phone;


    /**
     * gmt_datetime DATETIME(19)
     */
    @JsonSerialize(using = DateJsonSerializer.class)
    @JsonDeserialize(using = DateJsonDeserializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date gmtDatetime = new java.util.Date();


    /**
     * upt_datetime DATETIME(19)
     */
    @JsonSerialize(using = DateJsonSerializer.class)
    @JsonDeserialize(using = DateJsonDeserializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date uptDatetime = new java.util.Date();
    @TableField(exist = false)
    private String[] imageUrls;
}
