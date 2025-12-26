<template>
  <div class="sign-in-container">
    <el-card class="sign-in-card">
      <div class="sign-in-content">
        <div class="sign-in-header">
         
          <h2 class="sign-in-title">用户注册</h2>
          <p class="sign-in-subtitle">创建您的专属账号，开启校园二手交易之旅</p>
        </div>
        
        <el-form ref="form" :model="userInfo" class="sign-in-form">
          <div class="input-group">
            <el-input 
              placeholder="请输入昵称" 
              maxlength="30"  
              v-model="userInfo.nickname"
              prefix-icon="el-icon-user"
              class="sign-in-input"
              clearable
            ></el-input>
          </div>
          
          <div class="input-group">
            <el-input 
              placeholder="请输入手机号" 
              maxlength="11" 
              v-model="userInfo.accountNumber"
              prefix-icon="el-icon-phone"
              class="sign-in-input"
              clearable
            ></el-input>
          </div>
          
          <div class="input-group">
            <el-input 
              placeholder="请输入密码" 
              show-password 
              maxlength="16" 
              v-model="userInfo.userPassword"
              prefix-icon="el-icon-lock"
              class="sign-in-input"
              clearable
            ></el-input>
          </div>
          
          <div class="input-group">
            <el-input 
              placeholder="请再次输入密码" 
              show-password 
              maxlength="16" 
              v-model="userPassword2"
              prefix-icon="el-icon-lock"
              class="sign-in-input"
              clearable
              @keyup.enter="signIn"
            ></el-input>
          </div>
          
          <div class="sign-in-submit">
            <el-button 
              type="primary" 
              class="sign-in-btn"
              @click="signIn"
              :loading="loading"
            >提交注册</el-button>
          </div>
          
          <div class="login-link">
            <span @click="toLogin" class="login-text">已有账号？立即登录</span>
          </div>
        </el-form>
      </div>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "sign-in",
  data() {
    return {
      loading: false,
      userPassword2: '',
      userInfo: {
        accountNumber: '',
        userPassword: '',
        nickname: ''
      }
    };
  },
  methods: {
    toLogin() {
      this.$router.replace({path: '/login'});
    },
    signIn() {
      this.loading = true;
      if (this.userInfo.accountNumber && 
          this.userInfo.userPassword && 
          this.userInfo.nickname) {
        
        if (this.userInfo.accountNumber.length !== 11) {
          this.$message.error('手机号长度不符合要求!');
          this.loading = false;
        } else if (this.userInfo.userPassword !== this.userPassword2) {
          this.$message.error('两次输入的密码不相同！');
          this.loading = false;
        } else {
          this.$api.signIn(this.userInfo).then(res => {
            if (res.status_code === 1) {
              this.$message({
                message: '注册成功！',
                type: 'success'
              });
              this.$router.replace({path: '/login'});
            } else {
              this.$message.error('注册失败，用户已存在！');
              this.loading = false;
            }
          }).catch(e => {
            this.$message.error('注册失败，网络异常！');
            this.loading = false;
          });
        }
      } else {
        this.$message.error('注册信息未填写完整！');
        this.loading = false;
      }
    }
  }
}
</script>

<style scoped>
.sign-in-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  width: 100%;
  background: linear-gradient(135deg, #ff9a9e 0%, #fecfef 100%);
  background-size: cover;
  background-attachment: fixed;
  background-image: url("../../img/4.png");
  background-size: 100% 100%;
}

.sign-in-card {
  width: 90%;
  max-width: 450px;
  border-radius: 16px;
  box-shadow: 0 12px 24px rgba(0, 0, 0, 0.15);
  animation: fadeIn 0.8s ease-out;
  overflow: hidden;
  background: rgba(255, 255, 255, 0.95);
}

@keyframes fadeIn {
  from { opacity: 0; transform: translateY(20px); }
  to { opacity: 1; transform: translateY(0); }
}

.sign-in-content {
  padding: 40px 35px;
}

.sign-in-header {
  text-align: center;
  margin-bottom: 30px;
}

.sign-in-icon {
  font-size: 48px;
  margin-bottom: 15px;
  color: #ff6b81;
}

.sign-in-title {
  font-size: 24px;
  font-weight: 600;
  color: #1f2d3d;
  margin-bottom: 8px;
}

.sign-in-subtitle {
  font-size: 14px;
  color: #8492a6;
  line-height: 1.5;
}

.sign-in-form {
  margin-top: 10px;
}

.input-group {
  margin-bottom: 22px;
}

.sign-in-input {
  border-radius: 10px;
  transition: box-shadow 0.3s ease;
}

.sign-in-input:hover {
  box-shadow: 0 4px 10px rgba(255, 107, 129, 0.2);
}

.sign-in-btn {
  width: 100%;
  height: 46px;
  font-size: 18px;
  font-weight: 500;
  border-radius: 10px;
  background: linear-gradient(to right, #ff6b81, #ff8e53);
  border: none;
  box-shadow: 0 4px 12px rgba(255, 107, 129, 0.3);
  transition: all 0.3s ease;
  color: white;
}

.sign-in-btn:hover {
  transform: translateY(-2px);
  box-shadow: 0 6px 14px rgba(255, 107, 129, 0.4);
}

.sign-in-btn:active {
  transform: translateY(1px);
}

.login-link {
  margin-top: 25px;
  text-align: center;
}

.login-text {
  color: #ff6b81;
  font-weight: 500;
  cursor: pointer;
  transition: color 0.3s ease;
  font-size: 16px;
}

.login-text:hover {
  color: #ff8e53;
  text-decoration: underline;
}

/* 响应式适配 */
@media (max-width: 768px) {
  .sign-in-card {
    width: 95%;
    max-width: none;
  }
  
  .sign-in-content {
    padding: 30px 20px;
  }
  
  .sign-in-title {
    font-size: 22px;
  }
}
</style>