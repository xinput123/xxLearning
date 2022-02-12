package com.xinput.thinking.in.spring.ioc.overview.domain;

import com.xinput.thinking.in.spring.ioc.overview.annotation.Super;

/**
 * 超级用户
 */
@Super
public class SuperUser extends User {
  private String addressss;

  public String getAddressss() {
    return addressss;
  }

  public void setAddressss(String addressss) {
    this.addressss = addressss;
  }

  @Override
  public String toString() {
    return "SuperUser{" +
        "addressss='" + addressss + '\'' +
        "} " + super.toString();
  }
}
