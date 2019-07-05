<template>
  <b-dropdown
    :id="'dropdown-form-'+type"
    :variant="'outline-'+variant"
    ref="dropdown"
    class="mr-2 mb-1 mt-3"
  >
    <template slot="button-content">{{$t(type)}}</template>
    <b-dropdown-form>
      <b-form-group
        label="Username"
        :label-for="'dropdown-form-username-'+type"
        @submit.stop.prevent
      >
        <b-form-input
          :id="'dropdown-form-username-'+type"
          v-model="form.username"
          size="sm"
          placeholder="Username"
        ></b-form-input>
      </b-form-group>

      <b-form-group label="Password" :label-for="'dropdown-form-password-'+type">
        <b-form-input
          :id="'dropdown-form-password-'+type"
          type="password"
          size="sm"
          placeholder="Password"
          v-model="form.password"
        ></b-form-input>
      </b-form-group>

      <b-button :variant="variant" size="sm" v-on:click="onSubmit(type);">{{$t('sign_in')}}</b-button>
    </b-dropdown-form>
    <b-dropdown-divider></b-dropdown-divider>
    <b-dropdown-item-button>{{$t('need_an_account')}}</b-dropdown-item-button>
  </b-dropdown>
</template>

<script>
import { LOGIN } from "@/store/actions.type";

export default {
  name: "LogIn",
  props: {
    type: String,
    variant: String
  },
  data() {
    return {
      form: {
        username: null,
        password: null,
        type: null
      }
    };
  },
  methods: {
    onSubmit(type) {
      this.form.type = type;
      this.$store.dispatch(LOGIN, this.form);
    }
  }
};
</script>

<style>
</style>
