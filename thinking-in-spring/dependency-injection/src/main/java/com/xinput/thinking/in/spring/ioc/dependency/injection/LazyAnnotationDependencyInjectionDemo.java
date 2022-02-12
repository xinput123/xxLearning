package com.xinput.thinking.in.spring.ioc.dependency.injection;

import com.xinput.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Set;

/**
 * {@link ObjectProvider} 实现延迟注入
 * <p>
 * 有 @Qualifier 时，和没有 @Qualifier 会做一个分组
 *
 * @author yuan.lai
 * @since 1.0
 */
public class LazyAnnotationDependencyInjectionDemo {

  @Autowired
  private User user; // 实时注入

  @Autowired
  private ObjectProvider<User> userObjectProvider; // 延迟注入

  @Autowired
  private ObjectFactory<Set<User>> userObjectFactory;

  public static void main(String[] args) {
    // 创建 Bean 容器
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
    // 注册 Configuration Class(配置类) --> Spring Bean
    applicationContext.register(LazyAnnotationDependencyInjectionDemo.class);

    XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
    String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
    // 加载 XML 资源, 解析并且生成 BeanDefinition
    beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

    // 启动 Spring 应用上下文
    applicationContext.refresh();

    // 依赖查找 QualifierAnnotationDependencyInjectionDemo Bean
    LazyAnnotationDependencyInjectionDemo demo = applicationContext.getBean(LazyAnnotationDependencyInjectionDemo.class);

    // 期待输出 superUser Bean
    System.out.println("demo.user : " + demo.user);

    // 期待输出 superUser Bean
    System.out.println("demo.userObjectProvider : " + demo.userObjectProvider.getObject()); // 继承 ObjectFactory

    System.out.println("demo.userObjectFactory : " + demo.userObjectFactory.getObject());


    demo.userObjectProvider.forEach(System.out::println);

    // 关闭 Spring 应用上下文
    applicationContext.close();
  }
}
