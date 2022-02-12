package com.xinput.thinking.in.spring.ioc.overview.container;

import com.xinput.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * {@link BeanFactory} 作为 IoC 容器示例
 * 什么时候用 BeanFactory、什么时候用 ApplicationContext
 */
public class BeanFactoryAsIoCContainerDemo {
  public static void main(String[] args) {
    // 创建 BeanFactory
    DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
    XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);

    // XML 配置文件 ClassPath 路径
    String location = "classpath:/META-INF/dependency-lookup-context.xml";
    // 加载配置, 返回 bean 定义加载的数量
    int beanDefinitions = reader.loadBeanDefinitions(location);
    System.out.println("Bean 定义加载的数量: " + beanDefinitions);

    // 依赖查找集合对象
    lookupByCollectionType(beanFactory);
  }

  private static void lookupByCollectionType(BeanFactory beanFactory) {
    if (beanFactory instanceof ListableBeanFactory) {
      ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
      Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
      System.out.println("查找标注 @Super 的 User 集合对象: " + users);
    }
  }
}
