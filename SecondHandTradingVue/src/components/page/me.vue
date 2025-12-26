<template>
  <div>
    <app-head :nickname-value="userInfo.nickname" :avatar-value="userInfo.avatar"></app-head>
    <app-body>
      <div class="profile-container">
        <el-card class="user-profile-card" shadow="hover">
          <div class="user-info-container">
            <div class="user-info-details">
              <el-upload
                action="http://localhost:8080/file/"
                :on-success="fileHandleSuccess"
                :file-list="imgFileList"
                :show-file-list="false"
                accept="image/*"
                class="avatar-uploader"
              >
                <el-avatar :size="120" :src="userInfo.avatar" class="user-avatar">
                  <i class="el-icon-plus avatar-uploader-icon"></i>
                </el-avatar>
                <div class="avatar-hint">点击更换头像</div>
              </el-upload>
              <div class="user-info-details-text">
                <div class="user-info-details-text-nickname">{{ userInfo.nickname }}</div>
                <div class="user-info-details-text-time">
                  <i class="el-icon-time"></i> {{ userInfo.signInTime }} 加入平台
                </div>
                <div class="user-info-details-text-edit">
                  <el-button type="primary" icon="el-icon-edit" round @click="userInfoDialogVisible = true">
                    编辑个人信息
                  </el-button>
                </div>
              </div>
            </div>
          </div>
 
          <el-dialog
            @close="finishEdit"
            title="编辑个人信息"
            :visible.sync="userInfoDialogVisible"
            width="400px"
            center
          >
            <div class="edit-form">
              <div class="edit-form-item">
                <div class="edit-tip">昵称</div>
                <el-input
                  v-model="userInfo.nickname"
                  :disabled="notUserNicknameEdit"
                  placeholder="请输入昵称"
                  prefix-icon="el-icon-user"
                  @change="saveUserNickname"
                >
                  <el-button slot="append" type="warning" icon="el-icon-edit" @click="notUserNicknameEdit = false">
                    编辑
                  </el-button>
                </el-input>
              </div>
 
              <div class="edit-form-item" v-if="userPasswordEdit">
                <div class="edit-tip">原密码</div>
                <el-input v-model="userPassword1" placeholder="请输入原密码" prefix-icon="el-icon-lock" show-password></el-input>
                <div class="edit-tip">新密码</div>
                <el-input v-model="userPassword2" placeholder="请输入新密码" prefix-icon="el-icon-lock" show-password></el-input>
                <div class="edit-tip">确认新密码</div>
                <el-input v-model="userPassword3" placeholder="请再次输入新密码" prefix-icon="el-icon-lock" show-password></el-input>
                <div class="edit-actions">
                  <el-button type="primary" icon="el-icon-check" @click="savePassword">确认修改</el-button>
                  <el-button icon="el-icon-close" @click="userPasswordEdit = false">取消</el-button>
                </div>
              </div>
              <div class="edit-form-item" v-else>
                <div class="edit-tip">密码</div>
                <el-input
                  value="******"
                  :disabled="true"
                  prefix-icon="el-icon-lock"
                  show-password
                >
                  <el-button slot="append" type="warning" icon="el-icon-edit" @click="userPasswordEdit = true">
                    修改密码
                  </el-button>
                </el-input>
              </div>
            </div>
            <span slot="footer" class="dialog-footer">
              <el-button type="primary" @click="userInfoDialogVisible = false">完成</el-button>
            </span>
          </el-dialog>
        </el-card>
      </div>
      <app-foot></app-foot>
    </app-body>
  </div>
</template>
 
<script>
import AppHead from '../common/AppHeader.vue';
import AppBody from '../common/AppPageBody.vue';
import AppFoot from '../common/AppFoot.vue';
 
