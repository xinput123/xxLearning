package com.xinput.thinking.in.spring.bean.definition;

import com.xinput.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * bean 别名示例
 */
public class BeanAliasDemo {
  public static void main(String[] args) {
    // 配置 XML 配置文件
    // 启动 Spring 上下文
    BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-definitions-context.xml");

    // 通过别名 xinput-user 获得 user 的bean
    User xinput = beanFactory.getBean("xinput-user", User.class);
    User user = beanFactory.getBean("user", User.class);
    System.out.println("xinput 是否与 user 相同 : " + (xinput == user));
  }
}
