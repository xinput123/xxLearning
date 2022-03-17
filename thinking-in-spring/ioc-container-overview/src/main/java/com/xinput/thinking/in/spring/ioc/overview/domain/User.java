package com.xinput.thinking.in.spring.ioc.overview.domain;

import com.xinput.thinking.in.spring.ioc.overview.enums.City;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Arrays;
import java.util.List;

/**
 * 用户类
 */
public class User implements BeanNameAware {

  private Long id;

  private String name;

  private City city;

  private City[] workCities;

  private List<City> lifeCities;

  private Resource configFileLocation;

  private Company company;

  // 当前 BeanName
  private transient String beanName;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public City getCity() {
    return city;
  }

  public void setCity(City city) {
    this.city = city;
  }

  public City[] getWorkCities() {
    return workCities;
  }

  public void setWorkCities(City[] workCities) {
    this.workCities = workCities;
  }

  public List<City> getLifeCities() {
    return lifeCities;
  }

  public void setLifeCities(List<City> lifeCities) {
    this.lifeCities = lifeCities;
  }

  public Resource getConfigFileLocation() {
    return configFileLocation;
  }

  public void setConfigFileLocation(Resource configFileLocation) {
    this.configFileLocation = configFileLocation;
  }

  public Company getCompany() {
    return company;
  }

  public void setCompany(Company company) {
    this.company = company;
  }

  public String getBeanName() {
    return beanName;
  }

  @Override
  public String toString() {
    return "User{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", city=" + city +
        ", workCities=" + Arrays.toString(workCities) +
        ", lifeCities=" + lifeCities +
        ", configFileLocation=" + configFileLocation +
        ", company=" + company +
        ", beanName='" + beanName + '\'' +
        '}';
  }

  public static User createUser() {
    User user = new User();
    user.setId(111L);
    user.setName("xinput111");
    return user;
  }

  @PostConstruct
  public void init() {
    System.out.println("User Bean [" + beanName + "] 初始化...");
  }

  @PreDestroy
  public void destroy() {
    System.out.println("User Bean [" + beanName + "] 销毁中...");
  }

  @Override
  public void setBeanName(String name) {
    this.beanName = name;
  }
}
