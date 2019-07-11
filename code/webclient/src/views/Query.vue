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
              class="float-right mb-1 mt-4"
            >{{$t('reset')}}</b-button>
            <b-button
              type="submit"
              variant="outline-success"
              class="float-right mr-2 mb-1 mt-4"
            >{{$t('submit')}}</b-button>
          </b-form>
        </div>
      </b-card>

      <b-card class="mb-4">
        <div class="card-body d-flex flex-column">
          <b-card-text>
            {{$t('query_builder_result_text')}}
            <ul class="mt-2">
              <li class="button-list">
                <b-button variant="outline-success" size="sm" class="fix-btn-size">
                  <font-awesome-icon icon="check" />
                </b-button>
                {{$t('click_to_pick_db_code_number')}}
              </li>
              <li class="button-list">
                <b-button variant="outline-info" size="sm">
                  <font-awesome-icon icon="eye" />
                </b-button>
                {{$t('click_to_show_details')}}
              </li>
              <li>
                <b-button variant="outline-secondary" size="sm">
                  <font-awesome-icon icon="external-link-alt" />
                </b-button>
                {{$t('click_to_show_details_new_page')}}
              </li>
            </ul>

            <hr />
          </b-card-text>

          <!-- table begin -->
          <b-container fluid class="no-padding">
            <!-- User Interface controls -->
            <b-row>
              <b-col md="6" class="my-1 mb-3">
                <b-form-group label-cols-sm="3" :label="$t('filter')" class="mb-0">
                  <b-input-group>
                    <b-form-input v-model="filter" :placeholder="$t('type_to_search')"></b-form-input>
                    <b-input-group-append>
                      <b-button
                        variant="outline-danger"
                        :disabled="!filter"
                        @click="filter = ''"
                      >{{$t('clear')}}</b-button>
                    </b-input-group-append>
                  </b-input-group>
                </b-form-group>
              </b-col>

              <b-col md="6" class="my-1 mb-3">
                <b-form-group label-cols-sm="3" :label="$t('per_page')" class="mb-0">
                  <b-form-select v-model="perPage" :styled="true" :options="pageOptions"></b-form-select>
                </b-form-group>
              </b-col>
            </b-row>
            <!-- content -->
            <b-table
              id="table-transition"
              show-empty
              stacked="md"
              :items="questionnaires"
              :fields="fields"
              :current-page="currentPage"
              :per-page="perPage"
              :filter="filter"
              :sort-by.sync="sortBy"
              :sort-direction="sortDirection"
              :empty-text="$t('no_records_to_show')"
              :empty-filtered-text="$t('no_records_matching_your_request')"
              :busy="isBusy"
              @filtered="onFiltered"
            >
              <div slot="table-busy" class="text-center text-danger my-2">
                <b-spinner class="align-middle"></b-spinner>
                <span>&nbsp; {{$t('loading')}}</span>
              </div>
              <template slot="a1.dbCodeNumber" slot-scope="row">{{ row.item.a1.dbCodeNumber }}</template>
              <template slot="a1.subject" slot-scope="row">{{ row.item.a1.subject}}</template>
              <template
                slot="a1.datesOfUpdateQuestionnaire"
                slot-scope="row"
              >{{ $moment(row.item.a1.datesOfUpdateQuestionnaire[row.item.a1.datesOfUpdateQuestionnaire.length-1]).format("DD-MMM-YYYY HH:mm:ss")}}</template>

              <template
                slot="a1.dateOfQuestionnaireAdministration"
                slot-scope="row"
              >{{ row.item.a1.timeSdateOfQuestionnaireAdministration}}</template>

              <template slot="actions" slot-scope="row">
                <b-button
                  class="fix-btn-size"
                  :id="row.item.a1.dbCodeNumber+'check'"
                  variant="outline-success"
                  size="sm"
                  @click="setDbCodeNumber(row.item.a1.dbCodeNumber)"
                >
                  <font-awesome-icon icon="check" />
                </b-button>

                <b-button
                  :id="row.item.a1.dbCodeNumber+'eye'"
                  variant="outline-info ml-2"
                  size="sm"
                  @click="row.toggleDetails"
                >
                  <font-awesome-icon icon="eye" />
                </b-button>

                <router-link :to="'/questionnaire/'+row.item.a1.dbCodeNumber" target="_blank">
                  <b-button
                    :id="row.item.a1.dbCodeNumber+'external-link-alt'"
                    variant="outline-secondary ml-2"
                    size="sm"
                  >
                    <font-awesome-icon icon="external-link-alt" />
                  </b-button>
                </router-link>
              </template>

              <template slot="row-details" slot-scope="row">
                <b-card bg-variant="light">
                  <tree-view
                    :data="row.item"
                    :options="{maxDepth: 1, rootObjectKey: 'questionnaire'}"
                  ></tree-view>
                </b-card>
              </template>
            </b-table>

            <!-- pagination -->
            <b-row>
              <b-col md="6" class="my-1">
                <b-pagination
                  v-model="currentPage"
                  :total-rows="questionnaires.length"
                  :per-page="perPage"
                  class="my-0"
                ></b-pagination>
              </b-col>
            </b-row>
          </b-container>
        </div>
      </b-card>
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
import model from "@/assets/model.json";
import Utils from "@/common/utils";

export default {
  name: "query",
  components: { VueQueryBuilder, Message },

  data() {
    return {
      model: model,
      debug: false,
      query: {
        logicalOperator: "all",
        children: []
      },
      currentPage: 1,
      isBusy: false,
      perPage: 5,
      pageOptions: [5, 10, 15],
      sortBy: "a1.datesOfUpdateQuestionnaire",
      sortDirection: "asc",
      filter: null,
      fields: [
        {
          key: "a1.dbCodeNumber",
          label: this.$t("db_code_number"),
          sortable: true
        },
        {
          key: "a1.subject",
          label: this.$t("subject_table"),
          sortable: true
        },
        {
          key: "a1.datesOfUpdateQuestionnaire",
          label: this.$t("dates_of_update_questionnaire_table"),
          sortable: true
        },
        {
          key: "actions",
          label: this.$t("actions"),
          sortable: false
        }
      ],
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
    onFiltered(filteredItems) {
      // Trigger pagination to update the number of buttons/pages due to filtering
      this.totalRows = filteredItems.length;
      this.currentPage = 1;
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


