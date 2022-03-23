package com.xinput.thinking.in.spring.application.lifecycle;

import org.springframework.context.Lifecycle;

/**
 * TODO
 *
 * @author yuan.lai
 * @since
 */
public class Mylifecycle implements Lifecycle {

  private boolean running = false;

  @Override
  public void start() {
    running = true;
    System.out.println("启动中...");
  }

  @Override
  public void stop() {
    running = false;
    System.out.println("停止中...");
  }

  @Override
  public boolean isRunning() {
    return running;
  }
}
