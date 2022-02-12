package com.xinput.thinking.in.spring.bean.factory;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * {@link UserFactory} 实现
 * spring bean初始化顺序：PostContruct->afterPropertiesSet->自定义init方法
 */
public class DefaultUserFactory implements UserFactory, InitializingBean, DisposableBean {

  // 1、基于 @PostContruce 注解,最先加载
  @PostConstruct
  public void init() {
    System.out.println("@PostConstruct: UserFactory 初始化中...");
  }

  @Override
  public void initUserFactory() {
    System.out.println("自定义初始化方法: UserFactory 初始化中...");
  }

  //
  @Override
  public void afterPropertiesSet() throws Exception {
    System.out.println("InitializingBean#afterPropertiesSet: UserFactory 初始化中...");
  }

  @PreDestroy
  public void preDestroy() {
    System.out.println("@PreDestroy: UserFactory 销毁中...");
  }

  @Override
  public void destroy() throws Exception {
    System.out.println("DisposableBean#destroy: UserFactory 销毁中...");
  }

  @Override
  public void doDestroy() {
    System.out.println("自定义销毁方法 doDestroy(): UserFactory 销毁中...");
  }

  @Override
  public void finalize() throws Throwable {
    System.out.println("当前 DefaultUserFactory 对象正在被回收...");
  }
}