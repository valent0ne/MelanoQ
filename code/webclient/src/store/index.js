import Vue from "vue";
import Vuex from "vuex";

import auth from "./auth.module";
import error from "./error.module";
import message from "./message.module";
import questionnaire from "./questionnaire.module";
import a1 from "./a1.module";
import a2 from "./a2.module";
import b1 from "./b1.module";
import b2 from "./b2.module";
import b3 from "./b3.module";
import be from "./be.module";


Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    auth,
    error,
    message,
    questionnaire,
    a1,
    a2,
    b1,
    b2,
    b3,
    be
  }
});