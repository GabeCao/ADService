package com.zytx.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.zytx.dao.UserDao;
import com.zytx.entity.User;

public class LoginRealm extends AuthenticatingRealm {

	@Autowired
	private UserDao userDao;
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
		String username = usernamePasswordToken.getUsername();
		if("uk".equals(username)) {
			throw new UnknownAccountException("用户不存在");
		}
		User user = userDao.getUserByusername(username);
		if(user == null) {
			throw new UnknownAccountException("用户不存在");
		}
		Object principal = user;
		Object credentials = user.getPassword();
		String realmName = getName();
		ByteSource salt = ByteSource.Util.bytes(username);
		
		SimpleAuthenticationInfo info = new 
				SimpleAuthenticationInfo(principal, credentials,salt, realmName);
		return info;
	}

	
}
