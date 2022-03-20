package com.xinput.thinking.in.spring.event;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

/**
 * 注入 {@link org.springframework.context.ApplicationEventPublisher}
 */
public class InjectingApplicationEventPublisherDemo implements ApplicationEventPublisherAware,
    ApplicationContextAware, BeanPostProcessor {

  @Autowired
  private ApplicationEventPublisher applicationEventPublisher;

  @Autowired
  private ApplicationContext applicationContext;

  @Override
  public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) { // #1
    applicationEventPublisher.publishEvent(new MySpringEvent("1、The event form ApplicationEventPublisher"));
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException { // #2
    applicationEventPublisher.publishEvent(new MySpringEvent("2、The event form ApplicationContext"));
  }

  @PostConstruct
  public void init() {
    // #3
    applicationEventPublisher.publishEvent(new MySpringEvent("3、The event from @Autowire ApplicationEventPublisher"));
    // #4
    applicationContext.publishEvent(new MySpringEvent("4、The event from @Autowire applicationContext"));
  }

  public static void main(String[] args) {
    // 创建注解驱动 Spring 应用上下文
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

    // 注册 Configuration Class
    context.register(InjectingApplicationEventPublisherDemo.class);

    // 增加 Spring 事件监听器
    context.addApplicationListener(new MySpringEventListener());

    // 启动 Spring 应用上下文
    context.refresh();

    // 关闭 Spring 应用上下文
    context.close();
  }
}
