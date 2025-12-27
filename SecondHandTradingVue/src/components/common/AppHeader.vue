<template>
    <div class="header">
        <div class="header-container">
            <div class="app-name">
                <router-link to="/">
                    <i class="el-icon-reading"></i>
                    二手图书交易管理系统
                </router-link>
            </div>
            <div class="app-name" >
                <router-link to="/" style="color: #808080;font-size: 20px;">
                    
                    首页
                </router-link>
            </div>
            <div class="search-container">
                <el-input
                    placeholder="搜索二手图书..."
                    v-model="searchValue"
                    @keyup.enter.native="searchIdle"
                    prefix-icon="el-icon-search"
                    clearable>
                    <el-button slot="append" icon="el-icon-search" @click="searchIdle">搜索</el-button>
                </el-input>
            </div>
            
            <div class="action-buttons">
                <!-- 修改后的“我的交易”按钮 -->
                <el-button 
                    type="success" 
                    icon="el-icon-tickets" 
                    class="trade-btn"
                    @click="handleTradeClick">
                    我的交易
                </el-button>
                
                <el-button
                    type="primary"
                    icon="el-icon-plus"
                    class="publish-btn"
                    @click="toRelease">
                    发布图书/求购
                </el-button>
                
                <el-button 
                    type="info" 
                    icon="el-icon-chat-dot-round" 
                    class="message-btn" 
                    @click="toMessage">
                    消息
                </el-button>
                
                <router-link 
                    v-if="!isLogin" 
                    class="user-name-text login-btn" 
                    to="/login">
                    <i class="el-icon-user"></i> 登录
                </router-link>
                
                <el-dropdown 
                    trigger="click" 
                    v-else 
                    class="user-dropdown">
                    <div class="user-info">
                        <div class="user-name">
                            {{nicknameValue ? nicknameValue : nickname}}
                        </div>
                        <el-avatar 
                            :size="32" 
                            :src="avatarValue ? avatarValue : avatar" 
                            class="user-avatar">
                        </el-avatar>
                    </div>
                    <el-dropdown-menu slot="dropdown" class="dropdown-menu">
                        <el-dropdown-item>
                            <div @click="toMe" class="dropdown-item">
                                <i class="el-icon-user-solid"></i> 个人中心
                            </div>
                        </el-dropdown-item>
                        <el-dropdown-item divided>
                            <div @click="loginOut" class="dropdown-item logout">
                                <i class="el-icon-switch-button"></i> 退出登录
                            </div>
                        </el-dropdown-item>
                    </el-dropdown-menu>
                </el-dropdown>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'Header',
    props: ['searchInput', 'nicknameValue', 'avatarValue'],
    data() {
        return {
            searchValue: this.searchInput,
            nickname: '登录',
            avatar: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png',
            isLogin: false
        };
    },
    created() {
        if (!this.$globalData.userInfo.nickname) {
            this.$api.getUserInfo().then(res => {
                if (res.status_code === 1) {
                    this.nickname = res.data.nickname;
                    this.avatar = res.data.avatar;
                    this.$globalData.userInfo = res.data;
                    this.isLogin = true;
                }
            });
        } else {
            this.nickname = this.$globalData.userInfo.nickname;
            this.avatar = this.$globalData.userInfo.avatar;
            this.isLogin = true;
        }
    },
    methods: {
        searchIdle() {
            if (this.searchValue && this.searchValue.trim() !== '') {
                if ('/search' !== this.$route.path) {
                    this.$router.push({
                        path: '/search', 
                        query: { searchValue: this.searchValue }
                    });
                } else {
                    this.$router.replace({
                        path: '/search', 
                        query: { searchValue: this.searchValue }
                    });
                    this.$router.go(0);
                }
            } else {
                this.$message.warning('请输入搜索关键词');
            }
        },
        
        toMe() {
            if ('/me' !== this.$route.path) {
                this.$router.push({ path: '/me' });
            }
        },
        
        toMessage() {
            if ('/message' !== this.$route.path) {
                this.$router.push({ path: '/message' });
            }
        },
        
        toRelease() {
            if ('/release' !== this.$route.path) {
                this.$router.push({ path: '/release' });
            }
        },
        
        // 统一处理交易按钮点击
        handleTradeClick() {
            if (!this.isLogin) {
                this.$router.push('/login');
                return;
            }
            this.$router.push('/jiaoyi');
        },
        
        loginOut() {
            this.$api.logout().then(res => {
                if (res.status_code === 1) {
                    this.$globalData.userInfo = {};
                    this.$message.success('退出登录成功');
                    if ('/index' === this.$route.path) {
                        this.$router.go(0);
                    } else {
                        this.$router.push({ path: '/index' });
                    }
                } else {
                    this.$message.error('网络或系统异常，退出登录失败！');
                }
            });
        }
    }
};
</script>

