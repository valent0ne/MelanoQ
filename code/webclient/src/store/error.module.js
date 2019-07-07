import { SET_REST_ERROR, SET_ERROR, PURGE_ERROR } from "./mutations.type";
import { ADD_ERROR, ADD_REST_ERROR, DISMISS_ERROR } from "./actions.type";

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
  [ADD_ERROR](context, error) {
    context.commit(SET_ERROR, error);
  },
  [DISMISS_ERROR](context) {
    context.commit(PURGE_ERROR);
  },
}

const mutations = {
  [SET_REST_ERROR](state, response) {
    // eslint-disable-next-line
    console.log("analysing error: " + response)
    // eslint-disable-next-line
    console.log("error: " + response.error)
    // eslint-disable-next-line
    console.log("status: " + response.status)
    if (!response) {
      state.error = "connection_error"
    }
    else if (response.error) {
      state.error = response.error;
    }
    else if (response.status) {
      state.error = "rest_error_".concat(response.status)
    }
    else {
      state.error = "connection_error"
    }

  },
  [SET_ERROR](state, error) {
    state.error = error;
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