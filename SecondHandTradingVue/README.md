# 二手图书交易管理系统

## 项目介绍

这是一个基于 **Spring Boot + Vue.js** 开发的二手图书交易管理系统，专为校园环境设计，旨在为学生提供一个便捷的二手图书交易平台。

### 主要功能

- 📚 **图书发布**：用户可以发布二手图书信息，包括图书名称、价格、成色、分类等
- 🔍 **图书搜索**：支持按关键词搜索图书
- 📑 **分类浏览**：按教材教辅、文学小说、专业书籍、考试考研等分类浏览
- 💬 **留言互动**：买家和卖家可以通过留言板进行沟通
- 🛒 **购物车功能**：支持收藏心仪的图书
- 💰 **在线交易**：支持支付宝沙箱支付
- 📍 **地址管理**：管理收货地址信息
- 👤 **用户中心**：个人信息管理、我的发布、我的订单等
- 🔔 **求购信息**：发布图书求购需求

### 图书分类

1. **教材教辅** - 各类教材和辅导书籍
2. **文学小说** - 文学作品、小说类图书
3. **专业书籍** - 专业技术类图书
4. **考试考研** - 考试复习、考研资料
5. **求购信息** - 发布图书求购需求

## 技术栈

### 后端技术
- Spring Boot 2.4.0
- MyBatis
- MySQL 8.0
- 支付宝SDK

### 前端技术
- Vue.js 2.x
- Element UI
- Axios
- Vue Router

## 安装教程

### 环境要求
- JDK 1.8+
- MySQL 8.0+
- Node.js 12+
- Maven 3.6+

### 后端部署

1. 导入数据库
```sql
-- 在MySQL中创建数据库并导入SQL文件
source SecondHandTradingApi/src/main/resources/second_hand_trading.sql
```

2. 修改数据库配置
```properties
# 编辑 SecondHandTradingApi/src/main/resources/application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/second_hand_trading
spring.datasource.username=your_username
spring.datasource.password=your_password
```

3. 启动后端服务
```bash
cd SecondHandTradingApi
mvn clean install
mvn spring-boot:run
```

### 前端部署

1. 安装依赖
```bash
cd SecondHandTradingVue
npm install
```

2. 启动开发服务器
```bash
npm run serve
```

3. 构建生产版本
```bash
npm run build
```

## 使用说明

### 用户端
1. 注册/登录账号
2. 浏览图书列表或搜索图书
3. 查看图书详情，与卖家留言沟通
4. 加入购物车或直接购买
5. 填写收货地址，完成支付
6. 在个人中心管理订单和发布的图书

### 管理员端
- 访问 `/login-admin` 进入管理员登录页
- 默认账号：admin / 123456
- 管理用户、图书、订单等信息

## 项目结构

```
├── SecondHandTradingApi/          # 后端项目
│   ├── src/main/java/             # Java源代码
│   │   └── com/second/hand/trading/server/
│   │       ├── model/             # 数据模型
│   │       ├── service/           # 业务逻辑
│   │       └── utils/             # 工具类
│   └── src/main/resources/        # 资源文件
│       ├── mapper/                # MyBatis映射文件
│       └── second_hand_trading.sql # 数据库脚本
│
└── SecondHandTradingVue/          # 前端项目
    ├── public/                    # 静态资源
    └── src/
        ├── components/            # Vue组件
        │   ├── common/           # 公共组件
        │   └── page/             # 页面组件
        ├── router/               # 路由配置
        └── api/                  # API接口
```

## 系统截图

（可添加系统截图）

## 注意事项

1. 首次运行需要导入数据库文件
2. 支付功能使用支付宝沙箱环境，需要配置支付宝开发者账号
3. 图片上传路径默认为 `SecondHandTradingApi/xmtp/`
4. 建议在局域网环境下使用，或修改API地址配置

## 开发者

本项目为毕业设计项目，欢迎提出建议和改进意见。

## 许可证

MIT License
