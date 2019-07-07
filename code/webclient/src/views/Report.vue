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
              :disabled="(!canProceed)"
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
          <b-card-text>{{$t('select_from_available_questionnaires')}}</b-card-text>
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
  GET_ALL_QUESTIONNAIRES
} from "@/store/actions.type";

import { required, minLength, maxLength } from "vuelidate/lib/validators";

import Message from "@/components/Message.vue";

export default {
  name: "report",
  data() {
    return {
      canProceed: this.dbCodeNumber,
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
          .dispatch(GET_ALL_QUESTIONNAIRES, this.form)
          .then(() => {
            this.$store.dispatch(ADD_MESSAGE, "form_success");
            that.canProceed = true;
          })
          .catch(response => {
            this.$store.dispatch(ADD_REST_ERROR, response);
          });
      }
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
      dbCodeNumber: state => state.questionnaire.dbCodeNumber
    })
  }
};
</script>