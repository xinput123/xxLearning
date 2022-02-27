package com.xinput.thinking.in.spring.bean.lifecyle;

import com.xinput.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bean 实例化生命周期
 */
public class BeanInstantiationLifecycleDemo {
  public static void main(String[] args) {
    executeBeanFactory();

    System.out.println("=========================");

    executeApplictionContext();
  }

  private static void executeBeanFactory() {
    // 创建 BeanFactory
    DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
    // 方法一 添加 BeanPostProcessor 实现(实例)
//    beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
    // 方法二: 将 MyInstantiationAwareBeanPostProcessor 作为 Bean 注册

    // 基于 XML 资源 BeanDefinitionReader 实现
    XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

    // XML 配置文件 ClassPath 路径
    String[] location = {"META-INF/dependency-lookup-context.xml", "MATE-INF/bean-constructor-dependency-injection.xml"};
    // 基于 Classpath 加载 XML 文件
    int beanDefinitions = beanDefinitionReader.loadBeanDefinitions(location);
    System.out.println("已加载 Bean 数量: " + beanDefinitions);

    User user = beanFactory.getBean("user", User.class);
    System.out.println(user);
    User superUser = beanFactory.getBean("superUser", User.class);
    System.out.println(superUser);
    UserHolder userHolder = beanFactory.getBean("userHolder", UserHolder.class);
    System.out.println(userHolder);
  }

  public static void executeApplictionContext() {
    ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext();

    // XML 配置文件 ClassPath 路径
    String[] locations = {"META-INF/dependency-lookup-context.xml", "MATE-INF/bean-constructor-dependency-injection.xml"};
    applicationContext.setConfigLocations(locations);
    // 启动应用上下文
    applicationContext.refresh();

    User user = applicationContext.getBean("user", User.class);
    System.out.println(user);
    User superUser = applicationContext.getBean("superUser", User.class);
    System.out.println(superUser);
    UserHolder userHolder = applicationContext.getBean("userHolder", UserHolder.class);
    System.out.println(userHolder);

    // 关闭应用上下文
    applicationContext.close();
  }
}

