package com.fintechsn.huahuadai.service.shiro.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fintechsn.huahuadai.common.bean.ResponseCode;
import com.fintechsn.huahuadai.common.exception.RequestException;
import com.fintechsn.huahuadai.model.system.SysResource;
import com.fintechsn.huahuadai.model.system.SysUser;
import com.fintechsn.huahuadai.service.shiro.ShiroService;
import com.fintechsn.huahuadai.service.system.SysResourceService;
import com.fintechsn.huahuadai.service.system.SysUserService;
import com.fintechsn.huahuadai.shiro.ShiroRealm;
import com.fintechsn.huahuadai.shiro.jwt.JwtToken;
import com.fintechsn.huahuadai.shiro.util.SpringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class
ShiroServiceImpl implements ShiroService {

    @Autowired
    private SysResourceService sysResourceService;
    @Autowired
    private SysUserService sysUserService;

    @Override
    public Map<String, String> getFilterChainDefinitionMap() {
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        List<String[]> permsList = new LinkedList<>();
        List<String[]> anonList = new LinkedList<>();
        List<SysResource> resources = sysResourceService.findAllResource();
        if (resources != null) {
            for (SysResource resource : resources) {
                if (!StringUtils.isEmpty(resource.getUrl()) && !StringUtils.isEmpty(resource.getPermission())) {
                    if (!"".equals(resource.getPermission().trim())) {
                        //判断是否需要权限验证
                        if (resource.getVerification()) {
                            permsList.add(0, new String[]{resource.getUrl() +
                                    "/**", "perms[" + resource.getPermission() + ":*]"});
                        } else {
                            anonList.add(0, new String[]{resource.getUrl() +
                                    "/**", "anon"});
                        }
                    }
                }
                iterationAllResourceInToFilter(resource, permsList, anonList);
            }
        }
        for (String[] strings : anonList) {
            filterChainDefinitionMap.put(strings[0], strings[1]);
        }

        for (String[] strings : permsList) {
            filterChainDefinitionMap.put(strings[0], strings[1]);
        }

        filterChainDefinitionMap.put("/**", "anon");

        return filterChainDefinitionMap;
    }

    @Override
    public void iterationAllResourceInToFilter(SysResource resource, List<String[]> permsList, List<String[]> anonList) {
        if (resource.getChildren() != null && resource.getChildren().size() > 0) {
            for (SysResource v : resource.getChildren()) {
                if (!StringUtils.isEmpty(v.getUrl()) && !StringUtils.isEmpty(v.getPermission())) {
                    if (v.getVerification()) {
                        permsList.add(0, new String[]{v.getUrl() + "/**", "perms[" + v.getPermission() + ":*]"});
                    } else {
                        anonList.add(0, new String[]{v.getUrl() + "/**", "anon"});
                    }
                    iterationAllResourceInToFilter(v, permsList, anonList);
                }
            }
        }
    }

    @Override
    public void reloadPerms() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = SpringUtils.getBean(ShiroFilterFactoryBean.class);
        AbstractShiroFilter abstractShiroFilter;
        try {
            abstractShiroFilter = (AbstractShiroFilter) shiroFilterFactoryBean.getObject();
        } catch (Exception e) {
            throw new RequestException(ResponseCode.FAIL.code, "重新加载权限失败", e);
        }
        PathMatchingFilterChainResolver filterChainResolver =
                (PathMatchingFilterChainResolver) abstractShiroFilter.getFilterChainResolver();
        DefaultFilterChainManager manager = (DefaultFilterChainManager) filterChainResolver
                .getFilterChainManager();
        /*清除旧版权限*/
        manager.getFilterChains().clear();
        shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();
        /*更新新数据*/
        Map<String, String> filterChainDefinitionMap = getFilterChainDefinitionMap();
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        filterChainDefinitionMap.forEach(manager::createChain);
    }

    @Override
    public void clearAuthByUserId(String uid, Boolean author, Boolean out) {
        ShiroRealm shiroRealm = SpringUtils.getBean(ShiroRealm.class);
        shiroRealm.clearAuthByUserId(uid, author, out);
    }

    @Override
    public void clearAuthByUserIdCollection(List<String> userList, Boolean author, Boolean out) {
        ShiroRealm shiroRealm = SpringUtils.getBean(ShiroRealm.class);
        shiroRealm.clearAuthByUserIdCollection(userList, author, out);
    }

    @Override
    public SysUser getSysUser() {
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
        SysUser sysUser = sysUserService.getOne(userQueryWrapper);
        return sysUser;
    }
}
