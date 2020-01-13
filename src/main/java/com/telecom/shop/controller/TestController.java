package com.telecom.shop.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@CrossOrigin
public class TestController {

    @GetMapping(value = "securityList", produces = { "application/json;charset=UTF-8" })
    @PreAuthorize("hasAnyAuthority('security:list')")
    public String securityList(){
        return "你好";
    }

    @GetMapping("securityList2")
    @PreAuthorize("hasAnyAuthority('security:list222')")
    public String securityList2(){
        return "测试无权限";
    }

    @GetMapping("securityList3")
    public String securityList3(){
        return "你好2";
    }

}
