import ApiService from "@/common/api.service";
import JwtService from "@/common/jwt.service";
import { INSERT_DOC_ID, DELETE_DOC_ID } from "./actions.type";
import { SET_DOC_ID, PURGE_DOC_ID } from "./mutations.type";

const state = {
  documentId: ""
};

const getters = {
  documentId(state) {
    return state.documentId;
  },

};

const actions = {
  [INSERT_DOC_ID](context, documentId) {
    context.dipatch(SET_DOC_ID, documentId);
  },
  [DELETE_DOC_ID](context) {
    context.dipatch(DELETE_DOC_ID);
  }
};

const mutations = {
  [SET_DOC_ID](state, data) {
    state.documentId = data;
  },
  [PURGE_DOC_ID](state) {
    state.documentId = null;
  }
};

export default {
  state,
  actions,
  mutations,
  getters
};