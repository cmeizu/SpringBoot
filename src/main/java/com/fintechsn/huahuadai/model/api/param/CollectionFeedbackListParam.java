package com.fintechsn.huahuadai.model.api.param;

import com.fintechsn.huahuadai.model.system.dto.SplitPageDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CollectionFeedbackListParam extends SplitPageDTO {

    private String userName;
    private String phone;
    private String overdueGrade;
    private String collectionResult;
    private String companyId;
    private String collectorId;
    private String collectionOrderId;
}
