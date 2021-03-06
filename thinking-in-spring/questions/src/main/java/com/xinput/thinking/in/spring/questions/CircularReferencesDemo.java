package com.xinput.thinking.in.spring.questions;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * BeanFactory 循环引用(依赖) 示例
 */
public class CircularReferencesDemo {

  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    // 注册 Configuration Class
    context.register(CircularReferencesDemo.class);

    // 如果设置为 false， 则抛出异常 Requested bean is currently in creation: Is there an unresolvable circular reference?
//    context.setAllowCircularReferences(false);
    context.setAllowCircularReferences(true);

    // 启动 Spring 应用上下文
    context.refresh();

    System.out.println(context.getBean(Student.class));
    System.out.println(context.getBean(ClassRoom.class));

    // 关闭 Spring 应用上下文
    context.close();
  }

  @Bean
  public static Student student() {
    Student student = new Student();
    student.setId(1L);
    student.setName("张三");

    return student;
  }

  @Bean
  public static ClassRoom classRoom() {
    ClassRoom classRoom = new ClassRoom();
    classRoom.setName("C122");
    return classRoom;
  }
}
