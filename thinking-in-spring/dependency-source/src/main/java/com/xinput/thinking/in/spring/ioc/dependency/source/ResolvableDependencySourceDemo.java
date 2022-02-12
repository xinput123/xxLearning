package com.xinput.thinking.in.spring.ioc.dependency.source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

/**
 * ResolvableDependency 作为依赖来源
 *
 * @author yuan.lai
 * @since
 */
public class ResolvableDependencySourceDemo {

  @Autowired
  private String value;

  @PostConstruct
  public void init() {
    System.out.println(value);
  }

  public static void main(String[] args) {
    // 创建 BeanFactory 容器
    AnnotationConfigApplicationContext applicationContext
        = new AnnotationConfigApplicationContext();
    // 注册 Configuration Class(配置类) -> Spring Bean
    applicationContext.register(ResolvableDependencySourceDemo.class);

    applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
      beanFactory.registerResolvableDependency(String.class, "hi, ResolvableDependency demo");
    });

    // 启动 Spring 应用上下文
    applicationContext.refresh();

    // 关闭 Spring 应用上下文
    applicationContext.close();
  }
}
