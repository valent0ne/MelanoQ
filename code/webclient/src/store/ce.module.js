import ApiService from "@/common/api.service";
import AuthService from "@/common/auth.service";
import {
  ADD_REST_ERROR,
  INSERT_CE,
  UPDATE_CE,
  DELETE_CE
} from "./actions.type";

const state = {

};

const getters = {

};

const actions = {
  [INSERT_CE](context, payload) {
    return new Promise(resolve => {
      ApiService.setHeader(AuthService.getUser().token)
      ApiService.post("questionnaire/" + context.getters.documentId + "/ce", { payload })
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
  [UPDATE_CE](context, payload) {
    return new Promise(resolve => {
      ApiService.setHeader(AuthService.getUser().token)
      ApiService.patch("questionnaire/" + context.getters.documentId + "/ce", { payload })
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
  [DELETE_CE](context) {
    return new Promise(resolve => {
      ApiService.setHeader(AuthService.getUser().token)
      ApiService.delete("questionnaire/" + context.getters.documentId + "/ce", {})
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