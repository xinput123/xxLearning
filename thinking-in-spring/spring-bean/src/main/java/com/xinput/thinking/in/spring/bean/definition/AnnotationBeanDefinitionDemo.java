package com.xinput.thinking.in.spring.bean.definition;

import com.xinput.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 注解 BeanDefinition 示例
 */
@Import(AnnotationBeanDefinitionDemo.Config.class) // 3、通过 @Import 来进行导入
public class AnnotationBeanDefinitionDemo {
  public static void main(String[] args) {
    // 创建 BeanFactory
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
    // 注册 Configuration Class(配置类)
    applicationContext.register(Config.class);
    // 1、通过 @Bean 方式定义
    // 2、通过 @Component 方式
    // 3、通过 @Import 来进行导入

    // 通过 BeanDefinition 注册 API 实现
    // 1、命名 Bean 注册方式
    registerBeanDefinition(applicationContext, "xiaoxiao", User.class);
    // 2、非命名 Bean 注册方式
    registerBeanDefinition(applicationContext, User.class);

    // 启动应用上下文
    applicationContext.refresh();

    // 按照类型 依赖查找
    System.out.println("Config 类型的所有 Beans: " + applicationContext.getBeansOfType(Config.class));
    System.out.println("User 类型的所有 Beans: " + applicationContext.getBeansOfType(User.class));

    // 显示的关闭 Spring 上下文
    applicationContext.close();
  }

  /**
   * 命名 Bean 注册方式
   */
  public static void registerBeanDefinition(BeanDefinitionRegistry registry, String beanName, Class<?> beanClass) {
    BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(beanClass);
    beanDefinitionBuilder
        .addPropertyValue("id", 1)
        .addPropertyValue("name", "xinut");

    if (StringUtils.hasText(beanName)) {
      // 注册 BeanDefinition
      registry.registerBeanDefinition(beanName, beanDefinitionBuilder.getBeanDefinition());
    } else {
      // 非命名 Bean 注册方法
      BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinitionBuilder.getBeanDefinition(), registry);
    }
  }

  // 2、非命名 Bean 注册方式
  public static void registerBeanDefinition(BeanDefinitionRegistry registry, Class<?> beanClass) {
    registerBeanDefinition(registry, null, beanClass);
  }

  @Component // 2、通过 @Component 方式

  public static class Config {

    // 1、通过 @Bean 方式定义

    /**
     * 通过 Java 注解的方式定义了一个 Bean
     */
    @Bean(name = {"user", "xinput-user"})
    public User user() {
      User user = new User();
      user.setId(1L);
      user.setName("注解方式作为 IoC 容器");

      return user;
    }
  }
}
