package com.xinput.thinking.in.spring.bean.lifecyle;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;

/**
 * 注解 BeanDefinition 解析示例
 *
 * @author yuan.lai
 * @since
 */
public class AnnotatedBeanDefinitionParseDemo {
  public static void main(String[] args) {
    // 创建 BeanFactory
    DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
    // 基于 Java 注解的 AnnotatedBeanDefinitionReader 的实现
    AnnotatedBeanDefinitionReader beanDefinitionReader = new AnnotatedBeanDefinitionReader(beanFactory);
    int beforeBeanDefinitionCount = beanFactory.getBeanDefinitionCount();
    // AnnotatedBeanDefinitionReader 可以把任意一个类注册进去，不一定非要 @Component 注解的类
    // 注册当前类
    beanDefinitionReader.register(AnnotatedBeanDefinitionParseDemo.class);
    int afterBeanDefinitionCount = beanFactory.getBeanDefinitionCount();
    System.out.println("已定义加载的数量: " + (afterBeanDefinitionCount - beforeBeanDefinitionCount));

    // 普通 class 作为 Component 注册到 Spring IOC 容器，通常 bean 名称为类名的首字母小写
    // Bean 名称生成来自 BeanNameGenerator， 注解实现来源于 AnnotationBeanNameGenerator
    AnnotatedBeanDefinitionParseDemo demo =
        beanFactory.getBean("annotatedBeanDefinitionParseDemo", AnnotatedBeanDefinitionParseDemo.class);
    System.out.println(demo);
  }
}
