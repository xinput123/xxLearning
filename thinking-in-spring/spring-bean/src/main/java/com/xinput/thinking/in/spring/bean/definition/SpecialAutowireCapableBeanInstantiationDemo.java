package com.xinput.thinking.in.spring.bean.definition;

import com.xinput.thinking.in.spring.bean.factory.DefaultUserFactory;
import com.xinput.thinking.in.spring.bean.factory.UserFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bean 实例化示例
 */
public class SpecialAutowireCapableBeanInstantiationDemo {
  public static void main(String[] args) {
    // 配置 XML 配置文件
    // 启动 Spring 上下文
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/special-bean-instantiation-context.xml");
    // 通过 ApplicationContext 获取 AutowireCapableBeanFactory
    AutowireCapableBeanFactory beanFactory = applicationContext.getAutowireCapableBeanFactory();

    // 创建 UserFactory 对象, 通过 AutowireCapableBeanFactory
    UserFactory userFactory = beanFactory.createBean(DefaultUserFactory.class);
    System.out.println(userFactory.createUser());
  }
}
