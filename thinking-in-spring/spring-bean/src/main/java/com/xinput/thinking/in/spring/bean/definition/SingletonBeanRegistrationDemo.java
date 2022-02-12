package com.xinput.thinking.in.spring.bean.definition;

import com.xinput.thinking.in.spring.bean.factory.DefaultUserFactory;
import com.xinput.thinking.in.spring.bean.factory.UserFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 单体 Bean 注册实例 示例
 */
public class SingletonBeanRegistrationDemo {
  public static void main(String[] args) {
    demo1();
    demo2();
  }

  public static void demo2() {
    // 创建 BeanFactory 容器
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
    // 创建一个外部 UserFactory 对象
    UserFactory userFactory = new DefaultUserFactory();
    SingletonBeanRegistry singletonBeanRegistry = applicationContext.getBeanFactory();
    // 注册外部单体对象
    singletonBeanRegistry.registerSingleton("userFactory", userFactory);

    // 启动 Spring 应用上下文
    applicationContext.refresh();

    UserFactory userFactoryByLookup = applicationContext.getBean("userFactory", UserFactory.class);
    System.out.println("userFactory == userFactoryByLookup : " + (userFactoryByLookup == userFactory));

    // 关闭 Spring 应用上下文
    applicationContext.close();
  }

  public static void demo1() {
    // 创建 BeanFactory 容器
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
    // 创建一个外部 UserFactory 对象
    UserFactory userFactory = new DefaultUserFactory();
    ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
    // 注册外部单体对象
    beanFactory.registerSingleton("userFactory", userFactory);

    // 启动 Spring 应用上下文
    applicationContext.refresh();

    UserFactory userFactoryByLookup = beanFactory.getBean("userFactory", UserFactory.class);
    System.out.println("userFactory == userFactoryByLookup : " + (userFactoryByLookup == userFactory));

    // 关闭 Spring 应用上下文
    applicationContext.close();
  }
}
