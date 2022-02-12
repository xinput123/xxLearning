package com.xinput.thinking.in.spring.bean.factory;

import com.xinput.thinking.in.spring.ioc.overview.domain.User;

/**
 * {@link User} 工厂类
 */
public interface UserFactory {

  default User createUser() {
    return User.createUser();
  }

  void initUserFactory();

  void doDestroy();
}
