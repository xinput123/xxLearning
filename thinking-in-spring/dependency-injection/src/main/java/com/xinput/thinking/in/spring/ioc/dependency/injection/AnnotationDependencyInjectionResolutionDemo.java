package com.xinput.thinking.in.spring.ioc.dependency.injection;

import com.xinput.thinking.in.spring.ioc.dependency.injection.annotation.InjectUser;
import com.xinput.thinking.in.spring.ioc.dependency.injection.annotation.MyAutowired;
import com.xinput.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.inject.Inject;
import java.util.Map;
import java.util.Optional;

/**
 * 注解驱动的依赖注入处理过程
 */
public class AnnotationDependencyInjectionResolutionDemo {

  @Autowired // 依赖查找处理 + 延时
  @Lazy
  private User lazyUser;

  /**
   * 依赖查找处理 + 实时
   * DependencyDescriptor ->
   * 必须(required = true)
   * 实时注入(eager = true)
   * 通过类型(User.class)
   * 字段名称 ("user")
   * 是否首要 primary = true
   */
  @Autowired
  private User user;

  /**
   * 集合类型的依赖注入
   */
  @Autowired
  private Map<String, User> users; // user superUser

  //  @Autowired
  @MyAutowired
  private Optional<User> optionalUser; // user superUser

  @Inject
  private User injectUser;

  @InjectUser
  private User myInjectUser;

//  @Bean(name = AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME)
//  public static AutowiredAnnotationBeanPostProcessor beanPostProcessor() {
//    AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
//    // 替换原有注解处理，使用新注解 @InjectUser
////    beanPostProcessor.setAutowiredAnnotationType(InjectUser.class);
//    //旧注解 @Autowired + 新注解 @InjectUser
//    Set<Class<? extends Annotation>> autowireAnnotationTypes = new LinkedHashSet(Arrays.asList(Autowired.class, Inject.class, InjectUser.class));
//    beanPostProcessor.setAutowiredAnnotationTypes(autowireAnnotationTypes);
//    return beanPostProcessor;
//  }

  @Bean
  @Order(Ordered.LOWEST_PRECEDENCE - 3)
  public static AutowiredAnnotationBeanPostProcessor beanPostProcessor() {
    AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
    beanPostProcessor.setAutowiredAnnotationType(InjectUser.class);

    return beanPostProcessor;
  }

  public static void main(String[] args) {
    // 创建 Bean 容器
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
    // 注册 Configuration Class(配置类) --> Spring Bean
    applicationContext.register(AnnotationDependencyInjectionResolutionDemo.class);

    XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
    String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
    // 加载 XML 资源, 解析并且生成 BeanDefinition
    beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

    // 启动 Spring 应用上下文
    applicationContext.refresh();

    // 依赖查找 QualifierAnnotationDependencyInjectionDemo Bean
    AnnotationDependencyInjectionResolutionDemo demo = applicationContext.getBean(AnnotationDependencyInjectionResolutionDemo.class);

    // 期待输出 superUser Bean
    System.out.println("demo.user : " + demo.user);

    System.out.println("demo.injectUser : " + demo.injectUser);

    // 期待输出 user superUser Bean
    System.out.println("demo.user : " + demo.users);

    // 期待输出 user superUser Bean
    System.out.println("demo.optionalUser : " + demo.optionalUser);

    // 期待输出 superUser Bean
    System.out.println("demo.myInjectUser : " + demo.myInjectUser);

    // 关闭 Spring 应用上下文
    applicationContext.close();
  }
}
