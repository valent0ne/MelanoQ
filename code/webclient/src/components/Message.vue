<template>
  <div>
    <div v-for="error in errors" :key="error.key">
      <b-alert show variant="danger" fade dismissible @dismissed="dismissError(error.key)">
        <strong v-if="error.code">{{error.code}}</strong>
        {{$t(error.error)}}
      </b-alert>
    </div>

    <div v-for="message in messages" :key="message.key">
      <b-alert
        show
        variant="success"
        dismissible
        fade
        @dismissed="dismissMessage(message.key)"
      >{{$t(message.message)}}</b-alert>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import { DISMISS_ERROR, DISMISS_MESSAGE } from "@/store/actions.type";

export default {
  name: "message",
  data() {
    return {};
  },
  methods: {
    dismissError(key) {
      this.$store.dispatch(DISMISS_ERROR, key);
    },
    dismissMessage(key) {
      this.$store.dispatch(DISMISS_MESSAGE, key);
    }
  },
  computed: {
    ...mapState({
      errors: state => state.error.errors,
      messages: state => state.message.messages,
      isAuthenticated: state => state.auth.isAuthenticated,
      user: state => state.auth.user
    })
  }
};
</script>

<style>
</style>
