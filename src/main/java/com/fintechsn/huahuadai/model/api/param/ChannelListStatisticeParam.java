package com.fintechsn.huahuadai.model.api.param;

import com.fintechsn.huahuadai.model.system.dto.SplitPageDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ChannelListStatisticeParam extends SplitPageDTO {
    private String startTime;

    private String endTime;

    private String channelId;

    private int type;
}
