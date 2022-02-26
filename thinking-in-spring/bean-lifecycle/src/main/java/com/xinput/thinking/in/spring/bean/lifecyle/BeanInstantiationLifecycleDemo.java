package com.xinput.thinking.in.spring.bean.lifecyle;

import com.xinput.thinking.in.spring.ioc.overview.domain.SuperUser;
import com.xinput.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
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
    // XML 配置文件 ClassPath 路径
    String[] location = {"META-INF/dependency-lookup-context.xml", "MATE-INF/bean-constructor-dependency-injection.xml"};
    // 基于 Classpath 加载 XML 文件
    int beanDefinitions = beanDefinitionReader.loadBeanDefinitions(location);
    System.out.println("已加载 Bean 数量: " + beanDefinitions);

    User user = beanFactory.getBean("user", User.class);
    System.out.println(user);
    User superUser = beanFactory.getBean("superUser", User.class);
    System.out.println(superUser);
    UserHodler userHolder = beanFactory.getBean("userHolder", UserHodler.class);
    System.out.println(userHolder);
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

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
      if (ObjectUtils.nullSafeEquals("user", beanName) && User.class.equals(bean.getClass())) {
        User user = (User) bean;
        user.setId(10001L);
        user.setName("xinput...");
        // "user" 对象不允许属性赋值(配置元信息->属性值)
        return false;
      }
      return true;
    }

    /**
     * 如果 postProcessAfterInstantiation 方法返回 false 的时候，postProcessProperties 会被跳过。
     * 这个描述在 postProcessAfterInstantiation 的 doc 文档里。
     * <p>
     * user 是跳过 Bean 属性(填入)
     * superUser 也是跳过 Bean 实例化(Bean属性赋值(填入))
     * userHolder
     */
    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName)
        throws BeansException {
      // 对 userHolder Bean 进行拦截
      if (ObjectUtils.nullSafeEquals("userHolder", beanName) && UserHodler.class.equals(bean.getClass())) {
        // 假设 <property name="num" value="1"/> 配置的话，那么在 PropertyValues 就包含一个 PropertyValue(number=1)
        final MutablePropertyValues propertyValues;
        if (pvs instanceof MutablePropertyValues) {
          propertyValues = (MutablePropertyValues) pvs;
        } else {
          propertyValues = new MutablePropertyValues();
        }

        // 等价于 <property name="num" value="1"/>
        propertyValues.addPropertyValue("num", "1");

        // 原始配置 <property name="description" value="The user holder"/>
        // 如果存在 "description" 属性配置的话
        if (propertyValues.contains("description")) {
          // PropertyValue value 是不可变的
//          PropertyValue propertyValue = propertyValues.getPropertyValue("description");

          propertyValues.removePropertyValue("description");
          propertyValues.addPropertyValue("description", "The user holder description");
        }

        return propertyValues;
      }

      return null;
    }
  }
}
