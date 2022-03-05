package com.xinput.thinking.in.spring.configuration.metadata;

import com.xinput.thinking.in.spring.ioc.overview.domain.User;
import com.xinput.thinking.in.spring.ioc.overview.enums.City;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

/**
 * 基于 Java 注解的 YAML 外部化配置示例
 *
 * @author yuan.lai
 * @since
 */
@PropertySource(
    name = "yamlPropertySource",
    value = "classpath:/META-INF/user.yml",
    factory = YamlPropertySourceFactory.class
)
public class AnnotatedYamlPropertySourceDemo {

  /**
   * user.name 是 Java Properties 默认存在，当前用户：yuanlai，而非配置文件中定义"原小来"
   */
  @Bean
  public User user(@Value("${user.id}") Long id,
                   @Value("${user.name}") String name,
                   @Value("${user.city}") City city) {
    User user = new User();
    user.setId(id);
    user.setName(name);
    user.setCity(city);
    return user;
  }

  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    // 注册当前类作为 Configuration Class
    context.register(AnnotatedYamlPropertySourceDemo.class);
    // 启动 Spring 应用上下文
    context.refresh();

    // 获取 Map yaml 对象
    User user = context.getBean("user", User.class);
    System.out.println(user);

    // 关闭 Spring 应用上下文
    context.close();
  }
}
