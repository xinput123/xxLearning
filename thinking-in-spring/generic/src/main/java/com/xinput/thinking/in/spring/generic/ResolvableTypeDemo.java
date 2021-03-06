package com.xinput.thinking.in.spring.generic;

import org.springframework.core.ResolvableType;

/**
 * {@link ResolvableType}
 *
 * @see ResolvableType
 */
public class ResolvableTypeDemo {
  public static void main(String[] args) {
    // 工厂创建
    ResolvableType resolvableType = ResolvableType.forClass(StringList.class);

    resolvableType.getSuperType(); // ArrayList
    resolvableType.getSuperType().getSuperType(); // AbstractList

    System.out.println(resolvableType.asCollection().resolveGeneric(0));
  }
}
