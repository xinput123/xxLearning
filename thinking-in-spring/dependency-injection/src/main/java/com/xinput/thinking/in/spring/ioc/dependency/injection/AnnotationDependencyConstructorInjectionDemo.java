package com.xinput.thinking.in.spring.ioc.dependency.injection;

import com.xinput.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 基于 Java 注解的依赖 构造器 注入示例
 */
public class AnnotationDependencyConstructorInjectionDemo {
  public static void main(String[] args) {
    // 创建 BeanFactory 容器
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
    // 注册 Configuration Class (配置类)
    applicationContext.register(AnnotationDependencyConstructorInjectionDemo.class);

    XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

    String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
    // 加载 XML 资源, 解析并且生成 BeanDefinition
    beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

    // 启动 Spring 应用上下文
    applicationContext.refresh();

    // 依赖查找并且创建 Bean
    UserHolder userHolder = applicationContext.getBean(UserHolder.class);
    System.out.println(userHolder.toString());

    // 显示关闭 Spring 应用上下文
    applicationContext.close();
  }

  @Bean
  public UserHolder userHolder(User user) {
    return new UserHolder(user);
  }

}
