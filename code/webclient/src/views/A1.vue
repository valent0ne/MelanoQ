<template>
  <div>
    <div class="home container my-4">
      <Message/>
      <b-card>
        <div class="card-body d-flex flex-column">
          <b-card-title>{{$t('section_a')+'.I'}}</b-card-title>
          <div>
            <b-form @submit="onSubmit" @reset="onReset" v-if="show">
              <label for="dbCodeNumber" class="mt-4">
                <h6>{{'1. '+$t('subject_label')+': '+$t(this.type)}}</h6>
              </label>
              <br>

              <!-- dbCodeNumber -->
              <label for="dbCodeNumber" class="mt-4">
                <h6>{{'2. '+$t('dbCodeNumber_label')}}</h6>
              </label>
              <b-form-input
                id="dbCodeNumber"
                v-model="$v.form.dbCodeNumber.$model"
                :state="$v.form.dbCodeNumber.$dirty ? !$v.form.dbCodeNumber.$error : null"
                type="text"
                required
              ></b-form-input>
              <b-form-text>{{$t('dbCodeNumber_desc')}}</b-form-text>

              <!-- dateOfQuestionnaireAdministration -->
              <label for="dateOfQuestionnaireAdministration" class="mt-4">
                <h6>{{'3. '+$t('dateOfQuestionnaireAdministration_label')}}</h6>
              </label>
              <date-picker
                name="dateOfQuestionnaireAdministration"
                v-model="form.dateOfQuestionnaireAdministration"
                :config="datePickerOptions"
                required
              ></date-picker>
              <b-form-text>{{$t('dateOfQuestionnaireAdministration_desc')}}</b-form-text>

              <label for="typeOfMelanoma" class="mt-4">
                <h6>{{'5. '+$t('typeOfMelanoma_label')}}</h6>
              </label>

              <b-form-select
                id="typeOfMelanoma"
                v-model="$v.form.typeOfMelanoma.$model"
                :state="$v.form.typeOfMelanoma.$dirty ? !$v.form.typeOfMelanoma.$error : null"
                :options="typeOfMelanomaOptions"
              >{{$t('typeOfMelanoma_desc')}}</b-form-select>

              <!-- buttons -->
              <b-button
                type="submit"
                variant="outline-success"
                class="mr-2 mb-1 mt-5"
              >{{$t('submit')}}</b-button>
              <b-button type="reset" variant="outline-danger" class="mr-2 mb-1 mt-5">{{$t('reset')}}</b-button>

              <b-button
                type="button"
                @click="proceed()"
                :disabled="(!canProceed)"
                variant="outline-info"
                class="fa-button-outline mr-2 mb-1 mt-5 float-right"
              >
                {{$t('proceed')}}
                &nbsp;
                <font-awesome-icon icon="arrow-right"/>&nbsp;
              </b-button>
              <b-button
                type="button"
                to="/choice"
                variant="outline-info"
                class="fa-button-outline mr-2 mb-1 mt-5 float-right"
              >
                <font-awesome-icon icon="arrow-right" flip="horizontal"/>
                &nbsp;
                {{$t('back_to_section_choice')}}
              </b-button>
            </b-form>
          </div>
        </div>
      </b-card>
    </div>
    <br>
    <br>
  </div>
</template>
<script>
import { mapState } from "vuex";
import { ADD_MESSAGE, ADD_ERROR, INSERT_A1 } from "@/store/actions.type";
import {
  required,
  minLength,
  maxLength,
  requiredIf
} from "vuelidate/lib/validators";

import Message from "@/components/Message.vue";

export default {
  name: "a1",
  data() {
    return {
      form: {
        dbCodeNumber: "",
        dateOfQuestionnaireAdministration: this.$moment().format("DD/MMM/YYYY"),
        typeOfMelanoma: null
      },
      show: true,
      canProceed: false,
      typeOfMelanomaOptions: [
        { value: null, text: this.$t("please_select_option") },
        { value: "Sporadic", text: "Sporadic" },
        { value: "Familial", text: "Familial" },
        { value: "Don’t know", text: "Don’t know" },
        { value: "Other", text: "Other" }
      ],
      datePickerOptions: {
        // https://momentjs.com/docs/#/displaying/
        format: "DD/MMM/YYYY",
        useCurrent: true,
        showClear: true,
        showClose: true
      }
    };
  },
  created: function() {
    if (this.type !== "physician") {
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

      this.$v.$touch();
      // eslint-disable-next-line
      console.log(JSON.stringify(this.form));
      if (this.$v.$invalid) {
        this.$store.dispatch(ADD_ERROR, "form_dirty");
      } else {
        var that = this;
        this.$store.dispatch(INSERT_A1, this.form).then(() => {
          this.$store.dispatch(ADD_MESSAGE, "form_success");
          that.canProceed = true;
        });
      }
    },
    onReset(evt) {
      evt.preventDefault();
      //TODO reset
      this.form.dbCodeNumber = "";
      this.form.dateOfQuestionnaireAdministration = this.$moment().format(
        "DD/MMM/YYYY"
      );
      this.form.typeOfMelanoma = null;
      this.$v.$touch();

      // Trick to reset/clear native browser form validation state
      this.show = false;
      this.$nextTick(() => {
        this.show = true;
      });
    },
    proceed() {
      if (this.canProceed) {
        this.$router.push({ name: "a2" });
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
      },
      dateOfQuestionnaireAdministration: { required },
      typeOfMelanoma: {
        required: requiredIf(function() {
          return this.type !== "control";
        })
      }
      // typeOfMelanoma: {}
    }
  },
  computed: {
    ...mapState({
      user: state => state.auth.user,
      isAuthenticated: state => state.auth.isAuthenticated,
      type: state => state.auth.type
    })
  }
};
</script>