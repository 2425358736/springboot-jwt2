package com.telecom.shop.config.error;

import com.telecom.shop.util.result.AppResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartException;

import java.io.IOException;
import java.util.List;

/**
 * 开发公司：XX公司
 * 版权：XX公司
 * <p>
 * ExceptionHandler
 *
 * @author 刘志强
 * @created Create Time: 2019/1/19
 */
@RestController
@RestControllerAdvice
public class AdviceController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 方法参数无效异常处理异常处理
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @CrossOrigin
    public AppResult handlerUserNotExistException(MethodArgumentNotValidException ex) {
        logger.error(ex.getMessage());
        AppResult appResult = new AppResult<String>();
        appResult.setStatus(20011);
        StringBuilder eorrs = new StringBuilder("|");
        List<FieldError> eorrList = ex.getBindingResult().getFieldErrors();
        for (FieldError fieldError : eorrList) {
            eorrs.append(fieldError.getDefaultMessage()).append("|");
        }
        appResult.setSubMsg(ex.getMessage());
        appResult.setMsg(eorrs.toString());
        return appResult;
    }

    @ExceptionHandler(BindException.class)
    @CrossOrigin
    public AppResult bindException(BindException ex) {
        logger.error(ex.getMessage());
        AppResult appResult = new AppResult<String>();
        appResult.setStatus(20011);
        StringBuilder eorrs = new StringBuilder("|");
        List<FieldError> eorrList = ex.getBindingResult().getFieldErrors();
        for (FieldError fieldError : eorrList) {
            eorrs.append(fieldError.getDefaultMessage()).append("|");
        }
        appResult.setSubMsg(ex.getMessage());
        appResult.setMsg(eorrs.toString());
        return appResult;
    }

    /**
     * 文件流异常
     * @param ex
     * @return
     */
    @ExceptionHandler(MultipartException.class)
    @CrossOrigin
    public AppResult multipartException(MultipartException ex) {
        logger.error(ex.getMessage());
        AppResult appResult = new AppResult<String>();
        appResult.setStatus(20015);
        appResult.setMsg("文件处理异常，详细信息请查看subMsg");
        appResult.setSubMsg(ex.getMessage());
        return appResult;
    }

    @ExceptionHandler(NullPointerException.class)
    @CrossOrigin
    public AppResult nullPointerException(NullPointerException ex) {
        logger.error(ex.getMessage());
        AppResult appResult = new AppResult<String>();
        appResult.setStatus(20016);
        appResult.setMsg("空指针异常");
        appResult.setSubMsg(ex.getMessage());
        logger.error("空指针异常",ex);
        return appResult;
    }

    @ExceptionHandler(value = {IOException.class, RuntimeException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    private AppResult systemException(Exception ex) {
        logger.error(ex.getMessage());
        AppResult appResult = new AppResult<String>();
        appResult.setStatus(20016);
        appResult.setMsg(ex.getMessage());
        appResult.setSubMsg(ex.getLocalizedMessage());
        logger.error("500异常",ex);
        return appResult;
    }
}