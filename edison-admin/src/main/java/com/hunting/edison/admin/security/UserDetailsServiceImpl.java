package com.hunting.edison.admin.security;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hunting.edison.admin.domain.Users;
import com.hunting.edison.admin.sevice.UsersService;

/**
 * user login service用户登录认证信息查询
 * @author Louis & Edison
 * @date Nov 20, 2018
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	private static Logger log = LogManager.getLogger(UserDetailsServiceImpl.class);
	
    @Resource
    private UsersService usersService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	Users user = usersService.findByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("该用户不存在");
        }
        // 用户权限列表，根据用户拥有的权限标识与如 @PreAuthorize("hasAuthority('sys:menu:view')") 标注的接口对比，决定是否可以调用接口
        Set<String> permissions = usersService.findPermissions(user.getName());
        //输出调试信息
        log.log(Level.forName("NOTICE", 450),permissions);
        
        List<GrantedAuthority> grantedAuthorities = permissions.stream().map(GrantedAuthorityImpl::new).collect(Collectors.toList());
        return new JwtUserDetails(user.getName(), user.getPassword(), user.getSalt(), grantedAuthorities);
    }
}