import ApiService from "@/common/api.service";
import {
  ADD_ERROR,
  INSERT_USER
} from "./actions.type";

const state = {

};

const getters = {

};

const actions = {
  [INSERT_USER](context, payload) {
    if (!context.getters.currentUser.token) {
      context.dispatch(ADD_ERROR, "no_token")
      return;
    }
    return new Promise((resolve, reject) => {

      ApiService.setHeader(context.getters.currentUser.token)
      ApiService.post("user", payload)
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