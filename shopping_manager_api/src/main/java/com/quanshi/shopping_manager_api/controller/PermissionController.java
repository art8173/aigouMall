package com.quanshi.shopping_manager_api.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quanshi.shopping_common.entity.Permission;
import com.quanshi.shopping_common.entity.Permission;
import com.quanshi.shopping_common.result.BaseResult;
import com.quanshi.shopping_common.service.IPermissionService;
import com.quanshi.shopping_common.service.IPermissionService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permission")
public class PermissionController {


    @DubboReference
    private IPermissionService permissionService;

    /**
     * 根据主键id查询权限
     * @param pid 主键id
     * @return
     */
    @GetMapping("/findById")
    public BaseResult findById(Long pid){
        return BaseResult.success(permissionService.findById(pid));
    }

    /**
     * 新增权限
     * @param permission 权限对象
     * @return
     */
    @PostMapping("/add")
    public BaseResult add(@RequestBody Permission permission){
        permissionService.add(permission);
        return BaseResult.success();
    }

    /**
     * 根据主键id修改权限
     * @param permission 权限对象
     * @return
     */
    @PutMapping("/update")
    public BaseResult update(@RequestBody Permission permission){
        permissionService.update(permission);
        return BaseResult.success();
    }

    /**
     * 根据主键id删除权限
     * @param pid 主键id
     * @return
     */
    @DeleteMapping("/delete")
    public BaseResult delete(Long pid){
        permissionService.delete(pid);
        return BaseResult.success();
    }

    /**
     * 分页查询
     * @param page 页码
     * @param size 每页条数
     * @return
     */
    @GetMapping("/search")
    public BaseResult search(@RequestParam(defaultValue = "1") Integer page,
                             @RequestParam(defaultValue = "10") Integer size){
        Page<Permission> search = permissionService.search(page, size);
        return BaseResult.success(search);
    }


    /**
     * 查询所有权限
     * @return
     */
    @GetMapping("/findAll")
    public BaseResult findAll(){
        List<Permission> all = permissionService.findAll();
        return BaseResult.success(all);
    }


}
