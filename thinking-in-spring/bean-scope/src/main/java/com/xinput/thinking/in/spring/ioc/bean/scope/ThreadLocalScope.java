package com.xinput.thinking.in.spring.ioc.bean.scope;


import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.core.NamedThreadLocal;

import java.util.HashMap;
import java.util.Map;

/**
 * ThreadLocal 级别 scope
 */
public class ThreadLocalScope implements Scope {

  public static final String SCORE_NAME = "thread-local";

  public final NamedThreadLocal<Map<String, Object>> threadLocal = new NamedThreadLocal("thread-local-scope") {
    // 为了避免空指针
    public Map<String, Object> initialValue() {
      return new HashMap();
    }
  };

  @Override
  public Object get(String name, ObjectFactory<?> objectFactory) {
    // 非空
    Map<String, Object> context = getContext();
    Object object = context.get(name);
    if (object == null) {
      object = objectFactory.getObject();
      context.put(name, object);
    }

    return object;
  }

  private Map<String, Object> getContext() {
    return threadLocal.get();
  }

  @Override
  public Object remove(String name) {
    Map<String, Object> context = getContext();
    return context.remove(name);
  }

  @Override
  public void registerDestructionCallback(String name, Runnable callback) {
    // todo
  }

  @Override
  public Object resolveContextualObject(String key) {
    Map<String, Object> context = getContext();
    return context.get(key);
  }

  @Override
  public String getConversationId() {
    Thread thread = Thread.currentThread();
    return String.valueOf(thread.getId());
  }
}
