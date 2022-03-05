package com.xinput.thinking.in.spring.configuration.metadata;

import com.xinput.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSimpleBeanDefinitionParser;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * "user" 元素的 {@link BeanDefinitionParser} 实现
 */
public class UsersBeanDefinitionParser extends AbstractSimpleBeanDefinitionParser {

  @Override
  protected Class<?> getBeanClass(Element element) {
    return User.class;
  }

  @Override
  protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
    setPropertyValue("id", element, builder);
    setPropertyValue("name", element, builder);
    setPropertyValue("city", element, builder);
  }

  private void setPropertyValue(String attributeName, Element element, BeanDefinitionBuilder builder) {
    String attributeValue = element.getAttribute(attributeName);
    if (StringUtils.hasText(attributeName)) {
      builder.addPropertyValue(attributeName, attributeValue); // -> <property name="" value="" />
    }
  }
}
