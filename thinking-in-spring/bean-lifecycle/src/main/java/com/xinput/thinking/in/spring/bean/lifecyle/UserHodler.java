package com.xinput.thinking.in.spring.bean.lifecyle;

import com.xinput.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;

/**
 * User Hodler 类
 *
 * @author yuan.lai
 * @since
 */
public class UserHodler implements BeanNameAware, BeanClassLoaderAware, BeanFactoryAware,
    EnvironmentAware, InitializingBean, SmartInitializingSingleton {

  private final User user;

  private int num;

  private String description;

  private String beanName;

  private ClassLoader classLoader;

  private BeanFactory beanFactory;

  private Environment environment;

  public UserHodler(User user) {
    this.user = user;
  }

  public UserHodler(User user, int num) {
    this.user = user;
    this.num = num;
  }

  public int getNum() {
    return num;
  }

  public void setNum(int num) {
    this.num = num;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * @PostConstruct 依赖于注解驱动
   * 当场场景:
   */
  @PostConstruct
  public void initPostConstruct() {
    // postProcessBeforeInit V3 --> initPostConstruct V4
    this.description = "The user holder V4";
    System.out.println("initPostConstruct() = " + this.description);
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    // initPostConstruct V4 --> afterPropertiesSet V5
    this.description = "The user holder V5";
    System.out.println("afterPropertiesSet() = " + this.description);
  }

  /**
   * 自定义初始方法
   */
  public void init() {
    // afterPropertiesSet V5 --> init V6
    this.description = "The user holder V6";
    System.out.println("init() = " + this.description);
  }

  @Override
  public String toString() {
    return "UserHodler{" +
        "user=" + user +
        ", num=" + num +
        ", description='" + description + '\'' +
        ", beanName='" + beanName + '\'' +
        '}';
  }

  @Override
  public void setBeanName(String name) {
    this.beanName = name;
  }

  @Override
  public void setBeanClassLoader(ClassLoader classLoader) {
    this.classLoader = classLoader;
  }

  @Override
  public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
    this.beanFactory = beanFactory;
  }

  @Override
  public void setEnvironment(Environment environment) {
    this.environment = environment;
  }

  @Override
  public void afterSingletonsInstantiated() {
    // postProcessAfterInitialization V7 -> afterSingletonsInstantiated V8
    this.description = "The user holder V8";
    System.out.println("afterSingletonsInstantiated() = " + this.description);
  }
}
