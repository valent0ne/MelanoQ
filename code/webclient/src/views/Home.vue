<template>
  <div class="home container my-5">
    <Message />

    <b-card v-if="!isAuthenticated">
      <div class="card-body d-flex flex-column">
        <b-card-title>{{$t('welcome_message_title')}}</b-card-title>
        <b-card-text>{{$t('welcome_message_body')}}</b-card-text>

        <div>
          <LogIn type="case" variant="info" />
          <LogIn type="control" variant="primary" />
          <LogIn type="physician" variant="dark" />
        </div>
      </div>
    </b-card>

    <b-card v-if="isAuthenticated">
      <div class="card-body d-flex flex-column">
        <b-card-title>
          {{$t("hi") + ' '}}
          <strong>{{user.username+','}}</strong>
          {{$t('logged_welcome_message_title')}}
          <strong>{{$t(user.type).toUpperCase()}}</strong>
        </b-card-title>
        <b-card-text>{{$t('logged_welcome_message_body')}}</b-card-text>
        <div>
          <b-button
            variant="outline-info"
            class="mr-2 mb-1 mt-3"
            to="choice"
            @click="this.$store.dispatch(DELETE_DB_CODE_NUMBER)"
          >{{$t('create_a_new_questionnaire')}}</b-button>
          <b-button
            variant="outline-primary"
            class="mr-2 mb-1 mt-3"
            @click="computeCompleteDestination()"
          >{{$t('complete_an_existing_questionnaire')}}</b-button>
          <b-button
            variant="outline-dark"
            class="mr-2 mb-1 mt-3"
            to="query"
            :disabled="user.type !== 'physician'"
          >{{$t('query_the_database')}}</b-button>
        </div>
      </div>
    </b-card>
  </div>
</template>

<script>
import { mapState } from "vuex";
import LogIn from "@/components/LogIn.vue";
import Message from "@/components/Message.vue";

export default {
  name: "home",
  data() {
    return {};
  },
  components: {
    LogIn,
    Message
  },
  methods: {
    computeCompleteDestination() {
      if (this.dbCodeNumber) {
        this.$router.push("choice");
      } else {
        this.$router.push("lookup");
      }
    }
  },
  computed: {
    ...mapState({
      isAuthenticated: state => state.auth.isAuthenticated,
      user: state => state.auth.user,
      dbCodeNumber: state => state.questionnaire.dbCodeNumber
    })
  }
};
</script>