<template>
  <div class="home container my-5">
    <Message />

    <b-card v-if="!isAuthenticated">
      <div class="card-body d-flex flex-column">
        <b-card-title>todo</b-card-title>
        <b-card-text>todo</b-card-text>
      </div>
    </b-card>
  </div>
</template>

<script>
import { mapState } from "vuex";

import Message from "@/components/Message.vue";

export default {
  name: "about",
  data() {
    return {};
  },
  components: {
    Message
  },
  computed: {
    ...mapState({})
  }
};
</script>