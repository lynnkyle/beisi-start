<script setup lang="ts">
import {useLoginUserStore} from "@/stores/useLoginUserStore.ts";
import {onMounted} from "vue";

const loginUserStore = useLoginUserStore()

onMounted(() => {
  loginUserStore.fetchLoginUser()
})
</script>

<template>
  <div>欢迎,{{ loginUserStore.loginUser?.userName }}</div>
</template>
a
<style scoped>

</style>