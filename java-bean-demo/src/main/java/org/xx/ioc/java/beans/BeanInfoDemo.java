package org.xx.ioc.java.beans;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyEditorSupport;
import java.util.stream.Stream;

/**
 * @author yuan.lai
 * @version 1.0
 * @date 2021/12/19
 * @description
 */
public class BeanInfoDemo {
  public static void main(String[] args) throws IntrospectionException {
//    BeanInfo beanInfo = Introspector.getBeanInfo(Person.class);
    BeanInfo beanInfo = Introspector.getBeanInfo(Person.class, Object.class);

    Stream.of(beanInfo.getPropertyDescriptors()).forEach(propertyDescriptor -> {
//      System.out.println(propertyDescriptor.toString());
      // PropertyDescriptor 允许添加属性编辑器 - PropertyEditor
      // GUI -> text(String) -> PropertyType
      // name -> String
      // age -> Integer
      Class<?> propertyType = propertyDescriptor.getPropertyType();
      String propertyName = propertyDescriptor.getName();
      if ("age".equals(propertyName)) {
        // String -> Integer
        propertyDescriptor.setPropertyEditorClass(StringToIntegerPropertyEditor.class);
      }
    });
  }

  static class StringToIntegerPropertyEditor extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
      Integer value = Integer.valueOf(text);
      setValue(value);
    }
  }
}
