<template>
  <div id="header">
    <nav class="navbar navbar-expand-lg fixed-top navbar-dark bg-dark my-nav-top">
      <img src="../assets/logo.png" class="navbar-brand-img" />
      <router-link class="navbar-brand my-navbar-brand" to="/">MelanoQ</router-link>
      <button class="navbar-toggler p-0 border-0" type="button" data-toggle="offcanvas">
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="navbar-collapse offcanvas-collapse">
        <ul class="navbar-nav mr-auto">
          <li class="nav-item">
            <router-link to="/" class="nav-link">{{ $t('home') }}</router-link>
          </li>
          <li class="nav-item">
            <router-link to="/about" class="nav-link">{{$t('about')}}</router-link>
          </li>
        </ul>
        <ul class="navbar-nav">
          <li class="nav-item db-code-number-container mr-3" v-if="isAuthenticated">
            <span class="nav-link db-code-number">
              {{$t('current_db_code_number')+': '}}
              <strong>{{((dbCodeNumber != null) ? dbCodeNumber : $t('not_available'))}}</strong>
            </span>
          </li>
          <!-- locale changer -->
          <li class="nav-item locale-container mt-1">
            <b-form-select v-model="$i18n.locale" v-on:input="storeLocale()" size="sm">
              <option v-for="(lang, i) in langs" :key="`Lang${i}`" :value="lang">{{ lang }}</option>
            </b-form-select>
          </li>
        </ul>
      </div>
    </nav>
    <div class="nav-scroller bg-white shadow-sm">
      <nav class="nav nav-underline">
        <span
          class="nav-link active my-nav-link-wrapper no-underline"
          href="#"
          v-if="!isAuthenticated"
        >{{$t('please_log_in')}}</span>

        <a
          class="nav-link active my-nav-link-wrapper sign-out-link-wrapper"
          href="#"
          @click="signOut()"
          v-if="isAuthenticated"
        >
          <font-awesome-icon icon="sign-out-alt" flip="horizontal" :style="{ color: 'black' }" />&nbsp;
          <span class="my-nav-link">{{$t('sign_out')}}</span>
        </a>
        <router-link
          class="nav-link active my-nav-link-wrapper"
          to="insert/user"
          v-if="isAuthenticated && user && user.role && user.role == 'admin'"
        >
          <font-awesome-icon icon="user-plus" :style="{ color: 'black' }" />&nbsp;
          <span class="my-nav-link">{{$t('add_new_user')}}</span>
        </router-link>

        <span class="nav-link active my-nav-link-wrapper nav-item-right" v-if="isAuthenticated">
          {{$t('username')+': '}}
          <strong>{{user.username}}</strong>
          &nbsp;-&nbsp;
          {{' '+$t('profile')+': '}}
          <strong>{{$t(user.type).toLowerCase()}}</strong>
        </span>
      </nav>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import { LOGOUT } from "@/store/actions.type";
import { saveLocale } from "@/common/locale.service";
import JQuery from "jquery";
let $ = JQuery;

export default {
  name: "TheHeader",
  data() {
    return { langs: Object.keys(this.$i18n.messages) };
  },
  methods: {
    storeLocale() {
      saveLocale(this.$i18n.locale);
      //window.localStorage.setItem("locale", this.$i18n.locale);
      window.location.reload();
    },
    signOut() {
      this.$store.dispatch(LOGOUT).then(() => {
        this.$router.push({ name: "home" });
      });
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

$(function() {
  "use strict";

  $('[data-toggle="offcanvas"]').on("click", function() {
    $(".offcanvas-collapse").toggleClass("open");
  });
});
</script>

<style>
.my-navbar-brand {
  font-weight: 400 !important;
}
.my-nav-link:hover {
  text-decoration: underline !important;
}
.sign-out-link-wrapper {
  padding-right: 0.5px !important;
}
.my-nav-link-wrapper:hover {
  color: #343a40 !important;
}
.nav-item-right {
  margin-left: auto !important;
}
.db-code-number,
.db-code-number:hover {
  color: rgba(255, 255, 255, 0.75) !important;
}

.db-code-number-container,
.locale-container {
  margin-right: 0px !important;
}
</style>
