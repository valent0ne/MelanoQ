<template>
  <div>
    <div class="home container my-4">
      <Message/>
      <b-card>
        <div class="card-body d-flex flex-column">
          <b-card-title>{{$t('section_a')+'.II'}}</b-card-title>
          <div>
            <b-form @submit="onSubmit" @reset="onReset" v-if="show">
              <!-- sex -->
              <label for="sex" class="mt-4">{{$t('sex_label')}}</label>
              <b-form-select
                id="sex"
                v-model="$v.form.sex.$model"
                :state="$v.form.sex.$dirty ? !$v.form.sex.$error : null"
                :options="sexOptions"
              >{{$t('sex_desc')}}</b-form-select>

              <!-- dateOfBirth -->
              <label for="dateOfBirth" class="mt-4">{{$t('dateOfBirth_label')}}</label>
              <b-form-input
                id="dateOfBirth"
                v-model="$v.form.dateOfBirth.$model"
                :state="$v.form.dateOfBirth.$dirty ? !$v.form.dateOfBirth.$error : null"
                type="text"
                required
              ></b-form-input>
              <b-form-text>{{$t('dateOfBirth_desc')}}</b-form-text>

              <!-- cityOfBirth -->
              <label for="cityOfBirth" class="mt-4">{{$t('cityOfBirth_label')}}</label>
              <b-form-input
                id="cityOfBirth"
                v-model="$v.form.cityOfBirth.$model"
                :state="$v.form.cityOfBirth.$dirty ? !$v.form.cityOfBirth.$error : null"
                type="text"
                required
              ></b-form-input>
              <b-form-text>{{$t('cityOfBirth_desc')}}</b-form-text>

              <!-- provinceOfBirth -->
              <label for="provinceOfBirth" class="mt-4">{{$t('provinceOfBirth_label')}}</label>
              <b-form-input
                id="provinceOfBirth"
                v-model="$v.form.provinceOfBirth.$model"
                :state="$v.form.provinceOfBirth.$dirty ? !$v.form.provinceOfBirth.$error : null"
                type="text"
                required
              ></b-form-input>
              <b-form-text>{{$t('provinceOfBirth_desc')}}</b-form-text>

              <!-- countryOfBirth -->
              <label for="countryOfBirth" class="mt-4">{{$t('countryOfBirth_label')}}</label>
              <b-form-input
                id="countryOfBirth"
                v-model="$v.form.countryOfBirth.$model"
                :state="$v.form.countryOfBirth.$dirty ? !$v.form.countryOfBirth.$error : null"
                type="text"
                required
              ></b-form-input>
              <b-form-text>{{$t('countryOfBirth_desc')}}</b-form-text>

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
import { ADD_MESSAGE, ADD_ERROR, INSERT_A2 } from "@/store/actions.type";
import { required } from "vuelidate/lib/validators";
import { dateOfBirthValidator } from "@/plugins/vuelidate";

import Message from "@/components/Message.vue";

export default {
  name: "form",
  data() {
    return {
      form: {
        sex: null,
        dateOfBirth: "",
        cityOfBirth: "",
        provinceOfBirth: "",
        countryOfBirth: "",
        weight: "",
        height: "",
        ethnicity: ""
      },
      show: true,
      canProceed: false,
      sexOptions: [
        { value: null, text: this.$t("please_select_option") },
        { value: "M", text: "M" },
        { value: "F", text: "F" }
      ]
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
      alert(JSON.stringify(this.form));
      if (this.$v.$invalid) {
        this.$store.dispatch(ADD_ERROR, "form_dirty");
      } else {
        var that = this;
        this.$store.dispatch(INSERT_A2, this.form).then(() => {
          this.$store.dispatch(ADD_MESSAGE, "form_success");
          that.canProceed = true;
        });
      }
    },
    onReset(evt) {
      evt.preventDefault();
      //TODO reset
      this.form.sex = null;
      this.form.dateOfBirth = "";
      this.form.cityOfBirth = "";
      this.form.provinceOfBirth = "";
      this.form.countryOfBirth = "";
      this.form.weight = "";
      this.form.height = "";
      this.form.ethnicity = "";

      // Trick to reset/clear native browser form validation state
      this.show = false;
      this.$nextTick(() => {
        this.show = true;
      });
    },
    proceed() {
      if (this.canProceed) {
        this.$router.push({ name: "form" });
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
      sex: { required },
      dateOfBirth: { required, dateOfBirthValidator },
      cityOfBirth: { required },
      provinceOfBirth: { required },
      countryOfBirth: { required },
      weight: { required },
      height: { required },
      ethnicity: { required }
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