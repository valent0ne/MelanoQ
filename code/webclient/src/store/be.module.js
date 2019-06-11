import ApiService from "@/common/api.service";
import JwtService from "@/common/jwt.service";
import {
  ADD_REST_ERROR,
  INSERT_BE,
  UPDATE_BE,
  DELETE_BE
} from "./actions.type";

const state = {

};

const getters = {

};

const actions = {
  [INSERT_BE](context, payload) {
    return new Promise(resolve => {
      ApiService.setHeader(JwtService.getToken())
      ApiService.post("questionnaire/" + context.getters.documentId + "/be", { payload })
        .then(({ response }) => {
          resolve(response);
        })
        .catch(({ response }) => {
          context.dispatch(ADD_REST_ERROR, response);
        });
    });
  },
  [UPDATE_BE](context, payload) {
    return new Promise(resolve => {
      ApiService.setHeader(JwtService.getToken())
      ApiService.patch("questionnaire/" + context.getters.documentId + "/be", { payload })
        .then(({ response }) => {
          resolve(response);
        })
        .catch(({ response }) => {
          context.dispatch(ADD_REST_ERROR, response);
        });
    });
  },
  [DELETE_BE](context) {
    return new Promise(resolve => {
      ApiService.setHeader(JwtService.getToken())
      ApiService.delete("questionnaire/" + context.getters.documentId + "/be", {})
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