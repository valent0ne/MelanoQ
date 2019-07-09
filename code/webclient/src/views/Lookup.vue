<template>
  <div>
    <div class="home container my-4">
      <Message />
      <b-card v-if="isAuthenticated" class="mb-4">
        <div class="card-body d-flex flex-column">
          <b-card-title>
            {{$t('complete_an_existing_questionnaire')}}
            <!-- buttons -->
            <b-button
              type="button"
              @click="proceed()"
              :disabled="!dbCodeNumber"
              variant="outline-info"
              class="fa-button-outline float-right"
            >
              {{$t('proceed_report')}}
              &nbsp;
              <font-awesome-icon icon="arrow-right" />&nbsp;
            </b-button>
          </b-card-title>
          <b-card-text class="line-break mt-2">{{$t('complete_an_existing_questionnaire_text')}}</b-card-text>
        </div>
      </b-card>
      <b-card v-if="isAuthenticated" class="mb-4">
        <div class="card-body d-flex flex-column">
          <b-card-text>{{$t('insert_db_code_number_manually')}}</b-card-text>
          <b-form @submit="onSubmit">
            <b-input-group>
              <b-form-input
                id="dbCodeNumber"
                v-model="$v.form.dbCodeNumber.$model"
                :state="$v.form.dbCodeNumber.$dirty ? !$v.form.dbCodeNumber.$error : null"
                type="text"
                required
              ></b-form-input>
              <b-input-group-append>
                <b-button type="submit" variant="outline-success">{{$t('validate')}}</b-button>
              </b-input-group-append>
            </b-input-group>
            <b-form-text>{{$t('dbCodeNumber_desc')}}</b-form-text>
          </b-form>
        </div>
      </b-card>
      <b-card v-if="isAuthenticated" class="mb-4">
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
            <b-row>
              <b-col md="5" class="my-1 mb-3">
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

              <b-col md="5" class="my-1 mb-3">
                <b-form-group label-cols-sm="3" :label="$t('per_page')" class="mb-0">
                  <b-form-select v-model="perPage" :options="pageOptions"></b-form-select>
                </b-form-group>
              </b-col>

              <b-col md="2" class="my-1 mb-3">
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
              :sort-desc.sync="sortDesc"
              :sort-direction="sortDirection"
              @filtered="onFiltered"
            >
              <template slot="a1.dbCodeNumber" slot-scope="row">{{ row.item.a1.dbCodeNumber }}</template>
              <template slot="a1.subject" slot-scope="row">{{ row.item.a1.subject}}</template>
              <template
                slot="a1.datesOfUpdateQuestionnaire"
                slot-scope="row"
              >{{ row.item.a1.datesOfUpdateQuestionnaire[row.item.a1.datesOfUpdateQuestionnaire.length-1]? row.item.a1.datesOfUpdateQuestionnaire[row.item.a1.datesOfUpdateQuestionnaire.length-1] : '-'}}</template>

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
import {
  ADD_MESSAGE,
  ADD_ERROR,
  ADD_REST_ERROR,
  GET_ALL_QUESTIONNAIRES,
  VALIDATE_DB_CODE_NUMBER,
  INSERT_DB_CODE_NUMBER
} from "@/store/actions.type";

import { required, minLength, maxLength } from "vuelidate/lib/validators";

import Message from "@/components/Message.vue";

export default {
  name: "lookup",
  data() {
    return {
      currentPage: 1,
      perPage: 5,
      pageOptions: [5, 10, 15],
      sortBy: "a1.datesOfUpdateQuestionnaire",
      sortDesc: true,
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
          sortable: true,
          sortDirection: "desc"
        },
        {
          key: "a1.datesOfUpdateQuestionnaire",
          label: this.$t("dates_of_update_questionnaire_table"),
          sortable: true
        },
        {
          key: "actions",
          label: this.$t("actions"),
          sortable: false,
          sortDirection: null
        }
      ],
      form: {
        dbCodeNumber: ""
      }
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
    this.$store
      .dispatch(GET_ALL_QUESTIONNAIRES)
      .then(() => {})
      .catch(() => {
        this.$store.dispatch(ADD_ERROR, "cannot_retrieve_questionnaires");
      });
  },
  methods: {
    onSubmit(evt) {
      evt.preventDefault();
      document.body.scroll({
        top: 0,
        left: 0,
        behavior: "smooth"
      });
      this.$v.$touch();
      // eslint-disable-next-line
      console.log(JSON.stringify(this.form));
      if (this.$v.$invalid) {
        this.$store.dispatch(ADD_ERROR, "form_dirty");
      } else {
        var that = this;

        this.$store
          .dispatch(VALIDATE_DB_CODE_NUMBER, this.form.dbCodeNumber)
          .then(() => {
            this.$store
              .dispatch(INSERT_DB_CODE_NUMBER, that.form.dbCodeNumber)
              .then(() => {})
              .catch(response => {
                this.$store.dispatch(ADD_REST_ERROR, response);
              });
            this.$store.dispatch(ADD_MESSAGE, "db_code_number_saved");
          })
          .catch(response => {
            this.$store.dispatch(ADD_REST_ERROR, response);
          });
      }
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
    refreshList() {
      document.body.scroll({
        top: 0,
        left: 0,
        behavior: "smooth"
      });

      this.$store
        .dispatch(GET_ALL_QUESTIONNAIRES)
        .then(() => {
          this.$store.dispatch(ADD_MESSAGE, "success");
        })
        .catch(() => {
          this.$store.dispatch(ADD_ERROR, "cannot_retrieve_questionnaires");
        });
    },
    proceed() {
      this.$router.push("choice");
    }
  },
  components: {
    Message
  },
  props: {
    action: String
  },
  validations: {
    form: {
      dbCodeNumber: {
        required,
        minLength: minLength(9),
        maxLength: maxLength(9)
      }
    }
  },
  computed: {
    ...mapState({
      user: state => state.auth.user,
      isAuthenticated: state => state.auth.isAuthenticated,
      dbCodeNumber: state => state.questionnaire.dbCodeNumber,
      questionnaires: state => state.questionnaire.questionnaires
    }),
    sortOptions() {
      // Create an options list from our fields
      return this.fields
        .filter(f => f.sortable)
        .map(f => {
          return { text: f.label, value: f.key };
        });
    }
  }
};
</script>

