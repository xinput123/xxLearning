package com.xinput.thinking.in.spring.bean.lifecyle;

import com.xinput.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;

/**
 * Bean 实例化生命周期
 */
public class BeanInitializationLifecycleDemo {
  public static void main(String[] args) {
    executeBeanFactory();
  }

  private static void executeBeanFactory() {
    // 创建 BeanFactory
    DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
    // 添加 BeanPostProcessor 实现(实例)
    beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
    // 添加 CommonAnnotationBeanPostProcessor 解决 @PostConstruct 回调问题
    beanFactory.addBeanPostProcessor(new CommonAnnotationBeanPostProcessor());

    // 基于 XML 资源 BeanDefinitionReader 实现
    XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

    // XML 配置文件 ClassPath 路径
    // XML 配置文件 ClassPath 路径
    String[] location = {"META-INF/dependency-lookup-context.xml", "MATE-INF/bean-constructor-dependency-injection.xml"};
    // 基于 Classpath 加载 XML 文件
    int beanDefinitions = beanDefinitionReader.loadBeanDefinitions(location);
    System.out.println("已加载 Bean 数量: " + beanDefinitions);

    // 显示的执行 preInstantiateSingletons
    // SmartInitializingSingleton 通常在 Spring ApplicationContext 场景使用
    // preInstantiateSingletons 将已注册的 BeanDefinition 初始化成 Spring Bean
    beanFactory.preInstantiateSingletons();

    User user = beanFactory.getBean("user", User.class);
    System.out.println(user);
    User superUser = beanFactory.getBean("superUser", User.class);
    System.out.println(superUser);
    UserHodler userHolder = beanFactory.getBean("userHolder", UserHodler.class);

    System.out.println(userHolder);
  }
}

