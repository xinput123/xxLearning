package com.xinput.thinking.in.spring.configuration.metadata;

import com.xinput.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.MapPropertySource;

import java.util.HashMap;
import java.util.Map;

/**
 * 外部化配置示例
 *
 * @author yuan.lai
 * @since
 */
@PropertySource("classpath:/META-INF/user-bean-definitions.properties")
public class PropertySourceDemo {

  /**
   * user.name 是 Java Properties 默认存在，当前用户：yuanlai，而非配置文件中定义"原小来"
   */
  @Bean
  public User user(@Value("${user.id}") Long id, @Value("${user.name}") String name) {
    User user = new User();
    user.setId(id);
    user.setName(name);
    return user;
  }

  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

    // 扩展 Enviroment 中的 PropertySources
    // 添加 PropertySource 操作必须在 refresh 方法之前完成
    Map<String, Object> propertieSource = new HashMap();
    propertieSource.put("user.name", "yuanxiaolai");
    org.springframework.core.env.PropertySource propertySource = new MapPropertySource("first-property-source", propertieSource);
    context.getEnvironment().getPropertySources().addFirst(propertySource);

    // 注册当前类作为 Configuration Class
    context.register(PropertySourceDemo.class);
    // 启动 Spring 应用上下文
    context.refresh();

    Map<String, User> userMap = context.getBeansOfType(User.class);
    for (Map.Entry<String, User> entry : userMap.entrySet()) {
      System.out.printf("User Bean name : %s , content : %s \n", entry.getKey(), entry.getValue());
    }

    System.out.println(context.getEnvironment().getPropertySources());

    // 关闭 Spring 应用上下文
    context.close();
  }
}
