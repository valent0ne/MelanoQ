import ApiService from "@/common/api.service";
import {
  ADD_ERROR,
  ADD_REST_ERROR,
  INSERT_B3,
  UPDATE_B3,
  DELETE_B3
} from "./actions.type";

const state = {

};

const getters = {

};

const actions = {
  [INSERT_B3](context, payload) {
    return new Promise((resolve, reject) => {
      if (!context.getters.documentId) {
        context.dispatch(ADD_ERROR, "no_doc_id")
        return;
      }
      if (!context.getters.currentUser.token) {
        context.dispatch(ADD_ERROR, "no_token")
        return;
      }
      ApiService.setHeader(context.getters.currentUser.token)
      ApiService.post("questionnaire/" + context.getters.documentId + "/b3", payload)
        .then(({ data }) => {
          if (data.error || data.data == null) {
            throw data
          }
          resolve(data);
        })
        .catch((response) => {
          context.dispatch(ADD_REST_ERROR, response);
          reject(response)

        });
    });
  },
  [UPDATE_B3](context, payload) {
    return new Promise((resolve, reject) => {
      if (!context.getters.documentId) {
        context.dispatch(ADD_ERROR, "no_doc_id")
        return;
      }
      if (!context.getters.currentUser.token) {
        context.dispatch(ADD_ERROR, "no_token")
        return;
      }
      ApiService.setHeader(context.getters.currentUser.token)
      ApiService.patch("questionnaire/" + context.getters.documentId + "/b3", payload)
        .then(({ data }) => {
          if (data.error || data.data == null) {
            throw data
          }
          resolve(data);
        })
        .catch((response) => {
          context.dispatch(ADD_REST_ERROR, response);
          reject(response)

        });
    });
  },
  [DELETE_B3](context) {
    return new Promise((resolve, reject) => {
      if (!context.getters.documentId) {
        context.dispatch(ADD_ERROR, "no_doc_id")
        return;
      }
      if (!context.getters.currentUser.token) {
        context.dispatch(ADD_ERROR, "no_token")
        return;
      }
      ApiService.setHeader(context.getters.currentUser.token)
      ApiService.delete("questionnaire/" + context.getters.documentId + "/b3", {})
        .then(({ data }) => {
          if (data.error || data.data == null) {
            throw data
          }
          resolve(data);
        })
        .catch((response) => {
          context.dispatch(ADD_REST_ERROR, response);
          reject(response)

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