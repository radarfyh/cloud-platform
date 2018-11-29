# cloud-platform
1.6.10自制平台

自制平台：构建cloud-platform多模块项目用于搭建初始开发环境。
使用FileNewSpring Starter Project创建多模块项目，修改pom.xml，并删除多余的源文件:
 
接下来使用File-->New-->Spring Starter Project分别创建：

* 注册中心registration-center
* 网关gateway
* 配置中心config-center
* 认证中心authentication-center
* 认证中心monitoring-center
* 表盘hystrix-dashboard
* 示例服务account-service
* 单元测试示例rabbitmq-test

把这几个项目的目录移到cloud-platform目录下。

1.6.10.1环境Docker部署

参见“2.1.6.6 docker”和“2.1.6.7 docker compose”：

（1）准备Docker环境

（2）克隆完整代码
git clone https://github.com/radarfyh/cloud-platform.git

（3）设置环境变量
export CONFIG_SERVICE_PASSWORD=123456
export NOTIFICATION_SERVICE_PASSWORD=123456
export STATISTICS_SERVICE_PASSWORD=123456
export ACCOUNT_SERVICE_PASSWORD=123456
export MONGODB_PASSWORD=123456 ## 必填，其他变量可不设置

（4）部署
使用docker-compose运行如下命令（docker-compose.yml）（或查看通过脚本分别部署每个组件）：
docker-compose -f docker-compose.yml up –d

其中docker-compose.yml的内容如下：
（图片待上传）
 

a. mongodb的docker文件内容为：
 
其中，init.sh内容为：
（图片待上传）
 
其中，account-service-dump.js内容为：
（图片待上传）
 
b. config-center的docker文件为：
 （图片待上传）
 
c. registration-center的docker文件为：
 （图片待上传）
 
d. gateway的docker文件为：
 （图片待上传）
 
f. authentication-center的docker文件为：
 （图片待上传）
 
g.hystrix-dashboard的docker文件为：
 （图片待上传）
 
h.monitoring-center的docker文件为：
 （图片待上传）
 

（5）测试

访问路径：

http://DOCKER-HOST:80 - Gateway

http://DOCKER-HOST:8761 - Eureka Dashboard

http://DOCKER-HOST:9000/hystrix - Hystrix Dashboard

http://DOCKER-HOST:8989 - monitoring-center:Turbine stream,source for the Hystrix Dashboard

http://DOCKER-HOST:15672 - RabbitMq management 


（6）操作mongodb

docker ps查询当前容器:
8fb79f878c00 cloudplatform/authentication-mongodb "/init.sh" 9 minutes ago Up 9 minutes             27017/tcp authentication-mongodb_1


进入mongodb命令行：
docker exec -it 8fb79f878c00  bash进入mongodb


1.6.10.2环境Tomcat部署

参见“1.1 tomcat”。

在conf/server.xml的</Server>标签之前增加如下内容用于部署config-server：
  <Service name="configcenter">
    <Connector port="8888" protocol="HTTP/1.1"
               connectionTimeout="20000"
               redirectPort="8443" />

    <Connector port="8010" protocol="AJP/1.3" redirectPort="8443" />

    <Engine name="configcenter" defaultHost="localhost">

      <Realm className="org.apache.catalina.realm.LockOutRealm">
        <Realm className="org.apache.catalina.realm.UserDatabaseRealm"
               resourceName="UserDatabase"/>
      </Realm>

      <Host name="localhost"  appBase="configcenter"
            unpackWARs="true" autoDeploy="true">

        <Valve className="org.apache.catalina.valves.AccessLogValve" directory="logs"
               prefix="localhost_access_log" suffix=".txt"
               pattern="%h %l %u %t &quot;%r&quot; %s %b" />
		<Context path="" docBase="config-center" reloadable="true" />
      </Host>
    </Engine>
  </Service>


1.6.10.3构建配置中心

参见“1.6.7配置中心”：

（1）为了部署到外部tomcat，修改package为war：
 （图片待上传）
 
（2）为了部署到外部tomcat，修改应用类继承自SpringBootServletInitializer，并重载方法configure：
 （图片待上传）
 
（3）修改application.yml：
 （图片待上传）
 
其中，安全用户名自定为edison，密码为123456，这里写死了，后续需要修改为环境变量或者加密配置文件。配置搜索路径为相对路径/shared。服务器端口号为8888，上下文路径可以更改为任意值，此处为默认值。
（4）修改shared目录下的application.yml存放其他服务的公共配置：
 （图片待上传）
 
修改注册中心的配置registration.yml：
 （图片待上传）
 
修改网关的配置gateway.yml:
 （图片待上传）
 
修改认证中心的配置authentication.yml：
 （图片待上传）
 
修改仪表盘的配置hystrix-dashboard.yml:
server:
  port: 2001
  
