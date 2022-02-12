package com.xinput.thinking.in.spring.dependency.lookup;

import org.springframework.beans.BeanInstantiationException;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * {@link BeanInstantiationException} 示例
 */
public class NoUniqueBeanDefinitionExceptionDemo1 {
  public static void main(String[] args) {
    // 创建 BeanFactory 容器
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

    // 演示初始化数据失败
    BeanDefinitionBuilder definitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(CharSequence.class);
    applicationContext.registerBeanDefinition("errorBean", definitionBuilder.getBeanDefinition());
    // 启动应用上下文
    applicationContext.refresh();

    // 关闭上下文
    applicationContext.close();
  }
}
