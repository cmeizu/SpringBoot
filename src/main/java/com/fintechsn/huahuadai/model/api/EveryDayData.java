package com.fintechsn.huahuadai.model.api;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EveryDayData implements Serializable {

    @TableId(type = IdType.AUTO)
    private int id;
    private int num;
    private int decrementNum;
    private int type;
    private long channelId;
    private String dateTime;
    private Date createTime;
}
