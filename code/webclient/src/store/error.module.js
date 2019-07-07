import { SET_REST_ERROR, SET_ERROR, PURGE_ERROR } from "./mutations.type";
import { ADD_ERROR, ADD_REST_ERROR, DISMISS_ERROR } from "./actions.type";
import Utils from '@/common/utils'

const state = {
  errors: [],
};

const getters = {
  errors(state) {
    return state.errors;
  },

};

const actions = {
  [ADD_REST_ERROR](context, response) {
    context.commit(SET_REST_ERROR, response);
  },
  [ADD_ERROR](context, error) {
    context.commit(SET_ERROR, error);
  },
  [DISMISS_ERROR](context, key) {
    context.commit(PURGE_ERROR, key);
  },
}

const mutations = {
  [SET_REST_ERROR](state, response) {

    if (!response) {
      state.errors.push({ "key": Date.now(), "error": "connection_error" })
    }
    else if (response.status) {
      state.errors.push({ "key": Date.now(), "error": "rest_error_".concat(response.status), "code": response.status })
    }
    else if (response.error) {
      state.errors.push({ "key": Date.now(), "error": response.error });
    }
    else {
      state.errors.push({ "key": Date.now(), "error": "connection_error" })
    }

  },
  [SET_ERROR](state, error) {
    state.errors.push({ "key": Date.now(), "error": error });
  },
  [PURGE_ERROR](state, key) {
    Utils.removeByObjectKey(state.errors, key)
  },
};

export default {
  state,
  actions,
  mutations,
  getters
};