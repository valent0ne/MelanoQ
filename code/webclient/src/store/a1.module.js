import ApiService from "@/common/api.service";
import {
  ADD_ERROR,
  ADD_REST_ERROR,
  INSERT_A1,
  UPDATE_A1,
  DELETE_A1,
  INSERT_DOC_ID
} from "./actions.type";

const state = {

};

const getters = {

};

const actions = {
  [INSERT_A1](context, payload) {
    context.dispatch(INSERT_DOC_ID, payload.dbCodeNumber);
    if (!context.getters.documentId) {
      context.dispatch(ADD_ERROR, "no_doc_id")
      return;
    }
    if (!context.getters.currentUser.token) {
      context.dispatch(ADD_ERROR, "no_token")
      return;
    }
    return new Promise((resolve, reject) => {
      ApiService.setHeader(context.getters.currentUser.token)
      ApiService.post("questionnaire/" + context.getters.documentId + "/a1", payload)
        .then(({ data }) => {
          if (data.error || data.data == null) {
            throw data
          }
          resolve(data);
        })
        .catch((response) => {
          context.dispatch(ADD_REST_ERROR, response);
          reject(response);
        });
    });
  },
  [UPDATE_A1](context, payload) {
    if (!context.getters.documentId) {
      context.dispatch(ADD_ERROR, "no_doc_id")
      return;
    }
    if (!context.getters.currentUser.token) {
      context.dispatch(ADD_ERROR, "no_token")
      return;
    }
    return new Promise((resolve, reject) => {
      ApiService.setHeader(context.getters.currentUser.token)
      ApiService.patch("questionnaire/" + context.getters.documentId + "/a1", payload)
        .then(({ data }) => {
          if (data.error || data.data == null) {
            throw data
          }
          resolve(data);
        })
        .catch((response) => {
          context.dispatch(ADD_REST_ERROR, response);
          reject(response);


        });
    });
  },
  [DELETE_A1](context) {
    if (!context.getters.documentId) {
      context.dispatch(ADD_ERROR, "no_doc_id")
      return;
    }
    if (!context.getters.currentUser.token) {
      context.dispatch(ADD_ERROR, "no_token")
      return;
    }
    return new Promise((resolve, reject) => {
      ApiService.setHeader(context.getters.currentUser.token)
      ApiService.delete("questionnaire/" + context.getters.documentId + "/a1", {})
        .then(({ data }) => {
          if (data.error || data.data == null) {
            throw data
          }
          resolve(data);
        })
        .catch((response) => {
          context.dispatch(ADD_REST_ERROR, response);
          reject(response);


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