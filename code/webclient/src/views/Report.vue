<template>
  <div class="home container my-5">
    <b-alert :show="error" variant="danger" dismissible v-on:click="dismissError()">{{$t(error)}}</b-alert>
    <b-alert
      :show="message"
      variant="success"
      dismissible
      v-on:click="dismissMessage()"
    >{{$t(message)}}</b-alert>

    <b-card id="welcome-card" v-if="!isAuthenticated">
      <div class="card-body d-flex flex-column">
        <b-card-title>{{$t('welcome_message_title')}}</b-card-title>
        <b-card-text>{{$t('welcome_message_body')}}</b-card-text>

        <div>
          <LogIn type="case" variant="info"/>
          <LogIn type="control" variant="primary"/>
          <LogIn type="physician" variant="dark"/>
        </div>
      </div>
    </b-card>

    <b-card id="logged-in-card" v-if="!isAuthenticated">
      <div class="card-body d-flex flex-column">
        <b-card-title>{{$t('logged_welcome_message_title') + $t(user.role)}}</b-card-title>
        <b-card-text>{{$t('logged_welcome_message_body')}}</b-card-text>
        <div>
          <b-button
            variant="outline-success"
            class="mr-2 mb-1 mt-3"
            to="new"
          >{{$t('create_a_new_questionnaire')}}</b-button>
          <b-button
            variant="outline-secondary"
            class="mr-2 mb-1 mt-3"
            to="report"
          >{{$t('update_an_existing_questionnaire')}}</b-button>
        </div>
      </div>
    </b-card>
  </div>
</template>

<script>
import { mapState } from "vuex";
import LogIn from "@/components/LogIn.vue";
import { DISMISS_ERROR, DISMISS_MESSAGE } from "@/store/actions.type";

export default {
  name: "home",
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
  components: {
    LogIn
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
