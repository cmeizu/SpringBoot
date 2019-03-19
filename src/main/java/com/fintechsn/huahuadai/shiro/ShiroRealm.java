package com.fintechsn.huahuadai.shiro;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fintechsn.huahuadai.common.exception.RequestException;
import com.fintechsn.huahuadai.common.util.JwtUtil;
import com.fintechsn.huahuadai.model.system.SysUser;
import com.fintechsn.huahuadai.service.system.SysUserService;
import com.fintechsn.huahuadai.shiro.jwt.JwtToken;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ShiroRealm extends AuthorizingRealm {

    private Logger logger = LoggerFactory.getLogger(ShiroRealm.class);

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private CacheManager cacheManager;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        JwtToken jwtToken = new JwtToken();
        BeanUtils.copyProperties(principalCollection.getPrimaryPrincipal(), jwtToken);
        if (jwtToken.getUsername() != null) {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            SysUser findUser = sysUserService.findUserByName(jwtToken.getUsername(), true);
            logger.info("doGetAuthorizationInfo:" + JSON.toJSONString(findUser));
            if (findUser != null) {
                if (findUser.getRoles() != null) {
                    findUser.getRoles().forEach(role -> {
                        info.addRole(role.getName());
                        if (role.getResources() != null) {
                            role.getResources().forEach(v -> {
                                if (!"".equals(v.getPermission().trim())) {
                                    info.addStringPermission(v.getPermission());
                                }
                            });
                        }
                    });
                }
                return info;
            }
        }
        throw new DisabledAccountException("用户信息异常，请重新登录！");
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        JwtToken token = (JwtToken) authenticationToken;
        SysUser user;
        String username = token.getUsername() != null ? token.getUsername() : JwtUtil.getUsername(token.getToken());
        try {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("username", username);
            user = sysUserService.getOne(queryWrapper);
            logger.info("doGetAuthenticationInfo user:" + JSON.toJSONString(user));
        } catch (RequestException e) {
            throw new DisabledAccountException(e.getMsg());
        }
        if (user == null) {
            throw new DisabledAccountException("用户不存在！");
        }
        if (user.getStatus() != 1) {
            throw new DisabledAccountException("用户账户已锁定，暂无法登陆！");
        }
        if (token.getUsername() == null) token.setUsername(user.getUsername());
        String sign = JwtUtil.sign(user.getId(), user.getUsername(), user.getPassword());
        if (token.getToken() == null) token.setToken(sign);
        token.setUid(user.getId());
        logger.info("doGetAuthenticationInfo token:" + JSON.toJSONString(token));
        return new SimpleAuthenticationInfo(token, user.getPassword(), user.getId());
    }

    public void clearAuthByUserId(String uid, Boolean author, Boolean out) {
        //获取所有session
        Cache<Object, Object> cache = cacheManager
                .getCache(ShiroRealm.class.getName() + ".authorizationCache");
        cache.remove(uid);
    }

    public void clearAuthByUserIdCollection(List<String> userList, Boolean author, Boolean out) {
        Cache<Object, Object> cache = cacheManager
                .getCache(ShiroRealm.class.getName() + ".authorizationCache");
        userList.forEach(cache::remove);
    }
}
