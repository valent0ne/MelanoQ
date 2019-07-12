<template>
  <b-card class="mb-4">
    <div class="card-body d-flex flex-column">
      <b-card-text>
        {{$t('select_from_available_questionnaires')}}
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
        <b-row class="mb-3">
          <b-col md="5" class="my-auto">
            <b-form-group label-cols-sm="2" :label="$t('filter')" class="mb-0">
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

          <b-col md="3" class="my-auto">
            <b-form-group label-cols-sm="5" :label="$t('per_page')" class="mb-0">
              <b-form-select v-model="perPage" :options="pageOptions"></b-form-select>
            </b-form-group>
          </b-col>

          <b-col md="3" class="my-auto">
            <span>
              {{$t('#_of_results')+': '}}
              <span
                class="font-weight-600"
              >{{this.questionnaires? this.questionnaires.length : 0}}</span>
            </span>
          </b-col>

          <b-col md="1" class="my-auto">
            <b-button
              id="refreshListButton"
              variant="light"
              class="float-right"
              @click="refreshList()"
            >
              <font-awesome-icon icon="sync" />
            </b-button>
            <b-tooltip
              target="refreshListButton"
              :title="$t('click_to_refresh')"
              placement="top"
              :delay="{show: 1000, hide:0}"
            ></b-tooltip>
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
            <!--
                <b-tooltip
                  :target="row.item.a1.dbCodeNumber"
                  :title="$t('click_to_pick_db_code_number')"
                  placement="top"
                  :delay="{show: 1000, hide:0}"
            ></b-tooltip>-->
            <b-button
              :id="row.item.a1.dbCodeNumber+'eye'"
              variant="outline-info ml-2"
              size="sm"
              @click="row.toggleDetails"
            >
              <font-awesome-icon icon="eye" />
            </b-button>
            <!--
                <b-tooltip
                  :target="row.item.a1.dbCodeNumber+'1'"
                  :title="$t('click_to_show_details')"
                  placement="top"
                  :delay="{show: 1000, hide:0}"
            ></b-tooltip>-->
            <router-link :to="'/questionnaire/'+row.item.a1.dbCodeNumber" target="_blank">
              <b-button
                :id="row.item.a1.dbCodeNumber+'external-link-alt'"
                variant="outline-secondary ml-2"
                size="sm"
              >
                <font-awesome-icon icon="external-link-alt" />
              </b-button>
            </router-link>

            <!--
                <b-tooltip
                  :target="row.item.a1.dbCodeNumber+'2'"
                  :title="$t('click_to_show_details_new_page')"
                  placement="top"
                  :delay="{show: 1000, hide:0}"
            ></b-tooltip>-->
          </template>

          <template slot="row-details" slot-scope="row">
            <b-card bg-variant="light">
              <tree-view :data="row.item" :options="{maxDepth: 1, rootObjectKey: 'questionnaire'}"></tree-view>
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
</template>
<script>
import { mapState } from "vuex";
import {
  ADD_MESSAGE,
  ADD_ERROR,
  ADD_REST_ERROR,
  GET_ALL_QUESTIONNAIRES,
  INSERT_DB_CODE_NUMBER,
  DELETE_QUESTIONNAIRES
} from "@/store/actions.type";

export default {
  name: "lookup",
  data() {
    return {
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
      form: {
        dbCodeNumber: ""
      }
    };
  },

  created: function() {
    if (this.$route.path == "/query") {
      this.$store.dispatch(DELETE_QUESTIONNAIRES);
    } else {
      this.isBusy = true;
      this.$store
        .dispatch(GET_ALL_QUESTIONNAIRES)
        .then(() => {
          this.isBusy = false;
        })
        .catch(() => {
          this.$store.dispatch(ADD_ERROR, "cannot_retrieve_questionnaires");
          this.isBusy = false;
        });
    }
  },
  methods: {
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
    refreshList() {
      this.isBusy = true;

      document.body.scroll({
        top: 0,
        left: 0,
        behavior: "smooth"
      });

      this.$store
        .dispatch(GET_ALL_QUESTIONNAIRES)
        .then(() => {
          this.$store.dispatch(ADD_MESSAGE, "success");
          this.isBusy = false;
        })
        .catch(() => {
          this.$store.dispatch(ADD_ERROR, "cannot_update_questionnaires");
          this.isBusy = false;
        });
    },
    proceed() {
      this.$router.push("choice");
    }
  },
  props: {
    action: String
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

