package com.xinput.thinking.in.spring.event;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 层次性 Spring 事件传播 示例
 */
public class HierarchicalSpringEventPropagateDemo {

  public static void main(String[] args) {
    // 1、创建 parent Spring 应用上下文
    AnnotationConfigApplicationContext parentContent = new AnnotationConfigApplicationContext();
    parentContent.setId("parent-context");

    // 注册 MyListener 到 parent Spring 应用上下文
    parentContent.register(MyListener.class);

    // 2、创建 current Spring 应用上下文
    AnnotationConfigApplicationContext currentContent = new AnnotationConfigApplicationContext();
    currentContent.setId("current-context");

    // 3、current -> parent
    currentContent.setParent(parentContent);
    // 注册 MyListener 到 current Spring 应用上下文
    currentContent.register(MyListener.class);

    // 4、启动 parent Spring 应用上下文
    parentContent.refresh();
    // 5、启动 current Spring 应用上下文
    currentContent.refresh();

    // 关闭所有 Spring 应用
    currentContent.close();
    parentContent.close();
  }

  static class MyListener implements ApplicationListener<ApplicationContextEvent> {

    private static Set<ApplicationContextEvent> processedEvents = new LinkedHashSet();

    @Override
    public void onApplicationEvent(ApplicationContextEvent event) {
      if (processedEvents.add(event)) {
        System.out.printf("监听到 Spring 应用上下文[ID:%s] 事件 :%s\n",
            event.getApplicationContext().getId(), event.getClass().getSimpleName());
      }
    }
  }
}
