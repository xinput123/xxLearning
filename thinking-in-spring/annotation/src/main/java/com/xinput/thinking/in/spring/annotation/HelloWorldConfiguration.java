package com.xinput.thinking.in.spring.annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * HelloWorld Configuration Class
 *
 * @author yuan.lai
 * @since
 */
@Configuration
public class HelloWorldConfiguration {

  @Bean
  public String helloWorld() {
    return "Hello,World";
  }
}
