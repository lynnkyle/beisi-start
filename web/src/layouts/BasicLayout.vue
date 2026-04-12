<script setup lang="ts">
import GlobalHeader from '@/components/GlobalHeader.vue'
</script>
<template>
  <div id="basicLayout">
    <a-layout style="min-height: 100vh">
      <a-layout-header class="header">
        <GlobalHeader></GlobalHeader>
      </a-layout-header>
      <a-layout>
        <a-layout-content class="content">
          <router-view></router-view>
        </a-layout-content>
      </a-layout>
      <a-layout-footer class="footer">
        <a
            href="https://github.com/lynnkyle/beisi-start"
            style="display: flex; align-items: center; justify-content: center"
        >
          <svg height="28" width="28" viewBox="0 0 24 24" style="padding-right: 5px">
            <path
                d="M12 1C5.923 1 1 5.923 1 12c0 4.867 3.149 8.979 7.521 10.436.55.096.756-.233.756-.522 0-.262-.013-1.128-.013-2.049-2.764.509-3.479-.674-3.699-1.292-.124-.317-.66-1.293-1.127-1.554-.385-.207-.936-.715-.014-.729.866-.014 1.485.797 1.691 1.128.99 1.663 2.571 1.196 3.204.907.096-.715.385-1.196.701-1.471-2.448-.275-5.005-1.224-5.005-5.432 0-1.196.426-2.186 1.128-2.956-.111-.275-.496-1.402.11-2.915 0 0 .921-.288 3.024 1.128a10.193 10.193 0 0 1 2.75-.371c.936 0 1.871.123 2.75.371 2.104-1.43 3.025-1.128 3.025-1.128.605 1.513.221 2.64.111 2.915.701.77 1.127 1.747 1.127 2.956 0 4.222-2.571 5.157-5.019 5.432.399.344.743 1.004.743 2.035 0 1.471-.014 2.654-.014 3.025 0 .289.206.632.756.522C19.851 20.979 23 16.854 23 12c0-6.077-4.922-11-11-11Z"
            ></path>
          </svg>
          Github地址 by 林泽源
        </a>
      </a-layout-footer>
    </a-layout>
  </div>
</template>
<style scoped>
#basicLayout .header {
  background: #ffffff;
  color: unset;
  margin-bottom: 5px;
}

#basicLayout .slider {
  background: #ffffff;
  border-right: solid 1px #eee;
  padding-top: 20px;
}

#basicLayout .slider :deep(.ant-menu-root) {
  border-inline-end: none !important;
}

#basicLayout .content {
  background: linear-gradient(to right, #fefefe, #ffffff);
  padding: 20px;
  /**margin-bottom: 30px;**/
}

#basicLayout .footer {
  background: #efefef;
  padding: 16px;
  text-align: center;
}
</style>
