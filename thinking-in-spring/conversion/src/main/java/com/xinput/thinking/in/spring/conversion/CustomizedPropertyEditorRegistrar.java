package com.xinput.thinking.in.spring.conversion;

import com.xinput.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;

/**
 * 自定义 {@link PropertyEditorRegistrar} 实现
 */
//@Component // 3、将其申明为 Spring Bean
public class CustomizedPropertyEditorRegistrar implements PropertyEditorRegistrar {

  @Override
  public void registerCustomEditors(PropertyEditorRegistry registry) {
    // 1、通用类型转换
    // 2、Java Bean 属性类型转换
    registry.registerCustomEditor(User.class, "context", new StringToPropertyEditor());
  }
}
