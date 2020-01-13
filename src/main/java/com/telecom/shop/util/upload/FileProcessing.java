package com.telecom.shop.util.upload;


import com.aliyun.oss.OSSClient;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 开发公司：xxx公司
 * 版权：xxx公司
 * <p>
 * FileProcessing
 *
 * @author 刘志强
 * @created Create Time: 2019/2/14
 */
public class FileProcessing {

    private S3Model s3Model;

    private OSSModel ossModel;

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    public FileProcessing(S3Model s3Model, OSSModel ossModel) {
        this.s3Model = s3Model;
        this.ossModel = ossModel;
    }


    public PathModel createFile(MultipartFile file) {
        PathModel pathFile = new PathModel();
        String loadName = file.getOriginalFilename();
        String[] nameArr = loadName.split("\\.");
        String Suffix = nameArr[nameArr.length - 1];
        if (this.s3Model != null && this.s3Model.getAccessKey() != null) {
            try {
                DateFormat format = new SimpleDateFormat(this.s3Model.getDirName());
                String dirName = format.format(new Date());
                DateFormat formatFileName = new SimpleDateFormat(this.s3Model.getFileName());
                String imgName = formatFileName.format(new Date());
                imgName = imgName + "." + Suffix;
                dirName = "/" + (StringUtils.equals("MP4", Suffix.toUpperCase()) ? s3Model.getVideoDirName() : s3Model.getImgDirName()) + "/" + dirName + "/";
                String filename= dirName + imgName;
                s3Model.getS3Client().putObject(PutObjectRequest.builder().bucket(s3Model.getBucketName()).key(filename).build(), RequestBody.fromBytes(file.getBytes()));
                logger.info("===================== 上传成功! =====================");
                String httpUrl = "https://ydfx.s3.cn-northwest-1.amazonaws.com.cn"+filename;
                pathFile.setS3Path(httpUrl);
            } catch (Exception e) {
                logger.info("Exception e:" + e.toString());
            }
        }


        if (this.ossModel != null && this.ossModel.getAccessKeyId() != null) {
            OSSClient ossClient = new OSSClient(this.ossModel.getEndpoint(), this.ossModel.getAccessKeyId(), this.ossModel.getAccessKeySecret());
            boolean exists = ossClient.doesBucketExist(this.ossModel.getBucketName());
            // 判断存储空间是否存在，不存在则创建
            if (!exists) {
                ossClient.createBucket(this.ossModel.getBucketName());
            }

            DateFormat format = new SimpleDateFormat(this.ossModel.getDirName());
            String dirName = format.format(new Date());
            DateFormat formatFileName = new SimpleDateFormat(this.ossModel.getFileName());
            String imgName = formatFileName.format(new Date());
            imgName = imgName + "." + Suffix;
            imgName = UUID.randomUUID().toString().replaceAll("-", "") + imgName;
            dirName = (StringUtils.equals("MP4", Suffix.toUpperCase()) ? ossModel.getVideoDirName() : ossModel.getImgDirName()) + "/" + dirName + "/";

//            boolean found = ossClient.doesObjectExist(this.ossModle.getBucketName(), dirName + imgName);
//
//            if (found) {
//                imgName = UUID.randomUUID().toString().replaceAll("-", "") + imgName;
//            }
            // 文件上传
            try {
                logger.info("开始阿里云上传,当前时间"+ System.currentTimeMillis());
                ossClient.putObject(this.ossModel.getBucketName(), dirName + imgName, file.getInputStream());
                pathFile.setOssPath("https://" + this.ossModel.getBucketName() + ".oss-cn-beijing.aliyuncs.com/" + dirName + imgName);
                logger.info("结束阿里云上传,当前时间"+ System.currentTimeMillis());
            } catch (IOException e) {
                logger.error("阿里云上传失败:" + e.toString());
                e.printStackTrace();
            }
            // 关闭OSSClient。
            ossClient.shutdown();
        }
        return pathFile;
    }
}