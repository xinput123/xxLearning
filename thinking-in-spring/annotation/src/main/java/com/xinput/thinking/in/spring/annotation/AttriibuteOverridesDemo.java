package com.xinput.thinking.in.spring.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Spring 注解属性覆盖示例
 */
@MyComponentScan2(scanBasePackages = "com.xinput.thinking.in.spring.annotation")
// @MyComponentScan2.scanBasePackages <- @MyComponentScan.scanBasePackages
public class AttriibuteOverridesDemo {
  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

    // 注册 Configuration class
    context.register(AttriibuteOverridesDemo.class);

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
