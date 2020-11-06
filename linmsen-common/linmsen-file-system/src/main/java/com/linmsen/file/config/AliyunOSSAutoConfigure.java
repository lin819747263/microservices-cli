package com.linmsen.file.config;


import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.linmsen.file.model.FileInfo;
import com.linmsen.file.properties.FileServerProperties;
import com.linmsen.file.properties.OssProperties;
import com.linmsen.file.service.impl.AbstractFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Configuration
@ConditionalOnProperty(prefix = "linmsen.file.type", havingValue = "aliyun")
@EnableConfigurationProperties(FileServerProperties.class)
public class AliyunOSSAutoConfigure {

    @Autowired
    private FileServerProperties fileProperties;

    @Bean
    public OSSClient getClient(){
        return new OSSClient(fileProperties.getOss().getEndpoint(),
                new DefaultCredentialProvider(fileProperties.getOss().getAccessKey(), fileProperties.getOss().getAccessKeySecret()),
                null);
    }

    @Service
    public class AliyunOssServiceImpl extends AbstractFileService {
        @Autowired
        private OSSClient ossClient;

        @Override
        protected String fileType() {
            return fileProperties.getType();
        }

        @Override
        protected void uploadFile(MultipartFile file, FileInfo fileInfo) throws Exception {
            ossClient.putObject(fileProperties.getOss().getBucketName(), fileInfo.getName(), file.getInputStream());
            fileInfo.setUrl(fileProperties.getOss().getDomain() + "/" + fileInfo.getName());
        }

        @Override
        protected boolean deleteFile(FileInfo fileInfo) {
            ossClient.deleteObject(fileProperties.getOss().getBucketName(), fileInfo.getName());
            return true;
        }
    }
}
