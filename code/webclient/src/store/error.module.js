import { SET_REST_ERROR, SET_ERROR, PURGE_ERROR, PURGE_ERRORS, DO_UPDATE_ERROR_COUNT_DOWN } from "./mutations.type";
import { ADD_ERROR, ADD_REST_ERROR, DISMISS_ERROR, DISMISS_ERRORS, UPDATE_ERROR_COUNT_DOWN } from "./actions.type";
import Utils from '@/common/utils'

const state = {
  errors: [],
  dismissSecs: 5
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
  [DISMISS_ERRORS](context) {
    context.commit(PURGE_ERRORS)
  },
  [UPDATE_ERROR_COUNT_DOWN](context, key) {
    context.commit(DO_UPDATE_ERROR_COUNT_DOWN, key)
  }
}

const mutations = {
  [SET_REST_ERROR](state, response) {

    if (!response) {
      state.errors.push({ "key": Date.now(), "error": "connection_error", "dismissCountDown": state.dismissSecs })
    }
    else if (response.status) {
      state.errors.push({ "key": Date.now(), "error": (response.error) ? response.error.toLowerCase().replace(/ /g, '_') : "rest_error_" + response.status, "code": response.status, "dismissCountDown": state.dismissSecs })
    }
    else {
      state.errors.push({ "key": Date.now(), "error": "generic_error", "dismissCountDown": state.dismissSecs })
    }

  },
  [SET_ERROR](state, error) {
    state.errors.push({ "key": Date.now(), "error": error, "dismissCountDown": state.dismissSecs });
  },
  [PURGE_ERROR](state, key) {
    Utils.removeByObjectKey(state.errors, key)
  },
  [PURGE_ERRORS](state) {
    state.errors = []
  },
  [DO_UPDATE_ERROR_COUNT_DOWN](state, key) {
    var countDownValue = state.errors.find(x => x.key === key).dismissCountDown
    state.errors.find(x => x.key === key).dismissCountDown = countDownValue - 1
  }
};

export default {
  state,
  actions,
  mutations,
  getters
};