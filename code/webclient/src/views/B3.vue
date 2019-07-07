<template>
  <div>
    <div class="home container my-4">
      <Message />
      <b-card>
        <div class="card-body d-flex flex-column">
          <b-card-title>
            <span class="float-left mt-1">{{$t('section_b')+'.III'}}</span>
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
              <!-- smoking -->
              <label for="smoking" class="mt-4">
                <h6>{{'15. '+$t('smoking_label')}}</h6>
              </label>
              <b-form-select
                v-model="$v.form.smoking.howOften.$model"
                :state="$v.form.smoking.howOften.$dirty ? !$v.form.smoking.howOften.$error : null"
                :options="smokingHowOftenOptions"
              ></b-form-select>

              <span
                v-if="$v.form.smoking.howOften.$model != 'Never' && $v.form.smoking.howOften.$model != null && $v.form.smoking.howOften.$model != ''"
              >
                <b-form-input
                  class="mt-2 mb-2"
                  v-model="$v.form.smoking.ageWhenStartedSmoking.$model"
                  :state="$v.form.smoking.ageWhenStartedSmoking.$dirty ? !$v.form.smoking.ageWhenStartedSmoking.$error : null"
                  type="text"
                  :placeholder="$t('ageWhenStartedSmoking_desc')"
                ></b-form-input>

                <b-form-input
                  class="mb-2"
                  v-model="$v.form.smoking.howLongHaveYouBeenSmoking.$model"
                  :state="$v.form.smoking.howLongHaveYouBeenSmoking.$dirty ? !$v.form.smoking.howLongHaveYouBeenSmoking.$error : null"
                  type="text"
                  :placeholder="$t('howLongHaveYouBeenSmoking_desc')"
                ></b-form-input>

                <b-form-select
                  v-model="$v.form.smoking.howMuchTipicallySmoke.$model"
                  :state="$v.form.smoking.howMuchTipicallySmoke.$dirty ? !$v.form.smoking.howMuchTipicallySmoke.$error : null"
                  :options="smokingHowMuchOptions"
                ></b-form-select>
              </span>
              <b-form-text>{{$t('smoking_desc')}}</b-form-text>

              <!-- intakeOfVitaminesDuringLastYears -->
              <span class="form-inline mt-5">
                <label>
                  <h6>{{'16. '+$t('intakeOfVitaminesDuringLastYears_label')}}</h6>
                </label>
                <b-button-group class="ml-auto">
                  <b-button
                    :pressed="intakeOfVitaminesDuringLastYearsToggle"
                    @click="intakeOfVitaminesDuringLastYearsToggle = true"
                    variant="outline-success"
                  >{{$t('yes')}}</b-button>
                  <b-button
                    variant="outline-danger"
                    :pressed="!intakeOfVitaminesDuringLastYearsToggle"
                    @click="intakeOfVitaminesDuringLastYearsToggle = false; $v.form.intakeOfVitaminesDuringLastYears.$model = null"
                  >{{$t('no')}}</b-button>
                </b-button-group>
              </span>
              <span v-if="intakeOfVitaminesDuringLastYearsToggle">
                <b-form-select
                  class="mt-2 mr-2 mb-2"
                  v-model="$v.form.intakeOfVitaminesDuringLastYears.$model"
                  :state="$v.form.intakeOfVitaminesDuringLastYears.$dirty ? !$v.form.intakeOfVitaminesDuringLastYears.$error : null"
                  type="text"
                  :options="intakeOfVitaminesDuringLastYearsOptions"
                ></b-form-select>
                <b-form-text>{{$t('intakeOfVitaminesDuringLastYears_desc')}}</b-form-text>
              </span>

              <!-- vitamins -->
              <label class="mt-4">
                <h6>{{'17/18. '+$t('vitamins_label')+' '+$t('vitamins_desc')}}</h6>
              </label>

              <b-form class="mt-2">
                <span
                  class="w-100"
                  v-for="(vtm, index) in $v.form.vitamins.$each.$iter"
                  :key="index"
                >
                  <p>{{$t(vtm.name.$model.toLowerCase().replace(/\ /g, "_"))}}</p>

                  <b-form-select
                    class="mr-2 mb-2"
                    v-model="vtm.howOften.$model"
                    :state="vtm.howOften.$dirty ? !vtm.howOften.$error : null"
                    required
                    :options="vitaminsHowOftenOptions"
                  ></b-form-select>

                  <b-form-select
                    class="mr-2 mb-2"
                    v-model="vtm.howLong.$model"
                    :state="vtm.howLong.$dirty ? !vtm.howLong.$error : null"
                    required
                    :options="vitaminsHowLongOptions"
                  ></b-form-select>
                  <hr />
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
  INSERT_B3
} from "@/store/actions.type";
import { required, requiredIf, numeric } from "vuelidate/lib/validators";

import Message from "@/components/Message.vue";

