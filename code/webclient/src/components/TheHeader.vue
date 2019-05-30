<template>
  <div id="header">
    <TheLocaleChanger/>
    <TheNavBar/>
  </div>
</template>

<script>

import TheLocaleChanger from "@/components/TheLocaleChanger";
import TheNavBar from "@/components/TheNavBar";

export default {
  name: "TheHeader",
  components: {
    TheLocaleChanger,
    TheNavBar
  }
};
</script>

<style>
</style>
