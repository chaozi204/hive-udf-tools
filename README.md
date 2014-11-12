hive-udf-hook
=============
  
## UDF开发及发布过程
  1 用户编写UDF实现类
  2 编写完成后，在UDFHooks类中调用相关注册函数：
     调用 FunctionRegistry.registerUDF 注册udf
     调用 FunctionRegistry.registerUDAF 注册udaf
     调用FunctionRegistry.registerUDTF  注册udtf
  3 打包
      mvn clean package
  4 上传相应jar包到$HIVE_HOME/auxlib目录下

## 让hive自动加载udf包（该过程只需要一次执行，目前适应于hive-0.13.1环境）
  1 如果hive-site.xml文件没有配置如下属性，则需要添加该属性，如果已经存在则省略此过程
  <property>
    <name>hive.exec.driver.run.hooks</name>
    <value>com.chaozi.hive.UDFHooks</value>
  </property>
 
  2 创建$HIVE_HOME/auxlib路径，并将自定义udf jar放到该路径下

  3 测试
    调用hive -e "show functions" 命令，查看是否自定义的udf已经成功安装

 后期如果想要不手动再UDFHooks中注册udf，可以在实现udf的时候，使用@Description注解，并在UDFHooks中获取类的注解信息，同时完成注册功能（有待以后开发）
