package com.xinput.thinking.in.spring.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * {@link Component} 扫描示例
 *
 * @see Component
 * @see ComponentScan
 */
@MyComponentScan(scanBasePackages = "com.xinput.thinking.in.spring.annotation") // 指定 Class Path(s)
//@ComponentScan(value = "com.xinput.thinking.in.spring.annotation") // 指定 Class Path(s)
public class ComponentScanDemo {
  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

    // 注册 Configuration class
    context.register(ComponentScanDemo.class);

    // 启动 Spring 应用上下文
    context.refresh();

    // 依赖查找 TestClass Bean
    // TestClass 标注 @MyComponent2
    // @MyComponent2 <- @MyComponent <- @Component
    TestClass testClass = context.getBean(TestClass.class);
    // 从 Spring 4.0 开始支持多层次 @Component 派生
    System.out.println(testClass);

    // 关闭 Spring 应用上下文
    context.close();
  }
}
