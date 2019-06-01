<template>
  <div class="home container my-5">
    <b-alert :show="error" variant="danger" dismissible v-on:click="dismissError()">{{error}}</b-alert>

    <b-card>
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
  </div>
</template>

<script>
import { mapState } from "vuex";
import LogIn from "@/components/LogIn.vue";
import { DISMISS_ERROR } from "@/store/actions.type";

export default {
  name: "home",
  data() {
    return {};
  },
  methods: {
    dismissError() {
      this.$store.dispatch(DISMISS_ERROR, {});
    }
  },
  components: {
    LogIn
  },
  computed: {
    ...mapState({
      error: state => state.error.error
    })
  }
};
</script>
