package com.telecom.shop.util.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author admin
 */
@ApiModel(
        value = "CommonResult",
        description = "返回对象"
)
@Data
public class AppResult<T> implements Serializable {
    @ApiModelProperty(
            value = "状态码",
            name = "status"
    )
    private Integer status;

    @ApiModelProperty(
            value = "详细信息",
            name = "subMsg"
    )
    private String subMsg;

    @ApiModelProperty(
            value = "提示信息",
            name = "msg"
    )
    private String msg;

    @ApiModelProperty(
            value = "数据信息",
            name = "data"
    )
    private T data;

    @ApiModelProperty(
            value = "版本号",
            name = "version"
    )
    private String version;


    public AppResult() {
    }

    public static <T> AppResult<T> successReturnDate(T data) {
        AppResult<T> result = new AppResult();
        result.setData(data);
        result.setMsg("成功");
        result.setStatus(200);
        result.setSubMsg("");
        result.setVersion("1.0");
        return result;
    }

    public static <T> AppResult<T> successReturnDate(T data, String msg) {
        AppResult<T> result = new AppResult();
        result.setData(data);
        result.setMsg(msg);
        result.setStatus(200);
        result.setSubMsg("");
        result.setVersion("1.0");
        return result;
    }

    public static <T> AppResult<T> successReturn(T data, String msg, String version) {
        AppResult<T> result = new AppResult();
        result.setData(data);
        result.setMsg(msg);
        result.setStatus(200);
        result.setSubMsg("");
        result.setVersion(version);
        return result;
    }

    public static <T> AppResult<T> successReturn(String msg) {
        AppResult<T> result = new AppResult();
        result.setData(null);
        result.setMsg(msg);
        result.setStatus(200);
        result.setSubMsg("");
        result.setVersion("1.0");
        return result;
    }

    public static <T> AppResult<T> errorReturn(Integer status, String msg, String subMsg) {
        AppResult<T> result = new AppResult();
        result.setData(null);
        result.setMsg(msg);
        result.setStatus(status);
        result.setSubMsg(subMsg);
        result.setVersion("1.0");
        return result;
    }

    public static <T> AppResult<T> errorReturn(Integer status, String msg, String subMsg, String version) {
        AppResult<T> result = new AppResult();
        result.setData(null);
        result.setMsg(msg);
        result.setStatus(status);
        result.setSubMsg(subMsg);
        result.setVersion(version);
        return result;
    }

    public static <T> AppResult<T> errorReturn(Integer status, String msg) {
        AppResult<T> result = new AppResult();
        result.setData(null);
        result.setMsg(msg);
        result.setStatus(status);
        result.setSubMsg("");
        result.setVersion("1.0");
        return result;
    }
}

