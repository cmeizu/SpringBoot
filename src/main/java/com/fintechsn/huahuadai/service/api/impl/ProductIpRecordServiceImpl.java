package com.fintechsn.huahuadai.service.api.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fintechsn.huahuadai.mapper.api.ProductIpRecordMapper;
import com.fintechsn.huahuadai.model.api.ProductIpRecord;
import com.fintechsn.huahuadai.service.api.LoanMarketShowService;
import com.fintechsn.huahuadai.service.api.ProductIpRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class ProductIpRecordServiceImpl extends ServiceImpl<ProductIpRecordMapper, ProductIpRecord> implements ProductIpRecordService {

    /**
     * 由ip来统计pv和uv
     * @param id
     * @param ip
     * @return
     */
    @Override
    public boolean addIpRecord(Integer id, String ip) {
        ProductIpRecord ipRecord = new ProductIpRecord();
        ipRecord.setIp(ip);
        return save(ipRecord);
    }
}
