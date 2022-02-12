package com.xinput.thinking.in.spring.ioc.dependency.injection;

import com.xinput.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Lazy;

/**
 * 注解驱动的依赖注入处理过程
 *
 * @author yuan.lai
 * @since 1.0
 */
@Configurable
public class AnnotationDependencyResolutionDemo01 {

  @Autowired
  @Lazy
  private User lazyUser;

  /**
   * 依赖查找(处理)
   * DependencyDescriptor ->
   * 必须(required=true)
   * 实时注入(eager=true)
   * 通过类型(User.class)
   * 字段名称(fieldName=user)
   * 是否首要(primary=true)
   */
  @Autowired
  private User user;

  /**
   * 集合类型依赖注入
   * DependencyDescriptor ->
   * 必须(required=true)
   * 实时注入(eager=true)
   * 字段名称(fieldName=userMap)
   * 是否首要(primary=true)
   */
//  @Autowired
//  private Map<String, User> userMap;

//  @Autowired
//  private Optional<User> userOptional;
  public static void main(String[] args) {
    // 创建 BeanFactory 容器
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
    // 注册 Configuration Class (配置类) -> Spring Bean
    applicationContext.register(AnnotationDependencyResolutionDemo01.class);

    XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
    String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
    // 加载 XML 资源, 解析并且生成 BeanDefinition
    beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

    // 启动应用上下文
    applicationContext.refresh();

    // 依赖查找
    AnnotationDependencyResolutionDemo01 demo
        = applicationContext.getBean(AnnotationDependencyResolutionDemo01.class);

    // 期待输入 superUser
    System.out.println(demo.user);

    // 期待输入 user, superUser
//    System.out.println(demo.userMap);

    // 关闭应用上下文
    applicationContext.close();
  }
}
