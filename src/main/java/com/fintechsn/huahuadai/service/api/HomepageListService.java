package com.fintechsn.huahuadai.service.api;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fintechsn.huahuadai.model.api.HomepageList;

import java.util.List;

public interface HomepageListService extends IService<HomepageList> {
    /**
     * 首页产品列表
     * @return
     */
    List<HomepageList> homelist();
}
