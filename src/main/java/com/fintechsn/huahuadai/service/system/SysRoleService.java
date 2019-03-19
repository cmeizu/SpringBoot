package com.fintechsn.huahuadai.service.system;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fintechsn.huahuadai.model.system.SysRole;
import com.fintechsn.huahuadai.model.system.dto.role.FindRoleDTO;
import com.fintechsn.huahuadai.model.system.dto.role.RoleAddDTO;
import com.fintechsn.huahuadai.model.system.dto.role.RoleUpdateDTO;

import java.util.List;

public interface SysRoleService extends IService<SysRole> {
    /**
     * 获取指定ID用户的所有角色（并附带查询所有的角色的权限）
     *
     * @param uid 用户ID
     * @return 角色集合
     */
    List<SysRole> findAllRoleByUserId(String uid, Boolean hasResource);

    /**
     * 列表展示
     *
     * @param findRoleDTO
     * @return
     */
    IPage<SysRole> list(FindRoleDTO findRoleDTO);

    /**
     * 添加角色
     *
     * @param addDTO
     */
    void add(RoleAddDTO addDTO);

    /**
     * 更新角色
     *
     * @param rid
     * @param roleUpdateDTO
     */
    void update(String rid, RoleUpdateDTO roleUpdateDTO);

    /**
     * 更新缓存
     *
     * @param role   角色
     * @param author 是否清空授权信息
     * @param out    是否清空session
     */
    void updateCache(SysRole role, Boolean author, Boolean out);

    /**
     * 删除用户
     *
     * @param roleId 角色ID
     */
    void removeRole(String roleId);

}
