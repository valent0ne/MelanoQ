import ApiService from "@/common/api.service";
import JwtService from "@/common/jwt.service";
import {
  ADD_REST_ERROR,
  INSERT_C1,
  UPDATE_C1,
  DELETE_C1
} from "./actions.type";

const state = {

};

const getters = {

};

const actions = {
  [INSERT_C1](context, payload) {
    return new Promise(resolve => {
      ApiService.setHeader(JwtService.getToken())
      ApiService.post("questionnaire/" + context.getters.documentId + "/c1", { payload })
        .then(({ response }) => {
          resolve(response);
        })
        .catch(({ response }) => {
          context.dispatch(ADD_REST_ERROR, response);
        });
    });
  },
  [UPDATE_C1](context, payload) {
    return new Promise(resolve => {
      ApiService.setHeader(JwtService.getToken())
      ApiService.patch("questionnaire/" + context.getters.documentId + "/c1", { payload })
        .then(({ response }) => {
          resolve(response);
        })
        .catch(({ response }) => {
          context.dispatch(ADD_REST_ERROR, response);
        });
    });
  },
  [DELETE_C1](context) {
    return new Promise(resolve => {
      ApiService.setHeader(JwtService.getToken())
      ApiService.delete("questionnaire/" + context.getters.documentId + "/c1", {})
        .then(({ response }) => {
          resolve(response);
        })
        .catch(({ response }) => {
          context.dispatch(ADD_REST_ERROR, response);
        });
    });
  }
};

const mutations = {

};

export default {
  state,
  actions,
  mutations,
  getters
};