<style scoped>
.header {
    position: fixed;
    top: 0;
    left: 0;
    right: 0;
    width: 100%;
    height: 60px;
    background: #ffffff;
    display: flex;
    justify-content: center;
    border-bottom: 1px solid #ebeef5;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
    z-index: 1000;
}

.header-container {
    width: 1200px;
    max-width: 95%;
    height: 100%;
    display: flex;
    align-items: center;
    justify-content: space-between;
}

.app-name {
    flex-shrink: 0;
    margin-right: 15px;
}

.app-name a {
    color: #409EFF;
    font-size: 24px;
    font-weight: 600;
    text-decoration: none;
    display: flex;
    align-items: center;
    transition: all 0.3s;
}

.app-name a:hover {
    color: #66b1ff;
}

.app-name i {
    margin-right: 8px;
    font-size: 26px;
}

.search-container {
    flex-grow: 1;
    max-width: 500px;
    margin: 0 20px;
}

.action-buttons {
    display: flex;
    align-items: center;
    flex-shrink: 0;
}

/* 统一按钮间距 */
.action-buttons > * {
    margin-left: 8px !important;
}

.publish-btn, .message-btn {
    border-radius: 20px;
}

.trade-btn {
    border-radius: 20px;
    background-color: #67C23A;
    border-color: #67C23A;
    color: white;
    transition: all 0.3s ease;
    animation: pulse 2s infinite;
}

.trade-btn:hover {
    background-color: #85c82e;
    border-color: #85c82e;
}

/* 呼吸灯动画效果 */
@keyframes pulse {
    0% { transform: scale(1); }
    50% { transform: scale(1.05); }
    100% { transform: scale(1); }
}

.user-name-text {
    font-size: 16px;
    font-weight: 600;
    color: #409EFF;
    cursor: pointer;
    text-decoration: none;
    display: flex;
    align-items: center;
    transition: all 0.3s;
}

.user-name-text:hover {
    color: #66b1ff;
}

.login-btn i {
    margin-right: 5px;
}

.user-dropdown {
    cursor: pointer;
}

.user-info {
    display: flex;
    align-items: center;
    padding: 0 5px;
    border-radius: 20px;
    transition: all 0.3s;
}

.user-info:hover {
    background-color: #f5f7fa;
}

.user-name {
    font-size: 15px;
    color: #409EFF;
    margin-right: 8px;
}

.user-avatar {
    border: 2px solid #ebeef5;
}

.dropdown-menu {
    min-width: 130px;
}

.dropdown-item {
    display: flex;
    align-items: center;
    font-size: 14px;
}

.dropdown-item i {
    margin-right: 5px;
}

.logout {
    color: #f56c6c;
}

/* 响应式调整 */
@media (max-width: 992px) {
    .app-name a {
        font-size: 18px;
    }
    
    .search-container {
        max-width: 300px;
    }
    
    .publish-btn, .message-btn {
        padding: 9px 12px;
    }
    
    .trade-btn {
        padding: 9px 12px;
    }
}

@media (max-width: 768px) {
    .app-name a span {
        display: none;
    }
    
    .app-name i {
        margin-right: 0;
        font-size: 22px;
    }
    
    .search-container {
        max-width: 200px;
    }
    
    .publish-btn, .message-btn {
        padding: 7px 10px;
        font-size: 12px;
    }
    
    .trade-btn {
        padding: 7px 10px;
        font-size: 12px;
    }
    
    .user-name {
        font-size: 14px;
        margin-right: 5px;
    }
}
</style>