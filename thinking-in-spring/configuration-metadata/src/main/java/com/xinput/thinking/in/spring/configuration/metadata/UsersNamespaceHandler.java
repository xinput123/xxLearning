package com.xinput.thinking.in.spring.configuration.metadata;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * "users.xsd" {@link NamespaceHandlerSupport} 实现
 *
 * @author yuan.lai
 * @since
 */
public class UsersNamespaceHandler extends NamespaceHandlerSupport {
  @Override
  public void init() {
    // 将 "user" 元素注册对应的 BeanDefinitionParser 实现
    registerBeanDefinitionParser("user", new UsersBeanDefinitionParser());
  }
}
