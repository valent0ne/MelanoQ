
import { INSERT_DB_CODE_NUMBER, DELETE_DB_CODE_NUMBER } from "./actions.type";
import { SET_DB_CODE_NUMBER, PURGE_DB_CODE_NUMBER } from "./mutations.type";
import QuestionnaireService from "@/common/questionnaire.service";


const state = {
  dbCodeNumber: QuestionnaireService.getDbCodeNumber()
};

const getters = {
  dbCodeNumber(state) {
    return state.dbCodeNumber;
  },

};

const actions = {
  [INSERT_DB_CODE_NUMBER](context, dbCodeNumber) {
    context.commit(SET_DB_CODE_NUMBER, dbCodeNumber);
  },
  [DELETE_DB_CODE_NUMBER](context) {
    context.commit(DELETE_DB_CODE_NUMBER);
  }
};

const mutations = {
  [SET_DB_CODE_NUMBER](state, data) {
    state.dbCodeNumber = data;
    QuestionnaireService.saveDbCodeNumber(data)
  },
  [PURGE_DB_CODE_NUMBER](state) {
    state.dbCodeNumber = null;
    QuestionnaireService.destroyDbCodeNumber()
  }
};

export default {
  state,
  actions,
  mutations,
  getters
};