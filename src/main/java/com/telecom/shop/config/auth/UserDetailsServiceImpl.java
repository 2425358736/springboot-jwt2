package com.telecom.shop.config.auth;


import com.telecom.shop.config.auth.model.Auth;
import com.telecom.shop.config.auth.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author admin
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Override
    public User loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = new User();
        user.setUserName(userName);
        user.setPassword("123456789");
        if(user==null) {
            throw new UsernameNotFoundException("无此用户");
        }
        List<Auth> list = new ArrayList<>();
        list.add(new Auth("security:list"));
        list.add(new Auth("per2"));
        list.add(new Auth("per3"));
        user.setList(list);
        // 以上内容在真实环境中数据来自于数据库
        return user;
    }
}
