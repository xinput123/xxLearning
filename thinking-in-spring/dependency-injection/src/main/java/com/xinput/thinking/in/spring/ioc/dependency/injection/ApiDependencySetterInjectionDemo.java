package com.xinput.thinking.in.spring.ioc.dependency.injection;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 基于 Api 实现依赖 Setter 方法注入示例
 */
public class ApiDependencySetterInjectionDemo {
  public static void main(String[] args) {
    // 创建 BeanFactory 容器
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

    // 生成 UserHolder 的 BeanDefinition
    BeanDefinition userHolderBeanDefinition = createUserHolderBeanDefinition();
    // 注册 UserHolder 的 BeanDefinition
    applicationContext.registerBeanDefinition("userHolder", userHolderBeanDefinition);

    XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

    String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
    // 加载 XML 资源, 解析并且生成 BeanDefinition
    beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

    // 启动 Spring 应用上下文
    applicationContext.refresh();

    // 依赖查找并且创建 Bean
    UserHolder userHolder = applicationContext.getBean(UserHolder.class);
    System.out.println(userHolder.toString());

    // 显示关闭 Spring 应用上下文
    applicationContext.close();
  }

  /**
   * 为 {@link UserHolder} 生成 {@link BeanDefinition}
   */
  public static BeanDefinition createUserHolderBeanDefinition() {
    BeanDefinitionBuilder definitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(UserHolder.class);
    definitionBuilder.addPropertyReference("user", "superUser");
    return definitionBuilder.getBeanDefinition();
  }
}
