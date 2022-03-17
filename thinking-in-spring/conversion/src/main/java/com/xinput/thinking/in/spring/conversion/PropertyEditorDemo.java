package com.xinput.thinking.in.spring.conversion;

import java.beans.PropertyEditor;

/**
 * {@link java.beans.PropertyEditor} 示例
 */
public class PropertyEditorDemo {

  public static void main(String[] args) {
    // 模拟 Spring Framework 操作
    // 有一段文本 name = 李星云

    String text = "name = 李星云";
    PropertyEditor propertyEditor = new StringToPropertyEditor();
    // 传递 String 类型的内容
    propertyEditor.setAsText(text);

    System.out.println(propertyEditor.getValue());
    System.out.println(propertyEditor.getAsText());
  }
}
