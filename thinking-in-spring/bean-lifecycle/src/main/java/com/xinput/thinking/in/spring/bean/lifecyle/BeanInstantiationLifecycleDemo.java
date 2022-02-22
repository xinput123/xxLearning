package com.xinput.thinking.in.spring.bean.lifecyle;

import com.xinput.thinking.in.spring.ioc.overview.domain.SuperUser;
import com.xinput.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ObjectUtils;

/**
 * Bean 实例化生命周期
 */
public class BeanInstantiationLifecycleDemo {
  public static void main(String[] args) {
    // 创建 BeanFactory
    DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
    // 添加 BeanPostProcessor 实现(实例)
    beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());

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

  static class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
      if (ObjectUtils.nullSafeEquals("superUser", beanName) && SuperUser.class.equals(beanClass)) {
        // 把配置完成好的 superUser bean 覆盖掉
        return new SuperUser();
      }
      return null;
    }
  }
}
