package com.xinput.thinking.in.spring.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;

/**
 * {@link Profile} 示例
 *
 * @see Profile
 * @see Environment#getActiveProfiles()
 */
@Configuration
public class ProfileDemo {
  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    // 注册 Configuration Class
    context.register(ProfileDemo.class);

    // 获取 Environment 对象 (可配置)
    ConfigurableEnvironment environment = context.getEnvironment();
    // 默认 profiles = ["odd"] (兜底 profiles)
    environment.setDefaultProfiles("odd");
    // 增加活动 profiles
    environment.setActiveProfiles("even");

    // 启动 Spring Bean
    context.refresh();

    // 如果这里不指定 profile ，会提示 No bean named 'number' available
    Integer number = context.getBean("number", Integer.class);
    System.out.println(number);

    // 关闭 Spring Bean
    context.close();
  }

  @Bean(name = "number")
  @Profile("odd") // 奇数
  public Integer odd() {
    return 1;
  }

  @Bean(name = "number")
//  @Profile("even") // 偶数
  @Conditional(EvenProfileCondition.class)
  public Integer even() {
    return 2;
  }
}
