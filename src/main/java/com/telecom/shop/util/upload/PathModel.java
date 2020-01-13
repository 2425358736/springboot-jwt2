package com.telecom.shop.util.upload;

import lombok.Data;

/**
 * 开发公司：xxx公司
 * 版权：xxx公司
 * <p>
 * PathModel
 *
 * @author 刘志强
 * @created Create Time: 2019/2/18
 */
@Data
public class PathModel {
    public String s3Path;
    public String ossPath;
    private String mainPath;

    public void setOssPath(String ossPath) {
        this.ossPath = ossPath;
        this.mainPath = ossPath;
    }
}