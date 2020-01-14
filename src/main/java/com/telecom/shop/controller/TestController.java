package com.telecom.shop.controller;

import com.telecom.shop.config.auth.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
public class TestController {

    @Autowired
    @Qualifier("userDetailsServiceImpl")
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

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

    @PostMapping("/login")
    public String login(String userName,String password){
        UserDetails user =  userDetailsService.loadUserByUsername(userName);
        String token = jwtTokenUtil.generateToken(user);
        return token;
    }

}
