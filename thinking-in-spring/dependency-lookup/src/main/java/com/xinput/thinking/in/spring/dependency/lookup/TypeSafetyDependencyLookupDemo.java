package com.xinput.thinking.in.spring.dependency.lookup;

import com.xinput.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 类型安全 依赖查找示例
 */
public class TypeSafetyDependencyLookupDemo {
  public static void main(String[] args) {
    // 创建 BeanFactory 容器
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
    // 将当前类作为配置类
    applicationContext.register(TypeSafetyDependencyLookupDemo.class);
    // 启动应用上下文
    applicationContext.refresh();
    // 演示 BeanFactory#getBean 方法的安全性
    displayBeanFactoryGetBean(applicationContext);
    // 演示 ObjectFactory#getBean 方法的安全性
    displayObjectFactoryGetBean(applicationContext);
    // 演示 ObjectProvider#getIfAvailable 方法的安全性
    displayObjectProviderIfAvailable(applicationContext);

    // 演示 ListableBeanFactory#getBeanOfType 方法的安全性
    displayListableBeanFactoryGetBeansOfType(applicationContext);
    // 演示 ObjectProvider Stream 操作的安全性
    displayObjectProviderStreamOps(applicationContext);
    // 关闭上下文
    applicationContext.close();
  }

  private static void displayObjectProviderStreamOps(AnnotationConfigApplicationContext applicationContext) {
    // ObjectProvider is ObjectFactory
    ObjectProvider<User> userObjectProvider = applicationContext.getBeanProvider(User.class);
    printBeanException("displayObjectProviderStreamOps", () -> userObjectProvider.forEach(System.out::println));
  }

  private static void displayListableBeanFactoryGetBeansOfType(ListableBeanFactory listableBeanFactory) {
    printBeanException("displayListableBeanFactoryGetBeansOfType", () -> listableBeanFactory.getBeansOfType(User.class));
  }

  private static void displayObjectProviderIfAvailable(AnnotationConfigApplicationContext applicationContext) {
    ObjectProvider<User> objectProvider = applicationContext.getBeanProvider(User.class);
    printBeanException("displayObjectProviderIfAvailable", () -> objectProvider.getIfAvailable());
  }

  private static void displayObjectFactoryGetBean(AnnotationConfigApplicationContext applicationContext) {
    // ObjectProvider is ObjectFactory
    ObjectFactory<User> userObjectProvider = applicationContext.getBeanProvider(User.class);
    printBeanException("displayObjectFactoryGetBean", () -> userObjectProvider.getObject());
  }

  public static void displayBeanFactoryGetBean(BeanFactory beanFactory) {
    printBeanException("displayBeanFactoryGetBean", () -> beanFactory.getBean(User.class));
  }

  private static void printBeanException(String source, Runnable runnable) {
    System.err.println("==============");
    System.err.println("Source from: " + source);
    try {
      runnable.run();
    } catch (BeansException exception) {
      exception.printStackTrace();
    }
  }
}
