<template>
  <div id="app" class="bg-light">
    <TheHeader/>
    <router-view></router-view>
    <TheFooter/>
  </div>
</template>

<script>
import TheHeader from "@/components/TheHeader.vue";
import TheFooter from "@/components/TheFooter.vue";


export default {
  name: "App",
  components: {
    TheHeader,
    TheFooter
  }
};
</script>

<style>
  @import "./assets/styles/style.css";
</style>
