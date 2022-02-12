package com.xinput.thinking.in.spring.ioc.dependency.source;

import com.xinput.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;

/**
 * 依赖来源示例
 *
 * @author yuan.lai
 * @since 1.0
 */
public class DependencySourceDemo {

  // 注入在 postProcessProperties 方法执行，早于 setter 注入，也早于 @PostConstruct
  @Autowired
  private BeanFactory beanFactory;

  @Autowired
  private ResourceLoader resourceLoader;

  @Autowired
  private ApplicationContext applicationContext;

  @Autowired
  private ApplicationEventPublisher applicationEventPublisher;

  @PostConstruct
  public void init() {
    System.out.println("beanFactory == applicationContext " + (beanFactory == applicationContext));
    System.out.println("beanFactory == applicationContext.getBeanFactory() " + (beanFactory == applicationContext.getAutowireCapableBeanFactory()));
    System.out.println("resourceLoader == applicationContext " + (resourceLoader == applicationContext));
    System.out.println("applicationEventPublisher == applicationContext " + (applicationEventPublisher == applicationContext));
  }

  /**
   * 下面四个对象只能通过，通过依赖查找都查找不到，但是可以通过依赖注入进来
   * 所以这里管这个叫 游离对象
   */
  @PostConstruct
  public void initByLookUp() {
    getBean(BeanFactory.class);
    getBean(ResourceLoader.class);
    getBean(ApplicationContext.class);
    getBean(ApplicationEventPublisher.class);
//    System.out.println(getBean(User.class));
  }

  private <T> T getBean(Class<T> beanType) {
    try {
      return beanFactory.getBean(beanType);
    } catch (NoSuchBeanDefinitionException e) {
      System.out.println("当前类型: " + beanType.getName() + " 无法在 BeanFactory 中查找");
    }

    return null;
  }

  @Bean
  public User user() {
    User user = new User();
    user.setId(10001L);
    user.setName("xinput10001");
    return user;
  }

  public static void main(String[] args) {
    // 创建 BeanFactory 容器
    AnnotationConfigApplicationContext applicationContext
        = new AnnotationConfigApplicationContext();
    // 注册 Configuration Class(配置类) -> Spring Bean
    applicationContext.register(DependencySourceDemo.class);

    // 启动 Spring 应用上下文
    applicationContext.refresh();

    // 依赖查找 DependencySourceDemo Bean
    DependencySourceDemo demo = applicationContext.getBean(DependencySourceDemo.class);

    // 关闭 Spring 应用上下文
    applicationContext.close();
  }
}
