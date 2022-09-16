1.初始化mysql数据 (登陆数据库后，可以执行source d:/mall.sql)
2.在配置文件中修改连接mysql的配置(例如账号、密码)
3.安装window版本的redis(参考note/redis.txt)
1)下载并解压
2)切换到redis根目录
3)安装redis服务(redis-server --service-install redis.windows.conf)
4)启动redis服务(redis-server --service-start)

4.修改配置文件(application.yml)中默认的redis配置(假如和我的一样就不需要配置了)
spring:
  redis:
    host: localhost
    port: 6379
    password:
5.启动服务进行访问测试