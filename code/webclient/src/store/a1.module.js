import ApiService from "@/common/api.service";
import AuthService from "@/common/auth.service";
import {
  ADD_REST_ERROR,
  INSERT_A1,
  UPDATE_A1,
  DELETE_A1
} from "./actions.type";

const state = {

};

const getters = {

};

const actions = {
  [INSERT_A1](context, payload) {
    return new Promise(resolve => {
      ApiService.setHeader(AuthService.getUser().token)
      ApiService.post("questionnaire/" + context.getters.documentId + "/a1", { payload })
        .then(({ data }) => {
          if (data.error || data.data == null) {
            throw data
          }
          resolve(data);
        })
        .catch((response) => {
          context.dispatch(ADD_REST_ERROR, response);
        });
    });
  },
  [UPDATE_A1](context, payload) {
    return new Promise(resolve => {
      ApiService.setHeader(AuthService.getUser().token)
      ApiService.patch("questionnaire/" + context.getters.documentId + "/a1", { payload })
        .then(({ data }) => {
          if (data.error || data.data == null) {
            throw data
          }
          resolve(data);
        })
        .catch((response) => {
          context.dispatch(ADD_REST_ERROR, response);
        });
    });
  },
  [DELETE_A1](context) {
    return new Promise(resolve => {
      ApiService.setHeader(AuthService.getUser().token)
      ApiService.delete("questionnaire/" + context.getters.documentId + "/a1", {})
        .then(({ data }) => {
          if (data.error || data.data == null) {
            throw data
          }
          resolve(data);
        })
        .catch((response) => {
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