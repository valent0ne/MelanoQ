import ApiService from "@/common/api.service";
import {
  ADD_ERROR,
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
    if (!context.getters.dbCodeNumber) {
      context.dispatch(ADD_ERROR, "no_db_code_number")
      return;
    }
    if (!context.getters.currentUser.token) {
      context.dispatch(ADD_ERROR, "no_token")
      return;
    }
    return new Promise((resolve, reject) => {

      ApiService.setHeader(context.getters.currentUser.token)
      ApiService.post("questionnaire/" + context.getters.dbCodeNumber + "/ce", payload)
        .then(({ data }) => {
          if (data.error || data.data == null) {
            throw data
          }
          resolve(data);
        })
        .catch((response) => {

          reject(response)

        });
    });
  },
  [UPDATE_CE](context, payload) {
    if (!context.getters.dbCodeNumber) {
      context.dispatch(ADD_ERROR, "no_db_code_number")
      return;
    }
    if (!context.getters.currentUser.token) {
      context.dispatch(ADD_ERROR, "no_token")
      return;
    }
    return new Promise((resolve, reject) => {

      ApiService.setHeader(context.getters.currentUser.token)
      ApiService.patch("questionnaire/" + context.getters.dbCodeNumber + "/ce", payload)
        .then(({ data }) => {
          if (data.error || data.data == null) {
            throw data
          }
          resolve(data);
        })
        .catch((response) => {

          reject(response)

        });
    });
  },
  [DELETE_CE](context) {
    if (!context.getters.dbCodeNumber) {
      context.dispatch(ADD_ERROR, "no_db_code_number")
      return;
    }
    if (!context.getters.currentUser.token) {
      context.dispatch(ADD_ERROR, "no_token")
      return;
    }
    return new Promise((resolve, reject) => {

      ApiService.setHeader(context.getters.currentUser.token)
      ApiService.delete("questionnaire/" + context.getters.dbCodeNumber + "/ce", {})
        .then(({ data }) => {
          if (data.error || data.data == null) {
            throw data
          }
          resolve(data);
        })
        .catch((response) => {

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