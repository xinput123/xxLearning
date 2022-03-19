package com.xinput.thinking.in.spring.generic;

import org.springframework.core.GenericTypeResolver;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.List;
import java.util.Map;

/**
 * {@link GenericTypeResolver}  示例
 *
 * @see GenericTypeResolver
 */
public class GenericTypeResolverDemo {
  public static void main(String[] args) throws NoSuchMethodException {
    // String Comparable<String> 具体化
//    displayReturnTypeGenericInfo(GenericTypeResolverDemo.class, List.class, "getString");
    displayReturnTypeGenericInfo(GenericTypeResolverDemo.class, Comparable.class, "getString");
    // ArrayList<Object> 是 List 泛型参数具体化
    displayReturnTypeGenericInfo(GenericTypeResolverDemo.class, List.class, "getList");
    // StringList 也是 List 泛型参数具体化
    displayReturnTypeGenericInfo(GenericTypeResolverDemo.class, List.class, "getStringList");

    // 具备 ParameterizdeType 返回，否则 null

    // TypeVariable
    Map<TypeVariable, Type> typeVariableMap = GenericTypeResolver.getTypeVariableMap(StringList.class);
    for (Map.Entry<TypeVariable, Type> entry : typeVariableMap.entrySet()) {
      System.out.println(entry.getKey() + " = " + entry.getValue());
    }
  }

  public static StringList getStringList() {
    return null;
  }

//  public static <E> List<E> getList() {
//    return null;
//  }

  public static List<Object> getList() { // 泛型参数类型具体化
    return null;
  }

  public static String getString() {
    return null;
  }

  private static void displayReturnTypeGenericInfo(Class<?> contaioningClass, Class<?> genericIfc, String methodName, Class... argumentTypes) throws NoSuchMethodException {
    Method method = GenericTypeResolverDemo.class.getMethod(methodName, argumentTypes);
    // 声明类 GenericTypeResolverDemo.class
    Class<?> returnType = GenericTypeResolver.resolveReturnType(method, contaioningClass);

    // 常规类作为方法返回值
    System.out.printf("GenericTypeResolver.resolveReturnType(%s,%s) = %s\n",
        methodName, contaioningClass.getSimpleName(), returnType);

    // 常规方法不具备泛型参数类型 List<E>
    Class<?> returnTypeArgument = GenericTypeResolver.resolveReturnTypeArgument(method, genericIfc);
    System.out.printf("GenericTypeResolver.resolveReturnTypeArgument(%s,%s) = %s\n",
        methodName, contaioningClass, returnTypeArgument);
  }

}
