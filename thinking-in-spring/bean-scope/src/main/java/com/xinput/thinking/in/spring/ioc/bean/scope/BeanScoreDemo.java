package com.xinput.thinking.in.spring.ioc.bean.scope;

import com.xinput.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.util.Map;

/**
 * Bean 的作用域实例
 * 利用 DisposableBean 对 prototype Bean 进行销毁
 *
 * @author yuan.lai
 * @since
 */
public class BeanScoreDemo implements DisposableBean {

  @Bean
  // 默认 scope 就是 "singleton"
  public static User singletonUser() {
    return createUser();
  }

  @Bean
  @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
  public static User prototypeUser() {
    return createUser();
  }

  private static User createUser() {
    User user = new User();
    user.setId(System.nanoTime());
    return user;
  }

  @Autowired
  @Qualifier("singletonUser")
  private User singletonUser;

  @Autowired
  @Qualifier("singletonUser")
  private User singletonUser1;

  @Autowired
  @Qualifier("prototypeUser")
  private User prototypeUser;

  @Autowired
  @Qualifier("prototypeUser")
  private User prototypeUser1;

  @Autowired
  @Qualifier("prototypeUser")
  private User prototypeUser2;

  @Autowired
  private Map<String, User> users;

  @Autowired
  private ConfigurableListableBeanFactory beanFactory; // ResolvableDependency

  public static void main(String[] args) {
    // 创建 BeanFactory 容器
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
    // 注册 Configutation class(配置类) -> Spring Bean
    applicationContext.register(BeanScoreDemo.class);

    applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
      beanFactory.addBeanPostProcessor(new BeanPostProcessor() {
        @Override
        public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
          System.out.printf("%s Bean 名称:%s 在初始化后回调...%n", bean.getClass().getName(), beanName);
          return bean;
        }
      });
    });

    // 启动 Spring 应用上下文
    applicationContext.refresh();

    // 结论一:
    // Singleton Bean 无论依赖查找还是依赖注入，均为同一个对象
    // pritotype Bean 无论依赖查找还是依赖注入，都会生成新的对象

    // 结论二:
    // 如果依赖注入集合类型的对象， Singleton Bean 和 Prototype Bean 均会存在，且都只有一个
    // Prototype Bean 有别于其他地方的依赖注入 Prototype Bean

    // 结论三:
    // 无论是 Singleton 还是 Prototype Bean 均会执行初始化方法回调
    // 不过仅 Singleton Bean 会执行销毁方法回调

    scopeBeansByLookup(applicationContext);

    scopeBeansByInjection(applicationContext);

    // 关闭 Spring 应用上下文
    applicationContext.close();
  }

  // 判断 依赖查找 的方式
  private static void scopeBeansByLookup(AnnotationConfigApplicationContext applicationContext) {
    for (int i = 0; i < 3; i++) {
      User singletonUser = applicationContext.getBean("singletonUser", User.class);
      System.out.println(singletonUser);
      User prototypeUser = applicationContext.getBean("prototypeUser", User.class);
      System.out.println(prototypeUser);
    }
  }

  // 判断 依赖注入 的方式
  private static void scopeBeansByInjection(AnnotationConfigApplicationContext applicationContext) {
    BeanScoreDemo beanScoreDemo = applicationContext.getBean(BeanScoreDemo.class);
    System.out.println("beanScoreDemo.singletonUser = " + beanScoreDemo.singletonUser);
    System.out.println("beanScoreDemo.singletonUser1 = " + beanScoreDemo.singletonUser1);
    System.out.println("beanScoreDemo.prototypeUser = " + beanScoreDemo.prototypeUser);
    System.out.println("beanScoreDemo.prototypeUser1 = " + beanScoreDemo.prototypeUser1);
    System.out.println("beanScoreDemo.prototypeUser2 = " + beanScoreDemo.prototypeUser2);
    System.out.println("beanScoreDemo.users = " + beanScoreDemo.users);
  }

  @Override
  public void destroy() throws Exception {

    System.out.println("当前 BeanScoreDemo Bean 正在销毁中...");

    this.prototypeUser.destroy();
    this.prototypeUser1.destroy();
    this.prototypeUser2.destroy();
    // 获取 BeanDefinition
    for (Map.Entry<String, User> entry : this.users.entrySet()) {
      String beanName = entry.getKey();
      BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
      if (beanDefinition.isPrototype()) {
        User user = entry.getValue();
        user.destroy();
      }
    }

    System.out.println("当前 BeanScoreDemo Bean 销毁完成...");
  }
}
