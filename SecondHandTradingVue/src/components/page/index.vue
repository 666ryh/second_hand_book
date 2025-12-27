<template>
    <div>
        <app-head></app-head>
        <app-body>
            <div style="min-height: 85vh;">
                <!-- 页面标题和介绍 -->
                <div class="page-header">
                    <h1 class="page-title">二手图书<span class="highlight">交易管理系统</span></h1>
                    <p class="page-description">让知识流动起来，让书籍找到新的主人 📚</p>
                </div>

                <!-- 分类标签 -->
                <div class="category-tabs">
                    <el-tabs v-model="labelName" type="border-card" @tab-click="handleClick" class="custom-tabs">
                        <el-tab-pane label="全部图书" name="0">
                            <i class="el-icon-s-grid tab-icon"></i> 全部图书
                        </el-tab-pane>
                        <el-tab-pane label="教材教辅" name="1">
                            <i class="el-icon-reading tab-icon"></i> 教材教辅
                        </el-tab-pane>
                        <el-tab-pane label="文学小说" name="2">
                            <i class="el-icon-notebook-1 tab-icon"></i> 文学小说
                        </el-tab-pane>
                        <el-tab-pane label="专业书籍" name="3">
                            <i class="el-icon-document tab-icon"></i> 专业书籍
                        </el-tab-pane>
                        <el-tab-pane label="考试考研" name="4">
                            <i class="el-icon-edit-outline tab-icon"></i> 考试考研
                        </el-tab-pane>
                        <el-tab-pane label="求购信息" name="5">
                            <i class="el-icon-bell tab-icon"></i> 求购信息
                        </el-tab-pane>
                    </el-tabs>
                </div>

                <!-- 商品卡片列表 -->
                <div class="items-container">
                    <el-row :gutter="20">
                        <el-col :xs="24" :sm="12" :md="6" v-for="(idle, index) in idleList" :key="index">
                            <div class="item-card" @click="toDetails(idle)">
                                <div class="item-image-container">
                                    <el-image
                                        class="item-image"
                                        :src="idle.imgUrl"
                                        fit="cover">
                                        <div slot="error" class="image-slot">
                                            <i class="el-icon-picture-outline"></i>
                                            <span>暂无图片</span>
                                        </div>
                                    </el-image>
                                    <div class="item-tag" v-if="getItemCategory(idle.idleLabel)">
                                        {{getItemCategory(idle.idleLabel)}}
                                    </div>
                                </div>
                                <div class="item-content">
                                    <h3 class="item-title">{{idle.idleName}}</h3>
                                    <div class="item-meta">
                                        <div class="item-price" v-show="idle.idlePrice !== 0">¥{{idle.idlePrice}}</div>
                                        <div class="item-place"><i class="el-icon-location"></i> {{idle.idlePlace}}</div>
                                    </div>
                                    <div class="item-time"><i class="el-icon-time"></i> {{idle.timeStr}}</div>
                                    <div class="user-info">
                                        <el-avatar :size="30" :src="idle.user.avatar"></el-avatar>
                                        <div class="user-nickname">{{idle.user.nickname}}</div>
                                    </div>
                                </div>
                            </div>
                        </el-col>
                    </el-row>
                </div>

                <!-- 分页 -->
                <div class="pagination-container">
                    <el-pagination
                        background
                        @current-change="handleCurrentChange"
                        :current-page.sync="currentPage"
                        :page-size="8"
                        layout="prev, pager, next, jumper"
                        :total="totalItem">
                    </el-pagination>
                </div>
            </div>
            <app-foot></app-foot>
        </app-body>
    </div>
</template>

