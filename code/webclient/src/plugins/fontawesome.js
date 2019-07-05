import Vue from 'vue'

import { library } from '@fortawesome/fontawesome-svg-core'
import { faSignOutAlt, faCheck, faTimes, faArrowRight, faPlus, faMinus, faCircle, faUserPlus } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'

library.add(faSignOutAlt, faCheck, faTimes, faArrowRight, faPlus, faMinus, faCircle, faUserPlus)

Vue.component('font-awesome-icon', FontAwesomeIcon)

export default {

}