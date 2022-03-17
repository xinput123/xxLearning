package com.xinput.thinking.in.spring.ioc.overview.domain;

/**
 * TODO
 *
 * @author yuan.lai
 * @since
 */
public class Company {

  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Company{" +
        "name='" + name + '\'' +
        '}';
  }
}
