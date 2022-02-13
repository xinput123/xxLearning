package org.xx.ioc.java.beans;

/**
 * Setter / Getter 方法
 * JavaBeans 可写方法(Writable)/可读方法(Readable)
 */
public class Person {

  // String to String
  String name; // Property

  // String to Integer
  Integer age;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }
}
