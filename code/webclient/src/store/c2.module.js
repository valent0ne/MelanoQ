import ApiService from "@/common/api.service";
import JwtService from "@/common/jwt.service";
import {
  ADD_REST_ERROR,
  INSERT_C2,
  UPDATE_C2,
  DELETE_C2
} from "./actions.type";

const state = {

};

const getters = {

};

const actions = {
  [INSERT_C2](context, payload) {
    return new Promise(resolve => {
      ApiService.setHeader(JwtService.getToken())
      ApiService.post("questionnaire/" + context.getters.documentId + "/c2", { payload })
        .then(({ response }) => {
          resolve(response);
        })
        .catch(({ response }) => {
          context.dispatch(ADD_REST_ERROR, response);
        });
    });
  },
  [UPDATE_C2](context, payload) {
    return new Promise(resolve => {
      ApiService.setHeader(JwtService.getToken())
      ApiService.patch("questionnaire/" + context.getters.documentId + "/c2", { payload })
        .then(({ response }) => {
          resolve(response);
        })
        .catch(({ response }) => {
          context.dispatch(ADD_REST_ERROR, response);
        });
    });
  },
  [DELETE_C2](context) {
    return new Promise(resolve => {
      ApiService.setHeader(JwtService.getToken())
      ApiService.delete("questionnaire/" + context.getters.documentId + "/c2", {})
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