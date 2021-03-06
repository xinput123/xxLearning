package com.xinput.thinking.in.spring.ioc.dependency.injection;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 基于 {@link org.springframework.beans.factory.Aware} 接口回调的注入示例
 */
public class AwareInterfaceDependencyInjectionDemo implements BeanFactoryAware, ApplicationContextAware {

  // 不建议使用静态的，这里只是为了测试，简化的
  private static BeanFactory beanFactory;

  private static ApplicationContext applicationContext;

  public static void main(String[] args) {
    // 创建 BeanFactory 容器
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    // 注册 Configuration Class (配置类) --> Spring Bean
    context.register(AwareInterfaceDependencyInjectionDemo.class);

    // 启动 Spring 应用上下文
    context.refresh();

    System.out.println(beanFactory == context.getBeanFactory());
    System.out.println(applicationContext == context);

    // 关闭 Spring 应用上下文
    context.close();
  }

  @Override
  public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
    AwareInterfaceDependencyInjectionDemo.beanFactory = beanFactory;
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    AwareInterfaceDependencyInjectionDemo.applicationContext = applicationContext;
  }
}
