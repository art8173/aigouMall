package com.quanshi.shopping_manager_api.controller;

import com.quanshi.shopping_common.result.BaseResult;
import com.quanshi.shopping_common.service.IFilerService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/file")
public class FileController {


    @DubboReference
    private IFilerService filerService;

    /**
     * 上传图片
     * @param file 文件对象
     * @return
     * @throws IOException
     */
    @PostMapping("/uploadImage")
    public BaseResult uploadImage(MultipartFile file) throws IOException {
        String url = filerService.uploadImage(file.getBytes(), file.getOriginalFilename());
        return BaseResult.success(url);
    }

    /**
     * 根据图片地址删除图片
     * @param filePath
     * @return
     */
    @DeleteMapping("/delete")
    public BaseResult delete(String filePath){
        filerService.delete(filePath);
        return BaseResult.success();
    }
}
