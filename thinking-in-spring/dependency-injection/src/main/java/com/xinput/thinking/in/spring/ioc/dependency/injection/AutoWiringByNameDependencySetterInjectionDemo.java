package com.xinput.thinking.in.spring.ioc.dependency.injection;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * "ByName" AutoWiring 依赖 Setter 方法注入示例
 *
 * @author yuan.lai
 * @since 1.0
 */
public class AutoWiringByNameDependencySetterInjectionDemo {
  public static void main(String[] args) {
    DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
    XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
    String xmlResourcePath = "classpath:/MATE-INF/autowiring-dependency-setter-injection.xml";
    // 加载 XML 资源, 解析并且生成 BeanDefinition
    beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

    // 依赖查找并且创建 Bean
    UserHolder userHolder = beanFactory.getBean(UserHolder.class);
    System.out.println(userHolder.toString());
  }
}
