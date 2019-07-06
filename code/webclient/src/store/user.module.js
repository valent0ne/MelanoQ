import ApiService from "@/common/api.service";
import {
  ADD_ERROR,
  ADD_REST_ERROR,
  INSERT_USER
} from "./actions.type";

const state = {

};

const getters = {

};

const actions = {
  [INSERT_USER](context, payload) {
    return new Promise((resolve, reject) => {
      if (!context.getters.currentUser.token) {
        context.dispatch(ADD_ERROR, "no_token")
        return;
      }
      ApiService.setHeader(context.getters.currentUser.token)
      ApiService.post("user", payload)
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