# MagicCube

供应链质量组测试工具平台----魔方

项目介绍
基于Restful风格的server端测试平台平台

# 软件架构

框架主体：

Spring Boot（即Spring + SpringMVC + MyBatis + Vue）

# 项目设计结构：

1.DAO层用于封装数据库操作，包括查询，修改，删除，数据同步等.

2.ENGINE层用于封装通用方法，包括安全，鉴权，工具，数据加密同步等操作.

3.FACADE层用于封装传输层与显示层数据，屏蔽掉ENGINE层对数据的复杂操作,提高安全性.

4.SERVICE层用于封装暴露给外界调用的服务接口，方便外界接入微服务.

5.WEB层用于封装资源展示逻辑控制方法，使前端数据展示更加美观得体.


#### Server端实现部分功能说明

1. 基于Jwt token认证机制，实现用户操作模块，主要有：用户登录，注册，认证等主要功能.
2. 基于mysql实现用户信息查询，修改，保存与读取图片（使用保存图片路径的方式）.
3. 集成redis 保存高频率查询修改数据，提升性能.
4. 接口测试、一键创建数据、接口mock、质量监控等.
5. 用户权限管理

# 安装教程
使用说明

 一. 项目执行
     
     git clone -b main https://github.com/Eillot/MagicCube.git

二.工具平台效果图如下:
   
   ![工具平台效果图](https://github.com/Eillot/MagicCube/blob/main/image/interface_test_tools.png)

   ![新增工具效果图](https://github.com/Eillot/MagicCube/blob/main/image/create_interface_test_01.png)
   ![新增工具效果图](https://github.com/Eillot/MagicCube/blob/main/image/create_interface_test_02.png)
   ![新增工具效果图](https://github.com/Eillot/MagicCube/blob/main/image/create_interface_test_03.png)
   ![新增工具效果图](https://github.com/Eillot/MagicCube/blob/main/image/create_interface_test_04.png)


参与贡献
Fork 本项目
新建 Feat_xxx 分支
提交代码
新建 Pull Request
Wiki编辑说明风格约定为Markdown风格


####


开源协议： Apache License-2.0


Licensed under the Apache License, Version 2.0 (the "License"); 
you may not use this file except in compliance with the License. You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0
 
Unless required by applicable law or agreed to in writing, software distributed under the License is distributed 
on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for 
the specific language governing permissions and limitations under the License.


# 请勿用商业用途，侵权必究.

####
