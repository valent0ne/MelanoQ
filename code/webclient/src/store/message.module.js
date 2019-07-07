import { SET_MESSAGE, PURGE_MESSAGE } from "./mutations.type";
import { ADD_MESSAGE, DISMISS_MESSAGE } from "./actions.type";
import Utils from '@/common/utils'


const state = {
  messages: [],
};

const getters = {
  message(state) {
    return state.messages;
  },

};

const actions = {
  [ADD_MESSAGE](context, message) {
    context.commit(SET_MESSAGE, message);
  },
  [DISMISS_MESSAGE](context, key) {
    context.commit(PURGE_MESSAGE, key);
  },
}

const mutations = {
  [SET_MESSAGE](state, message) {
    state.messages.push({ "key": Date.now(), "message": message })

  },
  [PURGE_MESSAGE](state, key) {
    Utils.removeByObjectKey(state.messages, key)
  },
};

export default {
  state,
  actions,
  mutations,
  getters
};