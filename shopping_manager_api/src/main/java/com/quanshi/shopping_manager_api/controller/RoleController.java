package com.quanshi.shopping_manager_api.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quanshi.shopping_common.entity.Role;
import com.quanshi.shopping_common.result.BaseResult;
import com.quanshi.shopping_common.service.IRoleService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {


    @DubboReference
    private IRoleService roleService;

    /**
     * 根据主键id查询角色
     * @param rid 主键id
     * @return
     */
    @GetMapping("/findById")
    public BaseResult findById(Long rid){

        return BaseResult.success(roleService.findById(rid));
    }

    /**
     * 新增角色
     * @param role 角色对象
     * @return
     */
    @PostMapping("/add")
    public BaseResult add(@RequestBody Role role){
        roleService.add(role);
        return BaseResult.success();
    }

    /**
     * 根据主键id修改角色
     * @param role 角色对象
     * @return
     */
    @PutMapping("/update")
    public BaseResult update(@RequestBody Role role){
        roleService.update(role);
        return BaseResult.success();
    }

    /**
     * 根据主键id删除角色
     * @param rid 主键id
     * @return
     */
    @DeleteMapping("/delete")
    public BaseResult delete(Long rid){
        roleService.delete(rid);
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
        Page<Role> search = roleService.search(page, size);
        return BaseResult.success(search);
    }

    /**
     * 修改角色的权限
     * @param rid
     * @param pids
     * @return
     */
    @PutMapping("/updatePermissionToRole")
    public BaseResult updatePermissionToRole(Long rid,Long[] pids){
        roleService.updatePermissionToRole(rid,pids);
        return BaseResult.success();
    }

    /**
     * 查询所有角色
     * @return
     */
    @GetMapping("/findAll")
    public BaseResult findAll(){
        List<Role> all = roleService.findAll();
        return BaseResult.success(all);
    }


}
