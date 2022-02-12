package com.xinput.thinking.in.spring.bean.definition;

import com.xinput.thinking.in.spring.bean.factory.DefaultUserFactory;
import com.xinput.thinking.in.spring.bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

/**
 * Bean 初始化
 */
public class BeanInitializationDemo {
  public static void main(String[] args) {
    // 创建 BeanFactory 容器
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
    // 注册 Configuration Class (配置类)
    applicationContext.register(BeanInitializationDemo.class);
    // 启动 Spring 应用上下文
    applicationContext.refresh();
    // 非延迟初始化在 Spring 上下文启动完成后，被初始化
    System.out.println("Spring 应用上下文已启动...");
    // 依赖查找 UserFactory
    UserFactory userFactory = applicationContext.getBean(UserFactory.class);
    System.out.println(userFactory);
    System.out.println("Spring 上下文准备关闭...");
    // 关闭 Spring 应用上下文，调用 close 方法，即会销毁各种 bean
    applicationContext.close();
    System.out.println("Spring 上下文已关闭...");
  }

//  @Bean(initMethod = "initUserFactory")
//  public UserFactory userFactory() {
//    return new DefaultUserFactory();
//  }

  @Bean(initMethod = "initUserFactory", destroyMethod = "doDestroy")
  @Lazy
  public UserFactory userFactory() {
    return new DefaultUserFactory();
  }
}
