package com.xinput.thinking.in.spring.ioc.dependency.source;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;

/**
 * 外部化配置作为依赖赖来源
 */
@Configuration
@PropertySource(value = "META-INF/default.properties", encoding = "UTF-8")
public class ExternalConfigurationDependencySourceDemo {

  @Value("${user.id:-1}")
  private Long id;

  @Value("${usr.name:xinput}")
  private String name;

  @Value("${user.resource:classpath://default.properties}")
  private Resource resource;

  public static void main(String[] args) {
    // 创建 BeanFactory 容器
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
    // 注册 Configuration Class (配置类) -> Spring Bean
    applicationContext.register(ExternalConfigurationDependencySourceDemo.class);

    // 启动 Spring 应用上下文
    applicationContext.refresh();

    ExternalConfigurationDependencySourceDemo demo = applicationContext.getBean(ExternalConfigurationDependencySourceDemo.class);

    System.out.println("demo.id = " + demo.id);
    System.out.println("demo.name = " + demo.name);
    System.out.println("demo.resource = " + demo.resource);

    // 关闭 Spring 应用上下文
    applicationContext.close();
  }
}
