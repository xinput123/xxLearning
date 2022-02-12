package com.xinput.thinking.in.spring.bean.definition;

import com.xinput.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bean 实例化示例
 */
public class BeanInstantiationDemo {
  public static void main(String[] args) {
    // 配置 XML 配置文件
    // 启动 Spring 上下文
    BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-instantiation-context.xml");
    User user = beanFactory.getBean("user-by-static-method", User.class);
    System.out.println(user.toString());

    User userByInstanceMethod = beanFactory.getBean("user-by-instance-method", User.class);
    System.out.println(userByInstanceMethod.toString());
    System.out.println(userByInstanceMethod == user);

    User userByFactoryBean = beanFactory.getBean("user-factory-bean", User.class);
    System.out.println(userByFactoryBean.toString());
    System.out.println(userByFactoryBean == user);
  }
}
