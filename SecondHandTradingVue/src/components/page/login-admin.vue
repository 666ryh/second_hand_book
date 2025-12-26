<template>
  <div class="login-container">
    <el-card class="login-card">
      <div class="login-content">
        <div class="login-header">
         
          <h2 class="login-title">二手图书交易管理系统</h2>
          <p class="login-subtitle">安全高效的管理平台</p>
        </div>
        
        <el-form ref="form" :model="userForm" class="login-form">
          <div class="input-group">
            <el-input 
              placeholder="请输入管理员账号" 
              v-model="userForm.accountNumber"
              prefix-icon="el-icon-user"
              class="login-input"
            ></el-input>
          </div>
          
          <div class="input-group">
            <el-input 
              placeholder="请输入管理员密码" 
              v-model="userForm.adminPassword"
              prefix-icon="el-icon-lock"
              show-password
              @keyup.enter="login"
              class="login-input"
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
          
          <div class="other-actions">
            <router-link to="/login" class="action-link">用户登录入口</router-link>
          </div>
        </el-form>
      </div>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "login",
  data() {
    return {
      userForm: {
        accountNumber: '',
        adminPassword: ''
      },
      loading: false
    };
  },
  methods: {
    login() {
      this.loading = true;
      this.$api.adminLogin({
        accountNumber: this.userForm.accountNumber,
        adminPassword: this.userForm.adminPassword
      }).then(res => {
        if (res.status_code === 1) {
          this.$router.replace({path:'/platform-admin'});
        } else {
          this.$message.error('登录失败，账号或密码错误！');
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
  background: linear-gradient(135deg, #6a11cb 0%, #2575fc 100%);
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
}

@keyframes fadeIn {
  from { opacity: 0; transform: translateY(20px); }
  to { opacity: 1; transform: translateY(0); }
}

.login-content {
  padding: 40px 35px;
}

.login-header {
  text-align: center;
  margin-bottom: 35px;
}

.logo-icon {
  font-size: 48px;
  margin-bottom: 15px;
  color: #2575fc;
}

.login-title {
  font-size: 24px;
  font-weight: 600;
  color: #1f2d3d;
  margin-bottom: 8px;
}

.login-subtitle {
  font-size: 14px;
  color: #8492a6;
}

.login-form {
  margin-top: 10px;
}

.input-group {
  margin-bottom: 22px;
}

.login-input {
  border-radius: 10px;
  transition: box-shadow 0.3s ease;
}

.login-input:hover {
  box-shadow: 0 4px 10px rgba(37, 117, 252, 0.2);
}

.login-btn {
  width: 100%;
  height: 46px;
  font-size: 18px;
  font-weight: 500;
  border-radius: 10px;
  background: linear-gradient(to right, #6a11cb, #2575fc);
  border: none;
  box-shadow: 0 4px 12px rgba(37, 117, 252, 0.3);
  transition: all 0.3s ease;
}

.login-btn:hover {
  transform: translateY(-2px);
  box-shadow: 0 6px 14px rgba(37, 117, 252, 0.4);
}

.login-btn:active {
  transform: translateY(1px);
}

.other-actions {
  margin-top: 25px;
  text-align: center;
}

.action-link {
  color: #2575fc;
  text-decoration: none;
  font-weight: 500;
  transition: color 0.3s ease;
}

.action-link:hover {
  color: #6a11cb;
  text-decoration: underline;
}

/* 响应式适配 */
@media (max-width: 768px) {
  .login-card {
    width: 95%;
    max-width: none;
  }
  
  .login-content {
    padding: 30px 20px;
  }
  
  .login-title {
    font-size: 22px;
  }
}
</style>