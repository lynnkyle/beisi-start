import {ref} from 'vue'
import {defineStore} from 'pinia'
import {getLoginUser} from '@/api/userController.ts'

/**
 * 存储登录用户信息状态
 */
export const useLoginUserStore = defineStore('loginUser', () => {
    const loginUser = ref<API.UserVO>({
        userName: '游客',
        token: ''
    })

    async function fetchLoginUser(): Promise<void> {
        const resp = await getLoginUser()
        const res = resp.data
        if (res.data && res.code === 0) {
            loginUser.value = res.data
        }
    }

    /**
     * 设置登录用户
     * @param user
     */
    function setLoginUser(user: any) {
        loginUser.value = user
    }

    return {
        loginUser,
        fetchLoginUser,
        setLoginUser,
    }
})