export default {
  name: "b3",
  data() {
    return {
      form: {
        smoking: {
          howOften: null,
          ageWhenStartedSmoking: "",
          howLongHaveYouBeenSmoking: "",
          howMuchTipicallySmoke: null
        },
        intakeOfVitaminesDuringLastYears: null,
        vitamins: [
          {
            name: "β-carotene",
            howOften: null,
            howLong: null
          },
          {
            name: "Vitamin A",
            howOften: null,
            howLong: null
          },
          {
            name: "Vitamin C",
            howOften: null,
            howLong: null
          },
          {
            name: "Vitamin E",
            howOften: null,
            howLong: null
          },
          {
            name: "Vitamin D",
            howOften: null,
            howLong: null
          },
          {
            name: "Multivitamins",
            howOften: null,
            howLong: null
          }
        ]
      },

      intakeOfVitaminesDuringLastYearsToggle: false,

      smokingHowOftenOptions: [
        { value: null, text: this.$t("please_select_option") },
        { value: "Never", text: this.$t("never") },
        {
          value: "Former smoker (quit one year before diagnosis or earlier)",
          text: this.$t(
            "former_smoker_(quit_one_year_before_diagnosis_or_earlier)"
          )
        },
        { value: "Current smoker", text: this.$t("current_smoker") }
      ],

      smokingHowMuchOptions: [
        { value: null, text: this.$t("smokingHowMuch_desc") },
        {
          value: "one pack/day or more",
          text: this.$t("one_pack/day_or_more")
        },
        { value: "up to half pack/day", text: this.$t("up_to_half_pack/day") },
        { value: "only occasionally", text: this.$t("only_occasionally") }
      ],

      intakeOfVitaminesDuringLastYearsOptions: [
        { value: null, text: this.$t("please_select_option") },
        { value: "Never", text: this.$t("never") },
        { value: "1-3 days/month", text: this.$t("1-3_days/month") },
        { value: "1-3 days/week", text: this.$t("1-3_days/week") },
        { value: "4-6 days/week", text: this.$t("4-6 days/week") },
        { value: "Everyday", text: this.$t("everyday") }
      ],

      vitaminsHowOftenOptions: [
        { value: null, text: this.$t("vitaminsHowOften_desc") },
        { value: "Never", text: this.$t("never") },
        { value: "1-3 days/month", text: this.$t("1-3_days/month") },
        { value: "1-3 days/week", text: this.$t("1-3_days/week") },
        { value: "4-6 days/week", text: this.$t("4-6 days/week") },
        { value: "Everyday", text: this.$t("everyday") }
      ],

      vitaminsHowLongOptions: [
        { value: null, text: this.$t("vitaminsHowLong_desc") },
        { value: "<1 yr", text: this.$t("<1_yr") },
        { value: "1-4 yrs", text: this.$t("1-4_yrs") },
        { value: "5-9 yrs", text: this.$t("5-9_yrs") },
        { value: "≥10 yrs", text: this.$t("≥10_yrs") }
      ],
      show: true,
      canProceed: false
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
    if (!this.dbCodeNumber) {
      this.$store.dispatch(ADD_ERROR, "no_db_code_number");
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
          .dispatch(INSERT_B3, this.form)
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
      this.form.smoking = {
        howOften: null,
        ageWhenStartedSmoking: "",
        howLongHaveYouBeenSmoking: "",
        howMuchTipicallySmoke: null
      };

      this.form.intakeOfVitaminesDuringLastYears = null;

      this.form.vitamins = [
        {
          name: "β-carotene",
          howOften: null,
          howLong: null
        },
        {
          name: "Vitamin A",
          howOften: null,
          howLong: null
        },
        {
          name: "Vitamin C",
          howOften: null,
          howLong: null
        },
        {
          name: "Vitamin E",
          howOften: null,
          howLong: null
        },
        {
          name: "Vitamin D",
          howOften: null,
          howLong: null
        },
        {
          name: "Multivitamins",
          howOften: null,
          howLong: null
        }
      ];

      this.intakeOfVitaminesDuringLastYearsToggle = false;

      this.$v.$reset();
      // Trick to reset/clear native browser form validation state
      this.show = false;
      this.$nextTick(() => {
        this.show = true;
      });
    },
    proceed() {
      if (this.canProceed) {
        this.$router.push({ name: "b3" });
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
      smoking: {
        howOften: { required },
        ageWhenStartedSmoking: {
          numeric,
          required: requiredIf(function() {
            return this.form.howOften != "Never";
          })
        },
        howLongHaveYouBeenSmoking: {
          numeric,
          required: requiredIf(function() {
            return this.form.howOften != "Never";
          })
        },
        howMuchTipicallySmoke: {
          required: requiredIf(function() {
            return this.form.howOften != "Never";
          })
        }
      },
      intakeOfVitaminesDuringLastYears: {},
      vitamins: {
        $each: {
          name: { required },
          howOften: {},
          howLong: {}
        }
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