<template>
  <div>
    <div class="home container my-4">
      <Message />
      <b-card>
        <div class="card-body d-flex flex-column">
          <b-card-title>
            <span class="float-left mt-1">{{$t('section_a')+'.II'}}</span>
            <!-- buttons -->
            <b-button
              type="button"
              @click="proceed()"
              :disabled="(!canProceed)"
              variant="outline-info"
              class="fa-button-outline float-right"
            >
              {{$t('proceed')}}
              &nbsp;
              <font-awesome-icon icon="arrow-right" />&nbsp;
            </b-button>
            <b-button
              type="button"
              to="/choice"
              variant="outline-info"
              class="fa-button-outline mr-2 float-right"
            >
              <font-awesome-icon icon="arrow-right" flip="horizontal" />
              &nbsp;
              {{$t('back_to_section_choice')}}
            </b-button>
          </b-card-title>
          <div>
            <b-form @submit="onSubmit" @reset="onReset" v-if="show">
              <!-- sex -->
              <label for="sex" class="mt-4">
                <h6>{{'6. '+$t('sex_label')}}</h6>
              </label>
              <b-form-select
                id="sex"
                v-model="$v.form.sex.$model"
                :state="$v.form.sex.$dirty ? !$v.form.sex.$error : null"
                :options="sexOptions"
              >{{$t('sex_desc')}}</b-form-select>

              <!-- dateOfBirth -->
              <label for="dateOfBirth" class="mt-4">
                <h6>{{'7.a. '+$t('dateOfBirth_label')}}</h6>
              </label>
              <b-form-input
                id="dateOfBirth"
                v-model="$v.form.dateOfBirth.$model"
                :state="$v.form.dateOfBirth.$dirty ? !$v.form.dateOfBirth.$error : null"
                type="text"
                required
              ></b-form-input>
              <b-form-text>{{$t('dateOfBirth_desc')}}</b-form-text>

              <!-- cityOfBirth -->
              <label for="cityOfBirth" class="mt-4">
                <h6>{{'7.b. '+$t('cityOfBirth_label')}}</h6>
              </label>
              <b-form-input
                id="cityOfBirth"
                v-model="$v.form.cityOfBirth.$model"
                :state="$v.form.cityOfBirth.$dirty ? !$v.form.cityOfBirth.$error : null"
                type="text"
                required
              ></b-form-input>
              <b-form-text>{{$t('cityOfBirth_desc')}}</b-form-text>

              <!-- provinceOfBirth -->
              <label for="provinceOfBirth" class="mt-4">
                <h6>{{'7.c. '+$t('provinceOfBirth_label')}}</h6>
              </label>
              <b-form-input
                id="provinceOfBirth"
                v-model="$v.form.provinceOfBirth.$model"
                :state="$v.form.provinceOfBirth.$dirty ? !$v.form.provinceOfBirth.$error : null"
                type="text"
                required
              ></b-form-input>
              <b-form-text>{{$t('provinceOfBirth_desc')}}</b-form-text>

              <!-- countryOfBirth -->
              <label for="countryOfBirth" class="mt-4">
                <h6>{{'7.d. '+$t('countryOfBirth_label')}}</h6>
              </label>
              <b-form-input
                id="countryOfBirth"
                v-model="$v.form.countryOfBirth.$model"
                :state="$v.form.countryOfBirth.$dirty ? !$v.form.countryOfBirth.$error : null"
                type="text"
                required
              ></b-form-input>
              <b-form-text>{{$t('countryOfBirth_desc')}}</b-form-text>

              <!-- weight -->
              <label for="weight" class="mt-4">
                <h6>{{'8.a. '+$t('weight_label')}}</h6>
              </label>
              <b-form-input
                id="weight"
                v-model="$v.form.weight.$model"
                :state="$v.form.weight.$dirty ? !$v.form.weight.$error : null"
                type="text"
                required
              ></b-form-input>
              <b-form-text>{{$t('weight_desc')}}</b-form-text>

              <!-- height -->
              <label for="height" class="mt-4">
                <h6>{{'8.b. '+$t('height_label')}}</h6>
              </label>
              <b-form-input
                id="height"
                v-model="$v.form.height.$model"
                :state="$v.form.height.$dirty ? !$v.form.height.$error : null"
                type="text"
                required
              ></b-form-input>
              <b-form-text>{{$t('height_desc')}}</b-form-text>

              <!-- ethnicity -->
              <label for="ethnicity" class="mt-4">
                <h6>{{'9. '+$t('ethnicity_label')}}</h6>
              </label>
              <b-form-select
                v-model="$v.form.ethnicity.$model"
                :state="$v.form.ethnicity.$dirty ? !$v.form.ethnicity.$error : null"
                type="text"
                required
                :options="ethnicityOptions"
              ></b-form-select>
              <b-form-text>{{$t('ethnicity_desc')}}</b-form-text>

              <!-- residencies -->
              <label for="residencies" class="mt-4">
                <h6>{{'10. '+$t('residencies_label')}}</h6>
              </label>
              <b-form-text>{{$t('residencies_desc')}}</b-form-text>

              <b-form inline class="mt-2">
                <span v-for="(residency, index) in $v.form.residencies.$each.$iter" :key="index">
                  <b-form-input
                    class="mr-2 mb-2"
                    v-model="residency.residencyCountry.$model"
                    :state="residency.residencyCountry.$dirty ? !residency.residencyCountry.$error : null"
                    type="text"
                    required
                    :placeholder="$t('residencyCountry_desc')"
                  ></b-form-input>

                  <b-form-input
                    class="mr-2 mb-2"
                    v-model="residency.residencyCity.$model"
                    :state="residency.residencyCity.$dirty ? !residency.residencyCity.$error : null"
                    type="text"
                    required
                    :placeholder="$t('residencyCity_desc')"
                  ></b-form-input>

                  <b-form-input
                    class="mr-2 mb-2"
                    v-model="residency.residencyStartingTime.$model"
                    :state="residency.residencyStartingTime.$dirty ? !residency.residencyStartingTime.$error : null"
                    type="text"
                    required
                    :placeholder="$t('residencyStartingTime_desc')"
                  ></b-form-input>

                  <b-form-input
                    class="mr-2 mb-2"
                    v-model="residency.residencyEndingTime.$model"
                    :state="residency.residencyEndingTime.$dirty ? !residency.residencyEndingTime.$error : null"
                    type="text"
                    required
                    :placeholder="$t('residencyEndingTime_desc')"
                  ></b-form-input>
                  <hr v-if="residenciesHrVisible" />
                </span>
                <span class="mb-2 mb-auto ml-auto">
                  <b-button variant="outline-success" @click="addResidencyField()">
                    <font-awesome-icon icon="plus" />
                  </b-button>
                  <b-button variant="outline-danger" @click="removeResidencyField()" class="ml-2">
                    <font-awesome-icon icon="minus" />
                  </b-button>
                </span>
              </b-form>

              <!-- education -->
              <label for="education" class="mt-4">
                <h6>{{'11. '+$t('education_label')}}</h6>
              </label>
              <b-form-select
                id="education"
                v-model="$v.form.education.$model"
                :state="$v.form.education.$dirty ? !$v.form.education.$error : null"
                type="text"
                required
                :options="educationOptions"
              ></b-form-select>
              <b-form-text>{{$t('education_desc')}}</b-form-text>

              <!-- currentOccupationalStatus -->
              <label for="currentOccupationalStatus" class="mt-4">
                <h6>{{'12. '+$t('currentOccupationalStatus_label')}}</h6>
              </label>
              <b-form-select
                id="currentOccupationalStatus"
                v-model="$v.form.currentOccupationalStatus.$model"
                :state="$v.form.currentOccupationalStatus.$dirty ? !$v.form.currentOccupationalStatus.$error : null"
                type="text"
                required
                :options="currentOccupationalStatusOptions"
              ></b-form-select>
              <b-form-text>{{$t('currentOccupationalStatus_desc')}}</b-form-text>

              <!-- historyOfOccupations -->
              <label for="historyOfOccupations" class="mt-4">
                <h6>{{'13. '+$t('historyOfOccupations_label')}}</h6>
              </label>
              <b-form-text>{{$t('historyOfOccupations_desc')}}</b-form-text>

              <b-form inline class="mt-2">
                <span
                  v-for="(occupation, index) in $v.form.historyOfOccupations.$each.$iter"
                  :key="index"
                >
                  <b-form-input
                    class="mr-2 mb-2"
                    v-model="occupation.SICCode.$model"
                    :state="occupation.SICCode.$dirty ? !occupation.SICCode.$error : null"
                    type="text"
                    :placeholder="$t('SICCode_desc')"
                  ></b-form-input>

                  <b-form-input
                    class="mr-2 mb-2"
                    v-model="occupation.occupationStartingTime.$model"
                    :state="occupation.occupationStartingTime.$dirty ? !occupation.occupationStartingTime.$error : null"
                    type="text"
                    :placeholder="$t('occupationStartingTime_desc')"
                  ></b-form-input>

                  <b-form-input
                    class="mr-2 mb-2"
                    v-model="occupation.occupationEndingTime.$model"
                    :state="occupation.occupationEndingTime.$dirty ? !occupation.occupationEndingTime.$error : null"
                    type="text"
                    :placeholder="$t('occupationEndingTime_desc')"
                  ></b-form-input>
                  <hr v-if="historyOfOccupationsHrVisible" />
                </span>
                <span class="mb-2 mb-auto ml-auto">
                  <b-button variant="outline-success" @click="addOccupationField()">
                    <font-awesome-icon icon="plus" />
                  </b-button>
                  <b-button variant="outline-danger" @click="removeOccupationField()" class="ml-2">
                    <font-awesome-icon icon="minus" />
                  </b-button>
                </span>
              </b-form>

              <!-- buttons -->
              <hr class="mt-5" />
              <b-button
                type="reset"
                variant="outline-danger"
                class="float-right mb-1"
              >{{$t('reset')}}</b-button>
              <b-button
                type="submit"
                variant="outline-success"
                class="float-right mr-2 mb-1"
              >{{$t('submit')}}</b-button>
            </b-form>
          </div>
        </div>
      </b-card>
    </div>
    <br />
    <br />
  </div>
