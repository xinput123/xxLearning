package com.xinput.thinking.in.spring.ioc.dependency.injection;

import com.xinput.thinking.in.spring.ioc.dependency.injection.annotation.UserGroup;
import com.xinput.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Collection;

/**
 * {@link Qualifier} 注解依赖注入
 * <p>
 * 有 @Qualifier 时，和没有 @Qualifier 会做一个分组
 *
 * @author yuan.lai
 * @since 1.0
 */
public class QualifierAnnotationDependencyInjectionDemo {

  @Autowired
  private User user; // 不指定话，默认是 superUser -> primary=true

  @Autowired
  @Qualifier("user") // 指定 Bean 的名称 或者 id
  private User nameUser;

  // 整体应用上下文有 4 个 User 类型的 Bean
  // superUser
  // user
  // user1 -> @Qualifier
  // user2 -> @Qualifier

  @Autowired
  private Collection<User> allUsers; // 2个 UserBean = user + superUser

  @Autowired
  @Qualifier
  private Collection<User> qualifierUsers; // 2个 UserBean = user1 + user2 -> 4个Bean user1 user2 user3 user4

  @Autowired
  @UserGroup
  private Collection<User> groupUsers; // 2个 UserBean = user3 + user4

  @Bean
  @Qualifier // 进行逻辑分组

  public User user1() {
    return createUser(1001L);
  }

  @Bean
  @Qualifier // 进行逻辑分组
  public User user2() {
    return createUser(1002L);
  }

  @Bean
  @UserGroup
  public User user3() {
    return createUser(1003L);
  }

  @Bean
  @UserGroup
  public User user4() {
    return createUser(1004L);
  }

  private static User createUser(long id) {
    User user = new User();
    user.setId(id);
    return user;
  }

  public static void main(String[] args) {
    // 创建 Bean 容器
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
    // 注册 Configuration Class(配置类) --> Spring Bean
    applicationContext.register(QualifierAnnotationDependencyInjectionDemo.class);

    XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
    String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
    // 加载 XML 资源, 解析并且生成 BeanDefinition
    beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

    // 启动 Spring 应用上下文
    applicationContext.refresh();

    // 依赖查找 QualifierAnnotationDependencyInjectionDemo Bean
    QualifierAnnotationDependencyInjectionDemo demo = applicationContext.getBean(QualifierAnnotationDependencyInjectionDemo.class);

    // 期待输出 superUser Bean
    System.out.println("demo.user : " + demo.user);

    // 期待输出 User Bean
    System.out.println("demo.nameUser : " + demo.nameUser);

    // 期待输出 2 个 Bean : user + superUser
    System.out.println("demo.allUsers : " + demo.allUsers);

    // 期待输出 2 个 Bean： user1 + user2
    System.out.println("demo.qualifierUsers : " + demo.qualifierUsers);

    // 期待输出 2个 UserBean = user3 + user4
    System.out.println("demo.groupUsers : " + demo.groupUsers);

    // 关闭 Spring 应用上下文
    applicationContext.close();
  }
}
