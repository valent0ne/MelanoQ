import { SET_REST_ERROR, PURGE_ERROR } from "./mutations.type";
import { ADD_REST_ERROR, DISMISS_ERROR } from "./actions.type";

const state = {
  error: null,
};

const getters = {
  error(state) {
    return state.error;
  },

};

const actions = {
  [ADD_REST_ERROR](context, response) {
    context.commit(SET_REST_ERROR, response);
  },
  [DISMISS_ERROR](context) {
    context.commit(PURGE_ERROR);
  },
}

const mutations = {
  [SET_REST_ERROR](state, response) {
    if (response.error) {
      state.error = response.error;
    }
    if (response.status < 200 || response.status >= 300) {
      state.error = "Cannot communicate with the server"
    }

  },
  [PURGE_ERROR](state) {
    state.error = null;
  },
};

export default {
  state,
  actions,
  mutations,
  getters
};