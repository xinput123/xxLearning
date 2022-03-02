package com.xinput.thinking.in.spring.configuration.metadata;

import com.xinput.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

import java.util.Map;

/**
 * 基于 Java 注解 Spring IoC 容器元信息配置 示例
 */
// 将当前类作为 Configuration Class
@ImportResource("classpath:/META-INF/dependency-lookup-context.xml")
@Import(User.class)
@PropertySource("classpath:/META-INF/user-bean-definitions.properties") // Java 8+ @Repeatable 支持
@PropertySource("classpath:/META-INF/user-bean-definitions.properties")
//@PropertySources("")
public class AnnotatedSpringIoCContainerMetadataConfigurationDemo {

  /**
   * user.name 是 Java Properties 默认存在，当前用户：，而非配置文件中定义"原小来"
   */
  @Bean
  public User configuredUser(@Value("${user.id}") Long id, @Value("${user.name}") String name) {
    User user = new User();
    user.setId(id);
    user.setName(name);
    return user;
  }

  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

    // 注册当前类作为 Configuration Class
    context.register(AnnotatedSpringIoCContainerMetadataConfigurationDemo.class);
    // 启动应用上下文
    context.refresh();

    Map<String, User> userMap = context.getBeansOfType(User.class);
    for (Map.Entry<String, User> entry : userMap.entrySet()) {
      System.out.printf("User Bean name: %s, content : %s \n", entry.getKey(), entry.getValue());
    }

    // 关闭应用上下文
    context.close();
  }
}
