package com.fintechsn.huahuadai.service.system;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fintechsn.huahuadai.model.system.SysUser;
import com.fintechsn.huahuadai.model.system.dto.user.FindUserDTO;
import com.fintechsn.huahuadai.model.system.dto.SignInDTO;
import com.fintechsn.huahuadai.model.system.dto.user.ResetPasswordDTO;
import com.fintechsn.huahuadai.model.system.dto.user.UserAddDTO;
import com.fintechsn.huahuadai.model.system.dto.user.UserUpdateDTO;
import com.fintechsn.huahuadai.model.system.vo.SysUserVO;

import java.util.List;

public interface SysUserService extends IService<SysUser> {
    /**
     * 根据用户名查找用户
     *
     * @param name 用户名
     * @return User
     */
    SysUser findUserByName(String name, boolean hasResource);

    /**
     * 根据ID查找用户
     *
     * @param id ID
     * @return User
     */
    SysUser findUserById(String id, boolean hasResource);

    /**
     * 用户登录操作
     *
     * @param signInDTO 登录信息
     */
    void signIn(SignInDTO signInDTO);

    /**
     * 添加用户
     *
     * @param addDTO 用户数据DTO
     */
    void add(UserAddDTO addDTO);

    /**
     * 获取当前登录用户所有的权限标示
     */
    List<String> getAllPermissionTag();

    /**
     * 更新用户角色关联
     *
     * @param user
     * @return
     */
    void updateUserRole(SysUser user);

    /**
     * 获取所有用户（分页）
     *
     * @param findUserDTO 过滤条件
     * @return RequestResult
     */
    IPage<SysUserVO> getAllUserBySplitPage(FindUserDTO findUserDTO);

    /**
     * 更新用户数据
     *
     * @param id        用户id
     * @param updateDTO 用户数据DTO
     */
    void update(String id, UserUpdateDTO updateDTO);

    /**
     * 删除用户
     *
     * @param userId 用户ID
     */
    void removeUser(String userId);

    /**
     * 重置用户密码
     *
     * @param resetPasswordDTO
     */
    void resetPassword(ResetPasswordDTO resetPasswordDTO);

}
