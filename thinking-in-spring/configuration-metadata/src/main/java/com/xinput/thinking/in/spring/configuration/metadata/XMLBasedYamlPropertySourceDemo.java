package com.xinput.thinking.in.spring.configuration.metadata;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * 基于 XML 资源的 YAML 外部化配置示例
 *
 * @author yuan.lai
 * @since
 */
public class XMLBasedYamlPropertySourceDemo {
  public static void main(String[] args) {
    // 创建 IoC 底层容器
    DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
    // 创建 XML 资源的 BeanDefinitionReader
    XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
    // 加载 XML 资源
    reader.loadBeanDefinitions("classpath:/META-INF/yaml-property-source-context.xml");

    // 获取 Map yaml 对象
    Map<String, Object> yamlMap = beanFactory.getBean("yamlMap", Map.class);
    System.out.println(yamlMap);
  }
}
