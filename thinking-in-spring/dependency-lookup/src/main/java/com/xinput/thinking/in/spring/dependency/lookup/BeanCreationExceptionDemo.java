package com.xinput.thinking.in.spring.dependency.lookup;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

/**
 * {@link BeanCreationException} 示例
 */
public class BeanCreationExceptionDemo {
  public static void main(String[] args) {
    // 创建 BeanFactory 容器
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

    // 注册 BeanDefinition Bean Class 是一个 POJO 普通，不过初始化时会主动抛出异常
    BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(Pojo.class);
    applicationContext.registerBeanDefinition("errBean", beanDefinitionBuilder.getBeanDefinition());

    // 启动 Spring 应用上下文
    applicationContext.refresh();

    // 关闭 Spring 应用上下文
    applicationContext.close();
  }

  static class Pojo implements InitializingBean {

    @PostConstruct
    public void init() throws Exception {
      throw new Exception("init(): for purposes");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
      throw new Exception("afterPropertiesSet(): for purposes");
    }
  }
}
