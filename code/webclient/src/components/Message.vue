<template>
  <div>
    <b-alert :show="error" variant="danger" dismissible @dismissed="dismissError()">{{$t(error)}}</b-alert>
    <b-alert
      :show="message"
      variant="success"
      dismissible
      @dismissed="dismissMessage()"
    >{{$t(message)}}</b-alert>
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
    dismissError() {
      this.$store.dispatch(DISMISS_ERROR, {});
    },
    dismissMessage() {
      this.$store.dispatch(DISMISS_MESSAGE, {});
    }
  },
  computed: {
    ...mapState({
      error: state => state.error.error,
      message: state => state.message.message,
      isAuthenticated: state => state.auth.isAuthenticated,
      user: state => state.auth.user
    })
  }
};
</script>

<style>
</style>
