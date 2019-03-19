package com.fintechsn.huahuadai.service.system;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fintechsn.huahuadai.model.api.AboutXed;

import java.util.Map;

public interface AboutXedService extends IService<AboutXed> {
    /**
     * 关于我们
     */
    AboutXed aboutus(Integer type);
}
