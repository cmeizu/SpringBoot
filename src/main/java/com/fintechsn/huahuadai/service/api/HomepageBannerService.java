package com.fintechsn.huahuadai.service.api;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fintechsn.huahuadai.model.api.HomepageBanner;

import java.util.List;

public interface HomepageBannerService extends IService<HomepageBanner> {
    List<HomepageBanner> listEnable();
}
