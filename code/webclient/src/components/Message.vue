<template>
  <div>
    <div v-for="error in errors" :key="error.key">
      <b-alert
        :show="errorDismissSecs"
        variant="danger"
        class="message-box"
        fade
        dismissible
        @dismissed="dismissError(error.key)"
        @dismiss-count-down="updateCountDownError(error.key)"
      >
        <p>
          <strong v-if="error.code">{{error.code}}</strong>
          {{$t(error.error)}}
        </p>
        <b-progress
          variant="danger"
          :max="errorDismissSecs"
          :value="error.dismissCountDown"
          height="4px"
        ></b-progress>
      </b-alert>
    </div>
    <div v-for="message in messages" :key="message.key">
      <b-alert
        :show="messageDismissSecs"
        variant="success"
        class="message-box"
        fade
        dismissible
        @dismissed="dismissMessage(message.key)"
        @dismiss-count-down="updateCountDownMessage(message.key)"
      >
        <p>{{$t(message.message)}}</p>
        <b-progress
          variant="success"
          :max="messageDismissSecs"
          :value="message.dismissCountDown"
          height="4px"
        ></b-progress>
      </b-alert>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import {
  DISMISS_ERROR,
  DISMISS_MESSAGE,
  UPDATE_ERROR_COUNT_DOWN,
  UPDATE_MESSAGE_COUNT_DOWN
} from "@/store/actions.type";

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
    },
    updateCountDownError(key) {
      this.$store.dispatch(UPDATE_ERROR_COUNT_DOWN, key);
    },
    updateCountDownMessage(key) {
      this.$store.dispatch(UPDATE_MESSAGE_COUNT_DOWN, key);
    }
  },
  created: function() {
    /*if (this.errors.length > 0) {
      setTimeout(() => {
        this.$store.dispatch(DISMISS_ERRORS);
      }, 2000);
    }
    if (this.errors.length > 0) {
      setTimeout(() => {
        this.$store.dispatch(DISMISS_MESSAGES);
      }, 2000);
    }*/
  },
  computed: {
    ...mapState({
      errors: state => state.error.errors,
      messages: state => state.message.messages,
      errorDismissSecs: state => state.error.dismissSecs,
      messageDismissSecs: state => state.message.dismissSecs,

      isAuthenticated: state => state.auth.isAuthenticated,
      user: state => state.auth.user
    })
  }
};
</script>

<style>
</style>