export default {
  name: "me",
  components: {
    AppHead,
    AppBody,
    AppFoot
  },
  data() {
    return {
      imgFileList: [],
      userInfoDialogVisible: false,
      notUserNicknameEdit: true,
      userPasswordEdit: false,
      userPassword1: '',
      userPassword2: '',
      userPassword3: '',
      userInfo: {
        accountNumber: "",
        avatar: "",
        nickname: "",
        signInTime: "",
      }
    };
  },
  created() {
    if (!this.$globalData.userInfo.nickname) {
      this.$api.getUserInfo().then(res => {
        if (res.status_code === 1) {
          res.data.signInTime = res.data.signInTime.substring(0, 10);
          this.$globalData.userInfo = res.data;
          this.userInfo = this.$globalData.userInfo;
        }
      })
    } else {
      this.userInfo = this.$globalData.userInfo;
    }
  },
  methods: {
    saveUserNickname() {
      this.notUserNicknameEdit = true;
      this.$api.updateUserPublicInfo({
        nickname: this.userInfo.nickname
      }).then(res => {
        this.$globalData.userInfo.nickname = this.userInfo.nickname;
      })
    },
    savePassword() {
      if (!this.userPassword1 || !this.userPassword2) {
        this.$message.error('密码为空！');
      } else if (this.userPassword2 === this.userPassword3) {
        this.$api.updatePassword({
          oldPassword: this.userPassword1,
          newPassword: this.userPassword2
        }).then(res => {
          if (res.status_code === 1) {
            this.userPasswordEdit = false;
            this.$message.success('修改成功！');
            this.userPassword1 = '';
            this.userPassword2 = '';
            this.userPassword3 = '';
          } else {
            this.$message.error('旧密码错误，修改失败！');
          }
        })
      } else {
        this.$message.error('两次输入的密码不一致！');
      }
    },
    finishEdit() {
      this.notUserNicknameEdit = true;
      this.userInfoDialogVisible = false;
      this.userPasswordEdit = false;
    },
    fileHandleSuccess(response, file, fileList) {
      let imgUrl = response.data;
      this.imgFileList = [];
      this.$api.updateUserPublicInfo({
        avatar: imgUrl
      }).then(res => {
        this.userInfo.avatar = imgUrl;
        this.$globalData.userInfo.avatar = imgUrl;
      })
    }
  }
}
</script>

<style scoped>
.profile-container {
  padding: 20px;
}

.user-profile-card {
  margin-bottom: 20px;
  border-radius: 8px;
  overflow: hidden;
}

.user-info-container {
  width: 100%;
  padding: 20px 0;
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.user-info-details {
  display: flex;
  align-items: center;
  width: 100%;
}

.avatar-uploader {
  position: relative;
  cursor: pointer;
  transition: all 0.3s;
}

.avatar-uploader:hover {
  transform: scale(1.05);
}

.user-avatar {
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  border: 2px solid #fff;
}

.avatar-hint {
  position: absolute;
  bottom: -5px;
  left: 50%;
  transform: translateX(-50%);
  font-size: 12px;
  color: #909399;
  opacity: 0;
  transition: opacity 0.3s;
}

.avatar-uploader:hover .avatar-hint {
  opacity: 1;
}

.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 120px;
  height: 120px;
  line-height: 120px;
  text-align: center;
}

.user-info-details-text {
  margin-left: 30px;
  width: calc(100% - 150px);
}

.user-info-details-text-nickname {
  font-size: 26px;
  font-weight: 600;
  margin: 10px 0;
  color: #303133;
}

.user-info-details-text-time {
  font-size: 14px;
  margin-bottom: 16px;
  color: #909399;
  display: flex;
  align-items: center;
}

.user-info-details-text-time i {
  margin-right: 5px;
}

.user-info-details-text-edit {
  display: flex;
  gap: 10px;
}

.edit-form {
  padding: 10px;
}

.edit-form-item {
  margin-bottom: 20px;
}

.edit-tip {
  font-size: 14px;
  margin: 10px 5px;
  color: #606266;
}

.edit-actions {
  margin-top: 15px;
  display: flex;
  justify-content: flex-end;
  gap: 10px;
}

@media (max-width: 768px) {
  .user-info-details {
    flex-direction: column;
    align-items: center;
    text-align: center;
  }
  
  .user-info-details-text {
    margin-left: 0;
    margin-top: 20px;
    width: 100%;
  }
  
  .user-info-details-text-edit {
    justify-content: center;
  }
}
</style>