package com.example.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.example.entity.User;

@Component
public class AuthenticationProviderImpl implements AuthenticationProvider {
	
	private static final Logger logger = LoggerFactory.getLogger(AuthenticationProviderImpl.class);
	
	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		
		// ユーザーとパスワードを取得
		String id = auth.getName();
        String password = auth.getCredentials().toString();
		
        // 未設定の場合はエラー
        if ("".equals(id) || "".equals(password))  {
            // 例外はSpringSecurityにあったものを適当に使用
            throw new AuthenticationCredentialsNotFoundException("ログイン情報に不備があります。");
        }
        
        // 認証情報を取得
        User user = authCheck(id, password);
        if (user == null) {
        	throw new AuthenticationCredentialsNotFoundException("ログイン情報が存在しません。");
        }
		// トークンを返却
		return new UsernamePasswordAuthenticationToken(user, password, auth.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> token) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(token);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private User authCheck(String username, String password) {
		User user = null;
		
		List<Map<String, String>> list = new ArrayList<>();
		Map map;
		map = new HashMap<String, String>(){
			private static final long serialVersionUID = 1L;
			{put("username", "admin");}
			{put("password", "password");}
			{put("role", "ADMIN");}
		};
		list.add(map);
		map = new HashMap<String, String>(){
			private static final long serialVersionUID = 1L;
			{put("username", "user");}
			{put("password", "password");}
			{put("role", "USER");}
		};
		list.add(map);
		map = new HashMap<String, String>(){
			private static final long serialVersionUID = 1L;
			{put("username", "hogehoge");}
			{put("password", "password2");}
			{put("role", "USER");}
		};
		list.add(map);
		
		for(Map map1 : list) {
			if (map1.get("username").equals(username) && map1.get("password").equals(password)){
				user = new User();
				user.setUsername(map1.get("username").toString());
				user.setPassword(map1.get("password").toString());
				user.setRole(map1.get("role").toString());
				break;
			}
		}
		
		return user;
	}

}
