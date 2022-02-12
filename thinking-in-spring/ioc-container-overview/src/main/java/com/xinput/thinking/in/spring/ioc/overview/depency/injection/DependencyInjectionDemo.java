package com.xinput.thinking.in.spring.ioc.overview.depency.injection;

import com.xinput.thinking.in.spring.ioc.overview.repository.UserRepository;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

/**
 * 依赖注入示例
 */
public class DependencyInjectionDemo {

  public static void main(String[] args) {
    // 配置xml配置文件
    // 启动spring上下文
//    BeanFactory beanFactory = new ClassPathXmlApplicationContext(
//        "classpath:/META-INF/dependency-injection-context.xml");
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
        "classpath:/META-INF/dependency-injection-context.xml");

    // 依赖来源一: 自定义 Bean
    UserRepository userRepository = applicationContext.getBean("userRepository", UserRepository.class);
//    System.out.println(userRepository.getUsers());

    // 依赖来源二: 依赖注入(内建依赖)
    System.out.println(userRepository.getBeanFactory());

    ObjectFactory objectFactory = userRepository.getObjectFactory();
    System.out.println(objectFactory.getObject() == applicationContext);

    // 依赖查找(错误)
//    System.out.println(beanFactory.getBean(BeanFactory.class));

    // 依赖来源三: 容器内建 Bean
    Environment environment = applicationContext.getBean(Environment.class);
    System.out.println("获取 Environment 类型的Bean: " + environment);
  }

  // BeanFactory 和 ApplicationContext 谁才是IOC容器
//  private static void whoIsIocContainer(UserRepository userRepository, BeanFactory beanFactory) {
//    // 判断注入的对象和查找的对象是否是同一个
//    // 这个表达式为什么不会成立
//    System.out.println(userRepository.getBeanFactory() == beanFactory);
//
//    // ApplicationContext 就是 BeanFactory，但其实他们是两个不同的对象
//    // Spring官方文档中有一句话:
//    // BeanFactory 这个接口，提供了一些高级配置的一个机制，能够管理这些对象(管理的是对象而不是管理Bean)
//    // ApplicationContext 是 BeanFactory 的一个子接口
//
//  }

  private static void whoIsIocContainer(UserRepository userRepository, ApplicationContext applicationContext) {

    // ConfigurableApplicationContext <- ApplicationContext <- BeanFactory

    // ConfigurableApplicationContext#getBeanFactory

    // 判断注入的对象和查找的对象是否是同一个
    // 这个表达式为什么不会成立
    System.out.println(userRepository.getBeanFactory() == applicationContext);

    // ApplicationContext 就是 BeanFactory，但其实他们是两个不同的对象
  }
}
