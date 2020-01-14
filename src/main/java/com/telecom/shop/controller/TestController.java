package com.telecom.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
@CrossOrigin
public class TestController {

    @Autowired
    @Qualifier("consumerTokenServices")
    ConsumerTokenServices consumerTokenServices;

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
