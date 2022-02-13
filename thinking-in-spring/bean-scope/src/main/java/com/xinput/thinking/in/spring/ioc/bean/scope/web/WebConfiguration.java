package com.xinput.thinking.in.spring.ioc.bean.scope.web;

import com.xinput.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Web MVC 配置类
 *
 * @author yuan.lai
 * @since
 */
@Configuration
@EnableWebMvc
public class WebConfiguration {

  @Bean
//  @RequestScope
//  @SessionScope
  @ApplicationScope
  public User user() {
    User user = new User();
    user.setId(101L);
    user.setName("北京您好，我是101");
    return user;
  }
}
