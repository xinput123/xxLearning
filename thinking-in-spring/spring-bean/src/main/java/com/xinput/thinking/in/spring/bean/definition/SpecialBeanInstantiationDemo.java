package com.xinput.thinking.in.spring.bean.definition;

import com.xinput.thinking.in.spring.bean.factory.UserFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * Bean 实例化示例
 */
public class SpecialBeanInstantiationDemo {
  public static void main(String[] args) {
    // 配置 XML 配置文件
    // 启动 Spring 上下文
    BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/special-bean-instantiation-context.xml");
    // 使用 ServiceLoad 方式实例化 Bean
    ServiceLoader<UserFactory> serviceLoader = beanFactory.getBean("userFactoryServiceLoader", ServiceLoader.class);
    displayServiceLoader(serviceLoader);

//    serviceLoadDemo();
  }

  public static void serviceLoadDemo() {
    ServiceLoader<UserFactory> serviceLoader = ServiceLoader.load(UserFactory.class, Thread.currentThread().getContextClassLoader());
    displayServiceLoader(serviceLoader);
  }

  private static void displayServiceLoader(ServiceLoader<UserFactory> serviceLoader) {
    Iterator<UserFactory> iterator = serviceLoader.iterator();
    while (iterator.hasNext()) {
      UserFactory userFactory = iterator.next();
      System.out.println(userFactory.createUser());
    }
  }
}
