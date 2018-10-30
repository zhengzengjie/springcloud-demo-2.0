# springcloud-demo-2.0
#### eureka-server服务注册发现中心
#### user-register用户注册（模拟）
#### user-valid用户名注册验证（模拟）
#### email-server发送注册邮件（模拟）
#### zuul-server网关

1. 先启动zipkin-server-2.10.1-exec.jar，端口号9411，可浏览器访问：localhost:9411
2. 上面项目依次启动
3. 访问user-register项目,浏览器输入localhost:8088/user/register?name=user_jason
4. user-register会先调用user-valid，成功再调用email-server，最后返回
5. 访问 user-register(第3步url) 10次以上，然后刷新localhost:9411，点击页面中的"Find Traces"按钮，就可以看到链路了
