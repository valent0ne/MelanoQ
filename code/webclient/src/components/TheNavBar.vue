<template>
  <div id="nav">
    <b-nav pills align="center">
      <b-nav-item :to="'/'">Home</b-nav-item>
      <b-nav-item :to="'/about'">About</b-nav-item>
    </b-nav>
  </div>
</template>

<script>
export default {
  name: "TheNavBar"
};
</script>

<style>
</style>