<script>
    import AppHead from '../common/AppHeader.vue';
    import AppBody from '../common/AppPageBody.vue'
    import AppFoot from '../common/AppFoot.vue'

    export default {
        name: "index",
        components: {
            AppHead,
            AppBody,
            AppFoot
        },
        data() {
            return {
                labelName: '0',
                idleList: [],
                currentPage: 1,
                totalItem: 1,
                categories: {
                    '1': '教材教辅',
                    '2': '文学小说',
                    '3': '专业书籍',
                    '4': '考试考研',
                    '5': '求购'
                }
            };
        },
        created() {
            this.findIdleTiem(1)
        },
        watch:{
            $route(to,from){
                this.labelName=to.query.labelName;
                let val=parseInt(to.query.page)?parseInt(to.query.page):1;
                this.currentPage=parseInt(to.query.page)?parseInt(to.query.page):1;
                this.findIdleTiem(val);
            }
        },
        methods: {
            findIdleTiem(page){
                const loading = this.$loading({
                    lock: true,
                    text: '加载数据中',
                    spinner: 'el-icon-loading',
                    background: 'rgba(0, 0, 0, 0.7)'
                });
                if(this.labelName>0){
                    this.$api.findIdleTiemByLable({
                        idleLabel:this.labelName,
                        page: page,
                        nums: 8
                    }).then(res => {
                        console.log(res);
                        let list = res.data.list;
                        for (let i = 0; i < list.length; i++) {
                            list[i].timeStr = list[i].releaseTime.substring(0, 10) + " " + list[i].releaseTime.substring(11, 19);
                            let pictureList = JSON.parse(list[i].pictureList);
                            list[i].imgUrl = pictureList.length > 0 ? pictureList[0] : '';
                        }
                        this.idleList = list;
                        this.totalItem=res.data.count;
                        console.log(this.totalItem);
                    }).catch(e => {
                        console.log(e)
                        this.$message.error('获取数据失败，请稍后重试');
                    }).finally(()=>{
                        loading.close();
                    })
                }else{
                    this.$api.findIdleTiem({
                        page: page,
                        nums: 8
                    }).then(res => {
                        console.log(res);
                        let list = res.data.list;
                        for (let i = 0; i < list.length; i++) {
                            list[i].timeStr = list[i].releaseTime.substring(0, 10) + " " + list[i].releaseTime.substring(11, 19);
                            let pictureList = JSON.parse(list[i].pictureList);
                            list[i].imgUrl = pictureList.length > 0 ? pictureList[0] : '';
                        }
                        this.idleList = list;
                        this.totalItem=res.data.count;
                        console.log(this.totalItem);
                    }).catch(e => {
                        console.log(e)
                        this.$message.error('获取数据失败，请稍后重试');
                    }).finally(()=>{
                        loading.close();
                    })
                }
            },
            handleClick(tab, event) {
                console.log(this.labelName);
                this.$router.replace({query: {page: 1,labelName:this.labelName}});
            },
            handleCurrentChange(val) {
                console.log(`当前页: ${val}`);
                this.$router.replace({query: {page: val,labelName:this.labelName}});
            },
            toDetails(idle) {
                this.$router.push({path: '/details', query: {id: idle.id}});
            },
            getItemCategory(label) {
                return this.categories[label] || '';
            }
        }
    }
</script>

<style scoped>
    .page-header {
        text-align: center;
        padding: 20px 0;
        margin-bottom: 20px;
        background: linear-gradient(to right, #f0f9ff, #e6f7ff);
        border-radius: 8px;
    }
    
    .page-title {
        font-size: 28px;
        font-weight: 600;
        color: #303133;
        margin-bottom: 5px;
    }
    
    .highlight {
        color: #409EFF;
    }
    
    .page-description {
        font-size: 16px;
        color: #606266;
        margin: 10px 0 0;
    }
    
    .category-tabs {
        margin-bottom: 20px;
    }
    
    .custom-tabs {
        border-radius: 8px;
        overflow: hidden;
        box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
    }
    
    .tab-icon {
        margin-right: 5px;
    }
    
    .items-container {
        padding: 0 10px;
    }
    
    .item-card {
        height: 100%;
        border-radius: 8px;
        overflow: hidden;
        box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
        transition: all 0.3s;
        margin-bottom: 25px;
        background: #fff;
        cursor: pointer;
    }
    
    .item-card:hover {
        transform: translateY(-5px);
        box-shadow: 0 5px 15px rgba(0, 0, 0, 0.2);
    }
    
    .item-image-container {
        position: relative;
        height: 180px;
        overflow: hidden;
    }
    
    .item-image {
        width: 100%;
        height: 100%;
        transition: transform 0.3s;
    }
    
    .item-card:hover .item-image {
        transform: scale(1.05);
    }
    
    .item-tag {
        position: absolute;
        top: 10px;
        right: 10px;
        background: rgba(64, 158, 255, 0.8);
        color: white;
        padding: 2px 8px;
        border-radius: 12px;
        font-size: 12px;
    }
    
    .item-content {
        padding: 15px;
    }
    
    .item-title {
        font-size: 16px;
        font-weight: 600;
        color: #303133;
        margin: 0 0 10px;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
        line-height: 1.4;
    }
    
    .item-meta {
        display: flex;
        justify-content: space-between;
        margin-bottom: 8px;
    }
    
    .item-price {
        font-size: 18px;
        font-weight: 600;
        color: #f56c6c;
    }
    
    .item-place {
        font-size: 13px;
        color: #606266;
    }
    
    .item-time {
        font-size: 12px;
        color: #909399;
        margin-bottom: 10px;
    }
    
    .user-info {
        display: flex;
        align-items: center;
        padding-top: 10px;
        border-top: 1px solid #f0f0f0;
    }
    
    .user-nickname {
        margin-left: 10px;
        font-size: 14px;
        color: #606266;
    }
    
    .pagination-container {
        display: flex;
        justify-content: center;
        padding: 30px 0;
    }
    
    .image-slot {
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        width: 100%;
        height: 100%;
        background: #f5f7fa;
        color: #909399;
    }
    
    /* 响应式调整 */
    @media (max-width: 768px) {
        .page-title {
            font-size: 24px;
        }
        
        .page-description {
            font-size: 14px;
        }
        
        .item-image-container {
            height: 150px;
        }
    }
</style>