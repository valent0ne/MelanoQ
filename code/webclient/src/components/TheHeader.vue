<template>
  <div id="header">
    <nav class="navbar navbar-expand-lg fixed-top navbar-dark bg-dark my-nav-top">
      <img src="../assets/logo.png" class="navbar-brand-img">
      <router-link class="navbar-brand my-navbar-brand" to="/">MelanoQ</router-link>

      <div class="navbar-collapse offcanvas-collapse">
        <ul class="navbar-nav mr-auto">
          <li class="nav-item">
            <router-link to="/" class="nav-link">{{ $t('home') }}</router-link>
          </li>
          <li class="nav-item">
            <router-link to="/about" class="nav-link">{{$t('about')}}</router-link>
          </li>

          <!-- dropdown
          <li class="nav-item dropdown">
            <a
              class="nav-link dropdown-toggle"
              href="https://example.com"
              id="dropdown01"
              data-toggle="dropdown"
              aria-haspopup="true"
              aria-expanded="false"
            >Settings</a>
            <div class="dropdown-menu" aria-labelledby="dropdown01">
              <a class="dropdown-item" href="#">Action</a>
              <a class="dropdown-item" href="#">Another action</a>
              <a class="dropdown-item" href="#">Something else here</a>
            </div>
          </li>-->
        </ul>
        <!-- locale changer -->
        <div id="form-inline my-2 my-lg-0">
          <b-form-select v-model="$i18n.locale" size="sm">
            <option v-for="(lang, i) in langs" :key="`Lang${i}`" :value="lang">{{ lang }}</option>
          </b-form-select>
        </div>
      </div>
    </nav>
    <div class="nav-scroller bg-white shadow-sm">
      <nav class="nav nav-underline">
        <a
          class="nav-link active my-nav-link-wrapper"
          href="#"
          @click="signOut()"
          v-if="isAuthenticated"
        >
          <font-awesome-icon icon="sign-out-alt" flip="horizontal" :style="{ color: 'black' }"/>&nbsp;
          <span class="my-nav-link">{{$t('sign_out')}}</span>
        </a>
        <!--
        <div>
          <b-nav-dropdown>
            <template slot="button-content">{{$t('section_a')}}</template>
            <b-dropdown-item class="my-dropdown-item" href="#">A.I</b-dropdown-item>
            <b-dropdown-item class="my-dropdown-item" href="#">A.II</b-dropdown-item>
          </b-nav-dropdown>
        </div>
        <div>
          <b-nav-dropdown>
            <template slot="button-content">{{$t('section_b')}}</template>
            <b-dropdown-item class="my-dropdown-item" href="#">B.I</b-dropdown-item>
            <b-dropdown-item class="my-dropdown-item" href="#">B.II</b-dropdown-item>
          </b-nav-dropdown>
        </div>
        <div>
          <b-nav-dropdown>
            <template slot="button-content">{{$t('section_c')}}</template>
            <b-dropdown-item class="my-dropdown-item" href="#">C.I</b-dropdown-item>
            <b-dropdown-item class="my-dropdown-item" href="#">C.II</b-dropdown-item>
          </b-nav-dropdown>
        </div>
        <div>
          <b-nav-dropdown>
            <template slot="button-content">{{$t('section_d')}}</template>
            <b-dropdown-item class="my-dropdown-item" href="#">D.I</b-dropdown-item>
          </b-nav-dropdown>
        </div>
        -->
      </nav>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import { LOGOUT, ADD_MESSAGE } from "@/store/actions.type";

export default {
  name: "TheHeader",
  data() {
    return { langs: Object.keys(this.$i18n.messages) };
  },
  methods: {
    signOut() {
      this.$store.dispatch(LOGOUT).then(() => {
        this.$store.dispatch(ADD_MESSAGE, "success");
        this.$router.push({ name: "home" });
      });
    }
  },
  computed: {
    ...mapState({
      isAuthenticated: state => state.auth.isAuthenticated
    })
  }
};
</script>

<style>
.my-navbar-brand {
  font-weight: 400 !important;
}
.my-nav-link:hover {
  text-decoration: underline !important;
}
.my-nav-link-wrapper:hover {
  color: #343a40 !important;
}
</style>
