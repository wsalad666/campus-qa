# 校园互助学习平台

基于 Vue 3 + Spring Boot + MySQL 的校园问答与资源分享平台。

## 技术栈

| 层 | 技术 |
|---|---|
| 前端 | Vue 3 + Element Plus + Axios |
| 后端 | Spring Boot 2.7 + MyBatis |
| 数据库 | MySQL 8.0 |
| 接口文档 | Swagger (Springfox 3.0) |

## 快速上手

### 1. 克隆项目

```bash
git clone https://github.com/wsalad666/campus-qa.git
cd campus-qa
```

### 2. 数据库

- 安装 MySQL 8.0+，root 密码设为 `123456`
- 执行建库脚本：

```bash
mysql -u root -p123456 < database/schema.sql
```

### 3. 启动后端

- 用 IntelliJ IDEA 打开 `backend` 文件夹
- 等待 Maven 自动下载依赖
- 运行 `CampusQaApplication.java`
- 访问 http://localhost:8080/swagger-ui/index.html 确认启动成功

> 如果数据库密码不是 `123456`，修改 `backend/src/main/resources/application.yml` 中的配置。

### 4. 启动前端

```bash
cd frontend
npm install
npm run dev
```

访问 http://localhost:5173

## 项目结构

```
campus-qa/
├── database/          # 数据库建表脚本
├── backend/           # Spring Boot 后端
│   └── src/main/java/com/campusqa/
│       ├── entity/    # 数据实体类
│       ├── mapper/    # 数据库操作
│       ├── service/   # 业务逻辑
│       ├── controller/# API接口
│       ├── config/    # 配置类
│       └── common/    # 工具类
├── frontend/          # Vue 3 前端
│   └── src/
│       ├── pages/     # 页面组件
│       ├── router/    # 路由配置
│       ├── api/       # 接口请求
│       └── assets/    # 静态资源
└── docs/              # 文档
```

## 团队分工

详见 [docs/分工说明.md](docs/分工说明.md)

| 角色 | 人数 | 职责 |
|---|---|---|
| 前端开发 | 1人 | 所有页面 |
| 后端开发 | 2人 | 所有接口 |
| 部署运维 | 1人 | 服务器 + 上线 |
| PPT汇报 | 1人 | PPT + 演示 |
| 数据+测试 | 2人 | 造数据 + 测接口 + 修Bug |

## 日常协作

```bash
git pull                    # 开工前拉取最新代码
# ... 写代码 ...
git add .                   # 标记修改
git commit -m "完成xxx功能"  # 存档
git push                    # 上传
```