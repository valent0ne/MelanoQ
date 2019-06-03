import ApiService from "@/common/api.service";
import JwtService from "@/common/jwt.service";
import { GET_A1 } from "./getters.type";
import {
  CHECK_AUTH,
  ADD_REST_ERROR,
  ADD_MESSAGE,
  INSERT_A1,
  UPDATE_A1,
  DELETE_A1
} from "./actions.type";
import { SET_A1, PURGE_A1 } from "./mutations.type";

const state = {
  a1: {

  }
};

const getters = {
  [GET_A1](state) {
    return state.a1;
  },

};

const actions = {
  [INSERT_A1](context, payload) {
    return new Promise(resolve => {
      ApiService.setHeader(JwtService.getToken())
      ApiService.post("questionnaire/" + context.getters[GET_DOC_ID]() + "/a1", { payload })
        .then(({ response }) => {
          context.commit(SET_A1, payload)
          context.dipatch(ADD_MESSAGE, response.message);
          resolve(response);
        })
        .catch(({ response }) => {
          context.dispatch(ADD_REST_ERROR, response);
        });
    });
  },
  [UPDATE_A1](context, payload) {
    return new Promise(resolve => {
      ApiService.setHeader(JwtService.getToken())
      ApiService.patch("questionnaire/" + context.getters[GET_DOC_ID]() + "/a1", { payload })
        .then(({ response }) => {
          context.commit(SET_A1, payload)
          context.dipatch(ADD_MESSAGE, response.message);
          resolve(response);
        })
        .catch(({ response }) => {
          context.dispatch(ADD_REST_ERROR, response);
        });
    });
  },
  [UPDATE_A1](context, payload) {
    return new Promise(resolve => {
      ApiService.setHeader(JwtService.getToken())
      ApiService.delete("questionnaire/" + context.getters[GET_DOC_ID]() + "/a1", {})
        .then(({ response }) => {
          context.commit(PURGE_A1)
          context.dipatch(ADD_MESSAGE, response.message);
          resolve(response);
        })
        .catch(({ response }) => {
          context.dispatch(ADD_REST_ERROR, response);
        });
    });
  }
};

const mutations = {

  [SET_A1](state, data) {
    state.a1 = data;
  },
  [PURGE_A1](state) {
    for (key in state.a1) {
      if (obj.hasOwnProperty(key)) {
        obj[key] = null;
      }
    }
  }
};

export default {
  state,
  actions,
  mutations,
  getters
};