修改监控中心的配置monitoring-center.yml:
 （图片待上传）
 
1.6.10.4构建注册中心

参见“1.6.5注册中心”：

（1）为了部署到外部tomcat，修改应用类：
 （图片待上传）
 
（2）修改pom.xml:
把<packaging>jar</packaging>改为<packaging>war</packaging>。

（3）删除application.properties，增加bootstrap.yml:
 （图片待上传）
 
（4）问题
1、java.lang.IllegalStateException: Illegal access: this web application instance has been stopped already. Could not load [org.apache.catalina.loader.WebappClassLoaderBase]. The following stack trace is thrown for debugging purposes as well as to attempt to
好像没有影响。


2、javax.management.InstanceAlreadyExistsException: 
org.springframework.cloud.context.environment:name=environmentManager,type=EnvironmentManager
好像没有影响。


3、org.springframework.jmx.export.UnableToRegisterMBeanException
部署配置中心和注册中心war到外部tomcat的时候报错，修改各自的bootstrap.yml文件中的jmx.default-domain值：
（图片待上传）

4、org.apache.catalina.startup.Catalina.start The required Server component failed to start so Tomcat is unable to start.
 org.apache.catalina.LifecycleException: A child container failed during start ……
ava.util.concurrent.ExecutionException: org.apache.catalina.LifecycleException: Failed to start component ……
似乎把注册中心和配置中心war部署在同一个tomcat进程中时报错。使用STS启动注册中心不会报错，但是在命令行使用mvnw spring-boot:run或者java -jar registration-center-0.0.1-SNAPSHOT.jar就会报错，部署到war也会报错。分开启动两个tomcat分别容纳注册中心和配置中心则问题解决。
分开启动两个tomcat的办法：

（a）复制tomcat文件夹如下：
 （图片待上传）
 
后两个目录分别用于注册中心和配置中心。
（b）新增两个环境变量

把这两个环境变量写入到path变量中：
 （图片待上传）
 
（c）修改各自的startup.bat和catalina.bat
对于注册中心，把CATALINA_HOME替换CATALINA_HOME_REG
对于配置中心，把CATALINA_HOME替换CATALINA_HOME_CONFIG

（d）修改conf\server.xml

分别修改以下三个端口不能让其重复：
<Server port="8005" shutdown="SHUTDOWN">
<Connector port="8081" protocol="HTTP/1.1" connectionTimeout="20000"  redirectPort="8443" />
<Connector port="8009" protocol="AJP/1.3" redirectPort="8443" />
	
注册中心：再在</server>标签前面增加：
 （图片待上传）
 
配置中心：再在</server>标签前面增加：
 （图片待上传）
 
最后，分别执行startup.bat。

5、Missing artifact org.springframework.cloud:spring-cloud-starter-config:jar:Finchley.SR2
（图片待上传）

6、Endpoint ID 'service-registry' contains invalid characters, please migrate to a valid format
（图片待上传）

1.6.10.5构建监控中心和仪表盘

一、参见“1.6.9熔断器”，把1.6.9.1的两个实例导入进来：

（1）修改端口分别为8090和8091
断路器为8091，断路器监控的服务为8090。

（2）两个服务均增加actuator的支持

（3）8091工程的application.properties增加：
management.endpoints.web.exposure.include = hystrix.stream

（4）测试：
 
如果不断显示”loading…”，则多刷新如下地址几次：
http://localhost:8091/to-read


二、构建hystrix-dashboard项目

（1）依赖：
 （图片待上传）
 
（2）应用类：
 （图片待上传）
 
（3）bootstrap.yml:
 （图片待上传）
 

（4）运行和测试
在命令行进入工程根目录，输入:
mvnw spring-boot:run

在Chrome中输入：
http://localhost:2001/hystrix

界面为：
 （图片待上传）
 
输入http://localhost:8091/actuator/hystrix.stream，点Monitor Stream，显示如下：
 （图片待上传）


三、构建监控中心项目

（1）依赖：
 （图片待上传）
 
（2）应用类：
 （图片待上传）
 
（3）bootstrap.yml:
 （图片待上传）
 

问题：
1、turbine stream java.lang.IllegalStateException: Failed to introspect Class [org.springframework.cloud.stream.config.BindingServiceConfiguration] from ClassLoader [sun.misc.Launcher$AppClassLoader@764c12b6]

很久没有搞定，网上信息很少。
最后解决了但是也说不太清楚怎么解决的。
解决方向是各个版本不匹配，尽量使用一致的版本或者稳定的release版本。
有一个诀窍，在父工程指定版本。


2、Caused by: com.rabbitmq.client.AuthenticationFailureException: ACCESS_REFUSED -Login was refused using authentication mechanism PLAIN.
（图片待上传）

