package com.telecom.shop.util.upload;

import lombok.Data;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

/**
 * 开发公司：xxx公司
 * 版权：xxx公司
 * <p>
 * OSSModle
 *
 * @author 刘志强
 * @created Create Time: 2019/2/18
 */
@Data
public class S3Model {
    /**
     * 桶名
     */
    private String bucketName;
    /**
     * accessKey
     */
    private String accessKey;
    /**
     * secretKey
     */
    private String secretKey;
    /**
     * region
     */
    private String region;

    /**
     * 文件名策略 日期格式，
     */
    private String fileName;

    /**
     * 文件策略
     */
    private String dirName;

    /**
     * 图片文件夹策略 日期格式，
     */
    private String imgDirName;


    /**
     * 视频文件夹策略 日期格式，
     */
    private String videoDirName;

    private S3Client s3Client;


    public void setS3Client() {
        AwsBasicCredentials awsBasicCredentials = AwsBasicCredentials.create(
                this.getAccessKey(),
                this.getSecretKey());
        this.s3Client = S3Client.builder().region(Region.of(this.getRegion())).credentialsProvider(StaticCredentialsProvider.create(awsBasicCredentials)).build();
    }

}