package com.xinput.thinking.in.spring.configuration.metadata;

import com.xinput.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * Spring XML 元素扩展 示例
 */
public class ExtensibleXmlAuthoringDemo {
  public static void main(String[] args) {
    // 创建 IoC 底层容器
    DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
    // 创建 XML 资源
    XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
    // 加载 xml 资源
    reader.loadBeanDefinitions("META-INF/user-context.xml");
    // 获取 User Bean 对象
    User user = beanFactory.getBean(User.class);
    System.out.println(user);
  }
}
