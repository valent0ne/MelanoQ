import Vue from "vue";
import Vuex from "vuex";

import auth from "./auth.module";
import error from "./error.module";
import message from "./message.module";
import questionnaire from "./questionnaire.module";
import a1 from "./a1.module";

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    auth,
    error,
    message,
    questionnaire,
    a1
  }
});