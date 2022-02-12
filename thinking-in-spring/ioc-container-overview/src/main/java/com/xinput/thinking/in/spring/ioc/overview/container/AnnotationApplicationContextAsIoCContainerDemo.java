package com.xinput.thinking.in.spring.ioc.overview.container;

import com.xinput.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Map;

/**
 * 注解 {@link ApplicationContext} 作为 IoC 容器示例
 * 什么时候用 BeanFactory、什么时候用 ApplicationContext
 */
public class AnnotationApplicationContextAsIoCContainerDemo {
  public static void main(String[] args) {
    // 创建 BeanFactory
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

    // 将当前类 AnnotationApplicationContextAsIoCContainerDemo 作为p配置类 (Configuration Class)
    applicationContext.register(AnnotationApplicationContextAsIoCContainerDemo.class);

    // 启动应用上下文
    applicationContext.refresh();

    // 依赖查找集合对象
    lookupByCollectionType(applicationContext);

    // 关闭上下文
    applicationContext.close();
  }

  /**
   * 通过 Java 注解的方式定义了一个 Bean
   */
  @Bean
  public User user() {
    User user = new User();
    user.setId(1L);
    user.setName("注解方式作为 IoC 容器");

    return user;
  }

  private static void lookupByCollectionType(BeanFactory beanFactory) {
    if (beanFactory instanceof ListableBeanFactory) {
      ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
      Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
      System.out.println("查找标注 @Super 的 User 集合对象: " + users);
    }
  }
}
