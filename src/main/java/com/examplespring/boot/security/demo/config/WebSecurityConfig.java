package com.examplespring.boot.security.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//   @Bean
//    public UserDetailsService userDetailsService(){
//       //   基于内存的认证
//       InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//       manager.createUser(User.withUsername("zhangsan").password("123").authorities("ADMIN").build());
//       manager.createUser(User.withUsername("lizi").password("456").authorities("USER").build());
//       return manager;
//   }

    //   @Bean
//    public PasswordEncoder passwordEncoder(){
//       //   无加密
//       return NoOpPasswordEncoder.getInstance();
//   }
    @Bean
    public PasswordEncoder passwordEncoder() {
        //   BCryp加密
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()     // 对请求进行验证
                .antMatchers("/role/list","/role/add")
                .permitAll()                //  开放授权
                .antMatchers("/admin/**").hasAuthority("ADMIN")    // 必须有ADMIN权限
//                .antMatchers("/user/**").hasAnyAuthority("USER", "ADMIN")       //有任意一种权限
                .anyRequest()     //任意请求（这里主要指方法）
                .authenticated()   //// 需要身份认证
                .and()   //表示一个配置的结束
                .formLogin().permitAll()  //开启SpringSecurity内置的表单登录，会提供一个/login接口
                .and()
                .logout().permitAll()  //开启SpringSecurity内置的退出登录，会为我们提供一个/logout接口
                .and()
                .csrf().disable();    //关闭csrf跨站伪造请求
        //  自定义登录
//        http.formLogin().loginPage("/login-view")
//                .loginProcessingUrl("/login")
//                .successForwardUrl("/login-success")
//                .permitAll();
        //  自定义退出
//        http.logout().logoutUrl("/logout").logoutSuccessUrl("/login-view?logout=true");
        //  配置session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
    }
}
