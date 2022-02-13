package com.xinput.thinking.in.spring.ioc.bean.scope;

import com.xinput.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

/**
 * 自定义 Scope {@link ThreadLocalScope} 示例
 */
public class ThreadLocalScopeDemo {

  @Bean
  @Scope(value = ThreadLocalScope.SCORE_NAME)
  public User user() {
    return createUser();
  }

  private static User createUser() {
    User user = new User();
    user.setId(System.nanoTime());
    return user;
  }

  public static void main(String[] args) {
    // 创建 BeanFactory
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
    // 注册 Configuration Class (配置类) -> Spring Bean
    applicationContext.register(ThreadLocalScopeDemo.class);

    applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
      // 注册自定义 scope
      beanFactory.registerScope(ThreadLocalScope.SCORE_NAME, new ThreadLocalScope());
    });

    // 启动 Spring 应用上下文
    applicationContext.refresh();

    scopeBeansByLookup(applicationContext);

    // 关闭 Spring 应用上下文
    applicationContext.close();
  }

  // 判断 依赖查找 的方式
  private static void scopeBeansByLookup(AnnotationConfigApplicationContext applicationContext) {
    // 单线程
    for (int i = 0; i < 3; i++) {
      // user 是共享 Bean 对象
      User user = applicationContext.getBean("user", User.class);
      System.out.println(user);
    }

    // 多线程
    for (int i = 0; i < 3; i++) {
      Thread thread = new Thread(() -> {
        // user 是共享 Bean 对象
        User user = applicationContext.getBean("user", User.class);
        System.out.printf("Thread id :%d] user = %s%n", Thread.currentThread().getId(), user);
      });
      thread.start();

      try {
        // 强制线程执行完成
        thread.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  // 判断 依赖注入 的方式
  private static void scopeBeansByInjection(AnnotationConfigApplicationContext applicationContext) {
  }
}
