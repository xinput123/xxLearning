package com.xinput.thinking.in.spring.configuration.metadata;

import com.xinput.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.util.ObjectUtils;

/**
 * Bean 配置元信息示例
 */
public class BeanConfigurationMetadataDemo {

  public static void main(String[] args) {
    // BeanDefinition 的定义(声明)
    BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
    beanDefinitionBuilder.addPropertyValue("name", "xinput");

    // 获取 AbstractBeanDefinition
    AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
    // 附加属性(不影响 Bean 的实例化、属性赋值、初始化)
    beanDefinition.setAttribute("name", "原小来");
    // 当前 BeanDefinition 来自于何方
    beanDefinition.setSource(BeanConfigurationMetadataDemo.class);

    DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

    beanFactory.addBeanPostProcessor(new BeanPostProcessor() {
      @Override
      public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals("user", beanName) && User.class.equals(bean.getClass())) {
          BeanDefinition bd = beanFactory.getBeanDefinition("user");
          if (BeanConfigurationMetadataDemo.class.equals(bd.getSource())) {
            // 属性（存储）上下文
            String name = (String) bd.getAttribute("name"); // 就是 "原小来"
            System.out.println(name); // 就是 "原小来"
            User user = (User) bean;
            System.out.println("当前对象: " + user);
            user.setName(name);
          }
        }
        return bean;
      }
    });

    // 注册 User 的 BeanDefinition
    beanFactory.registerBeanDefinition("user", beanDefinitionBuilder.getBeanDefinition());

    User user = beanFactory.getBean("user", User.class);
    System.out.println(user);
  }
}
