package com.xinput.thinking.in.spring.bean.lifecyle;

import com.xinput.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

/**
 * User Hodler 类
 *
 * @author yuan.lai
 * @since
 */
public class UserHodler implements BeanNameAware, BeanClassLoaderAware, BeanFactoryAware, EnvironmentAware {

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
}
