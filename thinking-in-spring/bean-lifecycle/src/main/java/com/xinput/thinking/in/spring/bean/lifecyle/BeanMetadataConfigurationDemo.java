package com.xinput.thinking.in.spring.bean.lifecyle;

import com.xinput.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

import java.util.Map;

/**
 * Bean 元信息配置 示例
 *
 * @author yuan.lai
 * @since
 */
public class BeanMetadataConfigurationDemo {

  public static void main(String[] args) {
//    xmlBeanMetadataConfiguration();
    propertiesBeanMetadataConfiguration();
  }

  // 基于 Properties 方式来进行读取
  public static void propertiesBeanMetadataConfiguration() {
    // 创建 BeanFactory
    DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
    // 实例化基于 Properties 资源 BeanDefinitionReader
    PropertiesBeanDefinitionReader beanDefinitionReader = new PropertiesBeanDefinitionReader(beanFactory);

    String location = "MATE-INF/user.properties";

    // 基于 classpath 加载 properties 资源
    Resource resource = new ClassPathResource(location);
    // 指定字符编码 UTF-8
    EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");

    // 加载配置, 返回 bean 定义加载的数量
    int beanNumbers = beanDefinitionReader.loadBeanDefinitions(encodedResource);
    System.out.println("Bean 定义加载的数量: " + beanNumbers);

    // 通过 Bean Id 和 类型 进行查找
    User user = beanFactory.getBean("user", User.class);
    System.out.println(user);
  }

  // 基于 XML 方式来进行读取
  public static void xmlBeanMetadataConfiguration() {
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
