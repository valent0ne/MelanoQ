import ApiService from "@/common/api.service";
import JwtService from "@/common/jwt.service";
import {
  ADD_REST_ERROR,
  INSERT_A2,
  UPDATE_A2,
  DELETE_A2
} from "./actions.type";

const state = {

};

const getters = {

};

const actions = {
  [INSERT_A2](context, payload) {
    return new Promise(resolve => {
      ApiService.setHeader(JwtService.getToken())
      ApiService.post("questionnaire/" + context.getters.documentId + "/a2", { payload })
        .then(({ response }) => {
          resolve(response);
        })
        .catch(({ response }) => {
          context.dispatch(ADD_REST_ERROR, response);
        });
    });
  },
  [UPDATE_A2](context, payload) {
    return new Promise(resolve => {
      ApiService.setHeader(JwtService.getToken())
      ApiService.patch("questionnaire/" + context.getters.documentId + "/a2", { payload })
        .then(({ response }) => {
          resolve(response);
        })
        .catch(({ response }) => {
          context.dispatch(ADD_REST_ERROR, response);
        });
    });
  },
  [DELETE_A2](context) {
    return new Promise(resolve => {
      ApiService.setHeader(JwtService.getToken())
      ApiService.delete("questionnaire/" + context.getters.documentId + "/a2", {})
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