package com.xinput.thinking.in.spring.bean.definition;

import com.xinput.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * {@link BeanDefinition} 构建示例
 */
public class BeanDefinitionCreationDemo {

  public static void main(String[] args) {
    // 1、通过 BeanDefinitaionBuilder 构建
    BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
    // 通过属性设置
//    beanDefinitionBuilder.addPropertyValue("id", 1);
//    beanDefinitionBuilder.addPropertyValue("name", "xinput");
    beanDefinitionBuilder
        .addPropertyValue("id", 1)
        .addPropertyValue("name", "xinput");
    // 获取 BeanDefinition 对象
    BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
    // BeanDefinition 并非 Bean 终态，可以自定义修改

    // 2、通过 AbstractBeanDefinition 以及 派生类
    GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
    // 设置 Bean 类型
    genericBeanDefinition.setBeanClass(User.class);
    // 通过 MutablePropertyValues 批量操作属性
    MutablePropertyValues mutablePropertyValues = new MutablePropertyValues();
//    mutablePropertyValues.addPropertyValue("id", 1);
//    mutablePropertyValues.addPropertyValue("name", "xinput");
    mutablePropertyValues
        .add("id", 1)
        .add("name", "xinput");
    genericBeanDefinition.setPropertyValues(mutablePropertyValues);
  }
}
