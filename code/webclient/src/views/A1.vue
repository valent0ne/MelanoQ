<template>
  <!-- https://bootstrap-vue.js.org/docs/components/form -->
  <div>
    <div class="home container my-5">
      <Message/>
      <b-card>
        <div class="card-body d-flex flex-column">
          <b-card-title>{{$t('section_a')+'.I'}}</b-card-title>
          <div>
            <b-form @submit="onSubmit" @reset="onReset" v-if="show">
              <!-- dbCodeNumber -->
              <label for="dbCodeNumber" class="mt-4">{{$t('dbCodeNumber_label')}}</label>
              <b-form-input
                id="dbCodeNumber"
                v-model="$v.a1.dbCodeNumber.$model"
                :state="$v.a1.dbCodeNumber.$dirty ? !$v.a1.dbCodeNumber.$error : null"
                type="text"
                required
              ></b-form-input>
              <b-form-text>{{$t('dbCodeNumber_desc')}}</b-form-text>

              <!-- dateOfQuestionnaireAdministration -->
              <label
                for="dateOfQuestionnaireAdministration"
                class="mt-4"
              >{{$t('dateOfQuestionnaireAdministration_label')}}</label>
              <date-picker
                name="dateOfQuestionnaireAdministration"
                v-model="a1.dateOfQuestionnaireAdministration"
                :config="datePickerOptions"
                required
              ></date-picker>
              <b-form-text>{{$t('dateOfQuestionnaireAdministration_desc')}}</b-form-text>

              <!-- typeOfMelanoma -->
              <!--<label
                :class="{ 'text-muted' : (type === 'control')}"
                for="typeOfMelanoma"
                class="mt-4"
              >{{$t('typeOfMelanoma_label')}}</label>-->
              <label for="typeOfMelanoma" class="mt-4">{{$t('typeOfMelanoma_label')}}</label>
              <!--<b-form-input
                id="typeOfMelanoma"
                v-model="$v.a1.typeOfMelanoma.$model"
                :state="$v.a1.typeOfMelanoma.$dirty ? !$v.a1.typeOfMelanoma.$error : null"
                type="text"
                :disabled="(type === 'control')"
              ></b-form-input>-->
              <b-form-input
                id="typeOfMelanoma"
                v-model="$v.a1.typeOfMelanoma.$model"
                :state="$v.a1.typeOfMelanoma.$dirty ? !$v.a1.typeOfMelanoma.$error : null"
                type="text"
              ></b-form-input>
              <b-form-text>{{$t('typeOfMelanoma_desc')}}</b-form-text>

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
                class="mr-2 mb-1 mt-5 float-right"
              >{{$t('proceed')}}</b-button>
            </b-form>
          </div>
        </div>
      </b-card>
    </div>
  </div>
</template>
<script>
import { mapState } from "vuex";
import { ADD_MESSAGE, ADD_ERROR, INSERT_A1 } from "@/store/actions.type";
import {
  required,
  minLength,
  maxLength
  /*requiredIf*/
} from "vuelidate/lib/validators";

import Message from "@/components/Message.vue";

export default {
  name: "a1",
  data() {
    return {
      a1: {
        dbCodeNumber: "",
        dateOfQuestionnaireAdministration: this.$moment().format("DD/MMM/YYYY"),
        // TODO check
        typeOfMelanoma: ""
      },
      show: true,
      canProceed: false,
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
      alert(JSON.stringify(this.a1));
      if (this.$v.$invalid) {
        this.$store.dispatch(ADD_ERROR, "form_dirty");
      } else {
        var that = this;
        this.$store.dispatch(INSERT_A1, this.a1).then(() => {
          this.$store.dispatch(ADD_MESSAGE, "form_success");
          that.canProceed = true;
        });
      }
    },
    onReset(evt) {
      evt.preventDefault();
      //TODO reset
      this.a1.dbCodeNumber = "";
      this.a1.dateOfQuestionnaireAdministration = this.$moment().format(
        "DD/MMM/YYYY"
      );
      this.a1.typeOfMelanoma = "";

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
    a1: {
      dbCodeNumber: {
        required,
        minLength: minLength(9),
        maxLength: maxLength(9)
      },
      dateOfQuestionnaireAdministration: { required },
      /*typeOfMelanoma: {
        required: requiredIf(function() {
          return this.type !== "control";
        })
      }*/
      typeOfMelanoma: {}
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