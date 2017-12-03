# SpringMVC增删改查Demo
本项目是所用技术 <br/>
后端使用 Spring+SpringMVC+Spring Data JPA <br/>
前端使用 BootStrap，jQuery，ajaxfileupload.js插件，jquery-confirm.min.js插件 <br/>


## 效果
预览地址：[http://demo.liuyanzhao.com/SpringMVC_CURD/admin/user/list](http://demo.liuyanzhao.com/SpringMVC_CURD/admin/user/list)  <br/>

![](https://liuyanzhao.com/wp-content/uploads/2017/12/curd.jpg)


#### 版本问题
1、 jdk 1.8 必须要1.8以上  <br/>
2、 Maven 3.3 尽量使用3版本  <br/>
3、 Tomcat7.0 这个没要求  <br/>
4、 MySQL 无要求 <br/>
5、 开发工具，无要求 <br/>


#### 部署步骤
1、新建数据库springmvc_curd，然后导入上面给的数据库，就一张user表 <br/>
2、下载项目源码(pom.xml和src目录)，使用 Eclipse 或者 IDEA 导入项目（博主使用的是IDEA） <br/>
3、修改jdbc.properties里的数据库连接信息，使数据库连接正确<br/>
2、修改UploadFileController的第24行，即修改上传目录(如果不改的话，可能无法上传文件)<br/>
3、下载 uploads 目录，配置静态资源映射，通过服务器访问本地文件（如果不改的话，可能无法实时预览图片）<br/>


#### 最后
帮忙点下star或者fork啊，准备把给个模块写好，到时候做一个新的博客系统
