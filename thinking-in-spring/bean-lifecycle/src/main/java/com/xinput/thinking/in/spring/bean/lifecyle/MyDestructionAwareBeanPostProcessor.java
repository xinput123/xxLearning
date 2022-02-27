package com.xinput.thinking.in.spring.bean.lifecyle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.util.ObjectUtils;

/**
 * @{link DestructionAwareBeanPostProcessor} 实现
 */
public class MyDestructionAwareBeanPostProcessor implements DestructionAwareBeanPostProcessor {

  @Override
  public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {
    Class<UserHolder> userHolderClass = UserHolder.class;
    Class<?> aClass = bean.getClass();
    if (ObjectUtils.nullSafeEquals("userHolder", beanName) && UserHolder.class.equals(bean.getClass())) {
      UserHolder userholder = (UserHolder) bean;
      // afterSingletonInstantiated() = The user holder V8
      // UserHolder description = "The user holder V8"
      userholder.setDescription("The user holder V9");
      System.out.println("postProcessBeforeDestruction() : " + userholder.getDescription());
    }
  }
}
