<template>
  <div>
    <p>todo</p>
    <p>{{action}}</p>
  </div>
</template>
<script>
export default {
  name: "a2",
  props: {
    action: String
  }
};
</script>