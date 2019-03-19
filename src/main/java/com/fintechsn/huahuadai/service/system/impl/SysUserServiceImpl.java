package com.fintechsn.huahuadai.service.system.impl;

import cn.licoy.encryptbody.util.MD5EncryptUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fintechsn.huahuadai.common.bean.ResponseCode;
import com.fintechsn.huahuadai.common.exception.RequestException;
import com.fintechsn.huahuadai.common.util.Tools;
import com.fintechsn.huahuadai.mapper.system.SysUserMapper;
import com.fintechsn.huahuadai.model.system.SysRole;
import com.fintechsn.huahuadai.model.system.SysUser;
import com.fintechsn.huahuadai.model.system.SysUserDepartment;
import com.fintechsn.huahuadai.model.system.SysUserRole;
import com.fintechsn.huahuadai.model.system.dto.user.FindUserDTO;
import com.fintechsn.huahuadai.model.system.dto.SignInDTO;
import com.fintechsn.huahuadai.model.system.dto.user.ResetPasswordDTO;
import com.fintechsn.huahuadai.model.system.dto.user.UserAddDTO;
import com.fintechsn.huahuadai.model.system.dto.user.UserUpdateDTO;
import com.fintechsn.huahuadai.model.system.vo.SysUserVO;
import com.fintechsn.huahuadai.service.shiro.ShiroService;
import com.fintechsn.huahuadai.service.system.SysRoleService;
import com.fintechsn.huahuadai.service.system.SysUserDepartmentService;
import com.fintechsn.huahuadai.service.system.SysUserRoleService;
import com.fintechsn.huahuadai.service.system.SysUserService;
import com.fintechsn.huahuadai.shiro.jwt.JwtToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
@Transactional
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private SysUserDepartmentService sysUserDepartmentService;
    @Autowired
    private ShiroService shiroService;

    @Override
    public SysUser findUserByName(String name, boolean hasResource) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("username", name);
        SysUser user = this.getOne(queryWrapper);
        if (user == null) {
            return null;
        }
        user.setRoles(sysRoleService.findAllRoleByUserId(user.getId(), hasResource));
        return user;
    }

    @Override
    public SysUser findUserById(String id, boolean hasResource) {
        SysUser user = getById(id);
        if (user == null) {
            return null;
        }
        user.setRoles(sysRoleService.findAllRoleByUserId(user.getId(), false));
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("uid", id);
        SysUserDepartment sysUserDepartment = sysUserDepartmentService.getOne(queryWrapper);
        if (ObjectUtils.isNotEmpty(sysUserDepartment)) {
            user.setDepartmentId(sysUserDepartment.getDepartmentid());
        }
        return user;
    }

    @Override
    public void signIn(SignInDTO signInDTO) {
        if ("".equals(signInDTO.getUsername()) || "".equals(signInDTO.getPassword())) {
            throw new RequestException(ResponseCode.SING_IN_INPUT_EMPTY);
        }
        JwtToken token = new JwtToken(null, signInDTO.getUsername(), signInDTO.getPassword());
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            if (!subject.isAuthenticated()) {
                throw new RequestException(ResponseCode.SIGN_IN_INPUT_FAIL);
            }
        } catch (DisabledAccountException e) {
            throw new RequestException(ResponseCode.SIGN_IN_INPUT_FAIL.code, e.getMessage(), e);
        } catch (Exception e) {
            throw new RequestException(ResponseCode.SIGN_IN_FAIL, e);
        }
    }

    @Override
    public void add(UserAddDTO addDTO) {
        SysUser findUser = this.findUserByName(addDTO.getUsername(), false);
        if (findUser != null) {
            throw RequestException.fail(
                    String.format("已经存在用户名为 %s 的用户", addDTO.getUsername()));
        }
        try {
            findUser = new SysUser();
            BeanUtils.copyProperties(addDTO, findUser);
            findUser.setCreateDate(new Date());
            findUser.setPassword(MD5EncryptUtil.encrypt(String.valueOf(findUser.getPassword()) + findUser.getUsername()));
            save(findUser);
            sysUserDepartmentService.addUserDepartment(findUser.getId(), addDTO.getDepartmentId());
            this.updateUserRole(findUser);
        } catch (Exception e) {
            throw RequestException.fail("添加用户失败", e);
        }
    }

    @Override
    public List<String> getAllPermissionTag() {
        Tools.executeLogin();
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            throw new RequestException(ResponseCode.NOT_SING_IN);
        }
        JwtToken jwtToken = new JwtToken();
        Object principal = subject.getPrincipal();
        if (principal == null) {
            throw RequestException.fail("用户信息获取失败");
        }
        BeanUtils.copyProperties(principal, jwtToken);
        QueryWrapper userQueryWrapper = new QueryWrapper();
        userQueryWrapper.eq("username", jwtToken.getUsername());
        SysUser user = this.getOne(userQueryWrapper);
        if (user == null) {
            throw RequestException.fail("用户不存在");
        }
        List<SysRole> allRoleByUserId = sysRoleService.findAllRoleByUserId(user.getId(), true);
        List<String> permissions = new LinkedList<>();
        for (SysRole sysRole : allRoleByUserId) {
            if (sysRole.getResources() != null && sysRole.getResources().size() > 0) {
                sysRole.getResources().forEach(s -> permissions.add(s.getPermission()));
            }
        }
        return permissions;
    }

    @Override
    public void update(String id, UserUpdateDTO updateDTO) {
        SysUser user = this.getById(id);
        if (user == null) {
            throw RequestException.fail(
                    String.format("更新失败，不存在ID为 %s 的用户", id));
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("username", updateDTO.getUsername());
        queryWrapper.ne("id", id);
        SysUser findUser = this.getOne(queryWrapper);
        if (findUser != null) {
            throw RequestException.fail(
                    String.format("更新失败，已经存在用户名为 %s 的用户", updateDTO.getUsername()));
        }
        BeanUtils.copyProperties(updateDTO, user);
        try {
            this.updateById(user);
            this.updateUserRole(user);
            sysUserDepartmentService.updateUserDepartment(user.getId(), updateDTO.getDepartmentId());
            shiroService.clearAuthByUserId(user.getId(), true, false);
        } catch (RequestException e) {
            throw RequestException.fail(e.getMsg(), e);
        } catch (Exception e) {
            throw RequestException.fail("用户信息更新失败", e);
        }
    }

    @Override
    public void updateUserRole(SysUser user) {
        try {
            QueryWrapper sysUserRoleQueryWrapper = new QueryWrapper();
            sysUserRoleQueryWrapper.eq("uid", user.getId());
            sysUserRoleService.remove(sysUserRoleQueryWrapper);
            if (user.getRoles() != null && user.getRoles().size() > 0) {
                user.getRoles().forEach(v -> sysUserRoleService.save(SysUserRole.builder()
                        .uid(user.getId())
                        .rid(v.getId()).build()));
            }
        } catch (Exception e) {
            throw RequestException.fail("用户权限关联失败", e);
        }
    }

    @Override
    public IPage<SysUserVO> getAllUserBySplitPage(FindUserDTO findUserDTO) {
        QueryWrapper sysUserQueryWrapper = new QueryWrapper();
        sysUserQueryWrapper.orderByAsc("create_date");
        IPage<SysUser> userPage = this.page(new Page<>(findUserDTO.getPage(),
                findUserDTO.getPageSize()), sysUserQueryWrapper);
        IPage<SysUserVO> userVOPage = new Page<>();
        BeanUtils.copyProperties(userPage, userVOPage);
        List<SysUserVO> userVOS = new ArrayList<>();
        userPage.getRecords().forEach(v -> {
            SysUserVO vo = new SysUserVO();
            BeanUtils.copyProperties(v, vo);
            //查找匹配所有用户的角色
            vo.setRoles(sysRoleService.findAllRoleByUserId(v.getId(), false));
            userVOS.add(vo);
        });
        userVOPage.setRecords(userVOS);
        userVOPage.setTotal(userPage.getTotal());
        return userVOPage;
    }

    @Override
    public void removeUser(String userId) {
        SysUser user = this.getById(userId);
        if (user == null) {
            throw RequestException.fail("用户不存在！");
        }
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(SecurityUtils.getSubject().getPrincipal(), sysUser);
        if (user.getUsername().equals(sysUser.getUsername())) {
            throw RequestException.fail("不能删除自己的账户！");
        }
        try {
            this.removeById(userId);
            shiroService.clearAuthByUserId(userId, true, true);
        } catch (Exception e) {
            throw RequestException.fail("删除失败", e);
        }
    }

    @Override
    public void resetPassword(ResetPasswordDTO resetPasswordDTO) {
        SysUser user = this.getById(resetPasswordDTO.getUid().trim());
        if (user == null) {
            throw RequestException.fail(String.format("不存在ID为 %s 的用户", resetPasswordDTO.getUid()));
        }
        String password = MD5EncryptUtil.encrypt(String.valueOf(resetPasswordDTO.getPassword()) + user.getUsername());
        user.setPassword(password);
        try {
            this.updateById(user);
            shiroService.clearAuthByUserId(user.getId(), true, true);
        } catch (Exception e) {
            throw RequestException.fail(String.format("ID为 %s 的用户密码重置失败", resetPasswordDTO.getUid()), e);
        }
    }

}
