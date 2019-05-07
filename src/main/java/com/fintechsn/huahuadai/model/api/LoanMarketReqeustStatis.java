package com.fintechsn.huahuadai.model.api;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fintechsn.huahuadai.util.DateJsonDeserializer;
import com.fintechsn.huahuadai.util.DateJsonSerializer;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@TableName("loan_market_reqeust_statis")
@Data
public class LoanMarketReqeustStatis {
    /**
     * @备注:id
     * @字段:id int(11)
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * @备注:channel_id
     * @字段:channel_id int(11)
     */
    private Integer channelId;

    /**
     * @备注:page_view
     * @字段:page_view int(11)
     */
    private Integer pageView;
    /**
     * @备注:uniq_ip_view
     * @字段:uniq_ip_view int(11)
     */
    private Integer uniqIpView;
    /**
     * @备注：创建人
     * @字段：create_by
     */
    private String createBy = "system";
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
    private String updateBy = "system";
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
