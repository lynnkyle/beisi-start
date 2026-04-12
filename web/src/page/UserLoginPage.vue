<script setup lang="ts">
import {reactive, ref} from 'vue'
import {LockOutlined, UserOutlined} from '@ant-design/icons-vue'
import {message} from 'ant-design-vue'
import {useRouter} from 'vue-router'
import {userLogin} from "@/api/userController.ts";
import {useLoginUserStore} from "@/stores/useLoginUserStore.ts";

const loginUserStore = useLoginUserStore()
const router = useRouter()

const formState = reactive<API.UserLoginRequest>({
  userName: '',
  userPassword: '',
})
const loginLoading = ref<Boolean>(false)
const doSubmit = async (values: any) => {
  loginLoading.value = true
  try {
    const resp = await userLogin(values)
    const res = resp.data
    // 登录成功, 把登录态保存到全局状态中
    if (res.code === 0 && res.data) {
      // await loginUserStore.fetchLoginUser()
      message.success('登录成功')
      loginUserStore.setLoginUser(res.data)
      localStorage.setItem('token', res.data.token);
      localStorage.setItem('tokenExpire', Date.now() + 7200000); // 2小时
      router.push({
        path: '/index',
        replace: true,
      })
    } else {
      message.error(res.description)
    }
  } catch (e) {
    console.log('登录失败', e.message)
  }
  loginLoading.value = false
}
</script>

<template>
  <div id="userLoginPage">
    <a-spin :spinning="loginLoading" size="large">
      <div class="title">倍司 —— 用户登录</div>
      <div class="desc">倍司企业入门技术测试</div>
      <a-form :model="formState" class="login-form" @finish="doSubmit">
        <a-form-item
            name="userName"
            :rules="[
          { required: true, message: '请输入账号!' },
          { min: 4, message: '账号长度不允许小于4位!' },
        ]"
        >
          <a-input v-model:value="formState.userName" placeholder="请输入账号">
            <template #prefix>
              <UserOutlined class="site-form-item-icon"/>
            </template>
          </a-input>
        </a-form-item>
        <a-form-item
            name="userPassword"
            :rules="[
          { required: true, message: '请输入密码!' },
          { min: 4, message: '密码长度不允许小于4位!' },
        ]"
        >
          <a-input-password v-model:value="formState.userPassword" placeholder="请输入密码">
            <template #prefix>
              <LockOutlined class="site-form-item-icon"/>
            </template>
          </a-input-password>
        </a-form-item>
        <div class="tip">
          没有账号?
          <router-link to="/register"> 去注册</router-link>
        </div>
        <a-form-item>
          <a-button type="primary" html-type="submit" style="width: 100%">登录</a-button>
        </a-form-item>
      </a-form>
    </a-spin>
  </div>
</template>

<style scoped>
#userLoginPage {
  max-width: 360px;
  margin: 0 auto;
}

.title {
  text-align: center;
  font-size: 24px;
  margin-bottom: 16px;
}

.desc {
  color: #bbbbbb;
  text-align: center;
  margin-bottom: 16px;
}

.tip {
  text-align: right;
  font-size: 12px;
  color: #bbbbbb;
}
</style>
