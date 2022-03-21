package com.xinput.thinking.in.spring.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义 {@link Component} 注解
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@MyComponentScan
public @interface MyComponentScan2 {

  @AliasFor(annotation = MyComponentScan.class, attribute = "scanBasePackages") // 隐性别名
  String[] basePackages() default {};

  // @MyComponentScan2.basePackages
  // -> @MyComponentScan.scanBasePackages ->
  // -> @ComponentScan.value
  // -> @ComponentScan.basePackages

  /**
   * 与元注解 @MyComponentScan 同名属性
   *
   * @return
   */
  String[] scanBasePackages() default {};
}
