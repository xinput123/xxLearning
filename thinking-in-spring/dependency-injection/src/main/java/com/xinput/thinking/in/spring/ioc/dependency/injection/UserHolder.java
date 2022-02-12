package com.xinput.thinking.in.spring.ioc.dependency.injection;

import com.xinput.thinking.in.spring.ioc.overview.domain.User;

/**
 * {@link User} 的 Holder 类
 */
public class UserHolder {

  private User user;

  public UserHolder() {
  }

  public UserHolder(User user) {
    this.user = user;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @Override
  public String toString() {
    return "UserHolder{" +
        "user=" + user +
        '}';
  }
}
