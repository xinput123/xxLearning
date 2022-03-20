package com.xinput.thinking.in.spring.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.support.GenericApplicationContext;

/**
 * {@link ApplicationListener} 示例
 *
 * @see ApplicationListener
 */
public class ApplicationListenerDemo {

  public static void main(String[] args) {
    GenericApplicationContext context = new GenericApplicationContext();

    // 基于 Spring 接口: 向 Spring 应用上下文注册事件
    context.addApplicationListener(new ApplicationListener<ApplicationEvent>() {
      @Override
      public void onApplicationEvent(ApplicationEvent event) {
        System.out.println("接受到 Spring 事件：" + event);
      }
    });

    // 启动 Spring 应用上下文
    context.refresh();
    // 启动 Spring 应用上下文
    context.start();
    // 关闭 Spring 应用上下文
    context.close();

  }
}
