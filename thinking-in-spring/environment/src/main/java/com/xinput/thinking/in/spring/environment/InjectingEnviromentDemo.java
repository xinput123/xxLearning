package com.xinput.thinking.in.spring.environment;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

/**
 * 依赖注入 {@link Environment}
 */
public class InjectingEnviromentDemo implements EnvironmentAware, ApplicationContextAware {

  private Environment environment;

  @Autowired
  private Environment environment2;

  private ApplicationContext applicationContext;

  @Autowired
  private ApplicationContext applicationContext2;

  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    // 注册 Configuration Class
    context.register(InjectingEnviromentDemo.class);

    // 启动 Spring 应用上下文
    context.refresh();

    InjectingEnviromentDemo demo = context.getBean(InjectingEnviromentDemo.class);
    System.out.println(demo.environment);

    System.out.println(demo.environment == demo.environment2);

    System.out.println(demo.environment == context.getEnvironment());

    System.out.println(context == demo.applicationContext);

    System.out.println(context == demo.applicationContext2);

    // 关闭 Spring 应用上下文
    context.close();
  }

  @Override
  public void setEnvironment(Environment environment) {
    this.environment = environment;
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.applicationContext = applicationContext;
  }
}
