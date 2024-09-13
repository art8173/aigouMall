package com.quanshi.shopping_manager_api.controller;


import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.StpUtil;
import co.elastic.clients.elasticsearch.xpack.usage.Base;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quanshi.shopping_common.entity.Admin;
import com.quanshi.shopping_common.result.BaseResult;
import com.quanshi.shopping_common.service.IAdminService;
import com.quanshi.shopping_manager_api.service.ILoginService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 管理员管理
 */
@RestController
@RequestMapping("/admin")
public class AdminController {


    @DubboReference
    private IAdminService adminService;


    @Autowired
    private ILoginService loginService;


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
     * 新增管理员
     * @param admin 管理员对象
     * @return
     */
    @PostMapping("/add")
    public BaseResult add(@RequestBody Admin admin){
        //密码加密
        admin.setPassword(SaSecureUtil.md5(admin.getPassword()));
        adminService.add(admin);
        return BaseResult.success();
    }


    /**
     * 根据主键id修改管理员
     * @param admin 管理员对象
     * @return
     */
    @PutMapping("/update")
    public BaseResult update(@RequestBody Admin admin){
        //密码加密
        admin.setPassword(SaSecureUtil.md5(admin.getPassword()));
        adminService.update(admin);
        return BaseResult.success();
    }


    @DeleteMapping("/delete")
    public BaseResult delete(Long aid){
        adminService.deleteById(aid);
        return BaseResult.success();
    }


    @GetMapping("/search")
    public BaseResult search(@RequestParam(defaultValue = "1") Integer page,
                             @RequestParam(defaultValue = "10") Integer size){
        Page<Admin> search = adminService.search(page, size);
        return BaseResult.success(search);
    }

    @PutMapping("/updateRoleToAdmin")
    public BaseResult updateRoleToAdmin(Long aid,Long[] rids){
        adminService.updateRoleToAdmin(aid,rids);
        return BaseResult.success();
    }


    @PostMapping("/login")
    public BaseResult login(String username,String password){
        return loginService.login(username,password);
    }

    @PostMapping("/logout")
    public BaseResult logout(){
        //清除内存Session信息
        StpUtil.logout();
        return BaseResult.success();
    }


    @GetMapping("/getUsername")
    public BaseResult getUsername(){
        return BaseResult.success(StpUtil.getLoginId());
    }


}
