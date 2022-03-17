package com.xinput.thinking.in.spring.binding;

import com.xinput.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;

import java.util.HashMap;
import java.util.Map;

/**
 * {@link DataBinder} 示例
 *
 * @see DataBinder
 */
public class DataBinderDemo {

  public static void main(String[] args) {
    // 创建空白对象
    User user = new User();
    DataBinder binder = new DataBinder(user, "");

    // 创建 PropertyValues
    Map<String, Object> source = new HashMap();
    source.put("id", 1);
    source.put("name", "李星云");

    // PropertyValues 存在 user 中不存在的属性
    // DataBinder 特性一: 忽略未知的属性
    source.put("age", 18);

    // PropertyValues 存在 一个嵌套属性, 比如 company.name
    // DataBinder 特性二: 支持嵌套属性
    // Company company = new Company()
    // company.setName = "hello group"
    // user.setCompany(company)

//    source.put("company", new Company());
    source.put("company.name", "hello group");

    PropertyValues propertyValues = new MutablePropertyValues(source);

    // 1、调整 ignoreUnknownFields true(默认) -> false(抛出异常， age 字段不存在与 User 类)
    // binder.setIgnoreUnknownFields(false);

    // 2、调整自动增加嵌套路径 true(默认) -> false
    binder.setAutoGrowNestedPaths(false);

    // 3、调整 ignoreInvalidFields false(默认) -> true(默认情况调整不变化,需要调增 AutoGrowNestedPaths 为 false)
    binder.setIgnoreInvalidFields(true);

    binder.setRequiredFields("id", "name", "city");

    binder.bind(propertyValues);

    // 输出 User 内容
    System.out.println(user);

    // 绑定结果(结果包含错误文案 code, 不会抛出异常)
    BindingResult result = binder.getBindingResult();
    System.out.println(result);
  }
}
