package com.xinput.thinking.in.spring.application.lifecycle;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.LiveBeansView;

import java.io.IOException;

/**
 * {@link LiveBeansView} 示例
 */
public class LiveBeansViewDemo {

  public static void main(String[] args) throws IOException {
    // 添加 LiveBeansView 的 ObjectName 的 domain
    System.setProperty(LiveBeansView.MBEAN_DOMAIN_PROPERTY_NAME, "com.xinput.thinking.in.spring");

    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

    // 注册 Configuration Class
    context.register(LiveBeansViewDemo.class);

    // 启动 Spring 应用上下文
    context.refresh();

    System.out.println("按任意键继续");
    System.in.read();

    // 关闭 Spring 应用上下文
    context.close();
  }
}
