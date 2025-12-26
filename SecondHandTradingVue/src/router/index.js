import Vue from 'vue';
import Router from 'vue-router';

const originalReplace = Router.prototype.replace;
Router.prototype.replace = function replace(location) {
    return originalReplace.call(this, location).catch(err => err);
};
const originalPush = Router.prototype.push
Router.prototype.push = function push(location) {
    return originalPush.call(this, location).catch(err => err)
};

Vue.use(Router);

export default new Router({
    routes: [
        {
            path: '/',
            redirect: '/index'
        },
        {
            path: '/index',
            /*组件懒加载*/
            component: () => import('../components/page/index.vue'),
            meta: { title: '二手图书交易管理平台' }
        },
        {
            path: '/search',
            component: () => import('../components/page/search.vue'),
            meta: { title: '图书搜索 | 二手图书交易管理平台' }
        },
        {
            path: '/me',
            component: () => import('../components/page/me.vue'),
            meta: { title: '个人中心 | 二手图书交易管理平台' }
        },
        {
            path: '/message',
            component: () => import('../components/page/message.vue'),
            meta: { title: '消息 | 二手图书交易管理平台' }
        },
        {
            path: '/release',
            component: () => import('../components/page/release.vue'),
            meta: { title: '发布图书 | 二手图书交易管理平台' }
        },
        {
            path: '/jiaoyi',
            component: () => import('../components/page/jiaoyi.vue'),
            meta: { title: '图书详情 | 二手图书交易管理平台' }
        },
        {
            path: '/details',
            component: () => import('../components/page/idle-details.vue'),
            meta: { title: '图书详情 | 二手图书交易管理平台' }
        },
        {
            path: '/order',
            component: () => import('../components/page/order.vue'),
            meta: { title: '订单详情 | 二手图书交易管理平台' }
        },
        {
            path: '/alipay/pay',
            component: () => import('../components/page/alipay-success.vue'),
            meta: { title: '支付成功 | 二手图书交易管理平台' }
        },
        {
            path: '/login',
            component: () => import('../components/page/login.vue'),
            meta: { title: '登录 | 二手图书交易管理平台' }
        },
        {
            path: '/sign-in',
            component: () => import('../components/page/sign-in.vue'),
            meta: { title: '注册 | 二手图书交易管理平台' }
        },
        {
            path: '/login-admin',
            component: () => import('../components/page/login-admin.vue'),
            meta: { title: '管理员登陆' }
        },
        {
            path: '/platform-admin',
            component: () => import('../components/page/platform-admin.vue'),
            meta: { title: '后台管理' }
        },
        {
            path: '*',
            redirect: '/'
        }
    ]
});
