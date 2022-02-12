package com.xinput.thinking.in.spring.ioc.overview.depency.lookup;

import com.xinput.thinking.in.spring.ioc.overview.annotation.Super;
import com.xinput.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * 依赖查找示例
 * 1、通过名称来查找
 * 2、通过类型来查找
 */
public class DependencyLookupDemo {

  public static void main(String[] args) {
    // 配置xml配置文件
    // 启动spring上下文
//    BeanFactory beanFactory = new ClassPathXmlApplicationContext("META-INF/dependency-lookup-context.xml");
    BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-lookup-context.xml");

    // 实时依赖查找
    lookupInRealTome(beanFactory);
    // 延迟依赖查找
    lookupInLazy(beanFactory);

    // 按照类型查找
    lookupByType(beanFactory);
    // 按照类型查找 Collection
    lookupByCollectionType(beanFactory);

    // 通过注解查找对象
    lookupByAnnotation(beanFactory);
  }

  private static void lookupByAnnotation(BeanFactory beanFactory) {
    if (beanFactory instanceof ListableBeanFactory) {
      ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
      Map<String, User> users = (Map) listableBeanFactory.getBeansWithAnnotation(Super.class);
      System.out.println("查找标注 @Super 的 User 集合对象: " + users);
    }
  }

  private static void lookupByCollectionType(BeanFactory beanFactory) {
    if (beanFactory instanceof ListableBeanFactory) {
      ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
      Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
      System.out.println("查找到所有的 User 集合对象: " + users);
    }
  }

  private static void lookupByType(BeanFactory beanFactory) {
    User user = beanFactory.getBean(User.class);
    System.out.println("实时查找-按照类型查找: " + user.toString());
  }

  private static void lookupInRealTome(BeanFactory beanFactory) {
    User user = (User) beanFactory.getBean("user");
    System.out.println("实时查找: " + user.toString());
  }

  private static void lookupInLazy(BeanFactory beanFactory) {
    ObjectFactory<User> objectFactory = (ObjectFactory<User>) beanFactory.getBean("objectFactory");
    User user = objectFactory.getObject();
    System.out.println("延迟查找: " + user.toString());
  }
}
