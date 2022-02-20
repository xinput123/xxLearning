package com.xinput.thinking.in.spring.bean.lifecyle;

import com.xinput.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

/**
 * BeanDefinition 合并示例
 */
public class MergeBeanDefinitionDemo {
  public static void main(String[] args) {
    DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
    // 基于 XML 资源 BeanDefinitionReader 实现
    XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

    // XML 配置文件 ClassPath 路径
    String location = "META-INF/dependency-lookup-context.xml";
    // 基于 Classpath 加载 XML 文件
    ClassPathResource resource = new ClassPathResource(location);
    int beanDefinitions = beanDefinitionReader.loadBeanDefinitions(resource);
    System.out.println("已加载 Bean 数量: " + beanDefinitions);

    User user = beanFactory.getBean("user", User.class);
    System.out.println(user);
    User superUser = beanFactory.getBean("superUser", User.class);
    System.out.println(superUser);
  }
}
