package com.hunting.edison.admin.security;
import org.springframework.security.core.GrantedAuthority;

/**
 * granted authority 权限授予封装
 * @author Louis & Edison
 * @date Nov 20, 2018
 */
public class GrantedAuthorityImpl implements GrantedAuthority {
	
	private static final long serialVersionUID = 1L;

	private String authority;

    public GrantedAuthorityImpl(String authority) {
        this.authority = authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }
}