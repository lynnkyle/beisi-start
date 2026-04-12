import {createApp} from 'vue'
import './style.css'
import App from './App.vue'

import router from './router'
import Antd from 'ant-design-vue'
import 'ant-design-vue/dist/reset.css'
import {createPinia} from "pinia";

const app = createApp(App)
app.use(router)
app.use(Antd)
app.use(createPinia())
app.mount('#app')
