<script setup lang="ts">
import { reactive } from 'vue'
import { LockOutlined, UserOutlined } from '@ant-design/icons-vue'
import { message } from 'ant-design-vue'
import { useRouter } from 'vue-router'
import {userRegister} from "@/api/userController.ts";

const router = useRouter()

const formState = reactive<API.UserRegisterRequest>({
  userName: '',
  userPassword: '',
  checkPassword: '',
})
const doSubmit = async (values: any) => {
  if (values.userPassword !== values.checkPassword) {
    message.error('两次输入的密码不一致')
    return
  }
  try {
    const resp = await userRegister(values)
    const res = resp.data
    // 注册成功, 跳转到登录页面
    if (res.code === 0 && res.data) {
      message.success('注册成功')
      router.push({
        path: '/user/login',
        replace: true,
      })
    } else {
      message.error(res.description)
    }
  } catch (e) {
    message.error('注册失败', e.message)
  }
}
</script>

<template>
  <div id="userRegisterPage">
    <div class="title">倍司 —— 用户注册</div>
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
            <UserOutlined class="site-form-item-icon" />
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
            <LockOutlined class="site-form-item-icon" />
          </template>
        </a-input-password>
      </a-form-item>
      <a-form-item
        name="checkPassword"
        :rules="[
          { required: true, message: '请确认密码!' },
          { min: 4, message: '密码长度不允许小于4位!' },
        ]"
      >
        <a-input-password v-model:value="formState.checkPassword" placeholder="请确认密码">
          <template #prefix>
            <LockOutlined class="site-form-item-icon" />
          </template>
        </a-input-password>
      </a-form-item>
      <div class="tip">
        已有账号?
        <router-link to="/"> 去登录</router-link>
      </div>
      <a-form-item>
        <a-button type="primary" html-type="submit" style="width: 100%">注册</a-button>
      </a-form-item>
    </a-form>
  </div>
</template>

<style scoped>
#userRegisterPage {
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
