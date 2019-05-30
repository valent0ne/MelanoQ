<template>
  <div id="header">
    <nav class="navbar navbar-expand-lg fixed-top navbar-dark bg-dark">
      <img src="../assets/logo.png" class="navbar-brand-img"/>
      <a  class="navbar-brand" href="#">MelanoQ </a>
      <button class="navbar-toggler p-0 border-0" type="button" data-toggle="offcanvas">
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="navbar-collapse offcanvas-collapse" id="navbarsExampleDefault">
        <ul class="navbar-nav mr-auto">
          <li class="nav-item">
            <router-link to="/" class="nav-link" >
              {{ $t('home') }}
            </router-link>
          </li>
          <li class="nav-item">
            <router-link to="/about" class="nav-link">About</router-link>
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
          </li> -->
        </ul>
        <!-- locale changer -->
        <div id="form-inline my-2 my-lg-0">
          <b-row>
            <b-col sm>
              <b-form-select v-model="$i18n.locale" size="sm" >
                <option v-for="(lang, i) in langs" :key="`Lang${i}`" :value="lang">{{ lang }}</option>
              </b-form-select>
            </b-col>
          </b-row>
        </div>

      </div>
    </nav>
    <div class="nav-scroller bg-white shadow-sm">
      <nav class="nav nav-underline">
        <a class="nav-link active" href="#">Dashboard</a>
        <a class="nav-link" href="#">
          Friends
          <span class="badge badge-pill bg-light align-text-bottom">27</span>
        </a>
        <a class="nav-link" href="#">Explore</a>
        <a class="nav-link" href="#">Suggestions</a>
        <a class="nav-link" href="#">Link</a>
        <a class="nav-link" href="#">Link</a>
        <a class="nav-link" href="#">Link</a>
        <a class="nav-link" href="#">Link</a>
        <a class="nav-link" href="#">Link</a>
      </nav>
    </div>
  </div>
</template>

<script>

export default {
  name: "TheHeader",
  data() {
    return { langs: Object.keys(this.$i18n.messages)};
  }
};
</script>

<style>
.my-navbar-brand{
  margin-right: 1rem!important;
}
</style>
