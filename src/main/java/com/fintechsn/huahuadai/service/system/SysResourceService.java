package com.fintechsn.huahuadai.service.system;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fintechsn.huahuadai.model.system.SysResource;
import com.fintechsn.huahuadai.model.system.dto.resource.ResourceDTO;

import java.util.List;
import java.util.Map;

public interface SysResourceService extends IService<SysResource> {

    List<SysResource> findAllResource();

    /**
     * 递归查找所有的子集
     *
     * @param resource
     */
    void findAllChild(SysResource resource);

    /**
     * 获取资源所有的父级
     *
     * @param resource  资源
     * @param cacheMap  缓存对象
     * @param cacheMap2 缓存对象
     * @return 资源
     */
    SysResource getResourceAllParent(SysResource resource, Map<String, SysResource> cacheMap,
                                     Map<String, SysResource> cacheMap2);

    /**
     * 添加资源
     *
     * @param dto
     */
    void add(ResourceDTO dto);

    /**
     * 更新资源
     *
     * @param id
     * @param dto
     */
    void update(String id, ResourceDTO dto);

    /**
     * 删除资源
     *
     * @param id
     */
    void remove(String id);


}
