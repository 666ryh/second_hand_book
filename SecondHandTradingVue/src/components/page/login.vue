<template>
  <div class="login-container">
    <el-card class="login-card">
      <div class="login-header">
        
        <h1 class="login-title">二手图书交易管理平台</h1>
        <p class="login-subtitle">让知识流动起来</p>
      </div>
      
      <el-form ref="form" :model="userForm" class="login-form">
        <div class="input-group">
          <el-input 
            placeholder="请输入用户账号" 
            v-model="userForm.accountNumber"
            prefix-icon="el-icon-user"
            class="login-input"
            maxlength="11"
          ></el-input>
        </div>
        
        <div class="input-group">
          <el-input 
            placeholder="请输入用户密码" 
            v-model="userForm.userPassword"
            prefix-icon="el-icon-lock"
            show-password
            @keyup.enter="login"
            class="login-input"
            maxlength="16"
          ></el-input>
        </div>
        
        <div class="login-submit">
          <el-button 
            type="primary" 
            class="login-btn"
            @click="login"
            :loading="loading"
          >登录</el-button>
        </div>
        
        <div class="auth-actions">
          <router-link to="/sign-in" class="action-link">注册账号</router-link>
          <router-link to="/login-admin" class="action-link">管理员入口</router-link>
        </div>
      </el-form>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "login",
  data() {
    return {
      loading: false,
      userForm: {
        accountNumber: '',
        userPassword: ''
      }
    };
  },
  methods: {
    login() {
      this.loading = true;
      this.$api.userLogin({
        accountNumber: this.userForm.accountNumber,
        userPassword: this.userForm.userPassword
      }).then(res => {
        if (res.status_code === 1) {
          res.data.signInTime = res.data.signInTime.substring(0,10);
          this.$globalData.userInfo = res.data;
          this.$router.replace({path: '/index'});
        } else {
          this.$message.error(res.msg);
          this.loading = false;
        }
      });
    }
  }
}
</script>

<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  width: 100%;
  background: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);
  background-size: cover;
  background-image: url("../../img/4.png");
 
}

.login-card {
  width: 90%;
  max-width: 420px;
  border-radius: 16px;
  box-shadow: 0 12px 24px rgba(0, 0, 0, 0.15);
  animation: fadeIn 0.8s ease-out;
  overflow: hidden;
  background: rgba(255, 255, 255, 0.95);
  border: none;
}

@keyframes fadeIn {
  from { opacity: 0; transform: translateY(20px); }
  to { opacity: 1; transform: translateY(0); }
}

.login-header {
  text-align: center;
  padding: 30px 0 20px;
  border-bottom: 1px solid #eee;
  margin-bottom: 30px;
}


.login-title {
  font-size: 30px;
  font-weight: 600;
  color: #1f2d3d;
  margin-bottom: 8px;
}

.login-subtitle {
  font-size: 14px;
  color: #8492a6;
}

.login-form {
  padding: 0 30px;
}

.input-group {
  margin-bottom: 22px;
}

.login-input {
  border-radius: 10px;
  transition: box-shadow 0.3s ease;
  height: 48px;
}

.login-input:hover {
  box-shadow: 0 4px 10px rgba(75, 108, 183, 0.2);
}

.login-btn {
  width: 100%;
  height: 48px;
  font-size: 18px;
  font-weight: 500;
  border-radius: 10px;
  background: linear-gradient(to right, #4b6cb7, #182848);
  border: none;
  box-shadow: 0 4px 12px rgba(75, 108, 183, 0.3);
  transition: all 0.3s ease;
  color: white;
}

.login-btn:hover {
  transform: translateY(-2px);
  box-shadow: 0 6px 14px rgba(75, 108, 183, 0.4);
}

.login-btn:active {
  transform: translateY(1px);
}

.auth-actions {
  display: flex;
  justify-content: space-between;
  margin-top: 25px;
  padding: 0 10px;
}

.action-link {
  color: #4b6cb7;
  text-decoration: none;
  font-weight: 500;
  transition: color 0.3s ease;
  font-size: 14px;
}

.action-link:hover {
  color: #182848;
  text-decoration: underline;
}

/* 响应式适配 */
@media (max-width: 768px) {
  .login-card {
    width: 95%;
    max-width: none;
  }
  
  .login-header {
    padding: 20px 0;
  }
  
  .login-title {
    font-size: 22px;
  }
}
</style>