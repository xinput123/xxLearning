package com.xinput.thinking.in.spring.dependency.lookup;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 层次性依赖查找 示例
 */
public class HierarchicalDependencyLookupDemo {
  public static void main(String[] args) {
    // 创建 BeanFactory 容器
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
    // 将当前类作为配置类
    applicationContext.register(ObjectProviderDemo.class);

    // 1、获取 HierarchicalBeanFactory <- ConfigurableBeanFactory <- ConfigurableListableBeanFactory
    ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
    System.out.println("当前 BeanFactory 的 Parent BeanFactory : " + beanFactory.getParentBeanFactory());

    // 2、设置 Parent BeanFactory
    HierarchicalBeanFactory paraneBeanFactory = createParaneBeanFactory();
    beanFactory.setParentBeanFactory(paraneBeanFactory);
    System.out.println("当前 BeanFactory 的 Parent BeanFactory : " + beanFactory.getParentBeanFactory());

    displayContainLocalBean(beanFactory, "user");
    displayContainLocalBean(paraneBeanFactory, "user");

    displayContainBean(beanFactory, "user");
    displayContainBean(paraneBeanFactory, "user");

    // 启动应用上下文
    applicationContext.refresh();

    // 关闭上下文
    applicationContext.close();
  }

  private static void displayContainBean(HierarchicalBeanFactory beanFactory, String beanName) {
    System.out.printf("当前 BeanFactory[%s] 是否包含 Bean[name: %s] : %s\n", beanFactory, beanName, containsBean(beanFactory, beanName));
  }

  private static boolean containsBean(HierarchicalBeanFactory beanFactory, String beanName) {
    BeanFactory parentBeanFactory = beanFactory.getParentBeanFactory();
    if (parentBeanFactory instanceof HierarchicalBeanFactory) {
      HierarchicalBeanFactory parentHierarchicalBeanFactory = HierarchicalBeanFactory.class.cast(parentBeanFactory);
      if (containsBean(parentHierarchicalBeanFactory, beanName)) {
        return true;
      }
    }
    return beanFactory.containsLocalBean(beanName);
  }

  // 展示什么是本地 Bean
  private static void displayContainLocalBean(HierarchicalBeanFactory beanFactory, String beanName) {
    System.out.printf("当前 BeanFactory[%s] 是否包含 Local Bean[name: %s] : %s\n", beanFactory, beanName, beanFactory.containsLocalBean(beanName));
  }

  private static HierarchicalBeanFactory createParaneBeanFactory() {
    DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
    XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
    // XML 配置文件 ClassPath 路径
    String localtion = "classpath:/META-INF/dependency-lookup-context.xml";
    // 加载配置
    reader.loadBeanDefinitions(localtion);
    return beanFactory;
  }
}
