package com.xinput.thinking.in.spring.questions;

import com.xinput.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

/**
 * Bean 是否缓存 示例
 *
 * @author yuan.lai
 * @since
 */
public class BeanCachingDemo {
  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

    // 注册 Configuration Class
    context.register(BeanCachingDemo.class);

    // 启动 Spring 应用上下文
    context.refresh();

    BeanCachingDemo beanCachingDemo = context.getBean(BeanCachingDemo.class);
    for (int i = 0; i < 10; i++) {
      // Singleton Scope Bean 存在缓存
      System.out.println(beanCachingDemo == context.getBean(BeanCachingDemo.class));
    }


    // 关闭 Spring 应用上下文
    context.close();
  }

  @Bean
  @Lazy
  public static User user() {
    User user = new User();
    user.setId(1L);
    user.setName("奥雷里亚诺");

    return user;
  }
}
