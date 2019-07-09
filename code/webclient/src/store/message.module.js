import { SET_MESSAGE, PURGE_MESSAGE, PURGE_MESSAGES, DO_UPDATE_MESSAGE_COUNT_DOWN } from "./mutations.type";
import { ADD_MESSAGE, DISMISS_MESSAGE, DISMISS_MESSAGES, UPDATE_MESSAGE_COUNT_DOWN } from "./actions.type";
import Utils from '@/common/utils'


const state = {
  messages: [],
  dismissSecs: 5
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
  [DISMISS_MESSAGES](context) {
    context.commit(PURGE_MESSAGES)
  },
  [UPDATE_MESSAGE_COUNT_DOWN](context, key) {
    context.commit(DO_UPDATE_MESSAGE_COUNT_DOWN, key)
  }
}

const mutations = {
  [SET_MESSAGE](state, message) {
    state.messages.push({ "key": Date.now(), "message": message, "dismissCountDown": state.dismissSecs })

  },
  [PURGE_MESSAGE](state, key) {
    Utils.removeByObjectKey(state.messages, key)
  },
  [PURGE_MESSAGES](state) {
    state.messages = []
  },
  [DO_UPDATE_MESSAGE_COUNT_DOWN](state, key) {
    var countDownValue = state.messages.find(x => x.key === key).dismissCountDown
    state.messages.find(x => x.key === key).dismissCountDown = countDownValue - 1
  }
};

export default {
  state,
  actions,
  mutations,
  getters
};