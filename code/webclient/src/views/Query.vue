<template>
  <div>
    <div class="home container my-4">
      <Message />

      <b-card v-if="isAuthenticated" class="mb-4">
        <div class="card-body d-flex flex-column">
          <b-card-title>
            {{$t('query_builder_title')}}
            <!-- buttons -->

            <b-button
              type="button"
              @click="back()"
              variant="outline-info"
              class="fa-button-outline float-right"
            >
              <font-awesome-icon icon="arrow-right" flip="horizontal" />
              &nbsp;
              {{$t('back')}}
            </b-button>
            <b-button
              id="debugButton"
              type="button"
              @click="debug = !debug"
              variant="light"
              class="fa-button-outline mr-2 float-right"
            >
              <font-awesome-icon icon="bug" />
            </b-button>
            <b-tooltip
              target="debugButton"
              :title="$t('click_to_debug')"
              placement="bottom"
              :delay="{show: 1000, hide:0}"
            ></b-tooltip>
          </b-card-title>
          <b-card-text class="line-break mt-2">{{$t('query_builder_text')}}</b-card-text>

          <div class="mt-2 mb-2">
            <vue-query-builder :rules="rules" :maxDepth="1" :styled="true" v-model="query"></vue-query-builder>
          </div>

          <div class="panel-default" v-if="debug">
            <p>{{$t('generated_query_object')}}:</p>

            <div class="panel-heading">
              <pre>{{ JSON.stringify(this.query, null, 2) }}</pre>
            </div>
          </div>
          <b-form @submit="submitQuery" @reset="resetQuery">
            <!-- buttons -->
            <b-button
              type="reset"
              variant="outline-danger"
              class="float-right mb-1 mt-2"
            >{{$t('reset')}}</b-button>
            <b-button
              type="submit"
              variant="outline-success"
              class="float-right mr-2 mb-1 mt-2"
            >{{$t('submit')}}</b-button>
          </b-form>
        </div>
      </b-card>

      <!-- questionnaire's list-->
      <TheTable :key="this.theTableComponentKey" />
    </div>
  </div>
</template>
<script>
import { mapState } from "vuex";
import VueQueryBuilder from "vue-query-builder";

import {
  ADD_MESSAGE,
  ADD_ERROR,
  ADD_REST_ERROR,
  INSERT_DB_CODE_NUMBER,
  QUERY
} from "@/store/actions.type";

import Message from "@/components/Message.vue";
import TheTable from "@/components/TheTable.vue";
import model from "@/assets/model.json";
import Utils from "@/common/utils";

export default {
  name: "query",
  components: { VueQueryBuilder, Message, TheTable },

  data() {
    return {
      model: model,
      debug: false,
      theTableComponentKey: 0,
      query: {
        logicalOperator: "all",
        children: []
      },

      rules: []
    };
  },

  created: function() {
    if (this.user.type !== "physician") {
      this.$store.dispatch(ADD_ERROR, "unauthorized");
      this.$router.push({ name: "home" });
    }
    if (!this.isAuthenticated) {
      this.$store.dispatch(ADD_ERROR, "not_authenticated");
      this.$router.push({ name: "home" });
    }
    //compute rules
    this.rules = Utils.buildRuleList(this.model, new Array());
  },
  methods: {
    submitQuery(evt) {
      evt.preventDefault();
      this.isBusy = true;
      this.$store
        .dispatch(QUERY, this.query)
        .then(() => {
          this.$store.dispatch(ADD_MESSAGE, "success_query");
          this.isBusy = false;
        })
        .catch(response => {
          this.$store.dispatch(ADD_REST_ERROR, response);
          this.isBusy = false;
        });
      this.theTableComponentKey += 1;
      document.body.scroll({
        top: 9999,
        left: 0,
        behavior: "smooth"
      });
    },
    resetQuery(evt) {
      evt.preventDefault();
      this.query.children = [];
    },
    setDbCodeNumber(dbCodeNumber) {
      this.$store
        .dispatch(INSERT_DB_CODE_NUMBER, dbCodeNumber)
        .then(() => {})
        .catch(response => {
          this.$store.dispatch(ADD_REST_ERROR, response);
        });
      this.$store.dispatch(ADD_MESSAGE, "db_code_number_saved");
    },

    back() {
      this.$router.back();
    }
  },
  computed: {
    ...mapState({
      user: state => state.auth.user,
      isAuthenticated: state => state.auth.isAuthenticated,
      dbCodeNumber: state => state.questionnaire.dbCodeNumber,
      questionnaires: state => state.questionnaire.questionnaires
    })
  }
};
</script>
<style>
@import "../assets/styles/querybuilder.css";
</style>


