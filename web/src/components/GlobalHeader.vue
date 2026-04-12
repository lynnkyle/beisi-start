<script lang="ts" setup>
import {computed, h, ref} from 'vue'
import {type RouteRecordRaw, useRoute, useRouter} from 'vue-router'
import {UserOutlined, LogoutOutlined} from '@ant-design/icons-vue'
import {type ItemType, type MenuProps, message} from 'ant-design-vue'

// const loginUserStore = useLoginUserStore()
// loginUserStore.fetchLoginUser()

const router = useRouter()
// 菜单
const current = ref<string[]>(['/'])

interface MenuItem {
  key: string
  title: string
  label?: string
  icon?: () => any
  access?: string
  hideInMenu: boolean
  children?: MenuItem[]
}

const processRoutes = (routes: RouteRecordRaw[], menuList: MenuItem[]) => {
  routes.forEach((route) => {
    const menuItem: MenuItem = {
      key: route.path,
      title: route.name as string,
      label: route.name as string,
      icon: () => route.meta?.icon ?? null,
      access: route.meta?.access as string,
      hideInMenu: route.meta?.hideInMenu as boolean,
    }
    if (route.children && route.children.length > 0) {
      menuItem.children = []
      processRoutes(route.children, menuItem.children)
    }
    menuList.push(menuItem)
  })
}
// 用户注销
const doLogout = async () => {
  // const resp = await userLogout()
  // const res = resp.data
  // if (res.code === 0) {
  //   loginUserStore.setLoginUser({
  //     userName: '未登录',
  //   })
  //   message.success('退出登录')
  //   router.push({
  //     path: '/user/login',
  //   })
  // } else {
  //   message.error(res.description)
  // }
}
// 路由守卫
router.afterEach((to, from, next) => {
  current.value = [to.path]
})
</script>
<template>
  <div id="globalHeader">
    <a-row :wrap="false">
      <a-col flex="120px" style="display: flex; justify-content: center; align-items: center">
        <router-link to="/">
          <a-flex align="center">
            <img src="../assets/logo.jpg" alt="logo" class="logo"/>
            <div class="title">倍司</div>
          </a-flex>
        </router-link>
      </a-col>
      <a-col flex="auto">
        <a-menu
            v-model:selectedKeys="current"
            mode="horizontal"
        />
      </a-col>
      <!--用户信息展示-->
      <a-col flex="120px" style="display: flex; justify-content: center; align-items: center">
        <div v-if="false">
<!--        <div v-if="loginUserStore.loginUser.id">-->
          <a-dropdown>
            <a-space>
              <!--              <a-avatar size="large" :src="loginUserStore.loginUser.userAvatar">-->
              <a-avatar size="large">
                <template #icon>
                  <UserOutlined/>
                </template>
              </a-avatar>
              <!--              {{ loginUserStore.loginUser.userName ?? '无名' }}-->
            </a-space>
            <template #overlay>
              <a-menu>
                <a-menu-item>
                  <router-link to="/private">
                    <a-space>
                      <UserOutlined/>
                      我的空间
                    </a-space>
                  </router-link>
                </a-menu-item>
                <a-menu-item @click="doLogout">
                  <a-space>
                    <LogoutOutlined/>
                    <a href="javascript:;">退出登录</a>
                  </a-space>
                </a-menu-item>
              </a-menu>
            </template>
          </a-dropdown>
        </div>
        <div v-else>
          <a-button type="primary" href="/">登录</a-button>
        </div>
      </a-col>
    </a-row>
  </div>
</template>
<style scoped>
#globalHeader {
}

#globalHeader .title {
  color: #000000;
  font-size: 20px;
}

#globalHeader .logo {
  width: 48px;
  height: 48px;
}
</style>
