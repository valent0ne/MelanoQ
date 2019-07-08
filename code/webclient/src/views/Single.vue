<template>
  <div>
    <div class="home container my-4">
      <Message />
      <b-card v-if="isAuthenticated" class="mb-4">
        <div class="card-body d-flex flex-column">
          <b-card-title>title</b-card-title>
          <b-card-text class="line-break mt-2">text</b-card-text>
        </div>
      </b-card>
    </div>
  </div>
</template>
<script>
import { mapState } from "vuex";
import {} from "@/store/actions.type";

import Message from "@/components/Message.vue";

export default {
  name: "report",
  data() {
    return {};
  },

  created: function() {
    if (this.user.type !== "physician") {
      this.$store.dispatch(ADD_ERROR, "unauthorized");
      this.$router.push({ name: "home" });
    }
    if (!this.isAuthenticated) {
      this.$store.dispatch(ADD_ERROR, "not_authenticated");
      this.$router.push({ name: "home" });
    }
  },
  methods: {},
  components: {
    Message
  },
  computed: {
    ...mapState({
      user: state => state.auth.user,
      isAuthenticated: state => state.auth.isAuthenticated,
      dbCodeNumber: state => state.questionnaire.dbCodeNumber,
      questionnaires: state => state.questionnaire.questionnaires
    })
  }
};
</script>

