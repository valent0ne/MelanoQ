import Vue from 'vue'
import App from './App.vue'
import store from './store'
import router from './router'
import i18n from './plugins/i18n'
import bootstrap from './plugins/bootstrap'
import vuelidate from './plugins/vuelidate'
import datepicker from './plugins/datepicker'
import moment from './plugins/moment'
import fontawesome from './plugins/fontawesome'
import jsontreeview from './plugins/jsontreeview'

import ApiService from "./common/api.service"

ApiService.init();

Vue.config.productionTip = false


new Vue({
  store,
  router,
  i18n,
  bootstrap,
  vuelidate,
  datepicker,
  moment,
  fontawesome,
  jsontreeview, render: h => h(App)
}).$mount('#app')
