package com.fintechsn.huahuadai.controller.system;

import com.fintechsn.huahuadai.common.bean.ResponseCode;
import com.fintechsn.huahuadai.common.bean.ResponseResult;
import com.fintechsn.huahuadai.model.system.dto.department.DepartmentAddDTO;
import com.fintechsn.huahuadai.model.system.dto.department.DepartmentUpdateDTO;
import com.fintechsn.huahuadai.model.system.dto.department.FindDepartmentDTO;
import com.fintechsn.huahuadai.service.system.SysDepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/system/company")
@Api(tags = {"部门管理"})
public class SysDepartmentController {

    @Autowired
    private SysDepartmentService sysDepartmentService;

    @PostMapping(value = {"/add"})
    @ApiOperation(value = "添加部门")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "身份认证Token")
    public ResponseResult add(@RequestBody @Validated @ApiParam(value = "部门数据") DepartmentAddDTO addDTO) {
        sysDepartmentService.add(addDTO);
        return ResponseResult.e(ResponseCode.OK);
    }

    @PostMapping(value = {"/get/{id}"})
    @ApiOperation(value = "根据ID获取部门信息")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "身份认证Token")
    public ResponseResult getById(@PathVariable("id") @ApiParam(value = "部门ID") String id) {
        return ResponseResult.e(ResponseCode.OK, sysDepartmentService.getDepartment(id));
    }

    @PostMapping(value = {"/update/{id}"})
    @ApiOperation(value = "更新部门")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "身份认证Token")
    public ResponseResult update(@PathVariable("id") @ApiParam(value = "部门标识ID") String id,
                                 @RequestBody @Validated @ApiParam(value = "部门数据") DepartmentUpdateDTO updateDTO) {
        sysDepartmentService.update(id, updateDTO);
        return ResponseResult.e(ResponseCode.OK);
    }

    @PostMapping(value = {"/list"})
    @ApiOperation(value = "分页获取部门数据")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "身份认证Token")
    public ResponseResult get(@RequestBody @Validated @ApiParam(value = "") FindDepartmentDTO findDepartmentDTO) {
        return ResponseResult.e(ResponseCode.OK, sysDepartmentService.list(findDepartmentDTO));
    }

    @PostMapping(value = {"/remove/{id}"})
    @ApiOperation(value = "删除部门")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "身份认证Token")
    public ResponseResult remove(@PathVariable("id") @ApiParam(value = "部门标识ID") String id) {
        sysDepartmentService.deleteDepartment(id);
        return ResponseResult.e(ResponseCode.OK);
    }

}