</template>
<script>
import { mapState } from "vuex";
import {
  ADD_REST_ERROR,
  ADD_MESSAGE,
  ADD_ERROR,
  INSERT_A2
} from "@/store/actions.type";
import {
  required,
  minLength,
  maxLength,
  numeric
} from "vuelidate/lib/validators";
import { dateOfBirthValidator } from "@/plugins/vuelidate";

import Message from "@/components/Message.vue";

export default {
  name: "a2",
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
        ethnicity: null,
        residencies: [
          {
            residencyCountry: "",
            residencyCity: "",
            residencyStartingTime: "",
            residencyEndingTime: ""
          }
        ],
        education: null,
        currentOccupationalStatus: null,
        historyOfOccupations: [
          {
            SICCode: "",
            occupationStartingTime: "",
            occupationEndingTime: ""
          }
        ]
      },
      show: true,
      canProceed: false,
      residenciesHrVisible: false,
      historyOfOccupationsHrVisible: false,
      sexOptions: [
        { value: null, text: this.$t("please_select_option") },
        { value: "M", text: "M" },
        { value: "F", text: "F" }
      ],
      ethnicityOptions: [
        { value: null, text: this.$t("please_select_option") },
        { value: "White/Europe", text: this.$t("white/europe") },
        { value: "White/North Africa", text: this.$t("white/north_africa") },
        { value: "White/Middle East", text: this.$t("white/middle_east") },
        {
          value: "White/Jewish ancestry",
          text: this.$t("white/jewish_ancestry")
        },
        {
          value: "Black or African American",
          text: this.$t("black_or_african_american")
        },
        { value: "Asian", text: this.$t("asian") },
        { value: "Hispanic or Latino", text: this.$t("hispanic_or_latino") },
        { value: "Other", text: this.$t("other") }
      ],
      educationOptions: [
        { value: null, text: this.$t("please_select_option") },
        {
          value: "Up to junior high school (up to 14-16 yrs)",
          text: this.$t("up_to_junior_high_school_(up_to_14-16_yrs)")
        },
        {
          value: "High school (up to 18-19 yrs)",
          text: this.$t("high_school_(up_to_18-19_yrs)")
        },
        { value: "University", text: this.$t("university") }
      ],
      currentOccupationalStatusOptions: [
        { value: null, text: this.$t("please_select_option") },
        { value: "Employed", text: this.$t("employed") },
        { value: "Working at home", text: this.$t("working_at_home") },
        { value: "Unemployed", text: this.$t("unemployed") },
        { value: "Student", text: this.$t("student") },
        { value: "Retired", text: this.$t("retired") }
      ]
    };
  },
  created: function() {
    /*
    if (this.user.type !== "physician") {
      this.$store.dispatch(ADD_ERROR, "unauthorized");
      this.$router.push({ name: "home" });
    }
    */
    if (!this.isAuthenticated) {
      this.$store.dispatch(ADD_ERROR, "not_authenticated");
      this.$router.push({ name: "home" });
    }
  },
  methods: {
    addResidencyField() {
      this.form.residencies.push({
        residencyCountry: "",
        residencyCity: "",
        residencyStartingTime: "",
        residencyEndingTime: ""
      });
      this.residenciesHrVisible = true;
    },
    removeResidencyField() {
      if (this.form.residencies.length > 1) {
        this.form.residencies.pop();
      }
      if (this.form.residencies.length == 1) {
        this.residenciesHrVisible = false;
      }
    },
    addOccupationField() {
      this.form.historyOfOccupations.push({
        SICCode: "",
        occupationStartingTime: "",
        occupationEndingTime: ""
      });
      this.historyOfOccupationsHrVisible = true;
    },
    removeOccupationField() {
      if (this.form.historyOfOccupations.length > 1) {
        this.form.historyOfOccupations.pop();
      }
      if (this.form.historyOfOccupations.length == 1) {
        this.historyOfOccupationsHrVisible = false;
      }
    },
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
          .dispatch(INSERT_A2, this.form)
          .then(() => {
            this.$store.dispatch(ADD_MESSAGE, "form_success");
            that.canProceed = true;
          })
          .catch(response => {
            this.$store.dispatch(ADD_REST_ERROR, response);
          });
      }
    },
    onReset(evt) {
      evt.preventDefault();
      document.body.scroll({
        top: 0,
        left: 0,
        behavior: "smooth"
      });
      this.form.sex = null;
      this.form.dateOfBirth = "";
      this.form.cityOfBirth = "";
      this.form.provinceOfBirth = "";
      this.form.countryOfBirth = "";
      this.form.weight = "";
      this.form.height = "";
      this.form.ethnicity = null;
      this.form.residencies = [
        {
          residencyCountry: "",
          residencyCity: "",
          residencyStartingTime: "",
          residencyEndingTime: ""
        }
      ];
      this.form.education = null;
      this.form.currentOccupationalStatus = null;
      this.form.historyOfOccupations = [
        {
          SICCode: "",
          occupationStartingTime: "",
          occupationEndingTime: ""
        }
      ];
      this.$v.$reset();
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
      ethnicity: { required },
      education: { required },
      currentOccupationalStatus: { required },
      residencies: {
        $each: {
          residencyCountry: { required },
          residencyCity: { required },
          residencyStartingTime: {
            minLength: minLength(4),
            maxLength: maxLength(4),
            numeric
          },
          residencyEndingTime: {
            minLength: minLength(4),
            maxLength: maxLength(4),
            numeric
          }
        }
      },
      historyOfOccupations: {
        $each: {
          SICCode: {},
          occupationStartingTime: {
            minLength: minLength(4),
            maxLength: maxLength(4),
            numeric
          },
          occupationEndingTime: {
            minLength: minLength(4),
            maxLength: maxLength(4),
            numeric
          }
        }
      }
    }
  },
  computed: {
    ...mapState({
      user: state => state.auth.user,
      isAuthenticated: state => state.auth.isAuthenticated
    })
  }
};
</script>