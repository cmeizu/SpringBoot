package com.fintechsn.huahuadai.model.api;

import com.baomidou.mybatisplus.annotation.IdType;
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
import java.util.Date;

/**
 * @version : Ver 1.0
 * @User
 * @用户基本信息(user)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id BIGINT(19)
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 手机
     * phone char(11)
     */
    private String phone;
    /**
     * @备注:用户名
     * @字段:user_name varchar(64)
     */
    private String userName;
    /**
     * @备注:密码
     * @字段:password varchar(64)
     */
    private String password;
    /**
     * @备注:渠道id
     * @字段:channel_id int(11)
     */
    private Integer channelId;

    /**
     * @备注:设备类型
     * @字段:app char(10)
     */
    private String app;
    /**
     * @备注:版本号
     * @字段:version
     */
    private String version;
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
