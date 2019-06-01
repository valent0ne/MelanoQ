import { SET_MESSAGE, PURGE_MESSAGE } from "./mutations.type";
import { ADD_MESSAGE, DISMISS_MESSAGE } from "./actions.type";

const state = {
  message: null,
};

const getters = {
  message(state) {
    return state.message;
  },

};

const actions = {
  [ADD_MESSAGE](context, message) {
    context.commit(SET_MESSAGE, message);
  },
  [DISMISS_MESSAGE](context) {
    context.commit(PURGE_MESSAGE);
  },
}

const mutations = {
  [SET_MESSAGE](state, message) {
    state.message = message

  },
  [PURGE_MESSAGE](state) {
    state.message = null;
  },
};

export default {
  state,
  actions,
  mutations,
  getters
};