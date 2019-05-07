package com.fintechsn.huahuadai.oss;

import com.alibaba.fastjson.JSON;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Filename        OSSUploader.java
 * <p>
 * Description    阿里云OSS资源上传
 * Copyright     Copyright (c) 2016-2022 All Rights Reserved.
 * Company       fintechsn.com Inc Inc.
 *
 * @author 陈美足
 * @version 1.0
 * @date 2018/11/17 10:44
 */
@Service
public class OSSUploader {
    private static Logger log = LoggerFactory.getLogger(OSSUploader.class);

    @Autowired
    private OssParam ossParam;


    /**
     * @param inputStream
     * @param fileName
     * @param contentType
     * @return
     * @throws Exception
     */
    public String uploadFile(InputStream inputStream, String fileName, String contentType, String ossPath) {
        ossPath = ossPath + fileName;
        OSSClient ossClient = new OSSClient(ossParam.getEndpoint(), ossParam.getAccesskey(), ossParam.getAccessKeySecret());
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(contentType);
        PutObjectResult putObjectResult = ossClient.putObject(ossParam.getBucket(), ossPath, inputStream, metadata);
        log.info(JSON.toJSONString(putObjectResult));
        ossClient.shutdown();
        String url = ossParam.getImageUrl() + ossPath;
        log.info("<--oss uploadFile url:{} -->", url);
        return url;
    }

    /**
     * 文件上传
     *
     * @param imageBytes
     * @param fileName
     * @param contentType
     * @return
     */
    public String uploadFileBytes(byte[] imageBytes, String fileName, String contentType) {
        String ossPath = ossParam.getOssPath() + fileName;
        OSSClient ossClient = new OSSClient(ossParam.getEndpoint(), ossParam.getAccesskey(), ossParam.getAccessKeySecret());
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(contentType);
        ossClient.putObject(ossParam.getBucket(), ossPath, new ByteArrayInputStream(imageBytes), metadata);
        ossClient.shutdown();
        String url = ossParam.getImageUrl() + ossPath;
        log.info("<--oss uploadFile url:{} -->", url);
        return url;
    }


    /**
     * 文件上传
     *
     * @param imageBytes
     * @param fileName
     * @param contentType
     * @param ossPath
     * @return
     */
    public String uploadFileBytes(byte[] imageBytes, String fileName, String contentType, String ossPath) {
        ossPath = ossPath + fileName;
        OSSClient ossClient = new OSSClient(ossParam.getEndpoint(), ossParam.getAccesskey(), ossParam.getAccessKeySecret());
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(contentType);
        ossClient.putObject(ossParam.getBucket(), ossPath, new ByteArrayInputStream(imageBytes), metadata);
        ossClient.shutdown();
        String url = ossParam.getImageUrl() + ossPath;
        log.info("<--oss uploadFile url:{} -->", url);
        return url;
    }

    /**
     * 文件下载
     *
     * @param objectName
     * @return
     * @throws Exception
     */
    public String downloadFile(String objectName) throws Exception {
        log.info("<--downloadFile file:{}-->", objectName);
        OSSClient ossClient = new OSSClient(ossParam.getEndpoint(), ossParam.getAccesskey(), ossParam.getAccessKeySecret());
        if (!ossClient.doesObjectExist(ossParam.getBucket(), objectName)) {
            log.info("<--file does not exist :{}-->", objectName);
            ossClient.shutdown();
            return null;
        }
        OSSObject ossObject = ossClient.getObject(ossParam.getBucket(), objectName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(ossObject.getObjectContent()));
        String result = "";
        while (true) {
            String line = reader.readLine();
            if (line != null) {
                result += line;
            } else {
                break;
            }
        }
        reader.close();
        ossClient.shutdown();
        return result;
    }

}
