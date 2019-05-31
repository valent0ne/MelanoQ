import Vue from 'vue'
import './plugins/axios'
import App from './App.vue'
import store from './plugins/store'
import router from './plugins/router'
import i18n from './plugins/i18n'
import bootstrap from './plugins/bootstrap'
import vuelidate from './plugins/vuelidate'

import { library } from '@fortawesome/fontawesome-svg-core'
import { faUserSecret } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'

library.add(faUserSecret)

Vue.component('font-awesome-icon', FontAwesomeIcon)

Vue.config.productionTip = false

new Vue({
  store,
  router,
  i18n,
  bootstrap,
  vuelidate,
  render: h => h(App)
}).$mount('#app')
