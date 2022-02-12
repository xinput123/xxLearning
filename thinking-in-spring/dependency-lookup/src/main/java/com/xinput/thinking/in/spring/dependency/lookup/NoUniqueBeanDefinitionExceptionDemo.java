package com.xinput.thinking.in.spring.dependency.lookup;

import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @{link NoUniqueBeanDefinitionException} 示例
 */
public class NoUniqueBeanDefinitionExceptionDemo {
  public static void main(String[] args) {
    // 创建 BeanFactory 容器
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
    // 将当前类作为配置类
    applicationContext.register(NoUniqueBeanDefinitionExceptionDemo.class);

    // 启动应用上下文
    applicationContext.refresh();

    try {
      // 由于 Spring 应用上下文存在两个 String 类型的 Bean ，通过单一类型查找会抛出异常
      applicationContext.getBean(String.class);
    } catch (NoUniqueBeanDefinitionException e) {
      System.err.printf("Spring 应用上下文存在 %d 个 %s 类型的 Bean, 具体原因: %s", e.getNumberOfBeansFound(), String.class, e.getMessage());
    }

    // 关闭上下文
    applicationContext.close();
  }

  @Bean
  public String getBean1() {
    return "bean1";
  }

  @Bean
  public String getBean2() {
    return "bean2";
  }
}
