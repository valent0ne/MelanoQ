import ApiService from "@/common/api.service";
import AuthService from "@/common/auth.service";
import {
  ADD_REST_ERROR,
  INSERT_USER
} from "./actions.type";

const state = {

};

const getters = {

};

const actions = {
  [INSERT_USER](context, payload) {
    return new Promise(resolve => {
      ApiService.setHeader(AuthService.getUser().token)
      ApiService.post("user/", payload)
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