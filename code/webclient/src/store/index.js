import Vue from "vue";
import Vuex from "vuex";

import auth from "./auth.module";
import error from "./error.module";
import message from "./message.module";
import user from "./user.module";
import questionnaire from "./questionnaire.module";
import a1 from "./a1.module";
import a2 from "./a2.module";
import b1 from "./b1.module";
import b2 from "./b2.module";
import b3 from "./b3.module";
import be from "./be.module";
import c1 from "./c1.module";
import c2 from "./c2.module";
import c3 from "./c3.module";
import ce from "./ce.module";
import d from "./d.module";

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    auth,
    error,
    message,
    user,
    questionnaire,
    a1,
    a2,
    b1,
    b2,
    b3,
    be,
    c1,
    c2,
    c3,
    ce,
    d
  }
});