import Vue from "vue";
import axios from "axios";
import VueAxios from "vue-axios";
import AuthService from "@/common/auth.service";
import { API_URL } from "@/common/config";

const ApiService = {
  init() {
    Vue.use(VueAxios, axios);
    Vue.axios.defaults.baseURL = API_URL;
    Vue.axios.interceptors.response.use((response) => {
      /* eslint-disable */
      // console.log(JSON.stringify(response))
      return response;
    }, (error) => {
      console.log("rest error:")
      console.log(JSON.stringify(error))
      return Promise.reject(error)
    });
  },

  setHeader() {
    Vue.axios.defaults.headers.common['Authorization'] = 'Bearer ' + AuthService.getUser().token;
  },

  query(resource, params) {
    return Vue.axios.get(resource, params)
  },

  get(resource, slug = "") {
    return Vue.axios.get(`${resource}/${slug}`)
  },

  post(resource, params) {
    return Vue.axios.post(`${resource}`, params)
  },

  update(resource, slug, params) {
    return Vue.axios.put(`${resource}/${slug}`, params);
  },

  put(resource, params) {
    return Vue.axios.put(`${resource}`, params);
  },

  delete(resource) {
    return Vue.axios.delete(resource)
  }
};

export default ApiService;