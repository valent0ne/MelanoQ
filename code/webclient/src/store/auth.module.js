import ApiService from "@/common/api.service";
import JwtService from "@/common/jwt.service";
import {
  LOGIN,
  LOGOUT,
  CHECK_AUTH,
  ADD_REST_ERROR,
} from "./actions.type";
import { SET_AUTH, PURGE_AUTH } from "./mutations.type";

const state = {
  user: {},
  isAuthenticated: true, //!!JwtService.getToken(),
  // case, control, physician
  type: "physician"
};

const getters = {
  currentUser(state) {
    return state.user;
  },
  isAuthenticated(state) {
    return state.isAuthenticated;
  },
  type(state) {
    return state.type;
  }

};

const actions = {
  [LOGIN](context, credentials, type) {
    return new Promise(resolve => {
      ApiService.post("authentication", { credentials })
        .then(({ data }) => {
          context.commit(SET_AUTH, data.data, type);
          resolve(data);
        })
        .catch(({ response }) => {
          context.dispatch(ADD_REST_ERROR, response);
        });
    });
  },
  [LOGOUT](context) {
    context.commit(PURGE_AUTH);
  },
  [CHECK_AUTH](context) {
    if (JwtService.getToken()) {
      ApiService.setHeader();
      ApiService.get("user")
        .then(({ data }) => {
          context.commit(SET_AUTH, data.data);
        })
        .catch(({ response }) => {
          context.dispatch(ADD_REST_ERROR, response);
        });
    } else {
      context.commit(PURGE_AUTH);
    }
  }
};

const mutations = {

  [SET_AUTH](state, user, type) {
    state.isAuthenticated = true;
    state.user = user;
    state.type = type
    JwtService.saveToken(state.user.token);
  },
  [PURGE_AUTH](state) {
    state.isAuthenticated = false;
    state.user = {};
    state.type = "";
    JwtService.destroyToken();
  }
};

export default {
  state,
  actions,
  mutations,
  getters
};