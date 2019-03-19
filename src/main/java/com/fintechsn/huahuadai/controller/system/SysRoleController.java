package com.fintechsn.huahuadai.controller.system;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fintechsn.huahuadai.common.bean.ResponseCode;
import com.fintechsn.huahuadai.common.bean.ResponseResult;
import com.fintechsn.huahuadai.model.system.SysRole;
import com.fintechsn.huahuadai.model.system.dto.role.FindRoleDTO;
import com.fintechsn.huahuadai.model.system.dto.role.RoleAddDTO;
import com.fintechsn.huahuadai.model.system.dto.role.RoleUpdateDTO;
import com.fintechsn.huahuadai.service.system.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = {"/system/role"})
@Api(tags = {"角色管理"})
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @PostMapping("/add")
    @ApiOperation(value = "添加新增")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "身份认证Token", required = true)
    public ResponseResult add(@RequestBody RoleAddDTO roleAddDTO) {
        sysRoleService.add(roleAddDTO);
        return ResponseResult.e(ResponseCode.OK);
    }

    @PostMapping("/update/{id}")
    @ApiOperation(value = "更新指定ID对象的信息")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "身份认证Token", required = true)
    ResponseResult update(@PathVariable("id") String id, @RequestBody @Validated RoleUpdateDTO roleUpdateDTO) {
        sysRoleService.update(id, roleUpdateDTO);
        return ResponseResult.e(ResponseCode.OK);
    }

    @PostMapping("/list")
    @ApiOperation(value = "分页获取所有列表")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "身份认证Token", required = true)
    public ResponseResult<IPage<SysRole>> list(@RequestBody FindRoleDTO findDTO) {
        return ResponseResult.e(ResponseCode.OK, sysRoleService.list(findDTO));
    }

    @PostMapping(value = {"/remove/{id}"})
    @ApiOperation(value = "删除用户")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "身份认证Token", required = true)
    public ResponseResult remove(@PathVariable("id") @ApiParam(value = "用户标识ID") String id) {
        sysRoleService.removeRole(id);
        return ResponseResult.e(ResponseCode.OK);
    }
}
