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

> Maven 安装：下载 https://maven.apache.org → 解压到 `C:\maven` → 把 `C:\maven\bin` 加到系统 PATH。

---

## 1. 克隆项目

```bash
git clone https://github.com/wsalad666/campus-qa.git
cd campus-qa
```

---

## 2. 初始化数据库

```bash
mysql -u root -p123456 < database/schema.sql
```

执行后自动创建 `campus_qa` 数据库，包含 10 张表和 5 门初始课程。

> 密码不是 `123456` 的话，同时修改 `backend/src/main/resources/application.yml` 里的 `spring.datasource.password`。

---

## 3. 启动后端

### 方式一：命令行（不需要 IDE）

```bash
cd backend
mvn spring-boot:run
```

首次运行 Maven 会自动下载依赖，需要几分钟。

### 方式二：IntelliJ IDEA

1. 用 IDEA 打开 `backend` 文件夹
2. 等 Maven 自动下载依赖
3. 打开 `CampusQaApplication.java`
4. 点绿色三角 ▶ 运行

### 验证后端

看到 `Started CampusQaApplication` 即为成功。

然后打开浏览器访问：

> http://localhost:8080/swagger-ui/index.html

看到接口列表就说明后端完全正常。

> ⚠️ **注意：** `mvn spring-boot:run` 和 `npm run dev` 启动后会占用当前终端窗口。**不要关掉这个窗口**，否则服务就停了。可以另外开一个终端干别的事情。

---

## 4. 启动前端

```bash
cd frontend
npm install
npm run dev
```

看到 `VITE v4.x.x ready` 即为成功。

打开浏览器访问：

> http://localhost:5173

---

## 5. 接口文档

后端启动后，打开 http://localhost:8080/swagger-ui/index.html

点 "Try it out" 就能直接在网页上测试每个接口。

---

## 项目结构

```
campus-qa/
├── database/              # 建库脚本
├── backend/               # Spring Boot
│   └── src/main/java/com/campusqa/
│       ├── entity/        # 实体类
│       ├── mapper/        # 数据库操作
│       ├── service/       # 业务逻辑
│       ├── controller/    # API 接口
│       ├── config/        # 配置
│       └── common/        # 工具类
├── frontend/              # Vue 3
│   └── src/
│       ├── pages/         # 页面组件
│       ├── router/        # 路由
│       ├── api/           # 请求方法
│       └── assets/        # 样式
└── docs/
    └── 分工说明.md
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
git add .                   # 标记修改
git commit -m "做了什么"     # 存档
git push                    # 上传
```