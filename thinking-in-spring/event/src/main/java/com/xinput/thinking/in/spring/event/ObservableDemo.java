package com.xinput.thinking.in.spring.event;

import java.util.EventListener;
import java.util.EventObject;
import java.util.Observable;
import java.util.Observer;

/**
 * {@link Observer} 示例
 * Java 事件/ 监听器
 */
public class ObservableDemo {
  public static void main(String[] args) {
    // 创建 Observable
    Observable observable = new EventObservable();

    // 添加监听者
    observable.addObserver(new EventObserver());
    // 发布消息(事件)
    observable.notifyObservers("hello, world");
  }

  static class EventObservable extends Observable {
    @Override
    public void setChanged() {
      super.setChanged();
    }

    @Override
    public void notifyObservers(Object arg) {
      setChanged();
      super.notifyObservers(new EventObject(arg));
      clearChanged();
    }
  }

  static class EventObserver implements Observer, EventListener {

    @Override
    public void update(Observable o, Object event) {
      EventObject eventObject = (EventObject) event;
      System.out.println("收到事件: " + eventObject);
    }
  }
}
