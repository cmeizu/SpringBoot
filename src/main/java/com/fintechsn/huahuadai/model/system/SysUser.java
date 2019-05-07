package com.fintechsn.huahuadai.model.system;

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
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SysUser implements Serializable {


    @TableId(type = IdType.ID_WORKER_STR)
    private String id;
    private String username;
    private Integer age;
    private String password;
    private Integer status;
    @TableField(exist = false)
    private List<SysRole> roles;
    @JsonSerialize(using = DateJsonSerializer.class)
    @JsonDeserialize(using = DateJsonDeserializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;
    @TableField(exist = false)
    private String departmentId;
    private static final long serialVersionUID = 1L;

}