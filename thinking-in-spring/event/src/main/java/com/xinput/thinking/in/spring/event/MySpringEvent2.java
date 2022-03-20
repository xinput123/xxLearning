package com.xinput.thinking.in.spring.event;

/**
 * 自定义 Spring 事件
 */
public class MySpringEvent2 extends MySpringEvent {
  /**
   * Create a new {@code ApplicationEvent}.
   *
   * @param message 事件消息
   */
  public MySpringEvent2(Object message) {
    super(message);
  }

  @Override
  public String getSource() {
    return (String) super.getSource();
  }

  public String getMessage() {
    return getSource();
  }
}
