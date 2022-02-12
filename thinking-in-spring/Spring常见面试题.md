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