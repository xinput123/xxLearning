package com.xinput.thinking.in.spring.environment;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

/**
 * 依赖查找 {@link Environment}
 */
public class LookupEnviromentDemo implements EnvironmentAware {

  private Environment environment;

  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    // 注册 Configuration Class
    context.register(LookupEnviromentDemo.class);

    // 启动 Spring 应用上下文
    context.refresh();

    LookupEnviromentDemo lookupEnviromentDemo = context.getBean(LookupEnviromentDemo.class);

    // 通过 Environment Bean 名称 依赖查找
    Environment environment = context.getBean(ConfigurableApplicationContext.ENVIRONMENT_BEAN_NAME, Environment.class);

    System.out.println(lookupEnviromentDemo.environment);
    System.out.println(lookupEnviromentDemo.environment == environment);

    // 关闭 Spring 应用上下文
    context.close();
  }

  @Override
  public void setEnvironment(Environment environment) {
    this.environment = environment;
  }
}
