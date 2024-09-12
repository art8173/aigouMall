package com.quanshi.shopping_manager_api.controller;


import com.quanshi.shopping_common.entity.Admin;
import com.quanshi.shopping_common.result.BaseResult;
import com.quanshi.shopping_common.service.IAdminService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

/**
 * 管理员管理
 */
@RestController
@RequestMapping("/admin")
public class AdminController {


    @DubboReference
    private IAdminService adminService;

    /**
     * 新增管理员
     * @param admin 管理员对象
     * @return
     */
    @PostMapping("/add")
    public BaseResult add(@RequestBody Admin admin){
        adminService.add(admin);
        return BaseResult.success();
    }

    /**
     * 根据主键id查询管理员
     * @param aid 主键id
     * @return
     */
    @GetMapping("/findById")
    public BaseResult findById(Long aid){
        Admin admin = adminService.findById(aid);
        return BaseResult.success(admin);
    }

    /**
     * 根据主键id修改管理员
     * @param admin 管理员对象
     * @return
     */
    @PutMapping("/update")
    public BaseResult update(@RequestBody Admin admin){
        adminService.update(admin);
        return BaseResult.success();
    }


    @DeleteMapping("/delete")
    public BaseResult delete(Long aid){
        adminService.deleteById(aid);
        return BaseResult.success();
    }

}
