
import { INSERT_DOC_ID, DELETE_DOC_ID } from "./actions.type";
import { SET_DOC_ID, PURGE_DOC_ID } from "./mutations.type";
import QuestionnaireService from "@/common/questionnaire.service";


const state = {
  documentId: QuestionnaireService.getDocumentId()
};

const getters = {
  documentId(state) {
    return state.documentId;
  },

};

const actions = {
  [INSERT_DOC_ID](context, documentId) {
    context.commit(SET_DOC_ID, documentId);
  },
  [DELETE_DOC_ID](context) {
    context.commit(DELETE_DOC_ID);
  }
};

const mutations = {
  [SET_DOC_ID](state, data) {
    state.documentId = data;
    QuestionnaireService.saveDocumentId(data)
  },
  [PURGE_DOC_ID](state) {
    state.documentId = null;
    QuestionnaireService.destroyDocumentId()
  }
};

export default {
  state,
  actions,
  mutations,
  getters
};