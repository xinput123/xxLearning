package com.xinput.thinking.in.spring.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * {@link ApplicationListener} 示例
 *
 * @see ApplicationListener
 * @see EventListener
 */
@EnableAsync
public class ApplicationListenerDemo2 implements ApplicationEventPublisherAware {

  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

    // 将引导类 ApplicationListenerDemo2 作为 Configuration Class
    context.register(ApplicationListenerDemo2.class);

    // 方法一: 基于 Spring 接口: 向 Spring 应用上下文注册事件
    // a 方法 : 基于 ConfigurableApplication API 实现
    context.addApplicationListener(new ApplicationListener<ApplicationEvent>() {
      @Override
      public void onApplicationEvent(ApplicationEvent event) {
        System.out.println("ApplicationListener - 接受到 Spring 事件：" + event);
      }
    });

    // b 方法 : 基于 ApplicationListener 注册为 Spring Bean
    // 通过 Configuration Class 来注册
    context.register(MyApplicationListener.class);

    // 方法二: 基于 Spring 注解: @org.springframework.context.event.EventListener

    // ApplicationEventMulticaster
    // 启动 Spring 应用上下文
    context.refresh();
    // 启动 Spring 应用上下文
    context.start();
    // 关闭 Spring 应用上下文
    context.close();
  }

  @Override
  public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
    applicationEventPublisher.publishEvent(new ApplicationEvent("Hello,World") {
    });
    applicationEventPublisher.publishEvent("Hello,World");
    applicationEventPublisher.publishEvent(new MyPayloadApplicationEvent(this, "Hello,World"));
  }

  static class MyPayloadApplicationEvent<String> extends PayloadApplicationEvent<String> {

    /**
     * Create a new PayloadApplicationEvent.
     *
     * @param source  the object on which the event initially occurred (never {@code null})
     * @param payload the payload object (never {@code null})
     */
    public MyPayloadApplicationEvent(Object source, String payload) {
      super(source, payload);
    }
  }

  static class MyApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
      println("MyApplicationListener - 接收到 Spring 事件：" + event);
    }
  }

  @EventListener
  @Order(1)
  public void onApplicationEvent(ContextRefreshedEvent event) {
    println("@EventListenerOrder1(onApplicationEvent) - 接受到 Spring ContextRefreshedEvent：" + event);
  }

  @EventListener
  @Order(2)
  public void onApplicationEvent1(ContextRefreshedEvent event) {
    println("@EventListenerOrder2(onApplicationEvent1) - 接受到 Spring ContextRefreshedEvent：" + event);
  }

  @EventListener
  @Async
  public void onApplicationEventAsync(ContextRefreshedEvent event) {
    println("@EventListener(异步) - 接受到 Spring ContextRefreshedEvent：");
  }

  @EventListener
  public void onApplicationEvent(ContextStartedEvent event) {
    println("@EventListener - 接受到 Spring ContextStartedEvent：");
  }

  @EventListener
  public void onApplicationEvent(ContextClosedEvent event) {
    println("@EventListener - 接受到 Spring ContextClosedEvent：");
  }

  private static void println(Object printable) {
    System.out.printf("[线程: %s] : %s\n", Thread.currentThread().getName(), printable);
  }
}
