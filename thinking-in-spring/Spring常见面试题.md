### BeanFactory 和 FactoryBean 区别

- BeanFactory 是 IoC 底层容器
- FactoryBean 是创建 Bean 的一种方式，帮助实现复杂的初始化逻辑

### Spring IoC 启动时做了哪些准备

- IoC 配置元信息读取和解析
- IoC 容器生命周期
- Spring 事件发布
- 国际化等

### 如何注册一个 Spring Bean

- 通过 BeanDefinition
- 外部单体对象注册

### ObjectFactory 和 BeanFactory 有什么区别

- ObjectFactory 和 BeanFactory 均提供依赖查找的能力
- 不过 ObjectFactory 仅关注一个或一种类型的 Bean 依赖查找,并且自身不具备依赖查找的能力，能力则由 BeanFactory 输出。
- BeanFactory 则提供了单一类型、集合类型以及层次型等多种依赖。

### BeanFactory.getBean 是否是线程安全的

- 是安全的。有互斥锁

### singleton 是否在一个应用里是唯一的？

不是，singleton 仅在当前 Spring IoC 容器(BeanFactory)中是单例对象。 而一个应用可能有多个应用上下文。

### "application" Bean 是否被其他方案代替。

可以的。实际上，"application" Bean 与 "singleton" Bean 没有本质区别。无非就是在某个地方存储了一下，实际上它对后面的对象引用也是同一个。

### BeanPostProcessor 的使用场景有哪些

- BeanPostProcessor 提供了 Spring Bean 初始化前 和 初始化后 的生命周期回调，分别对应 postProcessBeforeInitialization 以及
  postProcessAfterInitialization 方法，允许对关心的 Bean 进行扩展
- 其中， ApplicationContext 相关的 Aware 回调也是基于 BeanPostProcessor 实现，即 ApplicationContextAwareProcesspr。

### BeanFactoryPostProcessor 与 BeanPostProcessor 的区别

- BeanFactoryPostProcessor 是 Spring BeanFactory 的后置处理器，用于扩展 BeanFactory， 或通过 BeanFactory 进行依赖查找或注入。
- BeanFactoryPostProcessor 必须有 Spring ApplicationContext 执行，BeanFactory 无法直接与其交互。
- 而 BeanPostProcessor 则直接与 BeanFactory 关联， 属于 N 对 1 的关系

### BeanFactory 是怎么处理 Bean 生命周期的？

BeanFactory 的默认实现为 DefaultListableBeanFactory，其中 bean生命周期与方法映射如下：

- 1.注册bean Definition registerBeanDefinition()
- 2.bean Definition的合并阶段 getMergedLocalBeanDefinition(),比如user和superUser 最后都变为root bean Definition
- 3.创建bean createBean()
- 4.将bean类型从string变为class类型 resolveBeanClass()
- 5.bean实例化前工作resolveBeforeInstantiation(),比如可以返回自定义的bean对象让spring不在实例化bean对象
- 6.开始实例化bean doCreateBean()
- 7.实例化bean createBeanInstance()
- 8.bean实例化后 postProcessAfterInstantiation()返回false即bean不在对属性处理
- 9.属性赋值前对属性处理postProcessProperties()
- 10.属性赋值applyPropertyValues()
- 11.bean初始化阶段initializeBean()
- 12.初始化前aware接口回调(非ApplicationContextAware),比如beanFactoryAware
- 13.初始化前回调applyBeanPostProcessorsBeforeInitialization(),比如@PostConstructor
- 14.初始化invokeInitMethods(),比如实现InitializingBean接口的afterPropertiesSet()方法回调
- 15.初始化后的回调applyBeanPostProcessorsAfterInitialization()
- 16.bean重新的填充覆盖来更新bean preInstantiateSingletons()
- 17.bean销毁前postProcessBeforeDestruction()
- 18.bean销毁,比如@PreDestroy
