package com.quanshi.shopping_file_service.service;

import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.quanshi.shopping_common.service.IFilerService;
import com.quanshi.shopping_file_service.config.OSSConfig;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@DubboService
public class FileServiceImpl implements IFilerService {


    @Autowired
    private OSSConfig ossConfig;


    public OSS getOss(){
        OSS ossClient = new OSSClientBuilder().build(ossConfig.getEndpoint(), ossConfig.getAccessKey(), ossConfig.getSecretKey());
        return ossClient;
    }

    @Override
    public String uploadImage(byte[] fileBytes, String fileName) {
        OSS oss = getOss();
        try {

            //获取文件后缀
            String suffix = FileNameUtil.getSuffix(fileName);// jpg,png,mp3,mp4
            //生成随机文件名称
            String random = RandomUtil.randomNumbers(20);

            //生成新的文件名称
            String newFileName = random + StrUtil.C_DOT + suffix;

            //将字节转换成IO
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(fileBytes);

            //oss上传文件
            oss.putObject(ossConfig.getBucketName(), newFileName, byteArrayInputStream);

            return ossConfig.getServerUrl()+ StrUtil.C_SLASH+newFileName;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            oss.shutdown();
        }
    }

    @Override
    public void delete(String filePath) {
        OSS oss = getOss();
        try {
            //http://jinken-shopping.oss-cn-beijing.aliyuncs.com/6677c385b35e056b4003218e.jpg
            //删除只需文件名称，截取文件名称
            List<String> list = StrUtil.split(filePath, FileNameUtil.UNIX_SEPARATOR);
            //获取最后一段
            String fileName = list.get(list.size()-1);
            //删除文件
            oss.deleteObject(ossConfig.getBucketName(), fileName);
        }finally {
            oss.shutdown();
        }
    }
}
