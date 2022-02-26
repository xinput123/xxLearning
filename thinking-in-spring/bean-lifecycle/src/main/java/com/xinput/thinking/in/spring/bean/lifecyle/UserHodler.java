package com.xinput.thinking.in.spring.bean.lifecyle;

import com.xinput.thinking.in.spring.ioc.overview.domain.User;

/**
 * User Hodler 类
 *
 * @author yuan.lai
 * @since
 */
public class UserHodler {

  private final User user;

  private int num;

  private String description;

  public UserHodler(User user) {
    this.user = user;
  }

  public UserHodler(User user, int num) {
    this.user = user;
    this.num = num;
  }

  public int getNum() {
    return num;
  }

  public void setNum(int num) {
    this.num = num;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return "UserHodler{" +
        "user=" + user +
        ", num=" + num +
        ", description='" + description + '\'' +
        '}';
  }
}
