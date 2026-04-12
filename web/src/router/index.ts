import {createRouter, createWebHistory} from 'vue-router'
import UserLoginPage from "../page/UserLoginPage.vue";
import UserRegisterPage from '../page/UserRegisterPage.vue';
import IndexPage from '../page/IndexPage.vue';

const routes = [
    {
        path: '/',
        name: '登录页',
        component: UserLoginPage,
    },
    {
        path: '/register',
        name: '注册页',
        component: UserRegisterPage,
    },
    {
        path: '/index',
        name: '首页',
        component: IndexPage,
    }
]
const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: routes,
})
export default router
