package com.xinput.thinking.in.spring.conversion;

import com.xinput.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.beans.PropertyEditor;

/**
 * Spring 定义 {@link PropertyEditor} 示例
 */
public class SpringCustomizedPropertyEditorDemo {
  public static void main(String[] args) {
    // 创建 BeanFactory 容器
    ConfigurableApplicationContext applicationContext =
        new ClassPathXmlApplicationContext("classpath:/META-INF/property-editors-context.xml");

    // AbstractApplicationContext 在创建的时候， -> 会去查找一个叫 "conversionService" ConversionService Bean
    // -> ConfigurableBeanFactory#setConversionService(ConversionService)
    // -> AbstractAutowireCapableBeanFactory.instantiateBean
    // -> AbstractBeanFactory#getConversionService ->
    // BeanDefinition -> BeanWrapper -> 属性转换(数据来源: PropertyValues)
    // set{rp[ertuValues(PropertyValues) -> TypeConverter#converIfNecessnary
    // TypeConverterDelegate#convertIfNecessnary -> PropertyEditor or ConversionService

    User user = applicationContext.getBean("user", User.class);
    System.out.println(user);

    // 关闭 Spring 应用上下文
    applicationContext.close();
  }
}
