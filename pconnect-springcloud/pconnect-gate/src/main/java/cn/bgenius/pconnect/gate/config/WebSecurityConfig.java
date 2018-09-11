package cn.bgenius.pconnect.gate.config;

import com.alibaba.fastjson.JSON;
import cn.bgenius.pconnect.common.enumtype.ResultCodeEnum;
import cn.bgenius.pconnect.common.util.HttpHelper;
import cn.bgenius.pconnect.common.util.JsonUtil;
import cn.bgenius.pconnect.gate.filter.CodeUsernamePasswordAuthenticationFilter;
import cn.bgenius.pconnect.gate.filter.CorsFilter;
import cn.bgenius.pconnect.gate.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * Created by 金飞 on 2017/10/16.
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private UserDetailsServiceImpl detailsService;

  @Autowired
  private CorsFilter corsFilter;

  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(detailsService) /*.passwordEncoder(new BCryptPasswordEncoder())*/;
  }

  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  /**
   * 忽略静态文件
   */
  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/js/**", "/img/**", "/css/**", "/images/**", "/assets/**");
    web.ignoring().antMatchers("fav.ico");
    web.ignoring().antMatchers("/pconnect-user/v1/tUser/register");
    web.ignoring().antMatchers("/pconnect-user/v2/api-docs","/pconnect-todo/v2/api-docs");
    web.ignoring().antMatchers("/swagger-ui.html","/swagger-resources/configuration/ui","/swagger-resources","/swagger-resources/configuration/security");
//    web.ignoring().antMatchers("/todo/v1/tTodo/getList/**");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    //解决Refused to display 'http://......' in a frame because it set 'X-Frame-Options' to 'DENY'. "错误
    http.headers().frameOptions().disable();

    http.authorizeRequests().antMatchers("/").permitAll()
            .antMatchers("/**/**/session/**").authenticated()//登录即可获取session信息
            // 其他地址的访问均需验证权限（需要登录，且有指定的权限）
            .anyRequest().access("@permissionService.hasPermission(request,authentication)").and()
            .addFilterBefore(corsFilter,UsernamePasswordAuthenticationFilter.class)
            .addFilterAt(codeUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class).exceptionHandling()
            .authenticationEntryPoint((request, response, authException) -> {
              String result = JSON.toJSONString(JsonUtil.getResultJson(ResultCodeEnum.NOLOGIN));
              HttpHelper.setResponseJsonData(response,result);
            }).and()
            .addFilterBefore(corsFilter,LogoutFilter.class)
            .formLogin().loginProcessingUrl("/login").permitAll().and()
            .logout().logoutSuccessHandler(logoutSuccessHandler()).permitAll();
    http.csrf().disable();
  }


  @Bean
  public CodeUsernamePasswordAuthenticationFilter codeUsernamePasswordAuthenticationFilter() throws Exception {
    CodeUsernamePasswordAuthenticationFilter codeUsernamePasswordAuthenticationFilter = new CodeUsernamePasswordAuthenticationFilter();
    codeUsernamePasswordAuthenticationFilter.setAuthenticationManager(authenticationManagerBean());
    codeUsernamePasswordAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler());
    codeUsernamePasswordAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler());
    return codeUsernamePasswordAuthenticationFilter;
  }

  @Bean
  public AuthenticationSuccessHandler authenticationSuccessHandler() {
    return new CustomAuthenticationSuccessHandler();
  }

  @Bean
  public AuthenticationFailureHandler authenticationFailureHandler() {
    return new CustomAuthenticationFailureHandler();
  }

  @Bean
  public LogoutSuccessHandler logoutSuccessHandler() {
    return new CustomLogoutSuccessHandler();
  }

}
