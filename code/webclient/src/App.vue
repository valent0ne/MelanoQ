<template>
  <div id="app" class="container">
    <TheHeader/>
    <router-view></router-view>
  </div>
</template>

<script>

import TheHeader from '@/components/TheHeader.vue'

export default {
  name: "App",
  components: {
    TheHeader
  }
};
</script>

<style>
body {
    margin: 0;
    font-family: -apple-system,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif,"Apple Color Emoji","Segoe UI Emoji","Segoe UI Symbol";
    font-size: 1rem;
    font-weight: 400;
    line-height: 1.5;
    color: #212529;
    text-align: left;
    background-color: #f8f9fa!important;

}

.container {
    width: 100%;
    padding-right: 15px;
    padding-left: 15px;
    margin-right: auto;
    margin-left: auto;
}

</style>
