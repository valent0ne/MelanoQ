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
      <b-card class="mb-4">
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
      <!-- questionnaire's list-->
      <TheTable />
    </div>
  </div>
</template>
<script>
import { mapState } from "vuex";
import {
  ADD_MESSAGE,
  ADD_ERROR,
  ADD_REST_ERROR,
  VALIDATE_DB_CODE_NUMBER,
  INSERT_DB_CODE_NUMBER
} from "@/store/actions.type";

import { required, minLength, maxLength } from "vuelidate/lib/validators";

import Message from "@/components/Message.vue";
import TheTable from "@/components/TheTable.vue";

export default {
  name: "lookup",
  data() {
    return {
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
    proceed() {
      this.$router.push("choice");
    }
  },
  components: {
    Message,
    TheTable
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
    })
  }
};
</script>

