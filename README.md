# 校园互助学习平台

基于 Vue 3 + Spring Boot + MySQL 的校园问答与资源分享平台。

## 技术栈

| 层 | 技术 |
|---|---|
| 前端 | Vue 3 + Element Plus + Axios |
| 后端 | Spring Boot 2.7 + MyBatis |
| 数据库 | MySQL 8.0 |
| 接口文档 | Swagger（启动后端后自动生成） |

---

## 环境准备

| 工具 | 必须 | 说明 |
|---|---|---|
| Java JDK | ✅ | 21+（`java -version` 检查） |
| Maven | ✅ | 3.9+（`mvn -version` 检查） |
| MySQL | ✅ | 8.0+，root 密码设为 `123456` |
| Node.js | ✅ | 18+（`node -v` 检查） |
| Git | ✅ | 用于代码协作 |

> 如果没用过 Maven：下载 https://maven.apache.org → 解压到 `C:\maven` → 把 `C:\maven\bin` 加到系统 PATH 环境变量。

---

## 1. 克隆项目

```bash
git clone https://github.com/wsalad666/campus-qa.git
cd campus-qa
```

---

## 2. 初始化数据库

```bash
# 用 root 账号执行建库脚本
mysql -u root -p123456 < database/schema.sql
```

执行后会自动创建 `campus_qa` 数据库，包含 10 张表和 5 门初始课程。

> 如果 MySQL root 密码不是 `123456`，改完密码后再执行。**同时记得修改 `backend/src/main/resources/application.yml` 里的 `spring.datasource.password`。**

---

## 3. 启动后端

### 方式一：命令行（推荐，不需要 IDE）

```bash
cd backend
mvn spring-boot:run
```

首次运行 Maven 会自动下载依赖，需要几分钟。看到以下输出表示成功：

```
Started CampusQaApplication in X.XXX seconds
```

### 方式二：IntelliJ IDEA

1. 用 IDEA 打开 `backend` 文件夹
2. 等 Maven 自动下载依赖（右下角进度条）
3. 打开 `src/main/java/com/campusqa/CampusQaApplication.java`
4. 点击行号旁边的绿色三角 ▶ 运行

### 验证后端

浏览器打开 http://localhost:8080/swagger-ui/index.html，看到接口列表即为成功。

---

## 4. 启动前端

```bash
cd frontend
npm install
npm run dev
```

看到以下输出表示成功：

```
VITE v4.5.14  ready in XXX ms
➜  Local:   http://localhost:5173/
```

浏览器打开 http://localhost:5173 即可看到首页。

---

## 5. 接口文档

后端启动后，打开 http://localhost:8080/swagger-ui/index.html

直接在页面上点 "Try it out" 就能测试每个接口。

---

## 项目结构

```
campus-qa/
├── database/              # 数据库建表脚本
│   └── schema.sql
├── backend/               # Spring Boot 后端
│   ├── pom.xml
│   └── src/main/java/com/campusqa/
│       ├── entity/        # 数据实体类（10个）
│       ├── mapper/        # 数据库操作
│       ├── service/       # 业务逻辑
│       ├── controller/    # API 接口（4个模块）
│       ├── config/        # 配置类（跨域、安全、Swagger）
│       └── common/        # 工具类（JWT、统一返回格式）
├── frontend/              # Vue 3 前端
│   └── src/
│       ├── pages/         # 12 个页面组件
│       ├── router/        # 路由配置
│       ├── api/           # 接口请求方法
│       └── assets/        # 全局样式
└── docs/
    └── 分工说明.md        # 团队分工文档
```

---

## 团队分工

详见 [docs/分工说明.md](docs/分工说明.md)

| 角色 | 人数 | 职责 |
|---|---|---|
| 前端开发 | 1人 | 所有页面 |
| 后端开发 | 2人 | 所有接口 |
| 部署运维 | 1人 | 服务器 + 上线 |
| PPT汇报 | 1人 | PPT + 演示 |
| 数据+测试 | 2人 | 造数据 + 测接口 + 修Bug |

---

## 日常协作

```bash
git pull                    # 开工前拉取最新代码
# ... 写代码 ...
git add .                   # 标记修改
git commit -m "完成xxx功能"  # 存档
git push                    # 上传
```