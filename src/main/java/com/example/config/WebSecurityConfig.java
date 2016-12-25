package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.handler.SampleAuthenticationFailureHandler;
import com.example.service.UserServiceImpl;

@Configuration
@EnableWebMvcSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
    protected void configure(HttpSecurity httpSecurity) throws Exception
    {
		// 認可の設定
        httpSecurity
        	.authorizeRequests()
        	.antMatchers("/login").permitAll()	// /loginは認証なしでアクセス可能
        	.anyRequest()
        	.authenticated();
        
        // ログイン設定
        httpSecurity
        	.formLogin()
        	.loginProcessingUrl("/login")	// 認証処理用
        	.loginPage("/login")	// ログインページ
        	.failureHandler(new SampleAuthenticationFailureHandler())	// 認証失敗時のハンドラクラス
        	.usernameParameter("username")	// ユーザー名のパラメータ
        	.passwordParameter("password")	// パスワードのパラメータ
        	.defaultSuccessUrl("/")	// 認証成功時の遷移先
        	.and();
        
        // ログアウト設定
        httpSecurity
        	.logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout**"))       // ログアウト処理のパス
            .logoutSuccessUrl("/login");	// ログイン完了後のパス 
    }
    
    @Configuration
    protected static class AuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter {
        @Autowired
        UserServiceImpl userDetailsService;

        @Override
        public void init(AuthenticationManagerBuilder auth) throws Exception {
            // 認証するユーザーを設定する
            auth.userDetailsService(userDetailsService);
        }
    }
	
}
