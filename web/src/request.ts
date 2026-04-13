import axios from 'axios'
import {message} from 'ant-design-vue'

const instance = axios.create({
    // baseURL: '',
    baseURL: '/api',
    timeout: 60000,
    withCredentials: true
})
instance.interceptors.response.use(
    (response) => {
        const {data} = response
        if (data.code === 40100) {
            if (
                !response.request.responseURL.includes('user/get/login') &&
                !window.location.pathname.includes('user/login')
            ) {
                message.warn('请先登录')
                window.location.href = `/user/login?redirect=${window.location.href}`
            }
        }
        return response
    },
    (error) => {
        return Promise.reject(error)
    }
)

export default instance
