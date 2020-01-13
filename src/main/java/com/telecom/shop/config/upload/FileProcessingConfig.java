package com.telecom.shop.config.upload;

import com.telecom.shop.util.upload.FileProcessing;
import com.telecom.shop.util.upload.OSSModel;
import com.telecom.shop.util.upload.S3Model;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 开发公司：XX公司
 * 版权：XX公司
 * <p>
 * FileProcessingConfig
 *
 * @author 刘志强
 * @created Create Time: 2019/2/16
 */
@Configuration
public class FileProcessingConfig {

    @Bean(name = "s3Model")
    @ConfigurationProperties(value = "file.s3.model")
    public S3Model fileModel() {
        S3Model s3Model = new S3Model();
        return s3Model;
    }

    @Bean(name = "ossModel")
    @ConfigurationProperties(prefix = "file.oss.model")
    public OSSModel ossModel() {
        OSSModel ossModel = new OSSModel();
        return ossModel;
    }

    @Bean(name = "fileProcessing")
    public FileProcessing fileProcessing(S3Model s3Model, OSSModel ossModel) {
        if (s3Model != null && s3Model.getAccessKey() != null) {
            s3Model.setS3Client();
        }
        FileProcessing fileProcessing = new FileProcessing(s3Model,ossModel);
        return  fileProcessing;
    }
}