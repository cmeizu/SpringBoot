package com.fintechsn.huahuadai.service.api;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fintechsn.huahuadai.model.api.ProductIpRecord;


public interface ProductIpRecordService extends IService<ProductIpRecord> {

    boolean addIpRecord(Integer id, String ip);

}
