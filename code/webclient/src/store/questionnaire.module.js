
import { INSERT_DB_CODE_NUMBER, DELETE_DB_CODE_NUMBER, GET_ALL_QUESTIONNAIRES, DELETE_QUESTIONNAIRES, ADD_ERROR, VALIDATE_DB_CODE_NUMBER } from "./actions.type";
import { SET_DB_CODE_NUMBER, PURGE_DB_CODE_NUMBER, SET_QUESTIONNAIRES, PURGE_QUESTIONNAIRES } from "./mutations.type";
import QuestionnaireService from "@/common/questionnaire.service";
import ApiService from '@/common/api.service';


const state = {
  questionnaires: [],
  dbCodeNumber: QuestionnaireService.getDbCodeNumber()
};

const getters = {
  dbCodeNumber(state) {
    return state.dbCodeNumber;
  },
  questionnaires(state) {
    return state.questionnaires
  }

};

const actions = {
  [VALIDATE_DB_CODE_NUMBER](context, dbCodeNumber) {
    if (!context.getters.currentUser.token) {
      context.dispatch(ADD_ERROR, "no_token")
      return;
    }
    return new Promise((resolve, reject) => {
      ApiService.setHeader(context.getters.currentUser.token)
      ApiService.get("/questionnaire/validate/" + dbCodeNumber)
        .then(({ data }) => {
          if (data.error || data.data == null) {
            throw data
          }
          resolve(data);
        })
        .catch((response) => {
          reject(response);
        });
    });
  },
  [INSERT_DB_CODE_NUMBER](context, dbCodeNumber) {
    context.commit(SET_DB_CODE_NUMBER, dbCodeNumber);
  },
  [DELETE_DB_CODE_NUMBER](context) {
    context.commit(PURGE_DB_CODE_NUMBER);
  },
  [GET_ALL_QUESTIONNAIRES](context) {
    if (!context.getters.currentUser.token) {
      context.dispatch(ADD_ERROR, "no_token")
      return;
    }
    return new Promise((resolve, reject) => {
      ApiService.setHeader(context.getters.currentUser.token)
      ApiService.get("/questionnaire/query")
        .then(({ data }) => {
          if (data.error || data.data == null) {
            throw data
          }
          context.commit(SET_QUESTIONNAIRES, data.data)
          resolve(data);
        })
        .catch((response) => {
          reject(response);
        });
    });
  },
  [DELETE_QUESTIONNAIRES](context) {
    context.commit(PURGE_QUESTIONNAIRES)
  }
};

const mutations = {
  [SET_QUESTIONNAIRES](state, data) {
    state.questionnaires = data
  },
  [PURGE_QUESTIONNAIRES](state) {
    state.questionnaires = []
  },
  [SET_DB_CODE_NUMBER](state, data) {
    state.dbCodeNumber = data.toUpperCase();
    QuestionnaireService.saveDbCodeNumber(data.toUpperCase())
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